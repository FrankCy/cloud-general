package com.spring.cloud.common.em;

/**
 * @version 1.0
 * @description：
 * @author: Yang.Chang
 * @project: cloud-general
 * @package: com.spring.cloud.common.em、
 * @email: cy880708@163.com
 * @date: 2019/3/11 上午11:41
 * @mofified By:
 */
public interface IEnum {
    Object getKey();

    void setKey(int key);

    String getValue();

    void setValue(String value);
}
