package com.example.service;

import cn.hutool.core.date.DateUtil;
import com.example.common.enums.ReserveEnum;
import com.example.common.enums.RoleEnum;
import com.example.common.enums.StatusEnum;
import com.example.entity.Account;
import com.example.entity.Classroom;
import com.example.entity.Reserve;
import com.example.mapper.ClassroomMapper;
import com.example.mapper.ReserveMapper;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 预约信息表业务处理
 **/
@Service
public class ReserveService {

    @Resource
    private ReserveMapper reserveMapper;
    @Resource
    private ClassroomMapper classroomMapper;

    /**
     * 新增
     */
    public void add(Reserve reserve) {
        reserve.setTime(DateUtil.now());
        reserveMapper.insert(reserve);

        //将对应教室状态变为使用中
        Classroom classroom = classroomMapper.selectById((reserve.getClassroomId()));
        classroom.setStatus(StatusEnum.NO.status);
        classroomMapper.updateById(classroom);
    }

    /**
     * 取消预约
     */
    public void deleteById(Integer id) {
        Reserve reserve = reserveMapper.selectById(id);
        reserveMapper.deleteById(id);
        //还原状态
        Classroom classroom = classroomMapper.selectById(reserve.getClassroomId());
        classroom.setStatus(StatusEnum.OK.status);
        classroomMapper.updateById(classroom);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            reserveMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Reserve reserve) {
        if(ReserveEnum.DONE.status.equals(reserve.getDostatus())|| ReserveEnum.NO.status.equals(reserve.getStatus())){
            //还原状态
            Classroom classroom = classroomMapper.selectById(reserve.getClassroomId());
            classroom.setStatus(StatusEnum.OK.status);
            classroomMapper.updateById(classroom);
        }
        reserveMapper.updateById(reserve);
    }

    /**
     * 根据ID查询
     */
    public Reserve selectById(Integer id) {
        return reserveMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Reserve> selectAll(Reserve reserve) {
        return reserveMapper.selectAll(reserve);
    }

    /**
     * 分页查询
     */
    public PageInfo<Reserve> selectPage(Reserve reserve, Integer pageNum, Integer pageSize) {
        Account currentUser = TokenUtils.getCurrentUser();
        if(RoleEnum.CLASSROOMADMIN.name().equals(currentUser.getRole())){
            reserve.setClassroomadminId(currentUser.getId());
        }
        if(RoleEnum.STUDENT.name().equals(currentUser.getRole())){
            reserve.setStudentId(currentUser.getId());
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Reserve> list = reserveMapper.selectAll(reserve);
        return PageInfo.of(list);
    }

}