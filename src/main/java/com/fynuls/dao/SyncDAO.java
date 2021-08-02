package com.fynuls.dao;

import com.fynuls.controller.greensales.Codes;
import com.fynuls.dal.Packet;
import com.fynuls.dal.SyncObjectSS;
import com.fynuls.entity.sale.*;
import com.fynuls.utils.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author Syed Muhammad Hassan
 * 24/06/2019
 */
public class SyncDAO {
    final static Logger LOG = Logger.getLogger(SyncDAO.class);

    /*
    Get all Staff
     */
    public List<SDStaff> getStaffs(){
        String query =  "from SDStaff";
        List<SDStaff> data = new ArrayList<SDStaff>();
        data = (List<SDStaff>) HibernateUtil.getDBObjects(query);

        return data;
    }

    /*
    Get  Staff
     */
    public String getStaffName(String code){
        String query =  "from SDStaff WHERE staffCode='"+code+"'";
        List<SDStaff> data = new ArrayList<>();
        data = (List<SDStaff>) HibernateUtil.getDBObjects(query);
        if(data!=null && data.size()>0){
            return data.get(0).getStaffName();
        }else{
            return "";
        }
    }

    /*
        Fetch all depots which are tagged to this person (Code).
     */
    public List<Depot> getDepots(String code){
        List<Depot> sdDepots = new ArrayList<Depot>();
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            SQLQuery query = session.createSQLQuery("SELECT DISTINCT(sd_depo.DEPO_CODE) ,sd_depo.DEPO_NAME FROM sd_depo" +
                    " JOIN sd_depo_staff ds on ds.DEPO_ID=sd_depo.DEPO_CODE WHERE ds.STAFF_CODE='"+code+"'")
                    .addEntity(Depot.class);
            sdDepots = query.list();
        }catch(Exception e){
            LOG.error(e);
        }finally {
            session.close();
        }
        return sdDepots;
    }

    /*
    Fetch All Towns which are tagged to person.
     */
    public List<Town> getTowns(String code){
        List<Town> sdTowns = new ArrayList<Town>();
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            SQLQuery query = session.createSQLQuery("SELECT DISTINCT(t.TOWN_ID), t.TOWN_NAME FROM SD_TOWN t " +
                    "INNER JOIN SD_TOWN_STAFF ts ON ts.TOWN_ID=t.TOWN_ID WHERE ts.STAFF_CODE='"+code+"'")
                    .addEntity(Town.class);
            sdTowns = query.list();
        }catch (Exception e){
            LOG.error(e);
        }finally {
            session.close();
        }
        return sdTowns;
    }

    /*
    Get cll customers tagged to person who is logging in.
     */
    public List<Customer> getCustomers(String code){
        List<Customer> customers = new ArrayList<Customer>();

        customers = (List<Customer>) HibernateUtil.getDBObjects("From Customer");
        return customers;
    }

    /*
    Fetch the mapping of Depot with region.
     */
    public List<TownDepot> getDepotRegionMapping(String code){
        List<TownDepot> townDepots = new ArrayList<TownDepot>();
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            String sql = "SELECT DISTINCT(td.TOWN_ID), td.DEPO_ID FROM SD_TOWN_DEPO td " +
                    " INNER JOIN SD_DEPOCUST_STAFF DCS ON td.DEPO_ID=SUBSTR(DEPO_CUST,0,3) AND DCS.STAFF_CODE='"+code+"'" +
                    " INNER JOIN SD_TOWN_STAFF TS ON TS.TOWN_ID=td.TOWN_ID AND TS.STAFF_CODE='"+code+"'";
            SQLQuery query = session.createSQLQuery(sql)
                    .addEntity(TownDepot.class);

            townDepots = query.list();
        }catch (Exception e){
            LOG.error(e);
        }finally {
            session.close();
        }

        return townDepots;
    }

    /*
    Fetch the Region and customer mapping.
     */
    public List<TownCustomer> getRegionCustomerMapping(String code){
        List<TownCustomer> townCustomers = new ArrayList<TownCustomer>();
        Session session = null;
        String queryString="SELECT DISTINCT(TC.CUSTOMER_ID), TC.TOWN_ID\n" +
                "   FROM SD_TOWN_CUSTOMER TC\n" +
                "   INNER JOIN SD_TOWN_STAFF TS ON TS.TOWN_ID=TC.TOWN_ID AND TS.STAFF_CODE='"+code+"'" +
                "   INNER JOIN SD_DEPOCUST_STAFF DCS ON DCS.STAFF_CODE='"+code+"' " +
                " AND SUBSTR(DCS.DEPO_CUST,4)=TC.CUSTOMER_ID";
        try{
            session = HibernateUtil.getSessionFactory().openSession();

            SQLQuery query = session.createSQLQuery(queryString)
                    .addEntity(TownCustomer.class);
            townCustomers = query.list();
        }catch(Exception e ){
            LOG.error(e);
        }finally {
            session.close();
        }

        return townCustomers;
    }

    /*
    Fetch the status
     */
    public List<Status> getStatuses(){
        List<Status> statuses = new ArrayList<Status>();
        String queryString="FROM Status";
        statuses = (List<Status>) HibernateUtil.getDBObjects(queryString);

        return statuses;
    }

    /*
    Get SKU
     */
    public List<SKUGroup> getSKUGroup(){
        List<SKUGroup> skuGroup = new ArrayList<SKUGroup>();
        skuGroup = (List<SKUGroup>) HibernateUtil.getDBObjects("From SKUGroup");
        return skuGroup;
    }

    /*
    Get Work with (RSM/ASM)
     */
    public List<WorkWith> getWorkWiths(){
        List<WorkWith> workWiths = new ArrayList<WorkWith>();

        String queryString="FROM WorkWith";

        workWiths = (List<WorkWith>) HibernateUtil.getDBObjects(queryString);

        return workWiths;
    }

    /*
    Insert the data coming from mobile.
     */
    public String insertData(SyncObjectSS syncObject, String staffCode) {
        String status="";
        String message="";
        boolean isSuccessful = true;
        try {
            Packet[] packets = syncObject.getOrderProduct();
            Order tempOrder;
            if(packets!=null){
                for (Packet packet : packets) {
                    tempOrder = new Order();
                    tempOrder = packet.getOrder();
                    tempOrder.setStaffCode(staffCode);
                    if(isSuccessful) {
                        isSuccessful = HibernateUtil.saveOrUpdate(tempOrder);
                    }
                    if(isSuccessful) {
                        isSuccessful = HibernateUtil.saveOrUpdateList(packet.getProductOrders());
                    }
                }
            }


            List<UnapprovedSDCustomer> unapprovedSDCustomers = syncObject.getUnapprovedSDCustomers();
            List<UnapprovedSDCustomer> unapprovedSDCustomersToSave = new ArrayList<>();
            if(unapprovedSDCustomers!=null){
                for (UnapprovedSDCustomer customer : unapprovedSDCustomers) {
                    customer.setId(0);
                    customer.setStaffCode(staffCode);
                    customer.setDoc(new Date());
                    unapprovedSDCustomersToSave.add(customer);
                }
            }
            if(isSuccessful){
                isSuccessful = HibernateUtil.saveOrUpdateList(unapprovedSDCustomersToSave);
            }

            List<LeaveEntry> leaveEntries = syncObject.getLeaveEntries();
            List<LeaveEntry> leaveEntriesToSave = new ArrayList<>();
            if(leaveEntries!=null){
                for (LeaveEntry leaveEntry : leaveEntries) {
                    leaveEntry.setId(0);
                    leaveEntry.setStaffCode(staffCode);
                    leaveEntriesToSave.add(leaveEntry);
                }
            }

            if(isSuccessful){
                HibernateUtil.saveOrUpdateList(leaveEntriesToSave);
            }

            if(isSuccessful){
                status= Codes.ALL_OK;
                message="Successfully Synced";
            }else{
                status=Codes.SOMETHING_WENT_WRONG;
                message="Synced failed. Something went wrong";
            }


        }catch (Exception e){
            status=Codes.SOMETHING_WENT_WRONG;
            message="Synced failed. Something went wrong";

        }

        return status;
    }

}
