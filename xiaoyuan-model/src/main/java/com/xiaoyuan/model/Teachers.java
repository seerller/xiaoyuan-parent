package com.xiaoyuan.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tl_school_teachers")
public class Teachers {
    @Id
    @Column(name = "teacher_ID")
    private Integer teacherId;

    @Column(name = "student_ID")
    private Integer studentId;

    private String teachername;

    private String schoolname;

    private String course;

    private String post;

    @Column(name = "teacher_mobilephone")
    private Integer teacherMobilephone;

    @Column(name = "teacher_telephone")
    private Integer teacherTelephone;

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
     * @return teachername
     */
    public String getTeachername() {
        return teachername;
    }

    /**
     * @param teachername
     */
    public void setTeachername(String teachername) {
        this.teachername = teachername == null ? null : teachername.trim();
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
     * @return course
     */
    public String getCourse() {
        return course;
    }

    /**
     * @param course
     */
    public void setCourse(String course) {
        this.course = course == null ? null : course.trim();
    }

    /**
     * @return post
     */
    public String getPost() {
        return post;
    }

    /**
     * @param post
     */
    public void setPost(String post) {
        this.post = post == null ? null : post.trim();
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
     * @return teacher_telephone
     */
    public Integer getTeacherTelephone() {
        return teacherTelephone;
    }

    /**
     * @param teacherTelephone
     */
    public void setTeacherTelephone(Integer teacherTelephone) {
        this.teacherTelephone = teacherTelephone;
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