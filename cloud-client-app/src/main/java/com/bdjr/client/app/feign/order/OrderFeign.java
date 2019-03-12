package com.bdjr.client.app.feign.order;

import com.bdjr.client.app.fallback.order.OrderFallback;
import com.bdjr.client.app.config.FeignConfiguration;
import com.spring.cloud.common.vo.OrderMainVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @version 1.0
 * @description：
 * @author: Yang.Chang
 * @project: cloud-general
 * @package: com.bdjr.client.app.feign.order、
 * @email: cy880708@163.com
 * @date: 2019/2/14 下午5:13
 * @mofified By:
 */
@FeignClient(name = "cloud-data-process-order", configuration = FeignConfiguration.class, fallback= OrderFallback.class)
public interface OrderFeign {

    @RequestMapping(value = "/insertOrder", method = RequestMethod.POST)
    String insertOrder(@RequestBody OrderMainVo orderMainVo);

    @RequestMapping(value = "/updateOrder", method = RequestMethod.POST)
    String updateOrder(@RequestBody OrderMainVo orderMainVo);

}
