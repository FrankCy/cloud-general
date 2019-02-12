package com.bdjr.data.process.mapper;

import com.github.pagehelper.Page;
import com.spring.cloud.common.po.Company;
import org.apache.ibatis.annotations.Param;

/**
 * @version 1.0
 * @description：
 * @author: Yang.Chang
 * @project: cloud-general
 * @package: com.bdjr.data.process.mapper、
 * @email: cy880708@163.com
 * @date: 2019/2/11 下午5:07
 * @mofified By:
 */
public interface CompanyDao {

    /**
     * @description：新增
     * @version 1.0
     * @author: Yang.Chang
     * @email: cy880708@163.com
     * @date: 2018/11/16 下午1:30
     * @mofified By:
     */
    int insertCompany(Company company);

    /**
     * 查询
     * @param company
     * @param pageNum
     * @param pageSize
     * @param orderColumn
     * @param orderType
     * @return
     */
    Page<Company> findAllCompany(@Param("com") Company company, @Param("pageNum")  int pageNum, @Param("pageSize") int pageSize, @Param("orderColumn")  String orderColumn, @Param("orderType")  String orderType);

}
