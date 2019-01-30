package com.spring.cloud.common.util;


/**
 * @author: HaijunLiu
 * @email: isaiah@sohu.com
 * @description：计算分页参数工具类
 * @date: 2018/1/30 21:09
 * @mofified By:
 * @version 1.0
 */
public class PageUtil {

    /**
     * 计算当前页
     * @param offset
     * @param pageSize
     * @return
     */
    public static int getPageNum(int offset, int pageSize){
        return offset % pageSize ==  0 ? offset/pageSize+1: offset/pageSize+2;
    }

}
