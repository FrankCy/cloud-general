package com.spring.cloud.common.vo;

import com.spring.cloud.common.po.Company;

/**
 * @version 1.0
 * @description：
 * @author: Yang.Chang
 * @project: cloud-general
 * @package: com.spring.cloud.common.vo、
 * @email: cy880708@163.com
 * @date: 2019/2/14 下午6:20
 * @mofified By:
 */
public class CompanyOrderVo extends Company {

    private Integer oId;

    private String oTitle;

    private String oDes;

    public Integer getoId() {
        return oId;
    }

    public void setoId(Integer oId) {
        this.oId = oId;
    }

    public String getoTitle() {
        return oTitle;
    }

    public void setoTitle(String oTitle) {
        this.oTitle = oTitle;
    }

    public String getoDes() {
        return oDes;
    }

    public void setoDes(String oDes) {
        this.oDes = oDes;
    }
}
