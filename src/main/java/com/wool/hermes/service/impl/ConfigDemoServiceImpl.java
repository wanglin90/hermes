package com.wool.hermes.service.impl;

import com.wool.hermes.dao.ConfigDemoDao;
import com.wool.hermes.model.ConfigDemoReq;
import com.wool.hermes.model.ConfigDemoResp;
import com.wool.hermes.model.MessageTask;
import com.wool.hermes.model.WoolResponse;
import com.wool.hermes.model.database.ConfigDemo;
import com.wool.hermes.model.database.ConfigDemoExample;
import com.wool.hermes.service.ConfigDemoService;
import com.wool.hermes.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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

    private static final Logger log = LoggerFactory.getLogger(ConfigDemoServiceImpl.class);

    @Autowired
    private ConfigDemoDao configDemoDao;

    @Autowired
    private RabbitTemplate rabbitTemplate;

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

    @Override
    @RabbitListener(queues = "test.aa")
    public void handMessage(Message message) {
        MessageTask msg = MessageTask.fromAmqpMessage(message);
        log.info("receive message: {}", msg.getPayload());
    }

    @Override
    public void sendMessage(String message) {
        MessageTask msg = new MessageTask(message);
        rabbitTemplate.send(msg.toAmqpMessage());
    }

    private ConfigDemoExample getExample(ConfigDemoReq req) {

        ConfigDemoExample example = new ConfigDemoExample();
        example.setOrderByClause("create_time desc");

        if (req.getOffset() != null && req.getLimit() != null) {
            example.setLimitClause(req.getOffset(), req.getLimit());
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
