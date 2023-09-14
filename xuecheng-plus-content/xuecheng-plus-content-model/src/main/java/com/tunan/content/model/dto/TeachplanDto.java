package com.tunan.content.model.dto;

import com.tunan.content.model.po.Teachplan;
import com.tunan.content.model.po.TeachplanMedia;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @projectName: xuecheng-plus
 * @package: com.tunan.content.model.dto
 * @className: TeachplanDto
 * @author: Jack
 * @description: 课程计划信息模型类
 * @date: 2023/9/14 10:21
 * @version: 1.0
 */

@Data
@ToString
public class TeachplanDto extends Teachplan {

    //课程计划关联的媒资信息
    private TeachplanMedia teachplanMedia;

    //子结点
    private List<TeachplanDto> teachPlanTreeNodes;

}

