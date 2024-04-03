package com.example.mapper;

import com.example.entity.Classroomadmin;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 操作classroomadmin相关数据接口
*/
public interface ClassroomadminMapper {

    /**
      * 新增
    */
    int insert(Classroomadmin classroomadmin);

    /**
      * 删除
    */
    int deleteById(Integer id);

    /**
      * 修改
    */
    int updateById(Classroomadmin classroomadmin);

    /**
      * 根据ID查询
    */
    Classroomadmin selectById(Integer id);

    /**
      * 查询所有
    */
    List<Classroomadmin> selectAll(Classroomadmin classroomadmin);

    @Select("select * from classroomadmin where username = #{username}")
    Classroomadmin selectByUsername(String username);
}