package com.tunan.content.api;

import com.tunan.content.model.dto.CourseTeacherDto;
import com.tunan.content.service.TeacherInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @projectName: xuecheng-plus
 * @package: com.tunan.content.api
 * @className: TeacherInfoController
 * @author: Jack
 * @description: 教师相关信息控制类
 * @date: 2023/9/17 21:42
 * @version: 1.0
 */
@Api(value = "教师信息管理接口",tags = "教师信息管理接口")
@RestController
public class TeacherInfoController {

    @Autowired
    TeacherInfoService teacherInfoService;

    @ApiOperation("根据课程id查询教师的相关信息")
    @GetMapping("/courseTeacher/list/{courseId}")
    public List<CourseTeacherDto> getTeacherInfoByCourseId(@PathVariable Long courseId){
        return teacherInfoService.getTeacherInfo(courseId);
    }

    @ApiOperation("新增课程教师信息")
    @PostMapping("/courseTeacher")
    public CourseTeacherDto creatNewCourseTeacher(@RequestBody CourseTeacherDto courseTeacherDto) {
        return  teacherInfoService.creatCourseTeacher(courseTeacherDto);
    }

    @ApiOperation("修改课程教师信息")
    @PutMapping("/courseTeacher")
    public CourseTeacherDto modifyCourseTeacher(@RequestBody CourseTeacherDto courseTeacherDto) {
        return  teacherInfoService.updateCourseTeacher(courseTeacherDto);
    }

    @DeleteMapping("/courseTeacher/course/{courseId}/{teacherId}}")
    public void deleteCourseTeacher(@PathVariable Long courseId,
                                    @PathVariable Long teacherId
                                        ) {

        teacherInfoService.deleteCourseTeacherInfo(courseId,teacherId);

    }

}
