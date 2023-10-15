package com.xuecheng.search.service;

/**
 * @author Mr.Jack
 * @version 1.0
 * @description 课程索引service
 * @date 2023/10/13 22:40
 */
public interface IndexService {

    /**
     * @param indexName 索引名称
     * @param id 主键
     * @param object 索引对象
     * @return Boolean true表示成功,false失败
     * @description 添加索引
     * @author Mr.Jack
     * @date 2023/10/13 22:57
     */
    public Boolean addCourseIndex(String indexName,String id,Object object);


    /**
     * @description 更新索引
     * @param indexName 索引名称
     * @param id 主键
     * @param object 索引对象
     * @return Boolean true表示成功,false失败
     * @author Mr.Jack
     * @date 2023/10/13 7:49
    */
    public Boolean updateCourseIndex(String indexName,String id,Object object);

    /**
     * @description 删除索引
     * @param indexName 索引名称
     * @param id  主键
     * @return java.lang.Boolean
     * @author Mr.Jack
     * @date 2022/10/13 9:27
    */
    public Boolean deleteCourseIndex(String indexName,String id);

}
