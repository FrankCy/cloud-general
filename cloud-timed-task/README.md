# cloud-timed-task #
定时任务服务

## 数据结构 ##
- job_main（定时任务主表）
```sql
CREATE TABLE `job_main` (
  `JOB_ID` varchar(32) NOT NULL,
  `JOB_NAME` varchar(64) NOT NULL COMMENT '定时器名称',
  `JOB_GROUP` varchar(64) NOT NULL COMMENT '所属分组',
  `CRO_EXP` varchar(64) DEFAULT NULL COMMENT '表达式[定时器执行频次]',
  `IS_USE` varchar(1) DEFAULT NULL COMMENT '是否可用 0:是；1:否',
  `CREATE_TIME` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `LAST_EXECUTE_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最近一次执行时间',
  PRIMARY KEY (`JOB_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```
- job_detail（定时任务详情信息）
```sql
CREATE TABLE `job_detail` (
  `JOB_DETAIL_ID` varchar(32) NOT NULL,
  `JOB_ID` varchar(32) NOT NULL,
  `DATA` varchar(255) COMMENT '执行时数据信息',
  `EXECUTE_TIME` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '执行时间',
  `CREATE_TIME` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`JOB_DETAIL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```
- job_modify_info（定时任务修改信息）
```sql
CREATE TABLE `job_modify_info` (
  `JOB_MODIFY_ID` varchar(32) NOT NULL,
  `JOB_ID` varchar(32) NOT NULL,
  `DESCRIPTION` varchar(255) COMMENT '修改备注',
  `BEFORE_EXP` varchar(255) COMMENT '修改前表达式[定时器执行频次]',
  `AFTER_EXP` varchar(255) COMMENT '修改后表达式[定时器执行频次]',
  `CREATE_TIME` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`JOB_MODIFY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```