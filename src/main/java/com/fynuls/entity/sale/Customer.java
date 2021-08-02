package com.fynuls.entity.sale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="FS_CUSTOMER")
public class Customer {

    @Id
    @Column(name="CUST_CODE")
    private String custCode;

    @Column(name="CUST_NAME")
    private String custName;

    @Column(name="CUST_ADD")
    private String custAdd;

    @Column(name="TYPE")
    private String type;

    public String getCustCode() {
        return custCode;
    }

    public void setCustCode(String custCode) {
        this.custCode = custCode;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustAdd() {
        return custAdd;
    }

    public void setCustAdd(String custAdd) {
        this.custAdd = custAdd;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
