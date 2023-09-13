package com.tunan.content.service;

import com.tunan.base.model.PageParams;
import com.tunan.base.model.PageResult;
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
}
