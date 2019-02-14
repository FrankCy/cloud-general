package com.bdjr.client.app.service.order;

import com.spring.cloud.common.vo.OrderMainVo;

/**
 * @version 1.0
 * @description：
 * @author: Yang.Chang
 * @project: cloud-general
 * @package: com.bdjr.client.app.service.company、
 * @email: cy880708@163.com
 * @date: 2019/2/14 下午5:10
 * @mofified By:
 */
public interface OrderService {

    /**
     * 新增公司
     * @param orderMainVo
     * @return
     */
    String insertOrder(OrderMainVo orderMainVo);


    /**
     * 修改订单
     * @param orderMainVo
     * @return
     */
    String updateOrder(OrderMainVo orderMainVo);

}
