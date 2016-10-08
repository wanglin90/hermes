package com.wool.hermes.service.impl;

import com.wool.hermes.dao.ConfigDemoDao;
import com.wool.hermes.model.ConfigDemoReq;
import com.wool.hermes.model.ConfigDemoResp;
import com.wool.hermes.model.WoolResponse;
import com.wool.hermes.model.database.ConfigDemo;
import com.wool.hermes.model.database.ConfigDemoExample;
import com.wool.hermes.service.ConfigDemoService;
import com.wool.hermes.utils.Utils;
import org.springframework.beans.factory.UnsatisfiedDependencyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.AutoPopulatingList;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wanglin on 16-10-7.
 */
@Service
public class ConfigDemoServiceImpl implements ConfigDemoService {

    @Autowired
    private ConfigDemoDao configDemoDao;


    @Override
    public WoolResponse getConfigDemoList() {

        WoolResponse response = null;
        ConfigDemoResp resp = new ConfigDemoResp();
        List<ConfigDemoResp.WoolConfigDemo> list = new ArrayList<>();

        ConfigDemoExample example = new ConfigDemoExample();
        List<ConfigDemo> configDemos = configDemoDao.selectByExample(example);

        if (!CollectionUtils.isEmpty(configDemos)) {
            for (ConfigDemo configDemo : configDemos) {
                ConfigDemoResp.WoolConfigDemo woolConfigDemo = new ConfigDemoResp.WoolConfigDemo();
                woolConfigDemo.setName(configDemo.getName());
                woolConfigDemo.setAge(configDemo.getAge());
                list.add(woolConfigDemo);
            }
        }

        resp.setTotalNum(configDemos.size());
        resp.setList(list);

        response = Utils.getRightResponse("success",resp);
        return response;
    }
}
