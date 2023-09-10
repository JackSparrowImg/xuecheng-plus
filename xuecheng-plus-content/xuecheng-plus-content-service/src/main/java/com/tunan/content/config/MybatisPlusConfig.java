package com.tunan.content.config;

/**
 * @projectName: xuecheng-plus
 * @package: com.tunan.content.config
 * @className: MybatisPlusConfig
 * @author: Jack
 * @description: TODO
 * @date: 2023/9/10 20:40
 * @version: 1.0
 */

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <P>
 *        Mybatis-Plus 配置
 * </p>
 */
@Configuration
@MapperScan("com.tunan.content.mapper")
public class MybatisPlusConfig {


    /**
     * 定义分页拦截器
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }


}
