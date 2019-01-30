package com.bdjr.client.app.fallback.company;

import com.bdjr.client.app.feign.company.CompanyFeign;
import com.spring.cloud.common.vo.CompanyUser;
import org.springframework.stereotype.Component;

/**
 * @version 1.0
 * @description：
 * @author: Yang.Chang
 * @project: cloud-general
 * @package: com.bdjr.client.app.fallback.company、
 * @email: cy880708@163.com
 * @date: 2019/1/30 下午4:36
 * @mofified By:
 */
@Component
public class CompanyFallback implements CompanyFeign {

    @Override
    public String insertCompany(CompanyUser companyUser) {
        return new String("新增公司信息失败！FeignClient");
    }

}
