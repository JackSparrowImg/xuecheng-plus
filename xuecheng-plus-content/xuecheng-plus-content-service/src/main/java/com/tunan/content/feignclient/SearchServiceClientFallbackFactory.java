package com.tunan.content.feignclient;

import com.xuecheng.search.po.CourseIndex;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
/**
 * @projectName: xuecheng-plus
 * @package: com.tunan.content.feignclient
 * @className: SearchServiceClientFallbackFactory
 * @author: Jack
 * @description: TODO
 * @date: 2023/10/13 21:39
 * @version: 1.0
 */

@Slf4j
@Component
public class SearchServiceClientFallbackFactory implements FallbackFactory<SearchServiceClient> {
    @Override
    public SearchServiceClient create(Throwable throwable) {
        return new SearchServiceClient() {
            @Override
            public Boolean add(CourseIndex courseIndex) {
                log.error("添加课程索引发生熔断，索引的信息：{},熔断的异常信息",courseIndex,throwable.toString());

                //走降级了我们返回false
                return false;
            }
        };
    }
}
