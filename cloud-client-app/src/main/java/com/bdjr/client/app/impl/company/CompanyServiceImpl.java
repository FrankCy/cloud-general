package com.bdjr.client.app.impl.company;

import com.bdjr.client.app.controller.CompanyController;
import com.bdjr.client.app.feign.company.CompanyFeign;
import com.bdjr.client.app.feign.order.OrderFeign;
import com.bdjr.client.app.service.company.CompanyService;
import com.spring.cloud.common.base.Constants;
import com.spring.cloud.common.po.Company;
import com.spring.cloud.common.vo.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @version 1.0
 * @description：
 * @author: Yang.Chang
 * @project: cloud-general
 * @package: com.bdjr.client.app.service.impl、
 * @email: cy880708@163.com
 * @date: 2019/1/30 下午4:28
 * @mofified By:
 */
@Service
public class CompanyServiceImpl implements CompanyService {

    private static final Log logger = LogFactory.getLog(CompanyServiceImpl.class);

    @Autowired
    private CompanyFeign companyFeign;

    @Autowired
    private OrderFeign orderFeign;

    @Override
    public String insertCompany(CompanyUser companyUser) {
        // TODO 这里可以做客户端的逻辑处理
        return companyFeign.insertCompany(companyUser);
    }

    @Override
    public String updateCompany(CompanyUser companyUser) {
        // TODO 这里可以做客户端的逻辑处理
        return companyFeign.updateCompany(companyUser);
    }

    @Override
    public String updateCompanyOrder(CompanyOrderVo companyOrderVo) {

        CompanyUser companyUser = new CompanyUser();

        OrderMainVo orderMainVo = new OrderMainVo();

        //将VO内相同的值放到PO内
        BeanUtils.copyProperties(companyOrderVo, companyUser);

        //将VO内相同的值放到PO内
        BeanUtils.copyProperties(companyOrderVo, orderMainVo);

        //修改公司信息
        String companyUpdateFlag = companyFeign.updateCompany(companyUser);

        //修改公司信息
        String orderUpdateFlag = orderFeign.updateOrder(orderMainVo);

        logger.info("公司修改：" + companyUpdateFlag);
        logger.info("订单修改：" + orderUpdateFlag);

        if(!StringUtils.isEmpty(companyUpdateFlag) &&
                Constants.operaterSuccess.equals(companyUpdateFlag) &&
                !StringUtils.isEmpty(orderUpdateFlag) &&
                Constants.operaterSuccess.equals(orderUpdateFlag)) {
            return Constants.operaterSuccess;
        } else {
            return Constants.operaterError;
        }

    }

    @Override
    public String deleteCompany(String cId) {
        // TODO 这里可以做客户端的逻辑处理
        return companyFeign.deleteCompany(cId);
    }

    @Override
    public Company findCompanyById(String cId) {
        // TODO 这里可以做客户端的逻辑处理
        return companyFeign.findCompanyById(cId);
    }

    @Override
    public PageResult<Company> findAllCompany(CompanyUser companyUser) {
        return companyFeign.findAllCompany(companyUser);
    }

}
