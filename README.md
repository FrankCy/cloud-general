# Cloud-General #

## 简介 ##
```网关改为Gateway，服务注册和发现使用Consul```

## 基础架构图  ##
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190212141032492.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L0N5X0xpZ2h0QnVsZQ==,size_16,color_FFFFFF,t_70)

##  应用组件 ##
|组件|作用|
|:--|:--|
|Spring Cloud Gateway|网关|
|Spring Cloud Consul|服务注册、服务发现、配置中心|
|Spring Cloud Feign|声明式web service客户端|
|Spring Cloud Hystrix|熔断机制|
|Spring Cloud Ribbon|客户端负载均衡|

## 项目描述 ##
实现对公司实体对CRUD操作。

## 启动 ##
- 顺序
首先要将Consul Server启动，默认8500端口。

| 顺序 | 服务 | 描述 | 端口 |
|:--|:--|:--|:--|
| 1 | GatewayApplication.java | 网关 | 9000 |
| 2 | DataProcessApplication.java | 业务处理service，与数据库交互 | 8099 |
| 3 | ClientAppApplication.java | 网关路由的客户端 | 8081 |

- 功能地址

| 请求方式 | 地址 | 描述 | 参数 |
|:--:|:--|:--|:--|
| POST | localhost:9000/insertCompany | 新增公司 | {cName:'Frank', cCode: '1231232', cDes: '描述'} ```格式有误，自行修改```|
| POST | localhost:9000/deleteCompany | 删除公司 | {cId:'2809'} ```格式有误，自行修改```|
| POST | localhost:9000/updateCompany | 修改公司 | {cId:'2809', cName:'Bill', cCode: '999', cDes: '修改描述'} ```格式有误，自行修改```|
| POST | localhost:9000/findCompanyById | 根据主键查询公司 | {cId:'2809'} ```格式有误，自行修改```|
| POST | localhost:9000/findAllCompany | 分页查询 | {pageNum:'1', pageSize:'10'} ```格式有误，自行修改```|

## 技术点 ##
### Gateway【网关】 ###
### Consul【服务和配置】 ###
### Feign【声明式的Web Service客户端】 ###
### Hystrix【熔断】 ###
### Druid【数据库配置】 ###

## 项目地址【GitHub】 ##
[cloud-general【GitHub】](https://github.com/FrankCy/cloud-general)