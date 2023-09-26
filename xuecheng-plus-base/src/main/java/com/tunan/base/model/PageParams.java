package com.tunan.base.model;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @description 分页查询通用参数
 * @author Jack
 * @date 2023/9/10 14:02
 * @version 1.0
 */
@Data
@ToString
public class PageParams {

    //当前页码
    @ApiModelProperty("页码")
    private Long pageNo = 1L;

    //每页记录数默认值
    @ApiModelProperty("每页记录数")
    private Long pageSize =10L;

    public PageParams(){
    }
    public PageParams(long pageNo,long pageSize){
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }
}
