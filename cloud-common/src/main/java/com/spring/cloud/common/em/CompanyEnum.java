package com.spring.cloud.common.em;

/**
 * @version 1.0
 * @description：
 * @author: Yang.Chang
 * @project: cloud-general
 * @package: com.spring.cloud.common.em、
 * @email: cy880708@163.com
 * @date: 2019/2/25 上午10:48
 * @mofified By:
 */
public enum CompanyEnum {

    SUBSISTING("subsisting","存续"),

    EMPLOYED("employed","在业"),

    REVOKE("revoke","吊销"),

    ANNUL("annul","注销"),

    MOVING("moving","迁入"),

    EMIGRATION("emigration","迁出"),

    SHUTOUT("shutout","停业"),

    LIQUIDATE("liquidate","清算");

    private final String key;

    private final String value;

    private CompanyEnum (String key,String value){
        this.key = key;
        this.value = value;
    }

    /**
     * 根据key获取枚举
     * @param key
     * @return
     */
    public static CompanyEnum getEnumByKey(String key){
        if(null == key){
            return null;
        }
        for(CompanyEnum temp:CompanyEnum.values()){
            if(temp.getKey().equals(key)){
                return temp;
            }
        }
        return null;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
    
}
