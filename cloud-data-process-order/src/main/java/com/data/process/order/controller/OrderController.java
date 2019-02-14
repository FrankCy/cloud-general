package com.data.process.order.controller;

import com.data.process.order.config.OrderConfig;
import com.spring.cloud.common.base.Constants;
import com.spring.cloud.common.po.Company;
import com.spring.cloud.common.po.OrderMain;
import com.spring.cloud.common.vo.OrderMainVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 1.0
 * @description：
 * @author: Yang.Chang
 * @project: cloud-general
 * @package: com.data.process.order.controller、
 * @email: cy880708@163.com
 * @date: 2019/2/14 下午4:53
 * @mofified By:
 */
@RestController
public class OrderController {

    @Autowired
    private OrderConfig orderConfig;

    @RequestMapping(value = "/insertOrder", method = RequestMethod.POST)
    public String insertOrder(@RequestBody OrderMainVo orderMainVo){

        //声明实体对象
        OrderMain orderMain = new OrderMain();

        //将VO内相同的值放到PO内
        BeanUtils.copyProperties(orderMainVo, orderMain);

        //判断并响应结果
        if(orderConfig.insertOrder(orderMain) > 0) {
            return Constants.operaterSuccess;
        } else  {
            return Constants.operaterError;
        }
    }


}
