package com.tunan.content.service;

import com.tunan.content.model.dto.CourseCategoryTreeDto;

import java.util.List;

/**
 * @projectName: xuecheng-plus
 * @package: com.tunan.content.service
 * @className: CourseCategoryService
 * @author: Jack
 * @description: TODO
 * @date: 2023/9/13 8:16
 * @version: 1.0
 */

public interface CourseCategoryService {
    /**
     * 课程分类树形结构查询
     *
     * @return
     */
    public List<CourseCategoryTreeDto> queryTreeNodes(String id);

}
