package com.spring.cloud.common.po;

import com.spring.cloud.common.em.CompanyStatusEnum;

import java.io.Serializable;

/**
 * @version 1.0
 * @description：
 * @author: Yang.Chang
 * @project: cloud
 * @package: com.spring.cloud.common.po
 * @email: cy880708@163.com
 * @date: 2018/11/14 下午8:12
 * @mofified By:
 */
public class Company implements Serializable {


    private static final long serialVersionUID = -1505541566786459102L;

    private Integer cId;

    private String cCode;

    private String cName;

    private String cDes;

    private CompanyStatusEnum status;

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public String getcCode() {
        return cCode;
    }

    public void setcCode(String cCode) {
        this.cCode = cCode;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcDes() {
        return cDes;
    }

    public void setcDes(String cDes) {
        this.cDes = cDes;
    }

    public CompanyStatusEnum getStatus() {
        return status;
    }

    public void setStatus(CompanyStatusEnum status) {
        this.status = status;
    }

    public Company() {
    }

    public Company(Integer cId, String cCode, String cName, String cDes, CompanyStatusEnum status) {
        this.cId = cId;
        this.cCode = cCode;
        this.cName = cName;
        this.cDes = cDes;
        this.status = status;
    }
}
