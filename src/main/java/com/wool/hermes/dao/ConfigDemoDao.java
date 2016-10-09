package com.wool.hermes.dao;

import com.wool.hermes.model.database.ConfigDemo;
import com.wool.hermes.model.database.ConfigDemoExample;

import java.util.List;

/**
 * Created by wanglin on 16-10-7.
 */
public interface ConfigDemoDao {

    /**
     * 添加
     * @param record
     * @return
     */
    void insertSelective(ConfigDemo record);

    /**
     * 删除
     * @param example
     * @return
     */
    void deleteByExample(ConfigDemoExample example);

    /**
     * 更新
     * @param record
     * @param example
     * @return
     */
    void updateByExampleSelective(ConfigDemo record, ConfigDemoExample example);

    /**
     * 查询
     * @param example
     * @return
     */
    List<ConfigDemo> selectByExample(ConfigDemoExample example);

    /**
     * 计数
     * @param example
     * @return
     */
    int countByExample(ConfigDemoExample example);

    /**
     * 支持分页的列表查询
     * @param example
     * @return
     */
    List<ConfigDemo> selectByExampleWithLimit(ConfigDemoExample example);



}
