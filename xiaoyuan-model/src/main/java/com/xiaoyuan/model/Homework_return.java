package com.xiaoyuan.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tl_school_homework_return")
public class Homework_return {
    @Id
    @Column(name = "homework_return_id")
    private Integer homeworkReturnId;

    @Column(name = "homework_id")
    private Integer homeworkId;

    private String subject;

    private String studentname;

    private String gurdian;

    private String files;

    private String content;

    private String status;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * @return homework_return_id
     */
    public Integer getHomeworkReturnId() {
        return homeworkReturnId;
    }

    /**
     * @param homeworkReturnId
     */
    public void setHomeworkReturnId(Integer homeworkReturnId) {
        this.homeworkReturnId = homeworkReturnId;
    }

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