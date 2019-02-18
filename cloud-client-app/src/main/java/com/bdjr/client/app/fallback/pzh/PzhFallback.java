package com.bdjr.client.app.fallback.pzh;

import com.bdjr.client.app.feign.pzh.PzhFeign;
import org.springframework.stereotype.Component;

/**
 * @version 1.0
 * @description：
 * @author: Yang.Chang
 * @project: cloud-general
 * @package: com.bdjr.client.app.fallback.pzh、
 * @email: cy880708@163.com
 * @date: 2019/2/18 下午5:42
 * @mofified By:
 */
@Component
public class PzhFallback implements PzhFeign {

    @Override
    public String pzhRequestTest(String test) {
        return "pzh requestTest error hystrix!";
    }
}
