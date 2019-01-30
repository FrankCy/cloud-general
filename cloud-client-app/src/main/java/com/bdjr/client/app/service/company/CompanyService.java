package com.bdjr.client.app.service.company;

import com.spring.cloud.common.vo.CompanyUser;

/**
 * @version 1.0
 * @description：
 * @author: Yang.Chang
 * @project: cloud-general
 * @package: com.bdjr.client.app.service.company、
 * @email: cy880708@163.com
 * @date: 2019/1/30 下午4:27
 * @mofified By:
 */
public interface CompanyService {

    /**
     * 新增公司
     * @param companyUser
     * @return
     */
    String insertCompany(CompanyUser companyUser);

}
