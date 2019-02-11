package com.bdjr.client.app.controller;

import com.bdjr.client.app.service.company.CompanyService;
import com.spring.cloud.common.base.Constants;
import com.spring.cloud.common.result.BdjrResult;
import com.spring.cloud.common.vo.CompanyUser;
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
 * @date: 2019/1/30 下午4:38
 * @mofified By:
 */
@RestController
public class CompanyController {

    @Autowired
    protected CompanyService companyService;

    /**
     * @description：新增公司信息
     * @version 1.0
     * @author: Yang.Chang
     * @email: cy880708@163.com
     * @date: 2019/2/11 下午4:27
     * @mofified By:
     */
    @RequestMapping(value = "/insertCompany", method = RequestMethod.POST)
    public BdjrResult insertCompany(CompanyUser companyUser){

        String insertCompanyMessage = companyService.insertCompany(companyUser);

        if(!StringUtils.isEmpty(insertCompanyMessage) && Constants.operaterSuccess.equals(insertCompanyMessage)) {
            return new BdjrResult.Builder<>().success("新增完毕").build();
        } else {
            return new BdjrResult.Builder<>().failure("新增失败").build();
        }
    }

}
