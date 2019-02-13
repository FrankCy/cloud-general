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
public interface CompanyMapper {

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
     * @description：删除
     * @version 1.0
     * @author: Yang.Chang
     * @email: cy880708@163.com
     * @date: 2019/2/12 下午6:08
     * @mofified By:
     */
    int deleteByPrimaryKey(@Param("cId") String cId);

    /**
     * @description：修改
     * @version 1.0
     * @author: Yang.Chang
     * @email: cy880708@163.com
     * @date: 2018/11/16 下午1:30
     * @mofified By:
     */
    int updateCompany(Company company);

    /**
     * @description：根据主键查询
     * @version 1.0
     * @author: Yang.Chang
     * @email: cy880708@163.com
     * @date: 2019/2/12 下午6:08
     * @mofified By:
     */
    Company selectByPrimaryKey(@Param("cId") String cId);

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
