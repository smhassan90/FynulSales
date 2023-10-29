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
@Table(name="BASE_PRD_GRP_ON")
public class PRDGroupOn {
    @Id
    @Column(name="PRD_NAME")
    private String PRD_NAME;
    @Column(name="PRD_CAT")
    private String PRD_CAT;
    @Column(name="GRP")
    private String GRP;
    @Column(name="PRD_GRP")
    private String PRD_GRP;
    @Column(name="GROUP_ON")
    private String GROUP_ON;
    @Column(name="QTY_CONVERSION")
    private double QTY_CONVERSION;
    @Column(name="CYP_CONVERSION")
    private double CYP_CONVERSION;

    public String getPRD_NAME() {
        return PRD_NAME;
    }

    public void setPRD_NAME(String PRD_NAME) {
        this.PRD_NAME = PRD_NAME;
    }

    public String getPRD_CAT() {
        return PRD_CAT;
    }

    public void setPRD_CAT(String PRD_CAT) {
        this.PRD_CAT = PRD_CAT;
    }

    public String getGRP() {
        return GRP;
    }

    public void setGRP(String GRP) {
        this.GRP = GRP;
    }

    public String getPRD_GRP() {
        return PRD_GRP;
    }

    public void setPRD_GRP(String PRD_GRP) {
        this.PRD_GRP = PRD_GRP;
    }

    public String getGROUP_ON() {
        return GROUP_ON;
    }

    public void setGROUP_ON(String GROUP_ON) {
        this.GROUP_ON = GROUP_ON;
    }

    public double getQTY_CONVERSION() {
        return QTY_CONVERSION;
    }

    public void setQTY_CONVERSION(double QTY_CONVERSION) {
        this.QTY_CONVERSION = QTY_CONVERSION;
    }

    public double getCYP_CONVERSION() {
        return CYP_CONVERSION;
    }

    public void setCYP_CONVERSION(double CYP_CONVERSION) {
        this.CYP_CONVERSION = CYP_CONVERSION;
    }
}
