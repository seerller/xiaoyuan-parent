package com.xiaoyuan.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tl_school_staff")
public class Staff {
    @Id
    @Column(name = "teacher_ID")
    private Integer teacherId;

    @Column(name = "school_ID")
    private Integer schoolId;

    private String schoolname;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "teacher_mobilephone")
    private Integer teacherMobilephone;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "teacher_adress")
    private String teacherAdress;

    /**
     * @return teacher_ID
     */
    public Integer getTeacherId() {
        return teacherId;
    }

    /**
     * @param teacherId
     */
    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    /**
     * @return school_ID
     */
    public Integer getSchoolId() {
        return schoolId;
    }

    /**
     * @param schoolId
     */
    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
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
     * @return user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return teacher_mobilephone
     */
    public Integer getTeacherMobilephone() {
        return teacherMobilephone;
    }

    /**
     * @param teacherMobilephone
     */
    public void setTeacherMobilephone(Integer teacherMobilephone) {
        this.teacherMobilephone = teacherMobilephone;
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

    /**
     * @return teacher_adress
     */
    public String getTeacherAdress() {
        return teacherAdress;
    }

    /**
     * @param teacherAdress
     */
    public void setTeacherAdress(String teacherAdress) {
        this.teacherAdress = teacherAdress == null ? null : teacherAdress.trim();
    }
}