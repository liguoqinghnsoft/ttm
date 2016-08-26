package com.ttm.basic.api.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liguoqing on 2016/6/8.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "root")
public class PageModel<T> implements Serializable{

    private int totalRecords;
    private int pageSize;
    private int offset;
    private int totalPage;

    public int getOffset() {
        return offset;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getPageSize() {
        return pageSize;
    }

    @JacksonXmlProperty(localName = "element")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<T> data = new ArrayList<>();
    public PageModel(){}
    public PageModel(int offset,int pageSize,int totalRecords){
        this.offset = offset;
        this.pageSize = pageSize;
        this.totalRecords = totalRecords;
        if(totalRecords % pageSize == 0){
            this.totalPage = totalRecords / pageSize;
        }else{
            this.totalPage = totalRecords / pageSize + 1;
        }
    }
    public int getTotalPage(){
        return totalPage;
    }

    @Override
    public String toString() {
        return "PageModel{" +
                "totalRecords=" + totalRecords +
                ", pageSize=" + pageSize +
                ", offset=" + offset +
                ", totalPage=" + totalPage +
                ", data=" + data +
                '}';
    }
}
