package com.bdjr.data.process.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 1.0
 * @description：
 * @author: Yang.Chang
 * @project: cloud-general
 * @package: com.bdjr.data.process.controller、
 * @email: cy880708@163.com
 * @date: 2019/1/30 下午4:48
 * @mofified By:
 */
@RestController
public class BasicController {

    private static final Log logger = LogFactory.getLog(BasicController.class);

    /**
     * 健康检测
     * @return
     */
    @GetMapping("/health")
    public String health() {
        logger.info("执行健康检测");
        return "SUCCESS";
    }


}
