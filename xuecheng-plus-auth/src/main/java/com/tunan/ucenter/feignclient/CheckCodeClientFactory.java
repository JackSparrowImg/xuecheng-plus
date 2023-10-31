package com.tunan.ucenter.feignclient;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @projectName: xuecheng-plus
 * @package: com.tunan.ucenter.feignclient
 * @className: CheckCodeClientFactory
 * @author: Jack
 * @description: 熔断降级处理
 * @date: 2023/10/30 16:34
 * @version: 1.0
 */

@Slf4j
@Component
public class CheckCodeClientFactory implements FallbackFactory<CheckCodeClient> {
    @Override
    public CheckCodeClient create(Throwable throwable) {
        return new CheckCodeClient() {
            @Override
            public Boolean verify(String key, String code) {
                log.debug("调用验证码服务熔断异常：{}",throwable.getMessage());
                return null;
            }
        };
    }
}
