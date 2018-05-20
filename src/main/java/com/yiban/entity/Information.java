package com.yiban.entity;

import java.io.Serializable;

public class Information implements Serializable {

    private static final long serialVersionUID = 9193842223814904244L;
    //编号
    private long id;
    //学号id
    private String studentId;
    //请假开始时间
    private String beginTime;
    //请假结束时间
    private String endTime;
    //请假节数
    private int number;
    //请假原因
    private String reason;
    //联系方式
    private String phone;
    //请假状态：（-1：拒绝，0：待审核，1：已同意未销假，2：已销假）
    private Integer status=0;
    //文件路径
    private String filePath;
    //验证码
    private String code;
    //学生实体
    private Student student;

    public Information(){
        super();
    }
    
  

    public Information(long id, int status) 
    {
		super();
		this.id = id;
		this.status = status;
	}



	public Information(String studentId, String beginTime, String endTime,
			int number, String reason, String phone) {
		super();
		this.studentId = studentId;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.number = number;
		this.reason = reason;
		this.phone = phone;
	}



	/*@Override
    public String toString() {
        return "Information{" +
                "id=" + id +
                ", studentId='" + studentId + '\'' +
                ", beginTime='" + beginTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", number=" + number +
                ", reason='" + reason + '\'' +
                ", phone='" + phone + '\'' +
                ", status=" + status +
                ", filePath='" + filePath + '\'' +
                ", code='" + code + '\'' +
                ", student='" + student.toString() + '\'' +
                '}';
    }*/

    @Override
	public String toString() {
		return "Information [id=" + id + ", studentId=" + studentId
				+ ", beginTime=" + beginTime + ", endTime=" + endTime
				+ ", number=" + number + ", reason=" + reason + ", phone="
				+ phone + ", status=" + status + ", filePath=" + filePath
				+ ", code=" + code + "]";
	}



	public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
