package com.xiyou.energy.pojo;

import java.util.Date;

public class Jiancha {
    private Integer id;

    private Integer eid;

    private Date createtime;

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

    public Byte getPercent() {
        return percent;
    }

    public void setPercent(Byte percent) {
        this.percent = percent;
    }
}