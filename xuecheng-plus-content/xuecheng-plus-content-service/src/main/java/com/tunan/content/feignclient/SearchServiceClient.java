package com.tunan.content.feignclient;

import com.xuecheng.search.po.CourseIndex;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @projectName: xuecheng-plus
 * @package: com.tunan.content.feignclient
 * @className: SearchServiceClient
 * @author: Jack
 * @description: TODO
 * @date: 2023/10/13 21:35
 * @version: 1.0
 */
@FeignClient(value = "search",fallbackFactory = SearchServiceClientFallbackFactory.class)
public interface SearchServiceClient {

    @PostMapping("/search/index/course")
    public Boolean add(@RequestBody CourseIndex courseIndex);
}
