package com.example.controller;

import com.example.common.Result;
import com.example.common.enums.StatusEnum;
import com.example.entity.Classroom;
import com.example.service.ClassroomService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
 * 教室信息表前端操作接口
 **/
@RestController
@RequestMapping("/classroom")
public class ClassroomController {

    @Resource
    private ClassroomService classroomService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Classroom classroom) {
        classroomService.add(classroom);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        classroomService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        classroomService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Classroom classroom) {
        classroomService.updateById(classroom);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Classroom classroom = classroomService.selectById(id);
        return Result.success(classroom);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Classroom classroom ) {
        List<Classroom> list = classroomService.selectAll(classroom);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Classroom classroom,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Classroom> page = classroomService.selectPage(classroom, pageNum, pageSize);
        return Result.success(page);
    }

    @GetMapping("/pie")
    public Result pie(){
        Map<String, Object> resultMap=new HashMap<>();
        List<Map<String,Object>> resultList = new ArrayList<>();
        //处理空闲/使用两种状态数据，封装进resultList
        List<Classroom> classrooms = classroomService.selectAll(new Classroom());
        long okCount = classrooms.stream().filter(x -> StatusEnum.OK.status.equals(x.getStatus())).count();
        long noCount = classrooms.stream().filter(x -> StatusEnum.NO.status.equals(x.getStatus())).count();

        Map<String,Object> okMap = new HashMap<>();
        okMap.put("name",StatusEnum.OK.status);
        okMap.put("value",okCount);
        resultList.add(okMap);

        Map<String,Object> noMap = new HashMap<>();
        noMap.put("name",StatusEnum.NO.status);
        noMap.put("value",noCount);
        resultList.add(noMap);

        resultMap.put("text","教室状态统计");
        resultMap.put("subtext","统计维度：状态");
        resultMap.put("name","数量");
        resultMap.put("data",resultList);
        return Result.success(resultMap);
    }
}