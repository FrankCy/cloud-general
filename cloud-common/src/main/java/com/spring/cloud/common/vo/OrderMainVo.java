package com.spring.cloud.common.vo;

import com.spring.cloud.common.po.OrderMain;

/**
 * @version 1.0
 * @description：
 * @author: Yang.Chang
 * @project: cloud-general
 * @package: com.spring.cloud.common.vo、
 * @email: cy880708@163.com
 * @date: 2019/2/14 下午5:08
 * @mofified By:
 */
public class OrderMainVo extends OrderMain {

    /**
     * 公司描述信息
     */
    private String companyDes;

    public String getCompanyDes() {
        return companyDes;
    }

    public void setCompanyDes(String companyDes) {
        this.companyDes = companyDes;
    }
}
