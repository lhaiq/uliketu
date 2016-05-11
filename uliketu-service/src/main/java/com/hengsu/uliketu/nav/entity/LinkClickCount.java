package com.hengsu.uliketu.nav.entity;

/**
 * Created by haiquanli on 16/4/25.
 */
public class LinkClickCount {
    private Long id;
    private Long userId;
    private Long count;

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
