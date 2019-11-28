package com.n01216688.testing;

public class WaitListStructure {
    private String cname;
    private String cphone;
    private String csize;
    private String rname;
    private String ctime;
    private String ctable;

    public WaitListStructure(String cname, String cphone, String csize, String rname, String ctime, String ctable) {
        this.cname = cname;
        this.cphone = cphone;
        this.csize = csize;
        this.rname = rname;
        this.ctime = ctime;
        this.ctable = ctable;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCphone() {
        return cphone;
    }

    public void setCphone(String cphone) {
        this.cphone = cphone;
    }

    public String getCsize() {
        return csize;
    }

    public void setCsize(String csize) {
        this.csize = csize;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getCtable() {
        return ctable;
    }

    public void setCtable(String ctable) {
        this.ctable = ctable;
    }
}
