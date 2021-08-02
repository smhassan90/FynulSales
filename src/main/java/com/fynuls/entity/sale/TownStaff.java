package com.fynuls.entity.sale;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="FS_TOWN_STAFF")
public class TownStaff {

    @Id
    @Column(name="TOWN_ID")
    private String townId;

    @Column(name="STAFF_CODE")
    private String staffCode;

    public String getTownId() {
        return townId;
    }

    public void setTownId(String townId) {
        this.townId = townId;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }
}
