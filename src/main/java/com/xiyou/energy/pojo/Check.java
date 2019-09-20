package com.xiyou.energy.pojo;

import java.util.Date;

public class Check {
    private Integer id;

    private Integer eid;

    private Date createtime;

    private Integer uid;

    private Byte percent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Byte getPercent() {
        return percent;
    }

    public void setPercent(Byte percent) {
        this.percent = percent;
    }
}