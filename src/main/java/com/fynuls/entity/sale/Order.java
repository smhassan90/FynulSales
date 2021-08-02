package com.fynuls.entity.sale;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="FS_ORDER")
public class Order implements Serializable {
    @Id
    @Column(name="ID")
    private int id;
    @Column(name="CUST_CODE")
    private String custCode;
    @Column(name="STATUS_ID")
    private int statusId;
    @Column(name="STAFF_CODE")
    private String staffCode;
    @Column(name="UPDATE_DATE")
    private Date updateDate = new Date();
    @Column(name="VISIT_DATE")
    private String visitDate;
    @Column(name="lat_lon")
    private String latLon;

    @Column(name="comments")
    private String comments;

    @Column(name="workWith")
    private String workWith;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustCode() {
        return custCode;
    }

    public void setCustCode(String custCode) {
        this.custCode = custCode;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    public String getLatLon() {
        return latLon;
    }

    public void setLatLon(String latLon) {
        this.latLon = latLon;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getWorkWith() {
        return workWith;
    }

    public void setWorkWith(String workWith) {
        this.workWith = workWith;
    }
}
