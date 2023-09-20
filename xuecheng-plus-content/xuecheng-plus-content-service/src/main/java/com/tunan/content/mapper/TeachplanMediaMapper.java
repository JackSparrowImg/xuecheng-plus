package com.tunan.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tunan.content.model.po.TeachplanMedia;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Jack
 */
public interface TeachplanMediaMapper extends BaseMapper<TeachplanMedia> {

    void deleteByTeachplanId(Long teachPlanId);
}
