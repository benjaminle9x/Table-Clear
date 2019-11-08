package com.n01216688.testing;

public class DataStructure {
    private String name;
    private String phone;
    private String address;
    private String timestamp;

    public DataStructure() {

    }

    public DataStructure(String name, String phone, String address, String timestamp) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.timestamp = timestamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}




