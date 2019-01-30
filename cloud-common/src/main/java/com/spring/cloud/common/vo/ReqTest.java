package com.spring.cloud.common.vo;

import java.io.Serializable;

/**
 * @version 1.0
 * @description：
 * @author: Yang.Chang
 * @project: cloud
 * @package: com.spring.cloud.common.vo
 * @email: cy880708@163.com
 * @date: 2018/11/13 下午6:12
 * @mofified By:
 */
public class ReqTest implements Serializable {

    private static final long serialVersionUID = 1503536810677222981L;

    private Integer page;

    private Integer rows;

    private String userid;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}