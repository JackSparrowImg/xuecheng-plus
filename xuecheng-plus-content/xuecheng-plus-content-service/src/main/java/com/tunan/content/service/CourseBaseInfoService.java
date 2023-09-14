package com.tunan.content.service;

import com.tunan.base.model.PageParams;
import com.tunan.base.model.PageResult;
import com.tunan.content.model.dto.AddCourseDto;
import com.tunan.content.model.dto.CourseBaseInfoDto;
import com.tunan.content.model.dto.EditCourseDto;
import com.tunan.content.model.dto.QueryCourseParamsDto;
import com.tunan.content.model.po.CourseBase;

/**
 * @projectName: xuecheng-plus
 * @package: com.tunan.content.service
 * @className: CourseBaseInfoService
 * @author: Jack
 * @description: 课程基本信息管理业务接口
 * @date: 2023/9/11 15:18
 * @version: 1.0
 */

public interface CourseBaseInfoService {
    PageResult<CourseBase> queryCourseBaseList(PageParams pageParams, QueryCourseParamsDto queryCourseParamsDto);

    /**
     * @description 添加课程基本信息
     * @param companyId  教学机构id
     * @param addCourseDto  课程基本信息
     * @return com.tunan.content.model.dto.CourseBaseInfoDto
     * @author Mr.Jack
     * @date 2023/9/13 17:51
     */
    CourseBaseInfoDto createCourseBase(Long companyId, AddCourseDto addCourseDto);


    /**
     * @description 添加课程基本信息
     * @param courseId  课程id
     * @return com.tunan.content.model.dto.CourseBaseInfoDto
     * @author Mr.Jack
     * @date 2023/9/13 17:51
     */
    public CourseBaseInfoDto getCourseBaseInfo(long courseId);


    /**
     * @description 添加课程基本信息
     * @param companyId  机构id
     * @param dto  课程信息
     * @return com.tunan.content.model.dto.CourseBaseInfoDto
     * @author Mr.Jack
     * @date 2023/9/13 17:51
     */
    public CourseBaseInfoDto updateCourseBase(Long companyId, EditCourseDto dto);
}
