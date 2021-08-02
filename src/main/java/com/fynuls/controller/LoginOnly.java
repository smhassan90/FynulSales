package com.fynuls.controller;

import com.fynuls.controller.greensales.Codes;
import com.fynuls.controller.greensales.Login;
import com.fynuls.dao.*;
import com.fynuls.utils.HibernateUtil;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Syed Muhammad Hassan
 * 31st August, 2020
 */
@Controller
public class LoginOnly {


    final static Logger LOG = Logger.getLogger(Login.class);

    String errorCode="";
    String message="";
    String data="";
    String token = "";
    String staffName = "";
    int isQATAllowed = 0;
    int isQTVAllowed = 0;

    /*
       This method will check the code validity and already logged in and then perform
       Login and generate token to further use for communication.
     */
    @RequestMapping(value = "/loginOnly", method = RequestMethod.GET,params={"username","password","uniqueId","staffType"})
    @ResponseBody
    public String index(String username, String password, String uniqueId, int staffType){

        JSONObject json = new JSONObject();

        IStaffDatabaseDAO staffDAO = null;
        if(staffType==Codes.FYNULS_APP_CODE){
            //request is coming from Sales team
            staffDAO = new StaffDAO();
        }
        String UUID =uniqueId;
        String staffCode = staffDAO.isCorrect(username,password);

        if(staffCode!=null && !"".equals(staffCode)){
            if(staffDAO.isExist(staffCode)){
                /*
                if(staffDAO.isLoggedIn(staffCode)){
                    // Show message that you need to contact admin.
                    errorCode=Codes.ALREADY_LOGGED_IN;
                    message="You are already logged in. Please contact admin";
                }else{
                    performLogin(UUID, staffDAO, staffCode );
                }
                */
                performLogin(UUID, staffDAO, staffCode );
            }else{
                String token = HibernateUtil.generateToken(UUID);
                staffDAO.insertRecord(staffCode, 1, token);
                verificationLoggedIn(staffDAO,staffCode,token);
            }

        }else{
            errorCode = Codes.NOT_FOUND;
            message = "Username or password is incorrect";
        }
        /*
        This code will logout the user from other device and then attempt login again
        For now, this functionality is on hold. The user has will be intimated that
        he is been online in other phone. Kindly contact admin to force logout from other
        phone.

        if(errorCode!=Codes.ALL_OK){
            //Request logout
            gssStaffDAO.logout(code);
            performLogin(UUID,gssStaffDAO,code);
        }
        */
        long baseID = HibernateUtil.getNextBaseID(staffType);
        try{
            json.put("status", errorCode);
            json.put("message", message);
            json.put("data",data);
            json.put("token", token);
            json.put("baseID",baseID);
            json.put("staffName",staffName);
            json.put("staffCode",staffCode);
        }catch(Exception e){
            LOG.error(e);
        }

        return json.toString();
    }

    private void verificationLoggedIn(IStaffDatabaseDAO gssStaffDAO, String code, String token) {

        if(gssStaffDAO.isLoggedIn(code)){
            errorCode = Codes.ALL_OK;
            message = "You are successfully logged in";
            if(gssStaffDAO.getToken(code).equals(token)){
                /*
                JSONObject dataResponse = gssStaffDAO.performSync(code);
                data = (String) dataResponse.get("data");
                errorCode = (String) dataResponse.get("status");
                message = (String) dataResponse.get("message");
                staffName = (String) dataResponse.get("staffName");
                */
                errorCode = Codes.ALL_OK;
                message = "Sync Successful";
                staffName = gssStaffDAO.getName(code);
                this.token = token;
            }else{
                data = "";
                errorCode = Codes.FORCED_LOGOUT;
                message = "Forced Logout";
            }
        }else{
            errorCode = Codes.SOMETHING_WENT_WRONG;
            message = "Something went wrong";
        }
    }
    private void performLogin(String UUID, IStaffDatabaseDAO gssStaffDAO, String code){
        // Logging in.
        String token = HibernateUtil.generateToken(UUID);
        gssStaffDAO.updateInformation(code,1, token);
        verificationLoggedIn(gssStaffDAO,code,token);
    }
}