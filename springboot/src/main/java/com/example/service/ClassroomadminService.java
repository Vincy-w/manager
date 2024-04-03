package com.example.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.Constants;
import com.example.common.enums.ResultCodeEnum;
import com.example.common.enums.RoleEnum;
import com.example.entity.Account;
import com.example.entity.Classroomadmin;
import com.example.exception.CustomException;
import com.example.mapper.ClassroomadminMapper;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 教室管理员业务处理
 **/
@Service
public class ClassroomadminService {

    @Resource
    private ClassroomadminMapper classroomadminMapper;

    /**
     * 新增
     */
    public void add(Classroomadmin classroomadmin) {
        Classroomadmin dbClassroomadmin = classroomadminMapper.selectByUsername(classroomadmin.getUsername());
        if (ObjectUtil.isNotNull(dbClassroomadmin)) {
            throw new CustomException(ResultCodeEnum.USER_EXIST_ERROR);
        }
        if (ObjectUtil.isEmpty(classroomadmin.getPassword())) {
            classroomadmin.setPassword(Constants.USER_DEFAULT_PASSWORD);
        }
        if (ObjectUtil.isEmpty(classroomadmin.getName())) {
            classroomadmin.setName(classroomadmin.getUsername());
        }
        classroomadmin.setRole(RoleEnum.CLASSROOMADMIN.name());
        classroomadminMapper.insert(classroomadmin);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        classroomadminMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            classroomadminMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Classroomadmin classroomadmin) {
        classroomadminMapper.updateById(classroomadmin);
    }

    /**
     * 根据ID查询
     */
    public Classroomadmin selectById(Integer id) {
        return classroomadminMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Classroomadmin> selectAll(Classroomadmin classroomadmin) {
        return classroomadminMapper.selectAll(classroomadmin);
    }

    /**
     * 分页查询
     */
    public PageInfo<Classroomadmin> selectPage(Classroomadmin classroomadmin, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Classroomadmin> list = classroomadminMapper.selectAll(classroomadmin);
        return PageInfo.of(list);
    }

    /**
     * 登录
     */
    public Account login(Account account) {
        Account dbClassroomadmin = classroomadminMapper.selectByUsername(account.getUsername());
        //用户不存在
        if (ObjectUtil.isNull(dbClassroomadmin)) {
            throw new CustomException(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }
        //账号或密码错误
        if (!account.getPassword().equals(dbClassroomadmin.getPassword())) {
            throw new CustomException(ResultCodeEnum.USER_ACCOUNT_ERROR);
        }
        // 生成token
        String tokenData = dbClassroomadmin.getId() + "-" + RoleEnum.CLASSROOMADMIN.name();
        String token = TokenUtils.createToken(tokenData, dbClassroomadmin.getPassword());
        dbClassroomadmin.setToken(token);
        return dbClassroomadmin;
    }


    /**
     * 修改密码
     */
    public void updatePassword(Account account) {
        Classroomadmin dbClassroomadmin = classroomadminMapper.selectByUsername(account.getUsername());
        if (ObjectUtil.isNull(dbClassroomadmin)) {
            throw new CustomException(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }
        if (!account.getPassword().equals(dbClassroomadmin.getPassword())) {
            throw new CustomException(ResultCodeEnum.PARAM_PASSWORD_ERROR);
        }
        dbClassroomadmin.setPassword(account.getNewPassword());
        classroomadminMapper.updateById(dbClassroomadmin);
    }

}