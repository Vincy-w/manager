package com.example.controller;

import com.example.common.Result;
import com.example.entity.Classroomadmin;
import com.example.service.ClassroomadminService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 教室管理员前端操作接口
 **/
@RestController
@RequestMapping("/classroomadmin")
public class ClassroomadminController {

    @Resource
    private ClassroomadminService classroomadminService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Classroomadmin classroomadmin) {
        classroomadminService.add(classroomadmin);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        classroomadminService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        classroomadminService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Classroomadmin classroomadmin) {
        classroomadminService.updateById(classroomadmin);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Classroomadmin classroomadmin = classroomadminService.selectById(id);
        return Result.success(classroomadmin);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Classroomadmin classroomadmin ) {
        List<Classroomadmin> list = classroomadminService.selectAll(classroomadmin);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Classroomadmin classroomadmin,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Classroomadmin> page = classroomadminService.selectPage(classroomadmin, pageNum, pageSize);
        return Result.success(page);
    }

}