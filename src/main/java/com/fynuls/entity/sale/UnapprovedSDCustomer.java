package com.fynuls.entity.sale;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="SD_UNAPPROVED_CUSTOMER")
public class UnapprovedSDCustomer {
    @Id
    @Column(name="ID")
    private int id;

    @Column(name="ADDRESS")
    private String address;

    @Column(name="NAME")
    private String name;

    @Column(name="CUST_CODE")
    private String custCode;

    @Column(name="LAT_LONG")
    private String latLong;

    @Column(name="STAFF_CODE")
    private String staffCode;

    @Column(name="STATUS")
    private int status;

    @Column(name="SAVE_TIME")
    private String saveTime;

    @Column(name="DOC")
    private Date doc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCustCode() {
        return custCode;
    }

    public void setCustCode(String custCode) {
        this.custCode = custCode;
    }

    public String getLatLong() {
        return latLong;
    }

    public void setLatLong(String latLong) {
        this.latLong = latLong;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSaveTime() {
        return saveTime;
    }

    public void setSaveTime(String saveTime) {
        this.saveTime = saveTime;
    }

    public Date getDoc() {
        return doc;
    }

    public void setDoc(Date doc) {
        this.doc = doc;
    }
}
