package com.tunan.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tunan.content.model.dto.TeachplanDto;
import com.tunan.content.model.po.Teachplan;

import java.util.List;

/**
 * <p>
 * 课程计划 Mapper 接口
 * </p>
 *
 * @author Jack
 */
public interface TeachplanMapper extends BaseMapper<Teachplan> {

    //课程计划查询
    public List<TeachplanDto> selectTreeNodes(long courseId);

    int selectChildrenCount(Long teachPlanId);
}

