package com.example.entity;

import java.io.Serializable;

/**
 * 教室分类表
*/
public class Classroom implements Serializable {
    private static final long serialVersionUID = 1L;

    /** ID */
    private Integer id;
    private String name;
    private String descr;
    private String start;
    private String end;
    private String status;
    private Integer typeId;
    private Integer classroomadminId;

    private String typeName;
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

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getClassroomadminId() {
        return classroomadminId;
    }

    public void setClassroomadminId(Integer classroomadminId) {
        this.classroomadminId = classroomadminId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getClassroomadminName() {
        return classroomadminName;
    }

    public void setClassroomadminName(String classroomadminName) {
        this.classroomadminName = classroomadminName;
    }
}