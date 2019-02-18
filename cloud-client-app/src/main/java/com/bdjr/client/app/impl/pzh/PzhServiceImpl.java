package com.bdjr.client.app.impl.pzh;

import com.bdjr.client.app.feign.pzh.PzhFeign;
import com.bdjr.client.app.service.pzh.PzhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @version 1.0
 * @description：
 * @author: Yang.Chang
 * @project: cloud-general
 * @package: com.bdjr.client.app.impl.pzh、
 * @email: cy880708@163.com
 * @date: 2019/2/18 下午5:40
 * @mofified By:
 */
@Service
public class PzhServiceImpl implements PzhService {

    @Autowired
    private PzhFeign pzhFeign;

    @Override
    public String pzhRequestTest(String test) {
        return pzhFeign.pzhRequestTest(test);
    }
}
