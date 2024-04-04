package com.example.entity;

import java.io.Serializable;

/**
 * 教室分类表
*/
public class Type implements Serializable {
    private static final long serialVersionUID = 1L;

    /** ID */
    private Integer id;
    private String name;
    private String descr;
    private Integer classroomadminId;
    private String classroomadminName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public Integer getClassroomadminId() {
        return classroomadminId;
    }

    public void setClassroomadminId(Integer classroomadminId) {
        this.classroomadminId = classroomadminId;
    }

    public String getClassroomadminName() {
        return classroomadminName;
    }

    public void setClassroomadminName(String classroomadminName) {
        this.classroomadminName = classroomadminName;
    }
}