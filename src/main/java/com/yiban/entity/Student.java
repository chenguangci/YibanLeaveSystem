package com.yiban.entity;

import java.io.Serializable;

public class Student implements Serializable {

    private static final long serialVersionUID = 3210359254553565346L;
    //易班ID
    private String yibanId;
    //学生学号
    private String studentId;
    //学生姓名
    private String name;
    //学生院系信息
    private String department;
    //学生班级信息
    private String className;

    public String getJudgeIsNewClassId() {
        return judgeIsNewClassId;
    }

    public void setJudgeIsNewClassId(String judgeIsNewClassId) {
        this.judgeIsNewClassId = judgeIsNewClassId;
    }

    private String judgeIsNewClassId;
    
    public Student()
    {
    	super();
    }
   
    public Student(String yibanId, String studentId, String name,
			String department, String className) {
		super();
		this.yibanId = yibanId;
		this.studentId = studentId;
		this.name = name;
		this.department = department;
		this.className = className;
	}

	@Override
    public String toString() {
        return "Student{" +
                "yibanId='" + yibanId + '\'' +
                ", studentId='" + studentId + '\'' +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", className='" + className + '\'' +
                '}';
    }

    public String getYibanId() {
        return yibanId;
    }

    public void setYibanId(String yibanId) {
        this.yibanId = yibanId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
