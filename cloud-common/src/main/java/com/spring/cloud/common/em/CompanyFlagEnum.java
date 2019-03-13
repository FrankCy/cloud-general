package com.spring.cloud.common.em;

/**
 * @version 1.0
 * @description：
 * @author: Yang.Chang
 * @project: cloud-general
 * @package: com.spring.cloud.common.em、
 * @email: cy880708@163.com
 * @date: 2019/3/13 下午4:56
 * @mofified By:
 */
public enum CompanyFlagEnum implements IEnum {

    FLAG_TRUE(0,"成功"),

    FLAG_ERROR(1,"失败");

    private final int key;

    private final String value;

    private CompanyFlagEnum (int key,String value){
        this.key = key;
        this.value = value;
    }

    /**
     * 根据key获取枚举
     * @param key
     * @return
     */
    public static CompanyFlagEnum getEnumByKey(int key){
        for(CompanyFlagEnum temp:CompanyFlagEnum.values()){
            if(temp.getKey().equals(key)){
                return temp;
            }
        }
        return null;
    }

    @Override
    public Object getKey() {
        return key;
    }

    @Override
    public void setKey(int key) {

    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setValue(String value) {

    }
}
