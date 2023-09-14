package com.tunan.content;

import com.tunan.content.mapper.TeachplanMapper;
import com.tunan.content.model.dto.TeachplanDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @projectName: xuecheng-plus
 * @package: com.tunan.content
 * @className: CourseBaseMapperTests
 * @author: Jack
 * @description: TODO
 * @date: 2023/9/10 20:42
 * @version: 1.0
 */

@SpringBootTest
public class TeachplanMapperTests {

    @Autowired
    TeachplanMapper teachplanMapper;

    @Test
    public void testSelectTreeNodes(){
        List<TeachplanDto> teachplanDtos = teachplanMapper.selectTreeNodes(117L);
        System.out.println(teachplanDtos);
    }
}
