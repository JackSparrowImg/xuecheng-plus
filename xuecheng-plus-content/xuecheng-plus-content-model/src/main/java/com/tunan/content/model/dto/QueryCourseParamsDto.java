package com.tunan.content.model.dto;

import lombok.Data;
import lombok.ToString;

/**
 * @description 课程查询条件模型类
 * @author Mr.Jack
 * @date 2023/9/10 14:36
 * @version 1.0
 */

@Data
@ToString
public class QueryCourseParamsDto {

    //审核状态
    private String auditStatus;
    //课程名称
    private String courseName;
    //发布状态
    private String publishStatus;

}

