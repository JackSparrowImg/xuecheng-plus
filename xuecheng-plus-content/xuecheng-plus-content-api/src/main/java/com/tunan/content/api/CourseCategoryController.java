package com.tunan.content.api;

import com.tunan.content.model.dto.CourseCategoryTreeDto;
import com.tunan.content.service.CourseCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @projectName: xuecheng-plus
 * @package: com.tunan.content.api
 * @className: CourseCategoryController
 * @author: Jack
 * @description: 课程分类相关接口
 * @date: 2023/9/12 20:02
 * @version: 1.0
 */

@RestController
public class CourseCategoryController {

    @Autowired
    CourseCategoryService courseCategoryService;

    @GetMapping("/course-category/tree-nodes")
    public List<CourseCategoryTreeDto> queryTreeNodes() {
        return courseCategoryService.queryTreeNodes("1");
    }

}
