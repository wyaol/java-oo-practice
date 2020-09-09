package com.twu.entity;

public class HotSearch {
    private Integer id;
    private String desc;
    private Integer hotValue;
    private Boolean isSuper;

    public HotSearch(Integer id, String desc, Integer hotValue, Boolean isSuper) {
        this.id = id;
        this.desc = desc;
        this.hotValue = hotValue;
        this.isSuper = isSuper;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getHotValue() {
        return hotValue;
    }

    public void setHotValue(Integer hotValue) {
        this.hotValue = hotValue;
    }

    public Boolean getIsSuper() {
        return isSuper;
    }

    public void setIsSuper(Boolean aSuper) {
        isSuper = aSuper;
    }

    public void vote(Integer voteNumber) {
        this.hotValue += (this.isSuper ? voteNumber*2 : voteNumber);
    }
}
