package com.tunan.content.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @projectName: xuecheng-plus
 * @package: com.tunan.content.model.dto
 * @className: EditCourseDto
 * @author: Jack
 * @description: 添加课程dto
 * @date: 2023/9/13 21:22
 * @version: 1.0
 */

@Data
@ApiModel(value="EditCourseDto", description="修改课程基本信息")
public class EditCourseDto extends AddCourseDto {

    @ApiModelProperty(value = "课程id", required = true)
    private Long id;

}

