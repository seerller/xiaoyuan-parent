package com.xiaoyuan.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tl_school_students")
public class Students {
    @Id
    @Column(name = "student_ID")
    private Integer studentId;

    @Column(name = "class_id")
    private Integer classId;

    private String studentname;

    private String grade;

    private String classname;

    private String schoolname;

    @Column(name = "student_mobliephone")
    private Integer studentMobliephone;

    private String gurdian;

    @Column(name = "gurdian_mobliephone")
    private Integer gurdianMobliephone;

    @Column(name = "student_telephone")
    private Integer studentTelephone;

    private String status;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * @return student_ID
     */
    public Integer getStudentId() {
        return studentId;
    }

    /**
     * @param studentId
     */
    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    /**
     * @return class_id
     */
    public Integer getClassId() {
        return classId;
    }

    /**
     * @param classId
     */
    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    /**
     * @return studentname
     */
    public String getStudentname() {
        return studentname;
    }

    /**
     * @param studentname
     */
    public void setStudentname(String studentname) {
        this.studentname = studentname == null ? null : studentname.trim();
    }

    /**
     * @return grade
     */
    public String getGrade() {
        return grade;
    }

    /**
     * @param grade
     */
    public void setGrade(String grade) {
        this.grade = grade == null ? null : grade.trim();
    }

    /**
     * @return classname
     */
    public String getClassname() {
        return classname;
    }

    /**
     * @param classname
     */
    public void setClassname(String classname) {
        this.classname = classname == null ? null : classname.trim();
    }

    /**
     * @return schoolname
     */
    public String getSchoolname() {
        return schoolname;
    }

    /**
     * @param schoolname
     */
    public void setSchoolname(String schoolname) {
        this.schoolname = schoolname == null ? null : schoolname.trim();
    }

    /**
     * @return student_mobliephone
     */
    public Integer getStudentMobliephone() {
        return studentMobliephone;
    }

    /**
     * @param studentMobliephone
     */
    public void setStudentMobliephone(Integer studentMobliephone) {
        this.studentMobliephone = studentMobliephone;
    }

    /**
     * @return gurdian
     */
    public String getGurdian() {
        return gurdian;
    }

    /**
     * @param gurdian
     */
    public void setGurdian(String gurdian) {
        this.gurdian = gurdian == null ? null : gurdian.trim();
    }

    /**
     * @return gurdian_mobliephone
     */
    public Integer getGurdianMobliephone() {
        return gurdianMobliephone;
    }

    /**
     * @param gurdianMobliephone
     */
    public void setGurdianMobliephone(Integer gurdianMobliephone) {
        this.gurdianMobliephone = gurdianMobliephone;
    }

    /**
     * @return student_telephone
     */
    public Integer getStudentTelephone() {
        return studentTelephone;
    }

    /**
     * @param studentTelephone
     */
    public void setStudentTelephone(Integer studentTelephone) {
        this.studentTelephone = studentTelephone;
    }

    /**
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}