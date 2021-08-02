package com.fynuls.entity.sale;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="FS_TOWN_CUSTOMER")
public class TownCustomer {
    @Id
    @Column(name="CUSTOMER_ID")
    private String customerId;

    @Column(name="TOWN_ID")
    private String townId;

    public String getTownId() {
        return townId;
    }

    public void setTownId(String townId) {
        this.townId = townId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
