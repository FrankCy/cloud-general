package com.bdjr.client.app.impl.order;

import com.bdjr.client.app.feign.order.OrderFeign;
import com.bdjr.client.app.service.order.OrderService;
import com.spring.cloud.common.vo.OrderMainVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @version 1.0
 * @description：
 * @author: Yang.Chang
 * @project: cloud-general
 * @package: com.bdjr.client.app.impl.order、
 * @email: cy880708@163.com
 * @date: 2019/2/14 下午5:12
 * @mofified By:
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderFeign orderFeign;


    @Override
    public String insertOrder(OrderMainVo orderMainVo) {
        // TODO 这里可以做客户端的逻辑处理
        return orderFeign.insertOrder(orderMainVo);
    }
}
