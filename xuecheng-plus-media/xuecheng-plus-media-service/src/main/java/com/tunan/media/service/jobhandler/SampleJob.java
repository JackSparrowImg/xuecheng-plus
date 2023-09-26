package com.tunan.media.service.jobhandler;

import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @projectName: xuecheng-plus
 * @package: com.tunan.media.service.jobhandler
 * @className: SampleJob
 * @author: Jack
 * @description: 测试执行器
 * @date: 2023/9/23 20:38
 * @version: 1.0
 */

@Component
@Slf4j
public class SampleJob {
    /**
     * 1. 简单任务实例（Bean模式）
     */
    @XxlJob("testJob")
    public void testJob() throws Exception{
        log.info("开始执行.................");
    }
}
