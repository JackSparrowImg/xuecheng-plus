package com.tunan.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tunan.base.exception.XueChengPlusException;
import com.tunan.content.mapper.TeachplanMapper;
import com.tunan.content.mapper.TeachplanMediaMapper;
import com.tunan.content.model.dto.BindTeachplanMediaDto;
import com.tunan.content.model.dto.SaveTeachplanDto;
import com.tunan.content.model.dto.TeachplanDto;
import com.tunan.content.model.po.Teachplan;
import com.tunan.content.model.po.TeachplanMedia;
import com.tunan.content.service.TeachplanService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @projectName: xuecheng-plus
 * @package: com.tunan.content.service.impl
 * @className: TeachplanServiceImpl
 * @author: Jack
 * @description: TODO
 * @date: 2023/9/14 10:29
 * @version: 1.0
 */

@Service
public class TeachplanServiceImpl implements TeachplanService {
    @Autowired
    TeachplanMapper teachplanMapper;


    @Override
    public List<TeachplanDto> findTeachplanTree(long courseId) {
        List<TeachplanDto> teachplanDtos = teachplanMapper.selectTreeNodes(courseId);
        return teachplanDtos;
    }

    @Transactional
    @Override
    public void saveTeachplan(SaveTeachplanDto teachplanDto) {

        //课程计划id
        Long id = teachplanDto.getId();
        //修改课程计划
        if(id!=null){
            Teachplan teachplan = teachplanMapper.selectById(id);
            BeanUtils.copyProperties(teachplanDto,teachplan);
            teachplanMapper.updateById(teachplan);
        }else{
            //取出同父同级别的课程计划数量
            int count = getTeachplanCount(teachplanDto.getCourseId(), teachplanDto.getParentid());
            Teachplan teachplanNew = new Teachplan();
            //设置排序号
            teachplanNew.setOrderby(count+1);
            BeanUtils.copyProperties(teachplanDto,teachplanNew);

            teachplanMapper.insert(teachplanNew);

        }

    }



    /**
     * @description 获取最新的排序号
     * @param courseId  课程id
     * @param parentId  父课程计划id
     * @return int 最新排序号
     * @author Jack
     * @date 2023/9/14 13:43
     */
    private int getTeachplanCount(long courseId,long parentId){
        LambdaQueryWrapper<Teachplan> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Teachplan::getCourseId,courseId);
        queryWrapper.eq(Teachplan::getParentid,parentId);
        Integer count = teachplanMapper.selectCount(queryWrapper);
        return count;
    }


    @Autowired
    private TeachplanMediaMapper teachplanMediaMapper;
    /**
     * @description 获取最新的排序号
     * @param teachPlanId 课程计划id
     * @return
     * @author Jack
     * @date 2023/9/14 20:05
     */
    @Transactional
    @Override
    public void delTeachplan(Long teachPlanId) {
        Teachplan teachplan = teachplanMapper.selectById(teachPlanId);

        //如果不是大章节，则直接删除
        if(teachplan != null && teachplan.getParentid() != 0){
            teachplanMapper.deleteById(teachPlanId);
            teachplanMediaMapper.deleteByTeachplanId(teachPlanId);
        }else{
            int childrenCount = teachplanMapper.selectChildrenCount(teachPlanId);
            if(childrenCount == 0){
                teachplanMapper.deleteById(teachPlanId);
            }else {
                throw new XueChengPlusException("课程计划信息还有子级信息，无法操作");
            }
        }
    }

    /**
     *
     * 调整教学计划顺序
     *
     * */
    @Transactional
    @Override
    public void moveTeachplan(String type, Long teachPlanId) {
        //获取当前需要调整位置的教学计划
        Teachplan teachplan = teachplanMapper.selectById(teachPlanId);
        int count = getTeachplanCount(teachplan.getCourseId(),teachplan.getParentid());
        int index = teachplan.getOrderby();

        if(StringUtils.equals(type,"movedown") == true){

            //符合条件，可以下移
            if(index < count){

                //查询同级章节/小节，如果并排序后方便修改
                QueryWrapper<Teachplan> queryWrapper = new QueryWrapper();
                queryWrapper.eq("course_id",teachplan.getCourseId());
                queryWrapper.eq("parentid",teachplan.getParentid());
                queryWrapper.orderByAsc("orderby");
                List<Teachplan> teachplanList = teachplanMapper.selectList(queryWrapper);

                teachplanList.get(index - 1).setOrderby(teachplanList.get(index).getOrderby());
                teachplanList.get(index).setOrderby(index);
                teachplanMapper.updateById(teachplanList.get(index - 1));
                teachplanMapper.updateById(teachplanList.get(index));
            }

        }else {

            //符合条件，可以上移
            if(1 < teachplan.getOrderby() && count >= 2){

                //查询同级章节/小节，如果并排序后方便修改
                QueryWrapper<Teachplan> queryWrapper = new QueryWrapper();
                queryWrapper.eq("course_id",teachplan.getCourseId());
                queryWrapper.eq("parentid",teachplan.getParentid());
                queryWrapper.orderByAsc("orderby");
                List<Teachplan> teachplanList = teachplanMapper.selectList(queryWrapper);

                //调换排序字段值
                teachplanList.get(index - 1).setOrderby(teachplanList.get(index - 2).getOrderby());
                teachplanList.get(index - 2).setOrderby(index);
                teachplanMapper.updateById(teachplanList.get(index - 1));
                teachplanMapper.updateById(teachplanList.get(index - 2));
            }

        }
    }


    public int delTeachplanByCourseId(Long courseId){
        QueryWrapper<Teachplan> queryWrapper =  new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
        return teachplanMapper.delete(queryWrapper);
    }

    public int delTeachplanMediaByCourseId(Long courseId){
        QueryWrapper<TeachplanMedia> queryWrapper =  new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
        return teachplanMediaMapper.delete(queryWrapper);
    }


    @Transactional
    @Override
    public TeachplanMedia associationMedia(BindTeachplanMediaDto bindTeachplanMediaDto) {
        //教学计划id
        Long teachplanId = bindTeachplanMediaDto.getTeachplanId();
        Teachplan teachplan = teachplanMapper.selectById(teachplanId);
        if(teachplan==null){
            XueChengPlusException.cast("教学计划不存在");
        }
        Integer grade = teachplan.getGrade();
        if(grade!=2){
            XueChengPlusException.cast("只允许第二级教学计划绑定媒资文件");
        }
        //课程id
        Long courseId = teachplan.getCourseId();

        //先删除原来该教学计划绑定的媒资
        teachplanMediaMapper.delete(new LambdaQueryWrapper<TeachplanMedia>().eq(TeachplanMedia::getTeachplanId,teachplanId));

        //再添加教学计划与媒资的绑定关系
        TeachplanMedia teachplanMedia = new TeachplanMedia();
        teachplanMedia.setCourseId(courseId);
        teachplanMedia.setTeachplanId(teachplanId);
        teachplanMedia.setMediaFilename(bindTeachplanMediaDto.getFileName());
        teachplanMedia.setMediaId(bindTeachplanMediaDto.getMediaId());
        teachplanMedia.setCreateDate(LocalDateTime.now());
        teachplanMediaMapper.insert(teachplanMedia);
        return teachplanMedia;
    }

    @Override
    public void disassociationMedia(String teachplanId, String media_id) {

        QueryWrapper<TeachplanMedia> queryWrapper =  new QueryWrapper<>();
        queryWrapper.eq("media_id",media_id);
        queryWrapper.eq("teachplan_id",teachplanId);
        //删除该教学计划绑定的媒资
        teachplanMediaMapper.delete(queryWrapper);
    }
}
