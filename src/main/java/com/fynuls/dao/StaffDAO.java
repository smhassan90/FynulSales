package com.fynuls.dao;

import com.fynuls.controller.greensales.Codes;
import com.fynuls.controller.greensales.Sync;
import com.fynuls.entity.login.LoginStatus;
import com.fynuls.entity.login.User;
import com.fynuls.utils.HibernateUtil;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import java.util.List;


public class StaffDAO implements IStaffDatabaseDAO {
    final static Logger LOG = Logger.getLogger(StaffDAO.class);
    public void insertRecord(String username, int status, String token) {
        LoginStatus loginStatus = new LoginStatus();
        User user = new User();

        List<User> users = (List<User>) HibernateUtil.getDBObjects("from User where username='"+username+"'");

        if(users!=null && users.size()>0){
            user =  users.get(0);
        }
        loginStatus.setUsername(user.getUserName());
        loginStatus.setStatus(status);
        loginStatus.setToken(token);
        loginStatus.setType(Codes.FYNULS_APP_CODE);
        if(loginStatus!=null){
            HibernateUtil.saveOrUpdate(loginStatus);
        }
    }


    public boolean isExist(String username) {
        List<LoginStatus> list = (List<LoginStatus>)HibernateUtil.getDBObjects("from LoginStatus where username='"+username+"'");

        if( list!=null && list.size()==0)
            return false;
        else
            return true;
    }

    public void updateInformation(String username, int status, String token) {

        List<LoginStatus> loginStatuses = (List<LoginStatus>)HibernateUtil.getDBObjects("from LoginStatus where username='"+username+"'");

        if(loginStatuses!=null && loginStatuses.size()>0){
            LoginStatus loginStatus =loginStatuses.get(0);
            loginStatus.setStatus(status);
            loginStatus.setToken(token);
            loginStatus.setUsername(username);
            loginStatus.setType(Codes.FYNULS_APP_CODE);
            HibernateUtil.saveOrUpdate(loginStatus);
        }

    }

    public boolean isLoggedIn(String username) {
        List<LoginStatus> list = (List<LoginStatus>)HibernateUtil.getDBObjects("from LoginStatus where username='"+username+"' AND status=1");

        if(list !=null && list.size()==0)
            return false;
        else
            return true;
    }

    public String getToken(String username) {
        List<LoginStatus> list = (List<LoginStatus>)HibernateUtil.getDBObjects("from LoginStatus where username='"+username+"'");

        if(list !=null && list.size()>0){
            return list.get(0).getToken();
        }else{
            return "";
        }
    }

    public String isCorrect(String username, String password) {
        Object obj = HibernateUtil.getDBObjects("from User where password='"+password+"' and username='"+username+"'");
        List<User> users = (List<User>)obj;

        if(users!=null && users.size()>0){
            return users.get(0).getUserName();
        }
        return "";
    }

    public JSONObject performSync(String username) {
        Sync sync = new Sync();
        return sync.performSync(username,"");
    }

    @Override
    public String getName(String code) {
        String name = HibernateUtil.getSingleString("SELECT NAME FROM User where username='"+code+"'");
        return name;
    }

    public boolean logout(String username) {
        List<LoginStatus> loginStatusList= null;

        loginStatusList =(List<LoginStatus>)HibernateUtil.getDBObjects("from LoginStatus where username='" + username + "'");

        if(loginStatusList!=null && loginStatusList.size()>0) {
            LoginStatus loginStatus = loginStatusList.get(0);
            loginStatus.setStatus(0);
            loginStatus.setToken("");

            HibernateUtil.saveOrUpdate(loginStatus);

            return true;
        }else{
            return false;
        }

    }

    public String isTokenValid(String token){
        boolean isValid = false;
        List<LoginStatus> loginStatusList = null;


        try{
            loginStatusList = (List<LoginStatus>) HibernateUtil.getDBObjects("FROM LoginStatus WHERE token ='"+token+"' and status=1");
        }catch (Exception e){
            LOG.error(e);
        }
        if(loginStatusList!=null && loginStatusList.size()>0){
            return loginStatusList.get(0).getUsername();
        }else{
            return "";
        }
    }
}
