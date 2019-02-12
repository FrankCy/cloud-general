package com.bdjr.client.app.controller;

import com.bdjr.client.app.service.company.CompanyService;
import com.spring.cloud.common.base.Constants;
import com.spring.cloud.common.po.Company;
import com.spring.cloud.common.result.BdjrResult;
import com.spring.cloud.common.util.PageUtil;
import com.spring.cloud.common.vo.CompanyUser;
import com.spring.cloud.common.vo.DataResult;
import com.spring.cloud.common.vo.PageBean;
import com.spring.cloud.common.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.util.StringUtils;
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
    public BdjrResult insertCompany(CompanyUser companyUser){

        String insertCompanyMessage = companyService.insertCompany(companyUser);

        if(!StringUtils.isEmpty(insertCompanyMessage) && Constants.operaterSuccess.equals(insertCompanyMessage)) {
            return new BdjrResult.Builder<>().success("新增成功").build();
        } else {
            return new BdjrResult.Builder<>().failure("新增失败").build();
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
    public BdjrResult deleteCompany(String cId){

        if(StringUtils.isEmpty(cId)) {
            return new BdjrResult.Builder<>().failure("参数传递错误，请联系管理员！").build();
        }

        String deleteCompanyMessage = companyService.deleteCompany(cId);

        if(!StringUtils.isEmpty(deleteCompanyMessage) && Constants.operaterSuccess.equals(deleteCompanyMessage)) {
            return new BdjrResult.Builder<>().success("删除成功").build();
        } else {
            return new BdjrResult.Builder<>().failure("删除失败").build();
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
    public BdjrResult findCompanyById(String cId){
        // 分页信息传递
        Company company = companyService.findCompanyById(cId);

        if(company != null) {
            return new BdjrResult.Builder<>().success("查询成功", company).build();
        } else {
            return new BdjrResult.Builder<>().failure("查询失败，未找到对应数据").build();
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
    public BdjrResult findAllCompany(CompanyUser companyUser, Integer pageNum, Integer pageSize){
        // 分页信息传递
        PageResult<Company> company = companyService.findAllCompany(companyUser, pageNum, pageSize);

        if(company.getList().size() > 0) {
            return new BdjrResult.Builder<>().success(new DataResult.Builder().build(company)).build();
        } else {
            return new BdjrResult.Builder<>().failure("查询失败，未找到对应数据").build();
        }
    }

}
