package com.spring.cloud.common.vo;

import com.spring.cloud.common.po.Company;

/**
 * @version 1.0
 * @description：
 * @author: Yang.Chang
 * @project: cloud
 * @package: com.spring.cloud.common.vo、
 * @email: cy880708@163.com
 * @date: 2018/11/16 下午3:57
 * @mofified By:
 */
public class CompanyUser extends Company {

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 分页信息
     */
    private PageBean pageBean;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public PageBean getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean pageBean) {
        this.pageBean = pageBean;
    }
}
