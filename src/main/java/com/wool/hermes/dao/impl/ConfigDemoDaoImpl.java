package com.wool.hermes.dao.impl;

import com.wool.hermes.dao.ConfigDemoDao;
import com.wool.hermes.dao.mapper.ConfigDemoMapper;
import com.wool.hermes.model.database.ConfigDemo;
import com.wool.hermes.model.database.ConfigDemoExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wanglin on 16-10-7.
 */
@Repository
public class ConfigDemoDaoImpl implements ConfigDemoDao {

    @Autowired
    private ConfigDemoMapper configDemoMapper;


    @Override
    public void insertSelective(ConfigDemo record) {
        configDemoMapper.insertSelective(record);
    }

    @Override
    public void deleteByExample(ConfigDemoExample example) {
        configDemoMapper.deleteByExample(example);
    }

    @Override
    public void updateByExampleSelective(ConfigDemo record, ConfigDemoExample example) {
        configDemoMapper.updateByExampleSelective(record, example);
    }

    @Override
    public List<ConfigDemo> selectByExample(ConfigDemoExample example) {
        return configDemoMapper.selectByExample(example);
    }

    @Override
    public int countByExample(ConfigDemoExample example) {
        return configDemoMapper.countByExample(example);
    }

    @Override
    public List<ConfigDemo> selectByExampleWithLimit(ConfigDemoExample example) {
        return configDemoMapper.selectByExampleWithLimit(example);
    }
}
