package com.xuecheng.search.dto;

import com.tunan.base.model.PageResult;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author Mr.Jack
 * @version 1.0
 * @description TODO
 * @date 2023/10/13 17:51
 */
@Data
@ToString
public class SearchPageResultDto<T> extends PageResult {

    //大分类列表
    List<String> mtList;
    //小分类列表
    List<String> stList;

    public SearchPageResultDto(List<T> items, long counts, long page, long pageSize) {
        super(items, counts, page, pageSize);
    }

}
