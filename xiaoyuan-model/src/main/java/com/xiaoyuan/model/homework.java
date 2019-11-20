package com.xiaoyuan.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tl_school_homework")
public class homework {
    @Id
    @Column(name = "homework_id")
    private Integer homeworkId;

    @Column(name = "student_ID")
    private Integer studentId;

    private String subject;

    private String content;

    private String teachername;

    private String classname;

    @Column(name = "end_time")
    private Date endTime;

    private String files;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * @return homework_id
     */
    public Integer getHomeworkId() {
        return homeworkId;
    }

    /**
     * @param homeworkId
     */
    public void setHomeworkId(Integer homeworkId) {
        this.homeworkId = homeworkId;
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
     * @return subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject
     */
    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
    }

    /**
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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
     * @return end_time
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * @param endTime
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * @return files
     */
    public String getFiles() {
        return files;
    }

    /**
     * @param files
     */
    public void setFiles(String files) {
        this.files = files == null ? null : files.trim();
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