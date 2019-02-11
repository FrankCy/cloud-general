package com.bdjr.gateway.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 1.0
 * @description：
 * @author: Yang.Chang
 * @project: cloud-general
 * @package: com.bdjr.gateway.controller、
 * @email: cy880708@163.com
 * @date: 2019/2/11 下午6:04
 * @mofified By:
 */
@RestController
public class BasicController {

    private static final Log logger = LogFactory.getLog(BasicController.class);

    /**
     * 健康检测
     * @return
     */
    @GetMapping("/gateway_health")
    public String gatewayHealth() {
        logger.info("Gateway 执行健康检测");
        return "SUCCESS";
    }

}
