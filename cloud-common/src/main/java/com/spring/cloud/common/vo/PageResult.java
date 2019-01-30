package com.spring.cloud.common.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.pagehelper.Page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 封装分页结果
 * @param <T>
 */
public class PageResult<T> implements Serializable{

    private static final long serialVersionUID = 7907490684048847072L;

    private Boolean hasNextPage;
    private List<T> list;
    public PageResult(){
        this.hasNextPage = false;
        this.list = new ArrayList<>();
    }
    /**
     * 当前页码
     */
    private Integer pageNum;

    /**
     * 总页数
     */
    @JsonProperty("totalPage")
    private Integer totalPage;
    /**
     * 总记录数
     *
     */
    private Long totalCount;

    /**
     * 将PageHelper插件中的Page转换成自定义的分页结果
     * @param page
     * @return
     */
    public static <T> PageResult getPageResult(Page<T> page){
        PageResult<T> pageResult = new PageResult<>();
        if (page != null){
            pageResult.setList(page.getResult());
        }else{
            pageResult.setList(new ArrayList<T>());
        }
        pageResult.setPageNum(page.getPageNum());
        pageResult.setTotalCount(page.getTotal());
        pageResult.setTotalPage(page.getPages());
        pageResult.setHasNextPage(page.getPageNum() < page.getPages() ?  true : false);
        return pageResult;
    }

    /**
     * 将PageHelper插件中的Page转换成自定义的分页结果
     * @param list
     * @param pageNum
     * @param totalCount
     * @param pages
     * @param <T>
     * @return
     */
    public static <T> PageResult getPageResult(List<T> list, int pageNum, long totalCount, int pages){
        PageResult<T> pageResult = new PageResult<>();
        pageResult.setList(list == null ? new ArrayList<>() : list);
        pageResult.setPageNum(pageNum);
        pageResult.setTotalCount(totalCount);
        pageResult.setTotalPage(pages);
        pageResult.setHasNextPage(pageNum < pages ?  true : false);
        return pageResult;
    }

    public Boolean getHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(Boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Integer getPage() {
        return pageNum;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }


}
