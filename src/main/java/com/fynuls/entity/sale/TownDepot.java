package com.fynuls.entity.sale;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="FS_TOWN_DEPO")
public class TownDepot {
    @Id
    @Column(name="TOWN_ID")
    private String townId;

    @Column(name="DEPO_ID")
    private String depotId;

    public String getTownId() {
        return townId;
    }

    public void setTownId(String townId) {
        this.townId = townId;
    }

    public String getDepotId() {
        return depotId;
    }

    public void setDepotId(String depotId) {
        this.depotId = depotId;
    }
}
