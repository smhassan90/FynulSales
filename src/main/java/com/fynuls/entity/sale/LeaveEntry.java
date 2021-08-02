package com.fynuls.entity.sale;

import javax.persistence.*;

@Entity
@Table(name="FS_LEAVE_REQUEST")
public class LeaveEntry {
    @Id
    @Column(name="ID")
    private int id;

    @Column(name="LEAVE_DATE")
    private String date;

    @Column(name="REASON")
    private String reason;

    @Column(name="SAVE_TIME")
    private String saveTime;

    @Column(name="SYNCDATE")
    private String syncDate;

    @Column(name="STAFF_CODE")
    private String staffCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getSaveTime() {
        return saveTime;
    }

    public void setSaveTime(String saveTime) {
        this.saveTime = saveTime;
    }

    public String getSyncDate() {
        return syncDate;
    }

    public void setSyncDate(String syncDate) {
        this.syncDate = syncDate;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }
}
