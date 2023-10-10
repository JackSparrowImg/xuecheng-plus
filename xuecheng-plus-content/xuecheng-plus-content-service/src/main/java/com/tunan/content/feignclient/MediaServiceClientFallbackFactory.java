package com.tunan.content.feignclient;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @projectName: xuecheng-plus
 * @package: com.tunan.content.feignclient
 * @className: MediaServiceClientFallbackFactory
 * @author: Jack
 * @description: TODO
 * @date: 2023/10/9 21:47
 * @version: 1.0
 */

@Slf4j
@Component
public class MediaServiceClientFallbackFactory implements FallbackFactory<MediaServiceClient> {

    //拿到当时熔断的异常信息
    @Override
    public MediaServiceClient create(Throwable throwable) {
        //发生熔断上游服务就会调用此方法执行降级逻辑
        return new MediaServiceClient() {
            @Override
            public String upload(MultipartFile upload, String objectName) throws IOException {
                log.debug("远程调用文件的接口发生熔断:{}",throwable.toString(),throwable);
                return null;
            }
        };
    }
}
