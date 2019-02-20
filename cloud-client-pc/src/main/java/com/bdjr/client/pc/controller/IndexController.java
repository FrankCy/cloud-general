package com.bdjr.client.pc.controller;

import com.spring.cloud.common.result.BdjrResult;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 1.0
 * @description：
 * @author: Yang.Chang
 * @project: cloud-general
 * @package: com.bdjr.client.pc.controller、
 * @email: cy880708@163.com
 * @date: 2019/2/19 下午4:42
 * @mofified By:
 */
@RestController
@RequestMapping("/pc")
public class IndexController {

    private static final Log logger = LogFactory.getLog(IndexController.class);

    /**
     * @description：新增公司信息
     * @version 1.0
     * @author: Yang.Chang
     * @email: cy880708@163.com
     * @date: 2019/2/11 下午4:27
     * @mofified By:
     */
    @RequestMapping(value = "/hello", method = RequestMethod.POST)
    public BdjrResult insertCompany(){

        logger.info("请求客户端PC" );

        if(true) {
            return new BdjrResult.Builder<>().success("访问成功").build();
        } else {
            return new BdjrResult.Builder<>().failure("访问失败").build();
        }
    }

}
