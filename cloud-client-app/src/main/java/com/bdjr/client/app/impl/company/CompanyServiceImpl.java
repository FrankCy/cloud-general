package com.bdjr.client.app.impl.company;

import com.bdjr.client.app.feign.company.CompanyFeign;
import com.bdjr.client.app.service.company.CompanyService;
import com.spring.cloud.common.po.Company;
import com.spring.cloud.common.vo.CompanyUser;
import com.spring.cloud.common.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    private CompanyFeign companyFeign;

    @Override
    public String insertCompany(CompanyUser companyUser) {
        // TODO 这里可以做客户端的逻辑处理
        return companyFeign.insertCompany(companyUser);
    }

    @Override
    public PageResult<Company> findAllCompany(CompanyUser companyUser, int pageNum, int pageSize) {
        return companyFeign.findAllCompany(companyUser);
    }

}
