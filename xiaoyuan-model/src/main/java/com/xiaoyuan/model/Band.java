package com.xiaoyuan.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tl_school_band")
public class Band {
    @Id
    @Column(name = "band_ID")
    private Integer bandId;

    @Column(name = "student_ID")
    private Integer studentId;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * @return band_ID
     */
    public Integer getBandId() {
        return bandId;
    }

    /**
     * @param bandId
     */
    public void setBandId(Integer bandId) {
        this.bandId = bandId;
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