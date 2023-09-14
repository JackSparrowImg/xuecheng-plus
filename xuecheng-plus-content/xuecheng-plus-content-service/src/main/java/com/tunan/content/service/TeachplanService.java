package com.tunan.content.service;

import com.tunan.content.model.dto.SaveTeachplanDto;
import com.tunan.content.model.dto.TeachplanDto;

import java.util.List;

public interface TeachplanService {
    public List<TeachplanDto> findTeachplanTree(long courseId);
    /**
     * @description 只在课程计划
     * @param teachplanDto  课程计划信息
     * @return void
     * @author Jack
     * @date 2023/9/14 13:39
     */
    public void saveTeachplan(SaveTeachplanDto teachplanDto);

}
