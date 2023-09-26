package com.tunan.messagesdk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tunan.messagesdk.po.MqMessage;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Jack
 * @since 2023-09-25
 */
public interface MqMessageService extends IService<MqMessage> {

    /**
     * @description 扫描消息表记录，采用与扫描视频处理表相同的思路
     * @param shardIndex 分片序号
     * @param shardTotal 分片总数
     * @param count 扫描记录数
     * @return java.util.List 消息记录
     * @author Jack
     * @date 2023/9/25 18:55
     */
    public List<MqMessage> getMessageList(int shardIndex, int shardTotal,  String messageType,int count);

    /**
     * @description 添加消息
     * @param businessKey1 业务id
     * @param businessKey2 业务id
     * @param businessKey3 业务id
     * @return com.tunan.messagesdk.model.po.MqMessage 消息内容
     * @author Jack
     * @date 2023/9/25 13:45
    */
    public MqMessage addMessage(String messageType,String businessKey1,String businessKey2,String businessKey3);
    /**
     * @description 完成任务
     * @param id 消息id
     * @return int 更新成功：1
     * @author Jack
     * @date 2023/9/25 20:49
     */
    public int completed(long id);

    /**
     * @description 完成阶段任务
     * @param id 消息id
     * @return int 更新成功：1
     * @author Jack
     * @date 2023/9/25 20:49
     */
    public int completedStageOne(long id);
    public int completedStageTwo(long id);
    public int completedStageThree(long id);
    public int completedStageFour(long id);

    /**
     * @description 查询阶段状态
     * @param id
     * @return int
     * @author Jack
     * @date 2023/9/25 20:54
    */
    public int getStageOne(long id);
    public int getStageTwo(long id);
    public int getStageThree(long id);
    public int getStageFour(long id);

}
