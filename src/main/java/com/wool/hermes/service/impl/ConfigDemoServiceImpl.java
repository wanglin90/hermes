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
    public WoolResponse getConfigDemoList(ConfigDemoReq req) {

        WoolResponse response = null;
        ConfigDemoResp resp = new ConfigDemoResp();
        List<ConfigDemoResp.WoolConfigDemo> list = new ArrayList<>();

        ConfigDemoExample example = getExample(req);

        int totalNum = configDemoDao.countByExample(example);

        if (totalNum > 0) {
            List<ConfigDemo> configDemos = configDemoDao.selectByExampleWithLimit(example);
            for (ConfigDemo configDemo : configDemos) {
                ConfigDemoResp.WoolConfigDemo woolConfigDemo = new ConfigDemoResp.WoolConfigDemo();
                woolConfigDemo.setName(configDemo.getName());
                woolConfigDemo.setAge(configDemo.getAge());
                list.add(woolConfigDemo);
            }
        }


        resp.setTotalNum(totalNum);
        resp.setList(list);

        response = Utils.getRightResponse("success", resp);
        return response;
    }

    private ConfigDemoExample getExample(ConfigDemoReq req) {

        ConfigDemoExample example = new ConfigDemoExample();
        example.setOrderByClause("create_time desc");

        if (req.getOffset() != null && req.getLimit() != null) {
            example.setLimitClause(req.getOffset(),req.getLimit());
        }

        ConfigDemoExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(req.getName())) {
            criteria.andNameEqualTo(req.getName());
        }

        if (req.getAge() != null) {
            criteria.andAgeEqualTo(req.getAge());
        }

        return example;
    }
}
