package com.fynuls.dao;

import com.fynuls.dal.ActualSaleData;
import com.fynuls.dal.OrderSummary;
import com.fynuls.entity.sale.Status;
import com.fynuls.utils.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Syed Muhammad Hassan
 * 15th May, 2019
 */
public class GSSDashboardDAO {
    final static Logger LOG = Logger.getLogger(GSSDashboardDAO.class);

    /*
    Dashboard - HTML
     */
    public String getDashboardHTML(String code){
        String html="";
        String providerSale = "";
        providerSale = getProviderSaleHTML(code);
        List<OrderSummary> summaries = getOrderSummary(code);
        Collections.sort(summaries);
        html = "<html>\n" +
                "<head>\n" +
                "<style>\n" +
                "#customers {\n" +
                "  font-family: \"Trebuchet MS\", Arial, Helvetica, sans-serif;\n" +
                "  border-collapse: collapse;\n" +
                "  width: 100%;\n" +
                "}\n" +
                "\n" +
                "#customers td, #customers th {\n" +
                "  border: 1px solid #ddd;\n" +
                "  padding: 8px;\n" +
                "}\n" +
                "\n" +
                "#customers tr:nth-child(even){background-color: #f2f2f2;}\n" +
                "\n" +
                "#customers tr:hover {background-color: #ddd;}\n" +
                "\n" +
                "#customers th {\n" +
                "  padding-top: 12px;\n" +
                "  padding-bottom: 12px;\n" +
                "  text-align: left;\n" +
                "  background-color: #4CAF50;\n" +
                "  color: white;\n" +
                "}\n" +
                "</style>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<table id=\"customers\">\n" +
                "  <tr>\n" +
                "    <th> </th>\n" +
                "    <th>Today</th>\n" +
                "    <th>Yesterday</th>\n" +
                "\t<th>Day Before</th>\n" +
                "  </tr>";
        List<Status> statuses = new ArrayList<Status>();
        int[] today = new int[20];
        int[] yesterday=new int[20];
        int[] dayBefore=new int[20];
        int loop=0;

        statuses = (List<Status>) HibernateUtil.getDBObjects("FROM Status");
        if(statuses!=null){
            for (Status status: statuses){
                for(OrderSummary summary: summaries){
                    if(summary.getStatusID() == status.getId()){
                        if(summary.getType()==1)
                            today[loop] = summary.getCount();
                        else if (summary.getType()==2)
                            yesterday[loop] = summary.getCount();
                        else if(summary.getType()==3)
                            dayBefore[loop] = summary.getCount();
                    }
                }
                html += "<tr>\n" +
                        "    <td>"+status.getStatus()+"</td>\n" +
                        "    <td>"+today[loop]+"</td>\n" +
                        "    <td>"+yesterday[loop]+"</td>\n" +
                        "\t<td>"+dayBefore[loop]+"</td>\n" +
                        "  </tr>";
                loop++;
            }
        }

            int todayTotal = 0;

            for (int i = 0; i < today.length; i++)
            {
                todayTotal = todayTotal + today[i];
            }

            int yesterdayTotal = 0;

            for (int i = 0; i < yesterday.length; i++)
            {
                yesterdayTotal = yesterdayTotal + yesterday[i];
            }

            int dayBeforeTotal = 0;

            for (int i = 0; i < dayBefore.length; i++)
            {
                dayBeforeTotal = dayBeforeTotal + dayBefore[i];
            }

            html+="<tr>\n" +
                    "    <td><b>Total Visited</b></td>\n" +
                    "    <td><b>"+todayTotal+"</b></td>\n" +
                    "    <td><b>"+yesterdayTotal+"</b></td>\n" +
                    "\t<td><b>"+dayBeforeTotal+"</b></td>\n" +
                    "  </tr>";
            html+="</table>\n" +
                    "\n" +
                    providerSale +
                    "</body>\n" +
                    "</html>";

        return html;
    }

    /*
    Get dashboard data
    */
    public List<OrderSummary> getOrderSummary(String code){


        List<OrderSummary> orderSummaries = new ArrayList<OrderSummary>();

        String queryString = "SELECT O.STATUS_ID,COUNT(STATUS_ID),S.Status,substr(o.VISIT_DATE, 1, instr(o.VISIT_DATE, ' ') -1) FROM SD_STATUS S \n" +
                "INNER JOIN GSS_ORDER O ON S.id = O.status_id\n" +
                "GROUP BY O.STATUS_ID,S.STATUS, O.STAFF_CODE, substr(o.VISIT_DATE, 1, instr(o.VISIT_DATE, ' ') -1)\n" +
                "HAVING O.STAFF_CODE='"+code+"' AND substr(o.VISIT_DATE, 1, instr(o.VISIT_DATE, ' ') -1) BETWEEN '"+getDate(3)+"' AND '"+getDate(4)+"'\n" +
                "ORDER BY substr(o.VISIT_DATE, 1, instr(o.VISIT_DATE, ' ') -1) DESC";

        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            SQLQuery query = session.createSQLQuery(queryString);
            List objects = query.list();

            String name="";
            int statusId=0;
            int count = 0;
            String date="";
            OrderSummary orderSummary = null;
            for(Object obj : objects){
                Object[] objArray = (Object[]) obj;

                statusId = Integer.valueOf(objArray[0].toString());
                count = Integer.valueOf(objArray[1].toString());
                name = objArray[2].toString();
                date = objArray[3].toString();

                String[] dateSplit = date.split(" ");
                String onlyDate="";
                if(dateSplit.length>0){
                    onlyDate = dateSplit[0];
                }
                int type = getDateType(onlyDate);
                if(type!=0){
                    orderSummary = new OrderSummary();
                    orderSummary.setName(name);
                    orderSummary.setDate(date);
                    orderSummary.setCount(count);
                    orderSummary.setStatusID(statusId);
                    orderSummary.setType(getDateType(onlyDate));
                    orderSummaries.add(orderSummary);
                }

            }
        }catch(Exception e){
            LOG.error(e);
        }finally{
            session.close();
        }

        return orderSummaries;
    }

    /*
    Type 0 = Not part of dashboard - ignore
    Type 1 = today
    type 2 = Yesterday
    type 3 = Day Before
     */
    private int getDateType(String date){
        int dateType = 0;

        if(getDate(0).equals(date)){
            dateType = 1;
        }else if(getDate(1).equals(date)){
            dateType = 2;
        }else if(getDate(2).equals(date)){
            dateType = 3;
        }

        return dateType;
    }

    /*
    Date format "yyyy-MM-dd"
    type = 0 means today Date
    type = 1 means yesterday
    type = 2 means day before
    type = 3 means 2 days before
    type = 4 means tomorrow
     */
    private String getDate(int type){
        String date = "";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // Create a calendar object with today date. Calendar is in java.util pakage.
        Calendar calendar = Calendar.getInstance();

        // Move calendar to required date
        if(type==1) {
            calendar.add(Calendar.DATE, -1);
        }else if(type == 2 ){
            calendar.add(Calendar.DATE, -1);
        }else if(type == 3 ){
            calendar.add(Calendar.DATE, -2);
        }else if(type == 4 ){
            calendar.add(Calendar.DATE, 1);
        }

        // Get current date of calendar which point to the yesterday now
        Date requiredDate = calendar.getTime();

        date = dateFormat.format(requiredDate).toString();
        return date;
    }

    private String getProviderSaleHTML(String staffCode){
        String html = "";
        html = "\n" +
                "<table id=\"providerSale\">\n" +
                "  <tr>\n" +
                "    <th> </th>\n" +
                "    <th>Name</th>\n" +
                "    <th>Unit</th>\n" +
                "\t<th>Amount</th>\n" +
                "  </tr>";
        List<ActualSaleData> actualSaleDataList = new ArrayList<ActualSaleData>();
        int[] today = new int[20];
        int[] yesterday=new int[20];
        int[] dayBefore=new int[20];
        int loop=0;

        actualSaleDataList = getProviderSaleForMIO(staffCode);
        if(actualSaleDataList!=null){
            for (ActualSaleData actualSaleData: actualSaleDataList){

                html += "<tr>\n" +
                        "    <td>"+actualSaleData.getProductName()+"</td>\n" +
                        "    <td>"+actualSaleData.getUnit()+"</td>\n" +
                        "    <td>"+actualSaleData.getAmount()+"</td>\n" +
                        "  </tr>";
                loop++;
            }
        }

        html+="</table>\n";


        return html;
    }

    private List<ActualSaleData> getProviderSaleForMIO(String staffCode){
        String queryString = "SELECT dd.prd_name, SUM(CASE WHEN dd.TYPE='S'\n" +
                "        THEN CAST(dd.net_qty AS INT)  WHEN dd.TYPE='R'\n" +
                "        THEN CAST(dd.net_qty AS INT)*-1 END)  AS UNITS, SUM(CASE WHEN dd.TYPE='S'\n" +
                "        THEN CAST(dd.net_value AS INT)  WHEN dd.TYPE='R'\n" +
                "        THEN CAST(dd.net_value AS INT)*-1 END) AS PRICE FROM SD_STAFF_PROVIDER SP\n" +
                "INNER JOIN MIOPROVIDERSALE dd ON dd.provider_code = sp.provider_code\n" +
                "WHERE dd.VID = (SELECT MAX(VID) FROM SD_MONTHLY_FINAL_DATA) AND dd.PROVIDER_CODE is not null AND sp.staff_code='"+staffCode+"'\n" +
                "GROUP BY dd.prd_name;";

        Session session = null;
        String productName = "";
        String unit = "";
        String amount = "";
        List<ActualSaleData> actualSaleDataList = new ArrayList<>();
        ActualSaleData actualSaleData = new ActualSaleData();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            SQLQuery query = session.createSQLQuery(queryString);
            List objects = query.list();
            for(Object obj : objects) {
                actualSaleData = new ActualSaleData();
                Object[] objArray = (Object[]) obj;

                productName = objArray[0].toString();
                unit = objArray[1].toString();
                amount = objArray[2].toString();
                actualSaleData.setProductName(productName);
                actualSaleData.setAmount(amount);
                actualSaleData.setUnit(unit);
                actualSaleDataList.add(actualSaleData);
            }
        }catch(Exception e){
            LOG.error(e);
        }finally {
            session.close();
        }


        return actualSaleDataList;
    }
}
