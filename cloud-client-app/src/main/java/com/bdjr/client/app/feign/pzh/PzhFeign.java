package com.bdjr.client.app.feign.pzh;

import com.bdjr.client.app.config.FeignLogConfiguration;
import com.bdjr.client.app.fallback.pzh.PzhFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @version 1.0
 * @description：
 * @author: Yang.Chang
 * @project: cloud-general
 * @package: com.bdjr.client.app.feign.pzh、
 * @email: cy880708@163.com
 * @date: 2019/2/18 下午5:41
 * @mofified By:
 */
@FeignClient(name = "pzhfeign", url = "https://api.github.com", configuration = FeignLogConfiguration.class, fallback = PzhFallback.class)
public interface PzhFeign {

    @RequestMapping(value = "/search/repositories", method = RequestMethod.GET)
    String pzhRequestTest(@RequestParam("q") String queryStr);

}
