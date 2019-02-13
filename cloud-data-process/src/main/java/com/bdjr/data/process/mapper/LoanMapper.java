package com.bdjr.data.process.mapper;

import com.spring.cloud.common.po.Loan;
import com.spring.cloud.common.po.LoanWithBLOBs;

public interface LoanMapper {
    int deleteByPrimaryKey(String id);

    int insert(LoanWithBLOBs record);

    int insertSelective(LoanWithBLOBs record);

    LoanWithBLOBs selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(LoanWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(LoanWithBLOBs record);

    int updateByPrimaryKey(Loan record);
}