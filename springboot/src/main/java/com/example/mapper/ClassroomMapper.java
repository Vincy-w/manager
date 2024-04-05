package com.example.mapper;

import com.example.entity.Classroom;

import java.util.List;

/**
 * 操作Classroom相关数据接口
*/
public interface ClassroomMapper {

    /**
      * 新增
    */
    int insert(Classroom classroom);

    /**
      * 删除
    */
    int deleteById(Integer id);

    /**
      * 修改
    */
    int updateById(Classroom classroom);

    /**
      * 根据ID查询
    */
    Classroom selectById(Integer id);

    /**
      * 查询所有
    */
    List<Classroom> selectAll(Classroom classroom);

}