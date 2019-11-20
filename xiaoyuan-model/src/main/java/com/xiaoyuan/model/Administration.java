package com.xiaoyuan.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tl_school_educational_administration")
public class Administration {
    @Id
    @Column(name = "cource_ID")
    private Integer courceId;

    @Column(name = "class_id")
    private Integer classId;

    private String cource;

    private String teachername;

    private String classname;

    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "end_time")
    private Date endTime;

    private String schoolname;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * @return cource_ID
     */
    public Integer getCourceId() {
        return courceId;
    }

    /**
     * @param courceId
     */
    public void setCourceId(Integer courceId) {
        this.courceId = courceId;
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
     * @return cource
     */
    public String getCource() {
        return cource;
    }

    /**
     * @param cource
     */
    public void setCource(String cource) {
        this.cource = cource == null ? null : cource.trim();
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
     * @return start_time
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * @param startTime
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
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