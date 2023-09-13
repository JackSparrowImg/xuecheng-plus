package com.tunan.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tunan.content.model.dto.CourseCategoryTreeDto;
import com.tunan.content.model.po.CourseCategory;

import java.util.List;

/**
 * <p>
 * 课程分类 Mapper 接口
 * </p>
 *
 * @author Jack
 */
public interface CourseCategoryMapper extends BaseMapper<CourseCategory> {


    public List<CourseCategoryTreeDto> selectTreeNodes(String id);

}
