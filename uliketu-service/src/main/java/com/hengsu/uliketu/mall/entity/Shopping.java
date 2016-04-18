package com.hengsu.uliketu.mall.entity;

import java.util.Date;

public class Shopping {
    private Long id;

    private Long goodsId;

    private Integer periods;

    private Long remainNum;

    private Date finishTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getPeriods() {
        return periods;
    }

    public void setPeriods(Integer periods) {
        this.periods = periods;
    }

    public Long getRemainNum() {
        return remainNum;
    }

    public void setRemainNum(Long remainNum) {
        this.remainNum = remainNum;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }
}