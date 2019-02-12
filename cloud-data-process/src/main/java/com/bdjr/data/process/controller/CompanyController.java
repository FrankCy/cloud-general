package com.bdjr.data.process.controller;

import com.bdjr.data.process.config.CompanyConfig;
import com.spring.cloud.common.base.Constants;
import com.spring.cloud.common.po.Company;
import com.spring.cloud.common.vo.CompanyUser;
import com.spring.cloud.common.vo.PageBean;
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

    @RequestMapping(value = "/findAllCompany", method = RequestMethod.GET)
    public PageResult<Company> findAllCompany(CompanyUser companyUser,
                                              @RequestParam("pageNum") Integer pageNum,
                                              @RequestParam("pageSize") Integer pageSize){

        PageBean pageBean = new PageBean();
        pageBean.setPageSize(pageSize);
        pageBean.setPageNum(pageNum);

        //声明实体对象
        Company company = new Company();

        //将VO内相同的值放到PO内
        BeanUtils.copyProperties(companyUser, company);

        //查询公司信息
        PageResult<Company> companyList = companyConfig.findAllCompany(company, pageBean);

        return companyList;
    }


}
