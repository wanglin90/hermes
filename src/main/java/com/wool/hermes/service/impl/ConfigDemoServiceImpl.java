package com.wool.hermes.service.impl;

import com.wool.hermes.dao.ConfigDemoDao;
import com.wool.hermes.service.ConfigDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wanglin on 16-10-7.
 */
@Service
public class ConfigDemoServiceImpl implements ConfigDemoService {

    @Autowired
    private ConfigDemoDao configDemoDao;


}
