package com.tunan.content.service;

import com.tunan.content.model.dto.CourseTeacherDto;

import java.util.List;

public interface TeacherInfoService {

    List<CourseTeacherDto> getTeacherInfo(Long courseId);

    CourseTeacherDto creatCourseTeacher(CourseTeacherDto courseTeacherDto);

    CourseTeacherDto updateCourseTeacher(CourseTeacherDto courseTeacherDto);

    void deleteCourseTeacherInfo(Long courseId, Long teacherId);
}
