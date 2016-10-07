package com.wool.hermes.dao;

import com.wool.hermes.model.database.ConfigDemo;
import com.wool.hermes.model.database.ConfigDemoExample;

import java.util.List;

/**
 * Created by wanglin on 16-10-7.
 */
public interface ConfigDemoDao {

    /**
     * add
     * @param record
     * @return
     */
    void insertSelective(ConfigDemo record);

    /**
     * delete
     * @param example
     * @return
     */
    void deleteByExample(ConfigDemoExample example);

    /**
     * update
     * @param record
     * @param example
     * @return
     */
    void updateByExampleSelective(ConfigDemo record, ConfigDemoExample example);

    /**
     * select
     * @param example
     * @return
     */
    List<ConfigDemo> selectByExample(ConfigDemoExample example);
}
