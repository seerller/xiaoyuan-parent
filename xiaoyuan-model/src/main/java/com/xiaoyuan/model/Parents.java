package com.xiaoyuan.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tl_school_parent")
public class Parents {
    @Id
    @Column(name = "parent_id")
    private Integer parentId;

    @Column(name = "student_ID")
    private Integer studentId;

    private String parentname;

    private String sex;

    private String profession;

    private Integer income;

    private Integer age;

    private String taglib;

    @Column(name = "create_time")
    private Date createTime;

    private String adress;

    /**
     * @return parent_id
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * @param parentId
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
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
     * @return parentname
     */
    public String getParentname() {
        return parentname;
    }

    /**
     * @param parentname
     */
    public void setParentname(String parentname) {
        this.parentname = parentname == null ? null : parentname.trim();
    }

    /**
     * @return sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * @param sex
     */
    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    /**
     * @return profession
     */
    public String getProfession() {
        return profession;
    }

    /**
     * @param profession
     */
    public void setProfession(String profession) {
        this.profession = profession == null ? null : profession.trim();
    }

    /**
     * @return income
     */
    public Integer getIncome() {
        return income;
    }

    /**
     * @param income
     */
    public void setIncome(Integer income) {
        this.income = income;
    }

    /**
     * @return age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * @param age
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * @return taglib
     */
    public String getTaglib() {
        return taglib;
    }

    /**
     * @param taglib
     */
    public void setTaglib(String taglib) {
        this.taglib = taglib == null ? null : taglib.trim();
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
     * @return adress
     */
    public String getAdress() {
        return adress;
    }

    /**
     * @param adress
     */
    public void setAdress(String adress) {
        this.adress = adress == null ? null : adress.trim();
    }
}