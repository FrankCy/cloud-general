package com.spring.cloud.common.po;

import java.io.Serializable;

public class OrderMain implements Serializable {

    private static final long serialVersionUID = -8794096017323294905L;

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
        this.oTitle = oTitle == null ? null : oTitle.trim();
    }

    public String getoDes() {
        return oDes;
    }

    public void setoDes(String oDes) {
        this.oDes = oDes == null ? null : oDes.trim();
    }
}