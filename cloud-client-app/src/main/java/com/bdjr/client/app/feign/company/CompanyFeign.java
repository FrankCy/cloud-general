package com.bdjr.client.app.feign.company;

import com.bdjr.client.app.fallback.company.CompanyFallback;
import com.bdjr.client.app.config.FeignLogConfiguration;
import com.spring.cloud.common.po.Company;
import com.spring.cloud.common.vo.CompanyUser;
import com.spring.cloud.common.vo.PageResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @version 1.0
 * @description：
 * @author: Yang.Chang
 * @project: cloud-general
 * @package: com.bdjr.client.app.feign.company、
 * @email: cy880708@163.com
 * @date: 2019/1/30 下午4:34
 * @mofified By:
 */
@FeignClient(name = "cloud-data-process", configuration = FeignLogConfiguration.class, fallback= CompanyFallback.class)
public interface CompanyFeign {

    @RequestMapping(value = "/insertCompany", method = RequestMethod.POST)
    String insertCompany(@RequestBody CompanyUser companyUser);

    @RequestMapping(value = "/deleteCompany", method = RequestMethod.POST)
    String deleteCompany(@RequestParam(value = "cId") String cId);

    @RequestMapping(value = "/updateCompany", method = RequestMethod.POST)
    String updateCompany(@RequestBody CompanyUser companyUser);

    @RequestMapping(value = "/findCompanyById", method = RequestMethod.GET)
    Company findCompanyById(@RequestParam(value = "cId") String cId);

    /**
     * 分页查询
     * @param companyUser
     * @return
     */
    @RequestMapping(value = "/findAllCompany", method = RequestMethod.GET)
    PageResult<Company> findAllCompany(CompanyUser companyUser);

}
