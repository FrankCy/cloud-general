package com.bdjr.client.app.service.company;

import com.spring.cloud.common.po.Company;
import com.spring.cloud.common.vo.CompanyOrderVo;
import com.spring.cloud.common.vo.CompanyUser;
import com.spring.cloud.common.vo.PageResult;

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

    /**
     * 修改公司
     * @param companyUser
     * @return
     */
    String updateCompany(CompanyUser companyUser);

    /**
     * 修改公司同时修改订单
     * @param companyOrderVo
     * @return
     */
    String updateCompanyOrder(CompanyOrderVo companyOrderVo);

    /**
     * 删除公司
     * @param cId
     * @return
     */
    String deleteCompany(String cId);

    /**
     * 根据主键查询
     * @param cId
     * @return
     */
    Company findCompanyById(String cId);

    /**
     * 查询公司信息
     * @param companyUser
     * @return
     */
    PageResult<Company> findAllCompany(CompanyUser companyUser);
}
