package com.bdjr.client.app.fallback.order;

import com.bdjr.client.app.feign.order.OrderFeign;
import com.spring.cloud.common.vo.OrderMainVo;
import org.springframework.stereotype.Component;

/**
 * @version 1.0
 * @description：
 * @author: Yang.Chang
 * @project: cloud-general
 * @package: com.bdjr.client.app.fallback.order、
 * @email: cy880708@163.com
 * @date: 2019/2/14 下午5:14
 * @mofified By:
 */
@Component
public class OrderFallback implements OrderFeign {

    @Override
    public String insertOrder(OrderMainVo orderMainVo) {
        return new String("新增订单信息失败！FeignClient");
    }

    @Override
    public String updateOrder(OrderMainVo orderMainVo) {
        return new String("修改订单信息失败！FeignClient");
    }
}
