package com.yiban.entity;

import java.io.Serializable;

/**
 * 班级表实体对象
 */
public class ClassTable implements Serializable {

    private static final long serialVersionUID = -2890008160294451071L;
    //班级ID
    private String classId;
    //班级对应辅导员的易班ID
    private String teacherYibanId;
    //班级班长的ID
    private String monitor;
    //班主任的ID
    private  String deanYiban_id;

    public ClassTable(String classId, String teacherYibanId, String monitor, String deanYiban_id) {
		super();
		this.classId = classId;
		this.teacherYibanId = teacherYibanId;
		this.monitor = monitor;
		this.deanYiban_id=deanYiban_id;
	}



	@Override
    public String toString() {
        return "ClassTable{" +
                "classId='" + classId + '\'' +
                ", teacherYibanId='" + teacherYibanId + '\'' +
                ", monitor='" + monitor + '\'' +
                ", deanYiban_id='" + deanYiban_id + '\'' +
                '}';
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getTeacherYibanId() {
        return teacherYibanId;
    }

    public void setTeacherYibanId(String teacherYibanId) {
        this.teacherYibanId = teacherYibanId;
    }

    public String getMonitor() {
        return monitor;
    }

    public void setMonitor(String monitor) {
        this.monitor = monitor;
    }

    public String getDeanYiban_id() {
        return deanYiban_id;
    }

    public void setDeanYiban_id(String deanYiban_id) {
        this.deanYiban_id = deanYiban_id;
    }
}
