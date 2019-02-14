package com.data.process.order.config;

import com.data.process.order.mapper.OrderMainMapper;
import com.spring.cloud.common.po.Company;
import com.spring.cloud.common.po.OrderMain;
import com.spring.cloud.common.vo.OrderMainVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @version 1.0
 * @description：
 * @author: Yang.Chang
 * @project: cloud-general
 * @package: com.data.process.order.config、
 * @email: cy880708@163.com
 * @date: 2019/2/14 下午4:48
 * @mofified By:
 */
@Component
public class OrderConfig {

    /**
     * 订单查询dao
     */
    @Autowired
    private OrderMainMapper orderMainMapper;

    public int insertOrder(OrderMain orderMain) {
        //TODO 具体的业务实现处理
        return orderMainMapper.insertSelective(orderMain);
    }

    public int updateOrder(OrderMain orderMain) {
        return orderMainMapper.updateByPrimaryKeySelective(orderMain);
    }

}
