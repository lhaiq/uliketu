package com.hengsu.uliketu.mall.entity;

import java.util.Date;

public class Goods {
    private Long id;

    private Long categoryId;

    private String name;

    private String image;

    private Long num;

    private Integer expenseBy;

    private String keywords;

    private Integer type;

    private Integer periods;

    private Integer excutePeriods;

    private Integer status;

    private Date addTime;

    private Integer sort;

    private Long remainNum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public Integer getExpenseBy() {
        return expenseBy;
    }

    public void setExpenseBy(Integer expenseBy) {
        this.expenseBy = expenseBy;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPeriods() {
        return periods;
    }

    public void setPeriods(Integer periods) {
        this.periods = periods;
    }

    public Integer getExcutePeriods() {
        return excutePeriods;
    }

    public void setExcutePeriods(Integer excutePeriods) {
        this.excutePeriods = excutePeriods;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public void setRemainNum(Long remainNum) {
        this.remainNum = remainNum;
    }

    public Long getRemainNum() {
        return remainNum;
    }
}