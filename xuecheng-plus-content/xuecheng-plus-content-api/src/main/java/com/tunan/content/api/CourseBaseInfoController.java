package com.tunan.content.api;

import com.tunan.base.model.PageParams;
import com.tunan.base.model.PageResult;
import com.tunan.content.model.dto.QueryCourseParamsDto;
import com.tunan.content.model.po.CourseBase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @projectName: xuecheng-plus
 * @package: com.tunan.content.api
 * @className: CourseBaseInfoController
 * @author: Jack
 * @description: TODO
 * @date: 2023/9/10 15:43
 * @version: 1.0
 */


@Api(value = "课程信息管理接口",tags = "课程信息管理接口")
@RestController
public class CourseBaseInfoController {

    @ApiOperation("课程查询接口")
    @PostMapping("/course/list")
    public PageResult<CourseBase> list(PageParams pageParams,
                                       @RequestBody(required = false) QueryCourseParamsDto queryCourseParamsDto) {

        return null;
    }
}
