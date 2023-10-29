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
@Table(name="BASE_ZONE")
public class Zone {
    @Id
    @Column(name="ID")
    private String ID;
    @Column(name="SUBZONE")
    private String SUBZONE;
    @Column(name="ZONE")
    private String ZONE;
    @Column(name="SUBZONE_ALIAS")
    private String SUBZONE_ALIAS;
    @Column(name="ZONE_ALIAS")
    private String ZONE_ALIAS;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getSUBZONE() {
        return SUBZONE;
    }

    public void setSUBZONE(String SUBZONE) {
        this.SUBZONE = SUBZONE;
    }

    public String getZONE() {
        return ZONE;
    }

    public void setZONE(String ZONE) {
        this.ZONE = ZONE;
    }

    public String getSUBZONE_ALIAS() {
        return SUBZONE_ALIAS;
    }

    public void setSUBZONE_ALIAS(String SUBZONE_ALIAS) {
        this.SUBZONE_ALIAS = SUBZONE_ALIAS;
    }

    public String getZONE_ALIAS() {
        return ZONE_ALIAS;
    }

    public void setZONE_ALIAS(String ZONE_ALIAS) {
        this.ZONE_ALIAS = ZONE_ALIAS;
    }
}
