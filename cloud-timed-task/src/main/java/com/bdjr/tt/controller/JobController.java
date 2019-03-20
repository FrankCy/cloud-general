package com.bdjr.tt.controller;

import com.bdjr.tt.config.QuartzManager;
import com.spring.cloud.common.result.ResultModel;
import com.spring.cloud.common.util.Constants;
import com.spring.cloud.common.vo.JobVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @version 1.0
 * @description：
 * @author: Yang.Chang
 * @project: spring-boot-quartz
 * @package: com.sb.quartz.controller、
 * @email: cy880708@163.com
 * @date: 2019/3/20 下午1:47
 * @mofified By:
 */
@RestController
public class JobController {

    /**
     * 初始化定时任务实体
     */
    private static final String JOB_INIT_INI_JOB = "com.bdjr.tt.job.JobInit";

    /**
     * 5s/次
     */
    private static final String JOB_INIT_CRON_EXP = "*/5 * * * * ?";

    @Autowired
    QuartzManager quartzManager;

    /**
     * @description：初始化所有任务
     * @version 1.0
     * @author: Yang.Chang
     * @email: cy880708@163.com
     * @date: 2019/3/20 下午2:25
     * @mofified By:
     */
    @RequestMapping(value = "/initJob", method = RequestMethod.GET)
    public ResultModel initJob() {
        if(quartzManager.addJob(Constants.JOB_INIT_INIT_JOB, JOB_INIT_INI_JOB, JOB_INIT_CRON_EXP)) {
            return new ResultModel.Builder<>().success("初始化任务成功").build();
        } else {
            return new ResultModel.Builder<>().failure("初始化任务失败").build();
        }
    }

    /**
     * @description：删除定时
     * @version 1.0
     * @author: Yang.Chang
     * @email: cy880708@163.com
     * @date: 2019/3/20 下午2:44
     * @mofified By:
     */
    @RequestMapping(value = "/deleteJob" , method = RequestMethod.GET)
    public ResultModel deleteJob(@RequestParam("jobName") String jobName) {
        if(quartzManager.deleteJob(jobName)) {
            return new ResultModel.Builder<>().success("删除任务成功").build();
        } else {
            return new ResultModel.Builder<>().failure("删除任务失败").build();
        }
    }

    /**
     * @description：修改定时任务
     * @version 1.0
     * @author: Yang.Chang
     * @email: cy880708@163.com
     * @date: 2019/3/20 下午2:45
     * @mofified By:
     */
    @RequestMapping(value = "/updateJob", method = RequestMethod.GET)
    public ResultModel updateJob(String jobName, String exp) {
        if(quartzManager.updateJob(jobName, exp)) {
            return new ResultModel.Builder<>().success("修改任务成功").build();
        } else {
            return new ResultModel.Builder<>().failure("修改任务失败").build();
        }
    }

    /**
     * @description：立即执行任务
     * @version 1.0
     * @author: Yang.Chang
     * @email: cy880708@163.com
     * @date: 2019/3/20 下午2:45
     * @mofified By:
     */
    @RequestMapping(value = "/triggerJob", method = RequestMethod.GET)
    public ResultModel triggerJob(String jobName) {
        if(quartzManager.triggerJob(jobName)) {
            return new ResultModel.Builder<>().success("任务触发成功").build();
        } else {
            return new ResultModel.Builder<>().failure("任务触发失败").build();
        }
    }

    /**
     * @description：查询正在执行的定时组
     * @version 1.0
     * @author: Yang.Chang
     * @email: cy880708@163.com
     * @date: 2019/3/20 下午2:45
     * @mofified By:
     */
    @RequestMapping(value = "/findJobs", method = RequestMethod.GET)
    public ResultModel findJobs() {
        List<JobVO> jobVOList = quartzManager.findJobs();
        if(jobVOList.size() > 0 ) {
            return new ResultModel.Builder<>().success(jobVOList).build();
        } else {
            return new ResultModel.Builder<>().failure("没有正在执行的任务").build();
        }
    }

}
