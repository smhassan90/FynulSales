package com.fynuls.utils;

import com.fynuls.controller.greensales.Codes;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HibernateUtil {
    private static SessionFactory sessionFactory = null;
    private static SessionFactory newSessionFactory = null;

    final static Logger LOG = Logger.getLogger(HibernateUtil.class);

    public static SessionFactory getSessionFactory(){
        if(sessionFactory == null){
            try{
                sessionFactory = new Configuration().configure("hibernate_mysql.cfg.xml").buildSessionFactory();
            }catch(Exception e){
                LOG.error(e);
            }

        }
        return sessionFactory;
    }

    public static SessionFactory getNewSessionFactory(){
        if(newSessionFactory == null){
            try{
                newSessionFactory = new Configuration().configure("hibernate_new.cfg.xml").buildSessionFactory();
            }catch(Exception e){
                LOG.error(e);
            }

        }
        return newSessionFactory;
    }

    public static SessionFactory getMySQLSessionFactory(){
        if(newSessionFactory == null){
            try{
                newSessionFactory = new Configuration().configure("hibernate_mysql.cfg.xml").buildSessionFactory();
            }catch(Exception e){
                LOG.error(e);
            }

        }
        return newSessionFactory;
    }

    public static boolean saveOrUpdate(Object obj){
        Session session = null;
        Transaction tx =null;
        boolean isSuccessful = false;
        try {
            session = getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.saveOrUpdate(obj);
            tx.commit();
            isSuccessful = true;
        }catch(Exception e){
            LOG.error(e);
        }finally {
            session.close();
        }
        return isSuccessful;
    }

    public static boolean save(Object obj){
        Session session = null;
        Transaction tx =null;
        boolean isSuccessful = false;
        try {
            session = getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.save(obj);
            tx.commit();
            isSuccessful = true;
        }catch(Exception e){
            LOG.error(e);
        }finally {
            session.close();
        }
        return isSuccessful;
    }

    public static Object getDBObjectsQueryParameter(String queryString, String paramName, List<Long> param){
        Session session = null;
        Object objects = null;

        try {

            session = getSessionFactory()
                    .openSession();
            Query query = session.createQuery(queryString);
            query.setParameterList(paramName,param);
            objects = query.list();
        }catch(Exception e){
            LOG.error(e);
        }finally {
            session.close();
        }
        return objects;
    }

    public static Object getDBObjectsQueryParameterNew(String queryString, String paramName, List<Long> param){
        Session session = null;
        Object objects = null;

        try {

            session = getNewSessionFactory()
                    .openSession();
            Query query = session.createQuery(queryString);
            query.setParameterList(paramName,param);
            objects = query.list();
        }catch(Exception e){
            LOG.error(e);
        }finally {
            session.close();
        }
        return objects;
    }
    public static Object getDBObjectsQueryParameterStringNew(String queryString, String paramName, List<String> param){
        Session session = null;
        Object objects = null;

        try {

            session = getNewSessionFactory()
                    .openSession();
            Query query = session.createQuery(queryString);
            query.setParameterList(paramName,param);
            objects = query.list();
        }catch(Exception e){
            LOG.error(e);
        }finally {
            session.close();
        }
        return objects;
    }

    public static ArrayList<Object> getDBObjectsFromSQLQueryNew(String query){
        Session session = null;
        ArrayList<Object> objects = new ArrayList<>();
        try {
            session = getNewSessionFactory()
                    .openSession();
            objects = (ArrayList<Object>) session.createSQLQuery(query).list();
        }catch(Exception e){
            LOG.error(e);
        }finally {
            session.close();
        }
        return objects;
    }

    public static ArrayList<Object> getDBObjectsFromSQLQueryClass(String query, Class cls){
        Session session = null;
        ArrayList<Object> objects = new ArrayList<>();
        try {

            session = getSessionFactory()
                    .openSession();
            objects = (ArrayList<Object>) session.createSQLQuery(query).addEntity(cls).list();
        }catch(Exception e){
            LOG.error(e);
        }finally {
            session.close();
        }
        return objects;
    }

    public static ArrayList<Object> getDBObjectsFromSQLQuery(String query){
        Session session = null;
        ArrayList<Object> objects = new ArrayList<>();
        try {

            session = getSessionFactory()
                    .openSession();
            objects = (ArrayList<Object>) session.createSQLQuery(query).list();
        }catch(Exception e){
            LOG.error(e);
        }finally {
            session.close();
        }
        return objects;
    }

    public static Object getDBObjects(String query){
        Session session = null;
        Object objects = null;
        try {

            session = getSessionFactory()
                    .openSession();
            objects = session.createQuery(query).list();
        }catch(Exception e){
            LOG.error(e);
        }finally {
            session.close();
        }
        return objects;
    }

    public static Object getDBObjectsNew(String query){
        Session session = null;
        Object objects = null;
        try {

            session = getNewSessionFactory()
                    .openSession();
            objects = session.createQuery(query).list();
        }catch(Exception e){
            LOG.error(e);
        }finally {
            session.close();
        }

        return objects;
    }

    public static boolean saveObjectNew(Object object){
        boolean isSuccessful = true;
        Session session = null;
        Transaction tx = null;
        try{
            session = getNewSessionFactory().openSession();
            tx = session.beginTransaction();
            session.saveOrUpdate(object);
            tx.commit();
        }catch (Exception e){
            LOG.error(e);
            isSuccessful = false;
        }finally {
            if(session.isOpen()){
                session.close();
            }
        }
        return isSuccessful;
    }

    public static String generateToken(String uuid) {
        String random = String.valueOf(System.currentTimeMillis())+uuid;
        return random;
    }

    /*
    This method will return count number.
     */
    public static int getRecordCount(String queryString){
        Session session = null;
        String result = "";
        Integer count = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            count = ((Long)session.createQuery(queryString).uniqueResult()).intValue();

        }catch(Exception e){
            LOG.error(e);
        }finally{
            session.close();
        }

        return (int) count;
    }

    public static int getRecordCountNew(String queryString){
        Session session = null;
        String result = "";
        Integer count = null;
        try{
            session = HibernateUtil.getNewSessionFactory().openSession();
            count = ((Long)session.createQuery(queryString).uniqueResult()).intValue();

        }catch(Exception e){
            LOG.error(e);
        }finally{
            session.close();
        }

        return (int) count;
    }

    /*
    This method will return single cell on provided Query.
     */
    public static String getSingleString(String queryString){
        Session session = null;
        String result = "";
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createSQLQuery(queryString);
            Object cell = query.uniqueResult();
            if(cell!=null){
                result = cell.toString();
            }

        }catch(Exception e){
            LOG.error(e);
        }finally{
            session.close();
        }

        return result;
    }

    public static long getNextBaseID(int appNumber){
        ArrayList<IDMANAGER> idManagers = (ArrayList<IDMANAGER>) getDBObjects("from IDMANAGER");
        IDMANAGER idManager = idManagers.get(0);
        long lastID = 0;
        if(appNumber== Codes.FYNULS_APP_CODE){
            lastID = idManager.getLastID()+50000;
            idManager.setLastID(lastID);
        }

        idManager.setId(1);
        HibernateUtil.saveOrUpdate(idManager);
        return lastID;
    }

    public static void insertDTCImg(String queryString){
        Session session = null;
        String result = "";
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createSQLQuery(queryString);
            query.executeUpdate();
            int v= 0;

        }catch(Exception e){
            LOG.error(e);
        }finally{
            session.close();
        }
    }

    public static boolean saveOrUpdateListNew(List<? extends Object> objs){
        Session session = null;
        Transaction tx =null;
        boolean isSuccessful = false;
        try {

            session = HibernateUtil.getNewSessionFactory().openSession();
            tx = session.beginTransaction();
            for (Object obj : objs){

                session.saveOrUpdate(obj);
            }

            tx.commit();
            isSuccessful = true;
        }catch(Exception e){
            LOG.error(e);
        }finally {
            session.close();
        }
        return isSuccessful;
    }

    public static boolean saveOrUpdateList(List<? extends Object> objs){
        Session session = null;
        Transaction tx =null;
        boolean isSuccessful = false;
        try {

            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            for (Object obj : objs){

                session.saveOrUpdate(obj);
            }

            tx.commit();
            isSuccessful = true;
        }catch(Exception e){
            LOG.error(e);
        }finally {
            session.close();
        }
        return isSuccessful;
    }

    public static boolean executeQuery(String query){
        Session session = null;
        Transaction tx =null;
        boolean isSuccessful = false;
        try {

            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            int s= session.createSQLQuery(query).executeUpdate();

            tx.commit();
            isSuccessful = true;
        }catch(Exception e){
            LOG.error(e);
        }finally {
            session.close();
        }
        return isSuccessful;
    }
    public static boolean executeQueryNew(String query){
        Session session = null;
        Transaction tx =null;
        boolean isSuccessful = false;
        try {

            session = HibernateUtil.getNewSessionFactory().openSession();
            tx = session.beginTransaction();
            int s= session.createSQLQuery(query).executeUpdate();

            tx.commit();
            isSuccessful = true;
        }catch(Exception e){
            LOG.error(e);
        }finally {
            session.close();
        }
        return isSuccessful;
    }

    public static Object getSQLQueryResult(String query){
        Session session = null;
        Object objects = null;
        try {

            session = getSessionFactory()
                    .openSession();
            objects = session.createSQLQuery(query).list();
        }catch(Exception e){
            LOG.error(e);
        }finally {
            session.close();
        }
        return objects;
    }

    public static String getHTMLForQuery(String query, ArrayList<String> headers, String caption){
        String html = "</br></br><table border=1 style=\"width:100%\"> <caption style=\"background-color:#FF6E33;color:white;font-size:20; font-weight:bold; width:100%\">"+caption+"</caption>";
        html+="<tr>";
        for(String header : headers){
            html += "<th>"+header+"</th>";
        }
        html += "</tr>";

        Object object = HibernateUtil.getSQLQueryResult(query);
        if(object==null){
            int uunnn = 9;
        }
        if(object==null || ((ArrayList) object).size()==0){
            return "";
        }
        List<Object>  objs = new ArrayList<>();
        String activeCount = "";
        objs = (List<Object>) object;
        if(objs!=null && objs.size()>0){

            for (Object obj : objs){
                List<Object> dataList = Arrays.asList(obj);
                if(dataList!=null && dataList.size()>0){
                    html+="<tr>";
                    for(Object data : dataList){
                        if(data.getClass().isArray()){
                            Object[] row = (Object[]) data;

                            for(int i =0 ; i<row.length ; i++){
                                html += "<td>" + row[i] + "</td>";
                            }
                        }else{
                            html += "<td>" + data.toString() + "</td>";
                        }

                    }
                    html+="</tr>";
                }
            }
        }
        html+="</table>";

        return html;
    }

    public static Object getMyDBObjects(String query){
        Session session = null;
        Object objects = null;
        try {

            session = getMySQLSessionFactory()
                    .openSession();
            objects = session.createQuery(query).list();
        }catch(Exception e){
            LOG.error(e);
        }finally {
            session.close();
        }

        return objects;
    }

}