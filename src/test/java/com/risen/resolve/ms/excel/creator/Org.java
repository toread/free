package com.risen.resolve.ms.excel.creator;

import java.io.Serializable;

/**
 * Created by Administrator on 2014-11-14.
 */
public class Org implements Serializable{
    public static final long serialVersionUID = -3286564461647015367L;
    private String name;
    private String ord;
    private String parent;
    private String tel;
    private String fix;
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrd() {
        return ord;
    }

    public void setOrd(String ord) {
        this.ord = ord;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFix() {
        return fix;
    }

    public void setFix(String fix) {
        this.fix = fix;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
