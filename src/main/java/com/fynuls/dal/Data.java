package com.fynuls.dal;

import com.fynuls.entity.sale.*;

import java.io.Serializable;
import java.util.List;

public class Data implements Serializable {
    List<Depot> depots;
    List<Town> towns;
    List<Customer> customers;
    List<TownDepot> townDepots;
    List<TownCustomer> townCustomers;
    List<Status> statuses;
    List<SKUGroup> skuGroup;
    List<WorkWith> workWiths;
    Dashboard dashboard;

    public List<Depot> getDepots() {
        return depots;
    }

    public void setDepots(List<Depot> depots) {
        this.depots = depots;
    }

    public List<Town> getTowns() {
        return towns;
    }

    public void setTowns(List<Town> towns) {
        this.towns = towns;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public List<TownDepot> getTownDepots() {
        return townDepots;
    }

    public void setTownDepots(List<TownDepot> townDepots) {
        this.townDepots = townDepots;
    }

    public List<TownCustomer> getTownCustomers() {
        return townCustomers;
    }

    public void setTownCustomers(List<TownCustomer> townCustomers) {
        this.townCustomers = townCustomers;
    }

    public List<Status> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<Status> statuses) {
        this.statuses = statuses;
    }

    public List<SKUGroup> getSkuGroup() {
        return skuGroup;
    }

    public void setSkuGroup(List<SKUGroup> skuGroup) {
        this.skuGroup = skuGroup;
    }

    public List<WorkWith> getWorkWiths() {
        return workWiths;
    }

    public void setWorkWiths(List<WorkWith> workWiths) {
        this.workWiths = workWiths;
    }

    public Dashboard getDashboard() {
        return dashboard;
    }

    public void setDashboard(Dashboard dashboard) {
        this.dashboard = dashboard;
    }
}
