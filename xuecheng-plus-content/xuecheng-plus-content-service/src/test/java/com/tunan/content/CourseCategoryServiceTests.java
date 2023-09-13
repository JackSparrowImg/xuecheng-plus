package com.tunan.content;

import com.tunan.content.model.dto.CourseCategoryTreeDto;
import com.tunan.content.service.CourseCategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @projectName: xuecheng-plus
 * @package: com.tunan.content
 * @className: CourseGategoryServiceTest
 * @author: Jack
 * @description: TODO
 * @date: 2023/9/13 8:40
 * @version: 1.0
 */

@SpringBootTest
class CourseCategoryServiceTests {

    @Autowired
    CourseCategoryService courseCategoryService;


    @Test
    void testqueryTreeNodes() {
        List<CourseCategoryTreeDto> categoryTreeDtos = courseCategoryService.queryTreeNodes("1");
        for(int i = 0; i < categoryTreeDtos.size(); i++){
            System.out.println(categoryTreeDtos.get(i));
        }
    }

}
