package com.xuecheng.search.service;

import com.tunan.base.model.PageParams;
import com.xuecheng.search.dto.SearchCourseParamDto;
import com.xuecheng.search.dto.SearchPageResultDto;
import com.xuecheng.search.po.CourseIndex;

/**
 * @description 课程搜索service
 * @author Mr.Jack
 * @date 2023/10/13 22:40
 * @version 1.0
 */
public interface CourseSearchService {


    /**
     * @description 搜索课程列表
     * @param pageParams 分页参数
     * @param searchCourseParamDto 搜索条件
     * @return com.tunan.base.model.PageResult<com.tunan.search.po.CourseIndex> 课程列表
     * @author Mr.Jack
     * @date 2023/10/13 22:45
    */
    SearchPageResultDto<CourseIndex> queryCoursePubIndex(PageParams pageParams, SearchCourseParamDto searchCourseParamDto);

 }
