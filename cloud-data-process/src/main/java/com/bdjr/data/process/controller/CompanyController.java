package com.bdjr.data.process.controller;

import com.spring.cloud.common.po.Company;
import com.spring.cloud.common.vo.CompanyUser;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 1.0
 * @description：
 * @author: Yang.Chang
 * @project: cloud-general
 * @package: com.bdjr.data.process.controller、
 * @email: cy880708@163.com
 * @date: 2019/1/30 下午4:00
 * @mofified By:
 */
@RestController
public class CompanyController {

    @RequestMapping(value = "/insertCompany", method = RequestMethod.POST)
    public String insertCompany(CompanyUser companyUser){

        //声明实体对象
        Company company = new Company();

        //将VO内相同的值放到PO内
        BeanUtils.copyProperties(companyUser, company);

        return companyUser.toString();
    }


}
