package com.n01216688.testing;

public class WaitListStructure {
    private String cName;
    private String cPhone;
    private int cSize;
    private String rName;
    private String cTime;
    private String tNo;

    public WaitListStructure(String cName, String cPhone, int cSize, String rName, String cTime, String tNo) {
        this.cName = cName;
        this.cPhone = cPhone;
        this.cSize = cSize;
        this.rName = rName;
        this.cTime = cTime;
        this.tNo = tNo;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcPhone() {
        return cPhone;
    }

    public void setcPhone(String cPhone) {
        this.cPhone = cPhone;
    }

    public int getcSize() {
        return cSize;
    }

    public void setcSize(int cSize) {
        this.cSize = cSize;
    }

    public String getrName() {
        return rName;
    }

    public void setrName(String rName) {
        this.rName = rName;
    }

    public String getcTime() {
        return cTime;
    }

    public void setcTime(String cTime) {
        this.cTime = cTime;
    }

    public String gettNo() {
        return tNo;
    }

    public void settNo(String tNo) {
        this.tNo = tNo;
    }
}
