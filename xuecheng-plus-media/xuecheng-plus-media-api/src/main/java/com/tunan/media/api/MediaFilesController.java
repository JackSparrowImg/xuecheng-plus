package com.tunan.media.api;

import com.tunan.base.model.PageParams;
import com.tunan.base.model.PageResult;
import com.tunan.media.model.dto.QueryMediaParamsDto;
import com.tunan.media.model.dto.UploadFileParamsDto;
import com.tunan.media.model.dto.UploadFileResultDto;
import com.tunan.media.model.po.MediaFiles;
import com.tunan.media.service.MediaFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @projectName: xuecheng-plus
 * @package: com.tunan.media.api
 * @className: MediafilesController
 * @author: Jack
 * @description: 媒资文件管理接口
 * @date: 2023/9/16 17:04
 * @version: 1.0
 */

@Api(value = "媒资文件管理接口",tags = "媒资文件管理接口")
@RestController
public class MediaFilesController {


    @Autowired
    MediaFileService mediaFileService;


    @ApiOperation("媒资列表查询接口")
    @PostMapping("/files")
    public PageResult<MediaFiles> list(PageParams pageParams,
                                       @RequestBody(required = false) QueryMediaParamsDto queryMediaParamsDto){
        Long companyId = 1232141425L;
        return mediaFileService.queryMediaFiels(companyId,pageParams,queryMediaParamsDto);

    }

    @ApiOperation("上传文件")
    @RequestMapping(value = "/upload/coursefile",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public UploadFileResultDto upload(@RequestPart("filedata") MultipartFile upload,
                                      @RequestParam(value = "folder",required=false) String folder,
                                      @RequestParam(value = "objectName",required=false) String objectName
                                            ) throws IOException {

        Long companyId = 1232141425L;
        UploadFileParamsDto uploadFileParamsDto = new UploadFileParamsDto();
        //文件大小
        uploadFileParamsDto.setFileSize(upload.getSize());
        //图片
        uploadFileParamsDto.setFileType("001001");
        //文件名称
        uploadFileParamsDto.setFilename(upload.getOriginalFilename());//文件名称
        //文件大小
        long fileSize = upload.getSize();
        uploadFileParamsDto.setFileSize(fileSize);
        //创建临时文件
        File tempFile = File.createTempFile("minio", "temp");
        //上传的文件拷贝到临时文件
        upload.transferTo(tempFile);
        //文件路径
        String absolutePath = tempFile.getAbsolutePath();
        //上传文件
        UploadFileResultDto uploadFileResultDto = mediaFileService.uploadFile(companyId, uploadFileParamsDto, absolutePath);

        return uploadFileResultDto;
    }





}
