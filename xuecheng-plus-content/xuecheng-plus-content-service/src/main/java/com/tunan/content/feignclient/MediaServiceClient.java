package com.tunan.content.feignclient;

import com.tunan.content.config.MultipartSupportConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @projectName: xuecheng-plus
 * @package: com.tunan.content.feignclient
 * @className: MediaServiceClient
 * @author: Jack
 * @description: 远程调用媒资服务的接口
 * @date: 2023/10/9 20:14
 * @version: 1.0
 */

//使用fallbake定义降级类是无法拿到熔断异常，使用fallbackFactory好处是可以拿到异常信息
@FeignClient(value = "media-api",configuration = {MultipartSupportConfig.class},fallbackFactory = MediaServiceClientFallbackFactory.class)
public interface MediaServiceClient {

    @RequestMapping(value = "/media/upload/coursefile",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String upload(@RequestPart("filedata") MultipartFile upload,
                         @RequestParam(value = "objectName",required=false) String objectName
    ) throws IOException;


}
