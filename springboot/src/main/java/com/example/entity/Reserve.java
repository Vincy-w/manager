package com.example.entity;

import java.io.Serializable;

/**
 * 预约信息表
*/
public class Reserve implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Integer id;
    private Integer classroomId;
    private Integer classroomadminId;
    private Integer studentId;
    private String time;
    private String status;
    private String dostatus;

    private String classroomName;
    private String classroomadminName;
    private String studentName;
    private String start;
    private String end;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(Integer classroomId) {
        this.classroomId = classroomId;
    }

    public Integer getClassroomadminId() {
        return classroomadminId;
    }

    public void setClassroomadminId(Integer classroomadminId) {
        this.classroomadminId = classroomadminId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDostatus() {
        return dostatus;
    }

    public void setDostatus(String dostatus) {
        this.dostatus = dostatus;
    }

    public String getClassroomName() {
        return classroomName;
    }

    public void setClassroomName(String classroomName) {
        this.classroomName = classroomName;
    }

    public String getClassroomadminName() {
        return classroomadminName;
    }

    public void setClassroomadminName(String classroomadminName) {
        this.classroomadminName = classroomadminName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
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
}