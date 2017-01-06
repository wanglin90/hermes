package com.wool.hermes.service;

import com.wool.hermes.model.ConfigDemoReq;
import com.wool.hermes.model.WoolResponse;
import org.springframework.amqp.core.Message;

/**
 * Created by wanglin on 16-10-7.
 */
public interface ConfigDemoService {

    WoolResponse getConfigDemoList(ConfigDemoReq req);

    void sendMessage(String message);

}
