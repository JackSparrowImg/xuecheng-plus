package com.tunan.ucenter.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @projectName: xuecheng-plus
 * @package: com.tunan.ucenter.feignclient
 * @className: CheckCodeClient
 * @author: Jack
 * @description: TODO
 * @date: 2023/10/30 16:32
 * @version: 1.0
 */

@FeignClient(value = "checkcode",fallbackFactory = CheckCodeClientFactory.class)
@RequestMapping("/checkcode")
public interface CheckCodeClient {
    @PostMapping(value = "/verify")
    public Boolean verify(@RequestParam("key") String key,@RequestParam("code") String code);
}
