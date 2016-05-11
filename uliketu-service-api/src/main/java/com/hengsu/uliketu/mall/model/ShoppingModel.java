package com.hengsu.uliketu.mall.model;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;

import java.util.Date;

@MapClass("com.hengsu.uliketu.mall.entity.Shopping")
public class ShoppingModel {

    public static final int STATUS_UNFINISH = 0;
    public static final int STATUS_WATTING_LOTTERY = 1;
    public static final int STATUS_FINISHED = 2;

    private Long id;
    private Long goodsId;
    private Integer periods;
    private Long remainNum;
    private Date finishTime;
    private Integer status;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getGoodsId() {
        return this.goodsId;
    }

    public void setPeriods(Integer periods) {
        this.periods = periods;
    }

    public Integer getPeriods() {
        return this.periods;
    }

    public void setRemainNum(Long remainNum) {
        this.remainNum = remainNum;
    }

    public Long getRemainNum() {
        return this.remainNum;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Date getFinishTime() {
        return this.finishTime;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }
}