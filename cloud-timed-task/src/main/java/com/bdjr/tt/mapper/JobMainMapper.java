package com.bdjr.tt.mapper;


import com.bdjr.tt.po.JobMain;

public interface JobMainMapper {
    int deleteByPrimaryKey(String jobId);

    int insert(JobMain record);

    int insertSelective(JobMain record);

    JobMain selectByPrimaryKey(String jobId);

    JobMain selectByName(String jobName);

    int updateByPrimaryKeySelective(JobMain record);

    int updateByPrimaryKey(JobMain record);
}