package com.spring.cloud.common.util;


import com.spring.cloud.common.vo.PageBean;
import sun.jvm.hotspot.debugger.Page;

/**
 * @description：
 * @version 1.0
 * @author: Yang.Chang
 * @email: cy880708@163.com
 * @date: 2019/2/12 下午3:38
 * @mofified By:
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

    /**
     * 初始化分页信息
     * @param pageBean
     * @return
     */
    public static Integer[] getPageInfo(PageBean pageBean){
        Integer[] pageInfo = new Integer[2];
        pageInfo[0] = pageBean.getPageSize() == 0 ? 1 : pageBean.getPageSize();
        pageInfo[1] = pageBean.getPageNum() == 0 ? 10 : pageBean.getPageNum();
        return pageInfo;
    }

    public static void main(String[] args) {
        PageBean pageBean = new PageBean();
        System.out.println(getPageInfo(pageBean)[0]);
        System.out.println(getPageInfo(pageBean)[1]);
    }

}
