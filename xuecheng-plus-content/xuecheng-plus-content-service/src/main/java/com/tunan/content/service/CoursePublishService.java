package com.tunan.content.service;

import com.tunan.content.model.dto.CoursePreviewDto;

import java.io.File;

/**
 * @projectName: xuecheng-plus
 * @package: com.tunan.content.service
 * @className: CoursePublicService
 * @author: Jack
 * @description: 课程预览、发布接口
 * @date: 2023/9/24 17:39
 * @version: 1.0
 */

public interface CoursePublishService {

    /**
     * @description 获取课程预览信息
     * @param courseId 课程id
     * @return com.tunan.content.model.dto.CoursePreviewDto
     * @author Jack
     * @date 2023/9/24 15:36
     */
    public CoursePreviewDto getCoursePreviewInfo(Long courseId);

    /**
     * @description 提交审核
     * @param courseId  课程id
     * @return void
     * @author Jack
     * @date 2023/9/24 10:31
     */
    public void commitAudit(Long companyId,Long courseId);

    public void publish(Long companyId,Long courseId);

    /**
     * @description 课程静态化
     * @param courseId  课程id
     * @return File
     * @author Jack
     * @date 2023/10/10 10:31
     */
    public File generateCourseHtml(Long courseId);

    /**
     * @description 上传课程静态化页面
     * @param file 静态化文件
     * @param courseId  课程id
     * @return void
     * @author Jack
     * @date 2023/9/24 10:31
     */
    public void uploadCourseHtml(Long courseId,File file);

}
