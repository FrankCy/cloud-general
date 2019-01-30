package com.bdjr.client.app.feign.company;

import com.bdjr.client.app.fallback.company.CompanyFallback;
import com.spring.cloud.common.vo.CompanyUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
@FeignClient(name = "cloud-data-process", fallback= CompanyFallback.class)
public interface CompanyFeign {

    @RequestMapping(value = "/insertCompany", method = RequestMethod.POST)
    String insertCompany(CompanyUser companyUser);

}
