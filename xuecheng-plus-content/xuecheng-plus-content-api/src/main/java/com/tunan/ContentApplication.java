package com.tunan;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @projectName: xuecheng-plus
 * @package: com.tunan
 * @className: ContentApplication
 * @author: Jack
 * @description: 内容管理服务启动类
 * @date: 2023/9/10 15:47
 * @version: 1.0
 */

@EnableFeignClients(basePackages = {"com.tunan.content.feignclient"})
@EnableSwagger2Doc
@SpringBootApplication
public class ContentApplication {
    public static void main(String[] args) {
        SpringApplication.run(ContentApplication.class,args);
    }
}
