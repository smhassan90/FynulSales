package com.fynuls.dal;

/**
 * @author Syed Muhammad Hassan
 * 10th May 2019
 */
public class OrderSummary implements Comparable<OrderSummary>{
    String name;
    int statusID;
    String date;
    int count;
    int type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatusID() {
        return statusID;
    }

    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int compareTo(OrderSummary o) {
        return this.type > o.type ? 1 : this.type < o.type ? -1 : 0;
    }
}
