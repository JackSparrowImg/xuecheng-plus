package com.tunan.content.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @projectName: xuecheng-plus
 * @package: com.tunan.media.model.dto
 * @className: BindTeachplanMediaDto
 * @author: Jack
 * @description: 教学计划-媒资绑定提交数据
 * @date: 2023/9/23 20:56
 * @version: 1.0
 */

@Data
@ApiModel(value="BindTeachplanMediaDto", description="教学计划-媒资绑定提交数据")
public class BindTeachplanMediaDto {

    @ApiModelProperty(value = "媒资文件id", required = true)
    private String mediaId;

    @ApiModelProperty(value = "媒资文件名称", required = true)
    private String fileName;

    @ApiModelProperty(value = "课程计划标识", required = true)
    private Long teachplanId;

}
