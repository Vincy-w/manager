package com.example.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.enums.ResultCodeEnum;
import com.example.common.enums.RoleEnum;
import com.example.common.enums.StatusEnum;
import com.example.entity.Account;
import com.example.entity.Classroom;
import com.example.entity.Type;
import com.example.exception.CustomException;
import com.example.mapper.ClassroomMapper;
import com.example.mapper.ClassroomadminMapper;
import com.example.mapper.TypeMapper;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 教室信息表业务处理
 **/
@Service
public class ClassroomService {

    @Resource
    private ClassroomMapper classroomMapper;
    @Resource
    private TypeMapper typeMapper;

    /**
     * 新增
     */
    public void add(Classroom classroom) {
        //查询该教室管理员信息
        Type type = typeMapper.selectById(classroom.getTypeId());
        if(ObjectUtil.isEmpty(type)){
            throw new CustomException(ResultCodeEnum.TYPE_NO_ERROR);
        }
        if(ObjectUtil.isEmpty(type.getClassroomadminId())){
            throw new CustomException(ResultCodeEnum.TYPE_CLASSROOMADMIN_NO_ERROR);
        }
        classroom.setClassroomadminId(type.getClassroomadminId());
        classroom.setStatus(StatusEnum.OK.status);
        classroomMapper.insert(classroom);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        classroomMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            classroomMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Classroom classroom) {
        classroomMapper.updateById(classroom);
    }

    /**
     * 根据ID查询
     */
    public Classroom selectById(Integer id) {
        return classroomMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Classroom> selectAll(Classroom classroom) {
        return classroomMapper.selectAll(classroom);
    }

    /**
     * 分页查询
     */
    public PageInfo<Classroom> selectPage(Classroom classroom, Integer pageNum, Integer pageSize) {
        Account currentUser = TokenUtils.getCurrentUser();
        if(RoleEnum.CLASSROOMADMIN.name().equals(currentUser.getRole())){
            classroom.setClassroomadminId(currentUser.getId());
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Classroom> list = classroomMapper.selectAll(classroom);
        return PageInfo.of(list);
    }

}