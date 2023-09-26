package com.tunan.media.service;


import com.tunan.base.model.PageParams;
import com.tunan.base.model.PageResult;
import com.tunan.base.model.RestResponse;
import com.tunan.media.model.dto.QueryMediaParamsDto;
import com.tunan.media.model.dto.UploadFileParamsDto;
import com.tunan.media.model.dto.UploadFileResultDto;
import com.tunan.media.model.po.MediaFiles;

/**
 * @author Jack
 * @version 1.0
 * @description 媒资文件管理业务类
 * @date 2023/9/10 8:55
 */
public interface MediaFileService {

    /**
     * @param pageParams          分页参数
     * @param queryMediaParamsDto 查询条件
     * @return com.tunan.base.model.PageResult<com.tunan.media.model.po.MediaFiles>
     * @description 媒资文件查询方法
     * @author Jack
     * @date 2023/9/17 8:57
     */
    public PageResult<MediaFiles> queryMediaFiels(Long companyId, PageParams pageParams, QueryMediaParamsDto queryMediaParamsDto);


    /**
     * 上传文件
     *
     * @param companyId           机构id
     * @param uploadFileParamsDto 上传文件信息
     * @param localFilePath       文件磁盘路径
     * @return 文件信息
     */
    public UploadFileResultDto uploadFile(Long companyId, UploadFileParamsDto uploadFileParamsDto, String localFilePath);

    public MediaFiles addMediaFilesToDb(Long companyId, String fileMd5, UploadFileParamsDto uploadFileParamsDto, String bucket, String objectName);

    public RestResponse<Boolean> checkFile(String fileMd5);

    /**
     * @param fileMd5    文件的md5
     * @param chunkIndex 分块序号
     * @return com.tunan.base.model.RestResponse<java.lang.Boolean> false不存在，true存在
     * @description 检查分块是否存在
     * @author Jack
     * @date 2023/9/21 15:39
     */
    public RestResponse<Boolean> checkChunk(String fileMd5, int chunkIndex);


    /**
     * @param fileMd5            文件md5
     * @param chunk              分块序号
     * @param localChunkFilePath 文件字节
     * @return com.xuecheng.base.model.RestResponse
     * @description 上传分块
     * @author Jack
     * @date 2023/9/21 14:39
     */
    public RestResponse uploadChunk(String fileMd5, int chunk, String localChunkFilePath);

    /**
     * @param companyId           机构id
     * @param fileMd5             文件md5
     * @param chunkTotal          分块总和
     * @param uploadFileParamsDto 文件信息
     * @return com.xuecheng.base.model.RestResponse
     * @description 合并分块
     * @author Jack
     * @date 2023/9/21 15:19
     */
    public RestResponse mergechunks(Long companyId, String fileMd5, int chunkTotal, UploadFileParamsDto uploadFileParamsDto);


    MediaFiles getFileById(String mediaId);
}
