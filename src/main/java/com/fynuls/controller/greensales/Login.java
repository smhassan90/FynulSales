package com.fynuls.controller.greensales;

import com.fynuls.dao.*;
import com.fynuls.utils.HibernateUtil;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Login {

    final static Logger LOG = Logger.getLogger(Login.class);

    String errorCode="";
    String message="";
    String data="";
    String token = "";
    String staffName = "";

    /*
       This method will check the code validity and already logged in and then perform
       Login and generate token to further use for communication.
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET,params={"code","uniqueId","staffType"})
    @ResponseBody
    public String index(String code, String uniqueId, int staffType){

        JSONObject json = new JSONObject();

        IStaffDatabaseDAO gssStaffDAO = null;
        if(staffType==Codes.FYNULS_APP_CODE){
            //request is coming from FMCG Sales team
            gssStaffDAO = new StaffDAO();
        }

        String UUID =uniqueId;
        String staffCode = gssStaffDAO.isCorrect("",code);

        if(staffCode!=null && !"".equals(staffCode)){
            if(gssStaffDAO.isExist(staffCode)){
                if(gssStaffDAO.isLoggedIn(staffCode)){
                    // Show message that you need to contact admin.
                    errorCode=Codes.ALREADY_LOGGED_IN;
                    message="You are already logged in. Please contact admin";
                }else{
                    performLogin(UUID, gssStaffDAO, staffCode );
                }
            }else{
                String token = HibernateUtil.generateToken(UUID);
                gssStaffDAO.insertRecord(staffCode, 1, token);
                verificationLoggedIn(gssStaffDAO,staffCode,token);
            }

        }else{
            errorCode = Codes.NOT_FOUND;
            message = "Code is invalid";
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
        try{
            json.put("status", errorCode);
            json.put("message", message);
            json.put("data",data);
            json.put("token", token);
            json.put("baseID",HibernateUtil.getNextBaseID(staffType));
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

                JSONObject dataResponse = gssStaffDAO.performSync(code);
                data = (String) dataResponse.get("data");
                errorCode = (String) dataResponse.get("status");
                message = (String) dataResponse.get("message");
                staffName = (String) dataResponse.get("staffName");
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