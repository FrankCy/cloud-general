package com.spring.cloud.common.po;

public class UserWithBLOBs extends User {
    private String comment;

    private String sealdetail;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public String getSealdetail() {
        return sealdetail;
    }

    public void setSealdetail(String sealdetail) {
        this.sealdetail = sealdetail == null ? null : sealdetail.trim();
    }
}