package com.xiaoyuan.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tl_school_news")
public class News {
    @Id
    @Column(name = "news_id")
    private Integer newsId;

    @Column(name = "school_ID")
    private Integer schoolId;

    private String title;

    private String subtitle;

    private String content;

    @Column(name = "create_time")
    private Date createTime;

    private byte[] pictuer;

    /**
     * @return news_id
     */
    public Integer getNewsId() {
        return newsId;
    }

    /**
     * @param newsId
     */
    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
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
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * @return subtitle
     */
    public String getSubtitle() {
        return subtitle;
    }

    /**
     * @param subtitle
     */
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle == null ? null : subtitle.trim();
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
     * @return pictuer
     */
    public byte[] getPictuer() {
        return pictuer;
    }

    /**
     * @param pictuer
     */
    public void setPictuer(byte[] pictuer) {
        this.pictuer = pictuer;
    }
}