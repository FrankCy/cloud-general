package com.bdjr.data.process.mapper;

import com.spring.cloud.common.po.Invest;
import com.spring.cloud.common.po.InvestWithBLOBs;

public interface InvestMapper {
    int deleteByPrimaryKey(String id);

    int insert(InvestWithBLOBs record);

    int insertSelective(InvestWithBLOBs record);

    InvestWithBLOBs selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(InvestWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(InvestWithBLOBs record);

    int updateByPrimaryKey(Invest record);
}