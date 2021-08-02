package com.fynuls.controller.greensales;

import com.fynuls.entity.sale.WorkWith;
import com.fynuls.entity.sale.Customer;
import com.fynuls.entity.sale.SKUGroup;
import com.fynuls.entity.sale.Status;
import com.google.gson.Gson;
import com.fynuls.dal.Dashboard;
import com.fynuls.dal.Data;
import com.fynuls.dal.SyncObjectSS;
import com.fynuls.dao.GSSDashboardDAO;
import com.fynuls.dao.StaffDAO;
import com.fynuls.dao.SyncDAO;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class Sync {

    @RequestMapping(value = "/sync", method = RequestMethod.GET,params={"data","token"})
    @ResponseBody
    public String index(String data, String token){
        StaffDAO gssStaffDAO = new StaffDAO();
        JSONObject response = new JSONObject();
        String staffCode = gssStaffDAO.isTokenValid(token);
        if(!"".equals(staffCode)){
            return performSync(staffCode,data).toString();
        }else{
            response.put("message", "Invalid Token, you might be logged in from another device");
            response.put("status", Codes.INVALID_TOKEN);
            response.put("data","");
            return response.toString();
        }
    }

    public JSONObject performSync(String code, String data){
        JSONObject response = new JSONObject();
        Data dataSync = new Data();
        List<Customer> customers = null;
        List<Status> statuses = null;
        List<SKUGroup> skuGroup = null;
        List<WorkWith> workWiths = null;
        String staffName = "";

        String insertCode = "";
        SyncObjectSS syncObject =  new Gson().fromJson(data, SyncObjectSS.class);
        SyncDAO sync = new SyncDAO();
        try {
            if(syncObject==null){
                insertCode = Codes.ALL_OK;
            }else{
                insertCode = sync.insertData(syncObject,code);
            }

            Dashboard dashboard = new Dashboard();
            GSSDashboardDAO dashboardDAO = new GSSDashboardDAO();

            String html = dashboardDAO.getDashboardHTML(code);
            dashboard.setId(1);
            dashboard.setHtml(html);

            customers = sync.getCustomers(code);
            statuses = sync.getStatuses();
            skuGroup = sync.getSKUGroup();
            workWiths = sync.getWorkWiths();
            staffName = sync.getStaffName(code);

            dataSync.setCustomers(customers);
            dataSync.setStatuses(statuses);
            dataSync.setSkuGroup(skuGroup);
            dataSync.setWorkWiths(workWiths);
            dataSync.setDashboard(dashboard);

            if(insertCode.equals("200")){
                response.put("message", "Successfully synced");
                response.put("status", Codes.ALL_OK);
                response.put("data", new Gson().toJson(dataSync));
                response.put("staffName",staffName);
            }else{
                response.put("message", "Something went wrong while inserting data");
                response.put("status", Codes.SOMETHING_WENT_WRONG);
                response.put("data", new Gson().toJson(dataSync));
            }

        }catch(Exception e){
            response.put("message", "Something went wrong");
            response.put("status", Codes.SOMETHING_WENT_WRONG);
            response.put("data","");
        }

        return response;
    }
}
