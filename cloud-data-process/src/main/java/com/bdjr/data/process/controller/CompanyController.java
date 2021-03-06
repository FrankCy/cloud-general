package com.bdjr.data.process.controller;

import com.bdjr.data.process.config.CompanyConfig;
import com.spring.cloud.common.base.Constants;
import com.spring.cloud.common.po.Company;
import com.spring.cloud.common.vo.CompanyUser;
import com.spring.cloud.common.vo.PageResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private CompanyConfig companyConfig;

    @RequestMapping(value = "/insertCompany", method = RequestMethod.POST)
    public String insertCompany(@RequestBody CompanyUser companyUser){

        //声明实体对象
        Company company = new Company();

        //将VO内相同的值放到PO内
        BeanUtils.copyProperties(companyUser, company);

        //判断并响应结果
        if(companyConfig.insertCompany(company) > 0) {
            return Constants.operaterSuccess;
        } else  {
            return Constants.operaterError;
        }
    }

    @RequestMapping(value = "/deleteCompany", method = RequestMethod.POST)
    public String deleteCompany(@RequestParam("cId") String cId){

        //判断并响应结果
        if(companyConfig.deleteCompany(cId) > 0) {
            return Constants.operaterSuccess;
        } else  {
            return Constants.operaterError;
        }
    }

    @RequestMapping(value = "/updateCompany", method = RequestMethod.POST)
    public String updateCompany(@RequestBody CompanyUser companyUser){

        //声明实体对象
        Company company = new Company();

        //将VO内相同的值放到PO内
        BeanUtils.copyProperties(companyUser, company);

        //判断并响应结果
        if(companyConfig.updateCompany(company) > 0) {
            return Constants.operaterSuccess;
        } else  {
            return Constants.operaterError;
        }
    }

    @RequestMapping(value = "/findCompanyById", method = RequestMethod.GET)
    public Company findCompanyById(@RequestParam("cId") String cId){
        Company company = companyConfig.selectById(cId);
        if(company != null) {
            return company;
        } else {
            return null;
        }
    }

    @RequestMapping(value = "/findAllCompany", method = RequestMethod.GET)
    public PageResult<Company> findAllCompany(CompanyUser companyUser){

        //声明实体对象
        Company company = new Company();

        //将VO内相同的值放到PO内
        BeanUtils.copyProperties(companyUser, company);

        //查询公司信息
        PageResult<Company> companyList = companyConfig.findAllCompany(company, companyUser.getPageBean());

        return companyList;
    }


}
