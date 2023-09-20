package com.tunan.media.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @projectName: xuecheng-plus
 * @package: com.tunan.media.model.dto
 * @className: QueryMediaParamsDto
 * @author: Jack
 * @description: 媒资文件查询请求模型类
 * @date: 2023/9/16 17:09
 * @version: 1.0
 */

@Data
@ToString
public class QueryMediaParamsDto {

    @ApiModelProperty("媒资文件名称")
    private String filename;
    @ApiModelProperty("媒资类型")
    private String fileType;
    @ApiModelProperty("审核状态")
    private String auditStatus;
}
