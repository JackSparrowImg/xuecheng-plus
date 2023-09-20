package com.tunan.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tunan.content.mapper.CourseTeacherMapper;
import com.tunan.content.model.dto.CourseTeacherDto;
import com.tunan.content.model.po.CourseTeacher;
import com.tunan.content.service.TeacherInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @projectName: xuecheng-plus
 * @package: com.tunan.content.service.impl
 * @className: TeacherInfoServiceImpl
 * @author: Jack
 * @description: 教师信息管理service类
 * @date: 2023/9/17 21:45
 * @version: 1.0
 */

@Service
public class TeacherInfoServiceImpl implements TeacherInfoService {

    @Autowired
    CourseTeacherMapper courseTeacherMapper;


    @Override
    public List<CourseTeacherDto> getTeacherInfo(Long courseId) {

        QueryWrapper<CourseTeacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
        List<CourseTeacher> courseTeachers = courseTeacherMapper.selectList(queryWrapper);
        if(courseTeachers.size() == 0){
            return null;
        }

        List<CourseTeacherDto> courseTeacherDtos = new ArrayList<>();
        for(int i = 0; i < courseTeachers.size(); i++) {
            courseTeacherDtos.add(new CourseTeacherDto());
            BeanUtils.copyProperties(courseTeachers.get(i), courseTeacherDtos.get(i));
        }
        return courseTeacherDtos;
    }

    @Override
    public CourseTeacherDto creatCourseTeacher(CourseTeacherDto courseTeacherDto) {
        int result = courseTeacherMapper.insert(courseTeacherDto);
        if(result == 1){
            return courseTeacherDto;
        }else{
            return null;
        }
    }

    @Override
    public CourseTeacherDto updateCourseTeacher(CourseTeacherDto courseTeacherDto) {
        CourseTeacher courseTeacher = courseTeacherMapper.selectById(courseTeacherDto.getId());
        if(courseTeacher == null){
            return null;
        }else {

            CourseTeacher newCourseTeacher = new CourseTeacher();
            BeanUtils.copyProperties(courseTeacherDto,newCourseTeacher);
            courseTeacherMapper.updateById(newCourseTeacher);
        }
        return courseTeacherDto;
    }

    @Override
    public void deleteCourseTeacherInfo(Long courseId, Long teacherId) {
        QueryWrapper<CourseTeacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
        queryWrapper.eq("id",teacherId);
        courseTeacherMapper.delete(queryWrapper);
    }

    public int deleteCourseTeacherInfo(Long courseId) {
        QueryWrapper<CourseTeacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
        return courseTeacherMapper.delete(queryWrapper);
    }
}
