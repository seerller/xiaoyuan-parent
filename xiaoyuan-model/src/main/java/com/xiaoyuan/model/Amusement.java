package com.xiaoyuan.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tl_school_amusement")
public class Amusement {
    @Id
    @Column(name = "amusemen_id")
    private Integer amusemenId;

    @Column(name = "school_ID")
    private Integer schoolId;

    private String amusementname;

    private String type;

    private String videoname;

    private Date time;

    private String status;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * @return amusemen_id
     */
    public Integer getAmusemenId() {
        return amusemenId;
    }

    /**
     * @param amusemenId
     */
    public void setAmusemenId(Integer amusemenId) {
        this.amusemenId = amusemenId;
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
     * @return amusementname
     */
    public String getAmusementname() {
        return amusementname;
    }

    /**
     * @param amusementname
     */
    public void setAmusementname(String amusementname) {
        this.amusementname = amusementname == null ? null : amusementname.trim();
    }

    /**
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * @return videoname
     */
    public String getVideoname() {
        return videoname;
    }

    /**
     * @param videoname
     */
    public void setVideoname(String videoname) {
        this.videoname = videoname == null ? null : videoname.trim();
    }

    /**
     * @return time
     */
    public Date getTime() {
        return time;
    }

    /**
     * @param time
     */
    public void setTime(Date time) {
        this.time = time;
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