package com.tunan.content.api;

import com.tunan.content.model.dto.BindTeachplanMediaDto;
import com.tunan.content.model.dto.SaveTeachplanDto;
import com.tunan.content.model.dto.TeachplanDto;
import com.tunan.content.service.TeachplanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @projectName: xuecheng-plus
 * @package: com.tunan.content.api
 * @className: TeachplanController
 * @author: Jack
 * @description: 课程计划编辑接口
 * @date: 2023/9/14 10:22
 * @version: 1.0
 */

@Api(value = "课程计划编辑接口",tags = "课程计划编辑接口")
@RestController
public class TeachplanController {

    @Autowired
    TeachplanService teachplanService;

    @ApiOperation("查询课程计划树形结构")
    @ApiImplicitParam(value = "courseId",name = "课程基础Id值",required = true,dataType = "Long",paramType = "path")
    @GetMapping("teachplan/{courseId}/tree-nodes")
    public List<TeachplanDto> getTreeNodes(@PathVariable Long courseId){
        return teachplanService.findTeachplanTree(courseId);
    }

    @ApiOperation("课程计划创建或修改")
    @PostMapping("/teachplan")
    public void saveTeachplan(@RequestBody SaveTeachplanDto teachplan){
        teachplanService.saveTeachplan(teachplan);
    }

    @ApiOperation("根据课程计划id删除章节")
    @ApiImplicitParam(value = "teachPlanId",name = "课程计划id",required = true,dataType = "Long",paramType = "path")
    @DeleteMapping("/teachplan/{teachPlanId}")
    public void saveTeachplan(@PathVariable Long teachPlanId){
        teachplanService.delTeachplan(teachPlanId);
    }

    @ApiOperation("将课程计划向下移动")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "type",name = "调整类型",required = true,dataType = "String",paramType = "path"),
            @ApiImplicitParam(value = "teachPlanId",name = "课程计划id",required = true,dataType = "Long",paramType = "path"),
    })
    @PostMapping("/teachplan/{type}/{teachPlanId}")
    public void move(@PathVariable String type,@PathVariable Long teachPlanId){
        teachplanService.moveTeachplan(type,teachPlanId);
    }


    @ApiOperation(value = "课程计划和媒资信息绑定")
    @PostMapping("/teachplan/association/media")
    void associationMedia(@RequestBody BindTeachplanMediaDto bindTeachplanMediaDto){
        teachplanService.associationMedia(bindTeachplanMediaDto);
    }

    @ApiOperation(value = "课程计划和媒资信息解绑")
    @DeleteMapping("/teachplan/association/media/{teachplanId}/{media_id}")
    public void delassociationMedia(@PathVariable String teachplanId,@PathVariable String media_id ){
        teachplanService.disassociationMedia(teachplanId,media_id);
    }



}
