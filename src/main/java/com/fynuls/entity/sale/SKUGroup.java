package com.fynuls.entity.sale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="FS_SKU_GRP")
public class SKUGroup {
    @Id
    @Column(name="GRP_ID")
    private String grpId;

    @Column(name="GRP_NAME")
    private String grpName;

    public String getGrpId() {
        return grpId;
    }

    public void setGrpId(String grpId) {
        this.grpId = grpId;
    }

    public String getGrpName() {
        return grpName;
    }

    public void setGrpName(String grpName) {
        this.grpName = grpName;
    }
}
