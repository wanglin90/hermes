package com.wool.hermes.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;


@Component
public class DistLocker {
    private final static Logger logger = LoggerFactory.getLogger(DistLocker.class);

    private final static String DISTRI_LOCK_KEY_PREFIX = "dislock:";

    private final int MAX_LOCKER_TIME = 3600 * 6;   // seconds

    @Autowired
    private StringRedisTemplate redis;

    public String lock(String name, int timeout) {
        if (timeout < 1 || timeout > MAX_LOCKER_TIME) {
            logger.error("invalid timeout. It must be between 1 and 3600");
            return null;
        }

        String key = getLockerKey(name);
        if (redis.opsForValue().get(key) != null) {
            logger.debug("Failed to acquire the locker '{}' because it's locked by other.", name);

            // process the expire exception case
            Long expireSeconds = redis.getExpire(key);
            if (expireSeconds == -1) {
                logger.warn("reset the timeout of bad distribute locker '%s'", key);
                redis.expire(key, timeout, TimeUnit.SECONDS);
            }

            return null;
        }

        try {
            String lockId = UUID.randomUUID().toString();
            if (redis.opsForValue().setIfAbsent(key, lockId)) {
                redis.expire(key, timeout, TimeUnit.SECONDS);
                logger.debug("obtained distributed locker '{} ({})'", name, lockId);
                return lockId;
            } else {
                logger.debug("Failed to create the locker key '{}' because it exists.", key);
                return null;
            }
        } catch (Exception e) {
            logger.warn("exception in locking: {}", e);
            return null;
        }
    }

    public void unlock(String name, String lockerId) {
        String key = getLockerKey(name);
        String value = redis.opsForValue().get(key);

        if (value == null) {
            logger.info("the locker '{}' doesn't exists", name);
            return;
        }

        if (!value.equals(lockerId)) {
            logger.warn("invalid locker id!");
            return;
        }

        redis.delete(key);
        logger.info("the locker '{}' is unlocked", name);
    }

    public boolean isLocked(String name) {
        return redis.opsForValue().get(getLockerKey(name)) != null;
    }


    public boolean update(String lockerName, int timeoutSeconds) {
        String key = getLockerKey(lockerName);
        if (redis.opsForValue().get(key) != null) {
            redis.expire(key, timeoutSeconds, TimeUnit.SECONDS);
            return true;
        } else {
            logger.debug("the locker '{}' doesn't exist", lockerName);
            return false;
        }
    }

    private String getLockerKey(String name) {
        return DISTRI_LOCK_KEY_PREFIX + ":" + name;
    }
}
