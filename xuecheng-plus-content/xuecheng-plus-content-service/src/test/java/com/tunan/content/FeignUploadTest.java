package com.tunan.content;

import com.tunan.content.config.MultipartSupportConfig;
import com.tunan.content.feignclient.MediaServiceClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @projectName: xuecheng-plus
 * @package: com.tunan.content
 * @className: FeignUploadTest
 * @author: Jack
 * @description: TODO
 * @date: 2023/10/9 20:19
 * @version: 1.0
 */

@SpringBootTest
public class FeignUploadTest {

    @Autowired
    MediaServiceClient mediaServiceClient;

    @Test
    public void test() throws IOException {

        //将file转成multipartsupport
        File file = new File("E:\\SpringCloudPractice\\code\\xuecheng-plus\\upload\\1.html");
        MultipartFile multipartFile = MultipartSupportConfig.getMultipartFile(file);


        mediaServiceClient.upload(multipartFile,"course/1.html");
    }
}
