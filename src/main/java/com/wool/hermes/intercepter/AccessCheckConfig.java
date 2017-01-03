package com.wool.hermes.intercepter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class AccessCheckConfig {

    private static final Logger logger = LoggerFactory.getLogger(AccessCheckConfig.class);
    private final Map<String, String> appSecretMap = new ConcurrentHashMap<String, String>();

    // demo
    @Value("${demo.ak}")
    private String demo_ak;
    @Value("${demo.sk}")
    private String demo_sk;


    @PostConstruct
    private void init() {
        if (StringUtils.isEmpty(demo_ak) || StringUtils.isEmpty(demo_sk)) {
            logger.error("Init TokenCheckConfig fail. some ak or sk is null");
            throw new IllegalStateException("Init TokenCheckConfig fail. some ak or sk is null");
        }

        appSecretMap.put(demo_ak, demo_sk);
    }

    public String getAppSecret(String appKey) {
        if (StringUtils.isEmpty(appKey)) {
            return null;
        } else {
            return appSecretMap.get(appKey);
        }
    }

}
