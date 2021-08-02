package com.fynuls.utils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Syed Muhammad Hassan
 * 10/07/2019
 */
@Entity
@Table(name="IDMANAGER")
public class IDMANAGER {

    @Id
    @Column(name="ID")
    private int id;

    @Column(name="LAST_ID")
    private long lastID;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getLastID() {
        return lastID;
    }

    public void setLastID(long lastID) {
        this.lastID = lastID;
    }
}
