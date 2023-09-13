package com.tunan.content.model.dto;

import com.tunan.content.model.po.CourseCategory;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @projectName: xuecheng-plus
 * @package: com.tunan.content.model.dto
 * @className: CourseCategoryTreeDto
 * @author: Jack
 * @description: TODO
 * @date: 2023/9/12 19:59
 * @version: 1.0
 */

@Data
@ToString
public class CourseCategoryTreeDto extends CourseCategory implements Serializable {

    List<CourseCategoryTreeDto> childrenTreeNodes;
}
