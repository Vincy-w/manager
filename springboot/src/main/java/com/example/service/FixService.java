package com.example.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.example.common.enums.RoleEnum;
import com.example.entity.Account;
import com.example.entity.Classroom;
import com.example.entity.Fix;
import com.example.entity.Type;
import com.example.mapper.ClassroomMapper;
import com.example.mapper.FixMapper;
import com.example.mapper.TypeMapper;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 报修信息表业务处理
 **/
@Service
public class FixService {

    @Resource
    private FixMapper fixMapper;
    @Resource
    private ClassroomMapper classroomMapper;
    @Resource
    private TypeMapper typeMapper;

    /**
     * 新增
     */
    public void add(Fix fix) {
        fix.setTime(DateUtil.now());
        Classroom classroom = classroomMapper.selectById(fix.getClassroomId());
        if(ObjectUtil.isNotEmpty(classroom) && ObjectUtil.isNotEmpty(classroom.getTypeId())){
            Type type = typeMapper.selectById(classroom.getTypeId());
            if(ObjectUtil.isNotEmpty(type)){
                fix.setTypeId(type.getId());
            }
        }
        fixMapper.insert(fix);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        fixMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            fixMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Fix fix) {
        fix.setFixtime(DateUtil.now());
        fixMapper.updateById(fix);
    }

    /**
     * 根据ID查询
     */
    public Fix selectById(Integer id) {
        return fixMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Fix> selectAll(Fix fix) {
        return fixMapper.selectAll(fix);
    }

    /**
     * 分页查询
     */
    public PageInfo<Fix> selectPage(Fix fix, Integer pageNum, Integer pageSize) {
        Account currentUser = TokenUtils.getCurrentUser();
        if(RoleEnum.STUDENT.name().equals(currentUser.getRole())){
            fix.setStudentId(currentUser.getId());
        }
        if(RoleEnum.CLASSROOMADMIN.name().equals(currentUser.getRole())){
            fix.setClassroomadminId(currentUser.getId());
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Fix> list = fixMapper.selectAll(fix);
        return PageInfo.of(list);
    }

}