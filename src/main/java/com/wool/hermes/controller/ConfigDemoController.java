package com.wool.hermes.controller;

import com.wool.hermes.model.ConfigDemoReq;
import com.wool.hermes.model.WoolResponse;
import com.wool.hermes.service.ConfigDemoService;
import com.wool.hermes.utils.ErrorCode;
import com.wool.hermes.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wanglin on 16-10-8.
 */
@RestController
public class ConfigDemoController {

    private static final Logger log = LoggerFactory.getLogger(ConfigDemoController.class);

    @Autowired
    private ReloadableResourceBundleMessageSource messageSource;

    @Autowired
    private ConfigDemoService configDemoService;

    @RequestMapping(value = "/config/demo/list",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public WoolResponse getConfigDemoList(@RequestBody ConfigDemoReq req) {

        WoolResponse response = null;

        if (req == null) {
            log.error("==> getConfigDemoList req is null");
            return Utils.getErrorResponse(ErrorCode.SYS_REQ_ERROR, messageSource);
        }

        try {
            response = configDemoService.getConfigDemoList();
        } catch (Exception e) {
            log.error("==> getConfigDemoList error , error = {}",e);
            response = Utils.getErrorResponse(ErrorCode.SYS_ERROR, messageSource);
        }

        return null;
    }
}
