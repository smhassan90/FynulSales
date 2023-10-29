package com.fynuls.entity.sale.base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Syed Muhammad Hassan
 * 29th April, 2021
 */
@Entity
@Table(name="BASE_EMPLOYEE")
public class Employee {

    @Id
    @Column(name="ID")
    private String ID;
    @Column(name="NAME")
    private String NAME;
    @Column(name="DESIGNATION_ID")
    private int DESIGNATION_ID;
    @Column(name="BASE")
    private String BASE;
    @Column(name="DEPARTMENT_ID")
    private int DEPARTMENT_ID;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public int getDESIGNATION_ID() {
        return DESIGNATION_ID;
    }

    public void setDESIGNATION_ID(int DESIGNATION_ID) {
        this.DESIGNATION_ID = DESIGNATION_ID;
    }

    public String getBASE() {
        return BASE;
    }

    public void setBASE(String BASE) {
        this.BASE = BASE;
    }

    public int getDEPARTMENT_ID() {
        return DEPARTMENT_ID;
    }

    public void setDEPARTMENT_ID(int DEPARTMENT_ID) {
        this.DEPARTMENT_ID = DEPARTMENT_ID;
    }
}
