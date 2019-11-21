package com.xiaoyuan.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tl_school_attence")
public class Attence {
    @Id
    @Column(name = "attence_id")
    private Integer attenceId;

    @Column(name = "student_ID")
    private Integer studentId;

    private String studentname;

    private String status;

    private Integer times;

    @Column(name = "break")
    private String leave;

    private String review;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * @return attence_id
     */
    public Integer getAttenceId() {
        return attenceId;
    }

    /**
     * @param attenceId
     */
    public void setAttenceId(Integer attenceId) {
        this.attenceId = attenceId;
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
     * @return times
     */
    public Integer getTimes() {
        return times;
    }

    /**
     * @param times
     */
    public void setTimes(Integer times) {
        this.times = times;
    }

    /**
     * @return leave
     */
    public String getLeave() {
        return leave;
    }

    /**
     * @param leave
     */
    public void setLeave(String leave) {
        this.leave = leave == null ? null : leave.trim();
    }

    /**
     * @return review
     */
    public String getReview() {
        return review;
    }

    /**
     * @param review
     */
    public void setReview(String review) {
        this.review = review == null ? null : review.trim();
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