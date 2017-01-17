package com.wool.hermes.service.impl;

import com.wool.hermes.service.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by wanglin on 17-1-17.
 */
@Service
public class DubboServiceImpl implements DubboService {

    public static final Logger logger = LoggerFactory.getLogger(DubboServiceImpl.class);

    @Override
    public String testDubbo(String name) {
        logger.info(" test dubbo : {}", name);
        return " test dubbo : " + name;
    }
}
