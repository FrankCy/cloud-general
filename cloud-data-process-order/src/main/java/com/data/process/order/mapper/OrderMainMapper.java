package com.data.process.order.mapper;

import com.spring.cloud.common.po.OrderMain;

public interface OrderMainMapper {
    int deleteByPrimaryKey(Integer oId);

    int insert(OrderMain record);

    int insertSelective(OrderMain record);

    OrderMain selectByPrimaryKey(Integer oId);

    int updateByPrimaryKeySelective(OrderMain record);

    int updateByPrimaryKey(OrderMain record);
}