package com.tunan.content;


import com.tunan.content.model.dto.CoursePreviewDto;
import com.tunan.content.service.CoursePublishService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/**
 * @projectName: xuecheng-plus
 * @package: com.tunan.content
 * @className: FreeMarkerTest
 * @author: Jack
 * @description: 测试freeMarker页面静态化测试
 * @date: 2023/10/9 19:10
 * @version: 1.0
 */

@SpringBootTest
public class FreeMarkerTest {


    @Autowired
    CoursePublishService coursePublishService;

    @Test
    public void testGenerateHtmlByTemplate() throws IOException, TemplateException {

        //配置freemarker
        Configuration configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);

        //拿到classpath路径
        String classpath = this.getClass().getResource("/").getPath();
        //指定模板的目录
        configuration.setDirectoryForTemplateLoading(new File(classpath+"/templates/"));
        //指定编码
        configuration.setDefaultEncoding("utf-8");

        //得到模板
        Template template = configuration.getTemplate("course_template.ftl");

        //准备数据
        CoursePreviewDto coursePreviewInfo = coursePublishService.getCoursePreviewInfo(1L);
        HashMap<String,Object> map = new HashMap<>();
        map.put("model",coursePreviewInfo);

        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);

        //使用流将html写入文件
        InputStream inputStream = IOUtils.toInputStream(html, "utf-8");

        //输出文件
        FileOutputStream fileOutputStream = new FileOutputStream(new File("E:\\SpringCloudPractice\\code\\xuecheng-plus\\upload\\1.html"));
        //使用流将html写入文件
        IOUtils.copy(inputStream,fileOutputStream);


    }
}
