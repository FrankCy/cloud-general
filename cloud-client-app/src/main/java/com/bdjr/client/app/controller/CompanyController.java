package com.bdjr.client.app.controller;

import com.bdjr.client.app.service.company.CompanyService;
import com.spring.cloud.common.base.Constants;
import com.spring.cloud.common.em.CompanyStatusEnum;
import com.spring.cloud.common.po.Company;
import com.spring.cloud.common.result.ResultModel;
import com.spring.cloud.common.util.JSONUtil;
import com.spring.cloud.common.vo.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 1.0
 * @description：
 * @author: Yang.Chang
 * @project: cloud-general
 * @package: com.bdjr.client.app.controller、
 * @email: cy880708@163.com
 * @date: 2019/1/30 下午4:38
 * @mofified By:
 */
@RestController
public class CompanyController {

    private static final Log logger = LogFactory.getLog(CompanyController.class);

    @Autowired
    protected CompanyService companyService;

    /**
     * @description：新增公司信息
     * @version 1.0
     * @author: Yang.Chang
     * @email: cy880708@163.com
     * @date: 2019/2/11 下午4:27
     * @mofified By:
     */
    @RequestMapping(value = "/insertCompany", method = RequestMethod.POST)
    public ResultModel insertCompany(@RequestBody String value){

        if(StringUtils.isEmpty(value)) {
            return new ResultModel.Builder<>().failure("新增失败，未获取到对应参数").build();
        }

        logger.info("value : " + value);

        CompanyUser companyUser = JSONUtil.jsonToBean(value, CompanyUser.class);

        if(companyUser.getStatus() == CompanyStatusEnum.SUBSISTING) {
            logger.info("Enum : " + CompanyStatusEnum.SUBSISTING);
            logger.info("key : " + CompanyStatusEnum.SUBSISTING.getKey());
            logger.info("value : " + CompanyStatusEnum.SUBSISTING.getValue());
        }

        String insertCompanyMessage = companyService.insertCompany(companyUser);

        logger.info("创建公司，服务器返回：" + insertCompanyMessage);

        if(!StringUtils.isEmpty(insertCompanyMessage) && Constants.operaterSuccess.equals(insertCompanyMessage)) {
            return new ResultModel.Builder<>().success("新增成功").build();
        } else {
            return new ResultModel.Builder<>().failure("新增失败").build();
        }
    }

    /**
     * @description：删除公司
     * @version 1.0
     * @author: Yang.Chang
     * @email: cy880708@163.com
     * @date: 2019/2/12 下午6:04
     * @mofified By:
     */
    @RequestMapping(value = "/deleteCompany", method = RequestMethod.POST)
    public ResultModel deleteCompany(String cId){

        //判空
        if(StringUtils.isEmpty(cId)) {
            return new ResultModel.Builder<>().failure("参数传递错误，请联系管理员！").build();
        }

        //查询是否有对应数据
        Company company = companyService.findCompanyById(cId);
        if(company == null) {
            return new ResultModel.Builder<>().failure("未找到对应数据！").build();
        }

        String deleteCompanyMessage = companyService.deleteCompany(cId);

        if(!StringUtils.isEmpty(deleteCompanyMessage) && Constants.operaterSuccess.equals(deleteCompanyMessage)) {
            return new ResultModel.Builder<>().success("删除成功").build();
        } else {
            return new ResultModel.Builder<>().failure("删除失败").build();
        }
    }

    /**
     * @description：修改公司
     * @version 1.0
     * @author: Yang.Chang
     * @email: cy880708@163.com
     * @date: 2019/2/12 下午6:04
     * @mofified By:
     */
    @RequestMapping(value = "/updateCompany", method = RequestMethod.POST)
    public ResultModel updateCompany(CompanyUser companyUser){

        //判空
        if(StringUtils.isEmpty(companyUser.getcId())) {
            return new ResultModel.Builder<>().failure("无效公司，修改失败！").build();
        }

        //查询是否有对应数据
        Company company = companyService.findCompanyById(companyUser.getcId().toString());
        if(company == null) {
            return new ResultModel.Builder<>().failure("未找到对应数据！").build();
        }

        String updateCompanyMessage = companyService.updateCompany(companyUser);

        if(!StringUtils.isEmpty(updateCompanyMessage) && Constants.operaterSuccess.equals(updateCompanyMessage)) {
            return new ResultModel.Builder<>().success("修改成功").build();
        } else {
            return new ResultModel.Builder<>().failure("修改失败").build();
        }

    }

    /**
     * @description：根据主键查询单条数据
     * @version 1.0
     * @author: Yang.Chang
     * @email: cy880708@163.com
     * @date: 2019/2/12 下午6:16
     * @mofified By:
     */
    @RequestMapping(value = "/findCompanyById", method = RequestMethod.POST)
    public ResultModel findCompanyById(String cId){
        // 分页信息传递
        Company company = companyService.findCompanyById(cId);

        if(company != null) {
            return new ResultModel.Builder<>().success("查询成功", company).build();
        } else {
            return new ResultModel.Builder<>().failure("查询失败，未找到对应数据").build();
        }
    }

    /**
     * @description：查询公司信息
     * @version 1.0
     * @author: Yang.Chang
     * @email: cy880708@163.com
     * @date: 2019/2/12 下午2:47
     * @mofified By:
     */
    @RequestMapping(value = "/findAllCompany", method = RequestMethod.POST)
    public ResultModel findAllCompany(CompanyUser companyUser, Integer pageNum, Integer pageSize){
        PageBean pageBean = new PageBean();
        pageBean.setPageNum(pageNum);
        pageBean.setPageSize(pageSize);
        companyUser.setPageBean(pageBean);
        // 分页信息传递
        PageResult<Company> company = companyService.findAllCompany(companyUser);

        if(company.getList().size() > 0) {
            return new ResultModel.Builder<>().success(new DataResult.Builder().build(company)).build();
        } else {
            return new ResultModel.Builder<>().failure("查询失败，未找到对应数据").build();
        }
    }

    /**
     * @description：修改公司信息，同时修改订单信息
     * @version 1.0
     * @author: Yang.Chang
     * @email: cy880708@163.com
     * @date: 2019/2/14 下午6:34
     * @mofified By:
     */
    @RequestMapping(value = "/updateCompanyOrder", method = RequestMethod.POST)
    public ResultModel updateCompanyOrder(CompanyOrderVo companyOrderVo){

        String updateCompanyMessage = companyService.updateCompanyOrder(companyOrderVo);

        if(!StringUtils.isEmpty(updateCompanyMessage) && Constants.operaterSuccess.equals(updateCompanyMessage)) {
            return new ResultModel.Builder<>().success("修改成功").build();
        } else {
            return new ResultModel.Builder<>().failure("修改失败").build();
        }

    }

}
