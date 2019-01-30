package com.spring.cloud.common.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @description：数据初始化
 * @version 1.0
 * @author: Yang.Chang
 * @email: cy880708@163.com
 * @date: 2018/11/29 下午1:45
 * @mofified By:
 */
public class DataResult<T> implements Serializable{

    private Long total;
    private List<T> rows;

    public DataResult(Long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }


    public DataResult(DataResult.Builder<T> builder) {
        this.total = builder.total;
        this.rows = builder.rows;
    }

    public static class Builder<T> {
        private Long total;
        private List<T> rows;

        public DataResult.Builder total(Long total) {
            this.total = total;
            return this;
        }

        public DataResult.Builder rows(List<T> rows) {
            this.rows = rows;
            return this;
        }

        public DataResult build() {
            return new DataResult(this);
        }

        public DataResult build(PageResult pageResult) {
            this.total = pageResult.getTotalCount();
            this.rows = pageResult.getList();
            return new DataResult(this);
        }
    }
}
