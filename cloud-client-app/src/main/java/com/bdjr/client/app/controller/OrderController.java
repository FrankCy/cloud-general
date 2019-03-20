package com.bdjr.client.app.controller;

import com.bdjr.client.app.service.company.CompanyService;
import com.bdjr.client.app.service.order.OrderService;
import com.spring.cloud.common.base.Constants;
import com.spring.cloud.common.result.ResultModel;
import com.spring.cloud.common.vo.OrderMainVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 1.0
 * @description：
 * @author: Yang.Chang
 * @project: cloud-general
 * @package: com.bdjr.client.app.controller、
 * @email: cy880708@163.com
 * @date: 2019/2/14 下午5:07
 * @mofified By:
 */
@RestController
public class OrderController {

    @Autowired
    protected CompanyService companyService;

    @Autowired
    protected OrderService orderService;

    /**
     * @description：新增订单
     * @version 1.0
     * @author: Yang.Chang
     * @email: cy880708@163.com
     * @date: 2019/2/14 下午5:29
     * @mofified By:
     */
    @RequestMapping(value = "/insertOrder", method = RequestMethod.POST)
    public ResultModel insertOrder(OrderMainVo orderMainVo){

        String insertOrderMessage = orderService.insertOrder(orderMainVo);

        if(!StringUtils.isEmpty(insertOrderMessage) && Constants.operaterSuccess.equals(insertOrderMessage)) {
            return new ResultModel.Builder<>().success("新增成功").build();
        } else {
            return new ResultModel.Builder<>().failure("新增失败").build();
        }
    }

    /**
     * @description：修改订单
     * @version 1.0
     * @author: Yang.Chang
     * @email: cy880708@163.com
     * @date: 2019/2/14 下午5:29
     * @mofified By:
     */
    @RequestMapping(value = "/updateOrder", method = RequestMethod.POST)
    public ResultModel updateOrder(OrderMainVo orderMainVo){

        String updateOrderMessage = orderService.updateOrder(orderMainVo);

        if(!StringUtils.isEmpty(updateOrderMessage) && Constants.operaterSuccess.equals(updateOrderMessage)) {
            return new ResultModel.Builder<>().success("修改成功").build();
        } else {
            return new ResultModel.Builder<>().failure("修改失败").build();
        }
    }

}
