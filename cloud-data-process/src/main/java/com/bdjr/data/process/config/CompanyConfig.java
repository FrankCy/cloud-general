package com.bdjr.data.process.config;

import com.bdjr.data.process.mapper.CompanyDao;
import com.github.pagehelper.Page;
import com.spring.cloud.common.po.Company;
import com.spring.cloud.common.vo.PageBean;
import com.spring.cloud.common.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @version 1.0
 * @description：
 * @author: Yang.Chang
 * @project: cloud-general
 * @package: com.bdjr.data.process.config、
 * @email: cy880708@163.com
 * @date: 2019/2/11 下午5:07
 * @mofified By:
 */
@Component
public class CompanyConfig {
    /**
     * 公司查询dao
     */
    @Autowired
    private CompanyDao companyDao;

    public int insertCompany(Company company) {
        return companyDao.insertCompany(company);
    }

    /**
     * @description：查询公司列表
     * @version 1.0
     * @author: Yang.Chang
     * @email: cy880708@163.com
     * @date: 2018/11/29 上午10:38
     * @mofified By:
     */
    public PageResult<Company> findAllCompany(Company company, PageBean pageBean) {

        Page<Company> companyPage = companyDao.findAllCompany(company, pageBean.getPageNum(), pageBean.getPageSize(), pageBean.getOrderName(), pageBean.getOrderType());
        if(companyPage.size() > 0) {
            return PageResult.getPageResult(companyPage);
        } else {
            return null;
        }

    }

}
