package com.fynuls.controller.greensales;

import com.fynuls.entity.sale.SDMonthlyFinalData;
import com.fynuls.entity.sale.SaleDetailTemp;
import com.fynuls.entity.sale.base.Employee;
import com.fynuls.entity.sale.base.PRDGroupOn;
import com.fynuls.entity.sale.base.TerritoryEmployeeMapping;
import com.fynuls.entity.sale.base.Zone;
import com.fynuls.utils.HibernateUtil;
import com.fynuls.utils.LogToFile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Syed Muhammad Hassan
 * 8th April, 2021
 */
@Controller
public class SalesDistribution {
    final static Logger LOG = Logger.getLogger("SalesDistribution");
    int autoIncrement = 1;

    @RequestMapping(value = "/distributeSales", method = RequestMethod.GET,params={"huid"})
    @ResponseBody
    public String distributeSales(String huid){
        PRDGroupOn prdgrpon = null;

        List<SDMonthlyFinalData> sdMonthlyFinalDataList = new ArrayList<>();
        //  sdMonthlyFinalDataList = (List<SDMonthlyFinalData>) HibernateUtil.getDBObjects("from SDMonthlyFinalData where TRANSACTION_DATE like '%-JUL-21'");
        sdMonthlyFinalDataList = (List<SDMonthlyFinalData>) HibernateUtil.getDBObjects("from SDMonthlyFinalData where TRANSACTION_DATE like '%-AUG-21'");
        //  sdMonthlyFinalDataList = (List<SDMonthlyFinalData>) HibernateUtil.getDBObjects("from SDMonthlyFinalData where HUID="+huid);


        long startCurrentMilis = Calendar.getInstance().getTimeInMillis();
        int i =0;
        if(sdMonthlyFinalDataList!=null && sdMonthlyFinalDataList.size()>0) {
            for (SDMonthlyFinalData sdMonthlyFinalData : sdMonthlyFinalDataList) {

                SaleDetailTemp saleDetail = new SaleDetailTemp();
                saleDetail.setHUID(sdMonthlyFinalData.getHUID());
                saleDetail.setSUID(sdMonthlyFinalData.getSUID());
                saleDetail.setSNDPOP(sdMonthlyFinalData.getSNDPOP());
                saleDetail.setCUST_NUMBER(sdMonthlyFinalData.getCUST_NUMBER());
                saleDetail.setCUST_NAME(sdMonthlyFinalData.getCUST_NAME());
                saleDetail.setADDRESS(sdMonthlyFinalData.getADDRESS());
                saleDetail.setPROVIDER_CODE(sdMonthlyFinalData.getPROVIDER_CODE());
                saleDetail.setLETTER(sdMonthlyFinalData.getLETTER());
                saleDetail.setTERRITORY(sdMonthlyFinalData.getTERRITORY());
                saleDetail.setTERRITORY_NAME(sdMonthlyFinalData.getTERRITORY_NAME());
                saleDetail.setDEPOT(sdMonthlyFinalData.getDEPOT());
                saleDetail.setSGP(sdMonthlyFinalData.getSGP());
                saleDetail.setGRP_NAME(sdMonthlyFinalData.getGRP_NAME());
                saleDetail.setPRODUCT_NO(sdMonthlyFinalData.getPRODUCT_NO());
                saleDetail.setPRD_NAME(sdMonthlyFinalData.getPRD_NAME());
                saleDetail.setPRD_SIZE(sdMonthlyFinalData.getPRD_SIZE());
                saleDetail.setPRICE(sdMonthlyFinalData.getPRICE());

                saleDetail.setTRANSACTION_DATE(sdMonthlyFinalData.getTRANSACTION_DATE());

                saleDetail.setINVOICE_NO(sdMonthlyFinalData.getINVOICE_NO());
                saleDetail.setBATCH(sdMonthlyFinalData.getBATCH());

//              saleDetail.setEXPIRY(sdMonthlyFinalData.getEXPIRY());

                saleDetail.setCLASS(sdMonthlyFinalData.getCLASS());
                String DIT_UNIT = "";
                double NET_VALUE = 0;
                double NET_QTY_NUM = 0;
                int unit = 0;
                String formula = "";
                formula = HibernateUtil.getSingleString("SELECT FORMULA FROM BASE_PRD_GRP_ON where PRD_NAME='" + sdMonthlyFinalData.getPRD_NAME() + "'");
                String CYP_CON_STR = HibernateUtil.getSingleString("SELECT CYP_CONVERSION FROM BASE_PRD_GRP_ON where PRD_NAME='" + sdMonthlyFinalData.getPRD_NAME() + "'");
                double CYP_CON = 0;
                if(CYP_CON_STR ==null || CYP_CON_STR.equals("") ){
                    CYP_CON = 0;
                }else{
                    CYP_CON = Double.valueOf(CYP_CON_STR);
                }
                DIT_UNIT = HibernateUtil.getSingleString("SELECT QTY_CONVERSION FROM BASE_PRD_GRP_ON where PRD_NAME='" + sdMonthlyFinalData.getPRD_NAME() + "'");
                if (DIT_UNIT != null && !DIT_UNIT.equals("")) {
                    unit = Integer.valueOf(DIT_UNIT);
                }
                double sum = Double.valueOf(sdMonthlyFinalData.getNET_QTY()) + sdMonthlyFinalData.getBONUS();

                NET_QTY_NUM = Double.valueOf(sdMonthlyFinalData.getNET_QTY());
                NET_VALUE = sdMonthlyFinalData.getNET_VALUE();
                double bonus=sdMonthlyFinalData.getBONUS();
                double discounts = sdMonthlyFinalData.getDISCOUNTS();
                double BONUS_VALUE = sdMonthlyFinalData.getBONUS_VALUE();
                if (sdMonthlyFinalData.getTYPE().equals("R")) {
                    NET_QTY_NUM = NET_QTY_NUM * -1;
                    NET_VALUE = NET_VALUE * -1;
                    bonus = bonus*-1;
                    discounts = discounts *-1;
                    BONUS_VALUE = BONUS_VALUE *-1;
                    sum = sum *-1;
                }
                double EACH_QTY_NUM = sum * unit;
                double cyp = 0;
                if(formula.equals("D")){
                    cyp = EACH_QTY_NUM / CYP_CON;
                }else if (formula.equals("M")){
                    cyp  = EACH_QTY_NUM * CYP_CON;
                }else if(formula.equals("MD")){
                    cyp = (EACH_QTY_NUM * 10)/CYP_CON;
                }
                double TP_SALE_VALUE = NET_VALUE + BONUS_VALUE;
                double NET_SALE_VALUE = TP_SALE_VALUE - bonus - discounts;
                saleDetail.setNET_SALE_VALUE(NET_SALE_VALUE);
                saleDetail.setTP_SALE_VALUE(TP_SALE_VALUE);
                saleDetail.setNET_QTY(NET_QTY_NUM);
                saleDetail.setNET_VALUE(NET_VALUE);
                saleDetail.setE_QTY(EACH_QTY_NUM);
                saleDetail.setBONUS(bonus);
                saleDetail.setBONUS_VALUE(BONUS_VALUE);
                saleDetail.setDISCOUNTS(discounts);
                saleDetail.setCYP(cyp);
                saleDetail.setGSM_PRODUCT_CODE(sdMonthlyFinalData.getGSM_PRODUCT_CODE());

                saleDetail.setGSM_TOWN(sdMonthlyFinalData.getGSM_TOWN());
                saleDetail.setSALEDISTRICT(sdMonthlyFinalData.getDISTRICT());
                saleDetail.setSALETEHSIL(sdMonthlyFinalData.getTEHSIL());
                saleDetail.setTYPE(sdMonthlyFinalData.getTYPE());
                saleDetail.setREMARKS(sdMonthlyFinalData.getREMARKS());
                saleDetail.setVID(sdMonthlyFinalData.getVID());
                //saleDetail.setSYSTEM_DATE(sdMonthlyFinalData.getSYSTEM_DATE());
                saleDetail.setBONUS_DISCOUNT(discounts+bonus);
                saleDetail.setFOC_DISCOUNT(sdMonthlyFinalData.getFOC_DISCOUNT());
                saleDetail.setBOOKED_BY(sdMonthlyFinalData.getBOOKED_BY());
                saleDetail.setSECTION_CODE(sdMonthlyFinalData.getSECTION_CODE());
                saleDetail.setSECTION_NAME(sdMonthlyFinalData.getSECTION_NAME());
                saleDetail.setNATURE(sdMonthlyFinalData.getNATURE());

                //VIEW
                try {
                    String POSITION_ID = "";

                    if(sdMonthlyFinalData.getPROVIDER_CODE()!=null){
                        POSITION_ID = HibernateUtil.getSingleString("SELECT position_id from BASE_EMP_TAGGING where tagged_to = '" + sdMonthlyFinalData.getPROVIDER_CODE() + "'");
                        if(!POSITION_ID.equals("")){
                            saleDetail = saveEmployeeDetailsFromPositionCode(POSITION_ID, saleDetail);
                            saleDetail.setGSM_REMARKS("Depends on tagging");
                        }else{
                            POSITION_ID = getPOSITION_CODEFromTerritoryMapping(sdMonthlyFinalData.getTERRITORY());
                            if(!POSITION_ID.equals("")) {
                                saleDetail = saveEmployeeDetailsFromPositionCode(POSITION_ID, saleDetail);
                                saleDetail.setGSM_REMARKS("Provider tagging not found, territory mapping used for position code");
                            }else{
                                POSITION_ID = getPOSITION_CODEFromDepot(sdMonthlyFinalData.getDEPOT(),"MIO");
                                saleDetail = saveEmployeeDetailsFromPositionCode(POSITION_ID, saleDetail);
                                saleDetail.setGSM_REMARKS("Provider tagging not found, territory mapping not found, depot mapping used for position code");
                            }
                        }
                    }
                    String tehsil_id = "";

                    if (sdMonthlyFinalData.getPROVIDER_CODE() != null) {
                        tehsil_id = HibernateUtil.getSingleString("SELECT TEHSIL_ID from BASE_PROVIDER_TEHSIL where PROVIDERCODE = '" + sdMonthlyFinalData.getPROVIDER_CODE() + "'");
                    } else {
                        tehsil_id = HibernateUtil.getSingleString("SELECT MAX(tehsil_id) from BASE_TEHSIL_SNDPOP where sndpop_id = SUBSTR('" + sdMonthlyFinalData.getSNDPOP() + "',1,9) || SUBSTR('" + sdMonthlyFinalData.getDEPOT() + "',1,3) || '" + sdMonthlyFinalData.getTERRITORY() + "'");
                    }

                    setLocationDetailsFromTehsilID(tehsil_id, saleDetail,sdMonthlyFinalData.getCLASS());

                    prdgrpon = null;
                    // Fetch Group on against each Sale record
                    String query = "from PRDGroupOn where PRD_NAME='" + sdMonthlyFinalData.getPRD_NAME() + "'";
                    List<PRDGroupOn> prdgrpons = (List<PRDGroupOn>) HibernateUtil.getDBObjects(query);

                    if (prdgrpons != null && prdgrpons.size() > 0) {
                        prdgrpon = new PRDGroupOn();
                        prdgrpon = prdgrpons.get(0);
                    }

                    ArrayList<PRDGroupOn> prdGroupOns = (ArrayList<PRDGroupOn>) HibernateUtil.getDBObjects("from PRDGroupOn where PRD_NAME='" + sdMonthlyFinalData.getPRD_NAME() + "'");
                    PRDGroupOn prdGroupOn = new PRDGroupOn();
                    if (prdGroupOns != null && prdGroupOns.size() > 0) {
                        prdGroupOn = prdGroupOns.get(0);
                    }else{
                        saleDetail.setGSM_REMARKS("prdGroupOn was null");
                    }

                    saleDetail.setPOSITION_CODE(POSITION_ID);

                    saleDetail.setGROUPON(prdGroupOn.getGROUP_ON());
                    saleDetail.setGRP(prdGroupOn.getGRP());
                    saleDetail.setGRP_CATEGORY(prdGroupOn.getPRD_CAT());
                    saleDetail.setPRODUCTGROUP(prdGroupOn.getPRD_GRP());
                    saleDetail.setPROVIDERCODE(sdMonthlyFinalData.getPROVIDER_CODE());

                    if (sdMonthlyFinalData.getPROVIDER_CODE() != null) {
                        //When provider code is not null
                        String nature = sdMonthlyFinalData.getNATURE();
                        if (nature != null && nature.equals("Direct HS")) {
                            if (!saleDetail.getTEAM().equals("Health Service")) {
                                String territory = sdMonthlyFinalData.getTERRITORY();
                                //Fetching Employee information
                                saleDetail.setGSM_REMARKS("Forcefully sales belongs to CHO");
                                saleDetail = getSaleDetailObject(saleDetail, "CHO", territory);
                            }
                        } else if (nature != null && nature.equals("Direct IPC")) {
                            if (!saleDetail.getTEAM().equals("Inter Personal Communication")) {
                                String remarks = "Position Mapping Required";
                                saleDetail.setGSM_REMARKS(remarks);
                            }
                        } else if (nature != null && nature.equals("Direct Pharma")) {
                            if (!saleDetail.getTEAM().equals("Pharmaceutical")) {
                                String territory = sdMonthlyFinalData.getTERRITORY();
                                saleDetail.setGSM_REMARKS("Forcefully sales belongs to MIO");
                                //Fetching Employee information
                                saleDetail = getSaleDetailObject(saleDetail, "MIO", territory);
                            }
                        } else if (prdgrpon.getGRP().equals("Nutraceutical")) {
                            //  Sale belongs to CHO
                            if (POSITION_ID.contains("CHO")) {
                                //Already tagged cho will get the sales
                            } else if (!POSITION_ID.contains("CHO")) {
                                //We need to tag it to CHO through town staff mapping.
                                //We can have Town from SDMonthlyFinalData
                                String territory = sdMonthlyFinalData.getTERRITORY();
                                saleDetail.setGSM_REMARKS("Forcefully sales belongs to CHO");
                                //Fetching Employee information
                                saleDetail = getSaleDetailObject(saleDetail, "CHO", territory);

                            }
                        } else if (prdgrpon.getPRD_GRP().contains("Novaject")
                                || prdgrpon.getPRD_GRP().contains("Femi Ject")
                                || prdgrpon.getPRD_GRP().contains("Enofer")) {
                            if (!POSITION_ID.contains("MIO")) {
                                String territory = sdMonthlyFinalData.getTERRITORY();
                                saleDetail.setGSM_REMARKS("Forcefully sales belongs to MIO");
                                //Fetching Employee information
                                saleDetail = getSaleDetailObject(saleDetail, "MIO", territory);
                            }

                        }else if (prdgrpon.getPRD_NAME().contains("OEM")
                                || prdgrpon.getPRD_NAME().contains("ZINKUP")) {
                            if (!POSITION_ID.contains("CHO")) {
                                POSITION_ID = HibernateUtil.getSingleString("SELECT position_id from BASE_EMP_TAGGING where position_id like '%CHO%' AND tagged_to = '" + sdMonthlyFinalData.getPROVIDER_CODE() + "'");
                                if(POSITION_ID!=null && POSITION_ID.equals("")){
                                    saleDetail.setGSM_REMARKS("POSITION CODE required against CHO");
                                }else{
                                    saleDetail = saveEmployeeDetailsFromPositionCode(POSITION_ID, saleDetail);
                                }
                            }

                        } else {
                            //  Depends on tagging
                            saleDetail.setGSM_REMARKS("Depends on tagging");
                        }

                    } else {
                        //When provider code is null
                        String nature = sdMonthlyFinalData.getNATURE();
                        if (nature != null && nature.equals("Direct HS")) {
                            if (!saleDetail.getTEAM().equals("Health Service")) {
                                String territory = sdMonthlyFinalData.getTERRITORY();
                                //Fetching Employee information
                                saleDetail.setGSM_REMARKS("Forcefully sales belongs to HS-AM");
                                saleDetail = getSaleDetailObject(saleDetail, "HS-AM", territory);
                            }
                        } else if (nature != null && nature.equals("Direct IPC")) {
                            if (!saleDetail.getTEAM().equals("Inter Personal Communication")) {
                                String remarks = "Position Mapping Required";
                                saleDetail.setGSM_REMARKS(remarks);
                            }
                        } else if (nature != null && nature.equals("Direct Pharma")) {
                            if (!saleDetail.getTEAM().equals("Pharmaceutical")) {
                                String territory = sdMonthlyFinalData.getTERRITORY();
                                saleDetail.setGSM_REMARKS("Forcefully sales belongs to PHR-ASM");
                                //Fetching Employee information
                                saleDetail = getSaleDetailObject(saleDetail, "PHR-ASM", territory);
                            }
                        } else if (nature != null && nature.equals("Direct FMCG01")) {
                            if (!saleDetail.getTEAM().equals("Fast Moving Consumer Goods - 1")) {
                                String territory = sdMonthlyFinalData.getTERRITORY();
                                saleDetail = getSaleDetailObject(saleDetail, "FMCG01-ASM", territory);
                                saleDetail.setGSM_REMARKS("Forcefully sales belongs to FMCG01-ASM");
                                if(saleDetail.getPOSITION_CODE()==null || saleDetail.getPOSITION_CODE().equals("")){
                                    saleDetail = getSaleDetailObject(saleDetail, "FMCG-ASM", territory);
                                    saleDetail.setGSM_REMARKS("Forcefully sales belongs to FMCG-ASM");
                                }
                            }
                        }else if (nature != null && nature.equals("Direct FMCG02")) {
                            if (!saleDetail.getTEAM().equals("Fast Moving Consumer Goods - 2")) {
                                String territory = sdMonthlyFinalData.getTERRITORY();
                                saleDetail = getSaleDetailObject(saleDetail, "FMCG02-ASM", territory);
                                saleDetail.setGSM_REMARKS("Forcefully sales belongs to FMCG02-ASM");
                                if(saleDetail.getPOSITION_CODE()==null || saleDetail.getPOSITION_CODE().equals("")){
                                    saleDetail = getSaleDetailObject(saleDetail, "FMCG-ASM", territory);
                                    saleDetail.setGSM_REMARKS("Forcefully sales belongs to FMCG-ASM");
                                }
                            }
                        }else if (nature != null && nature.equals("Direct HS-Inst")) {
                            if (!saleDetail.getTEAM().equals("Other Institutional")) {
                                saleDetail.setREMARKS("Other HS Institute");
                            }
                        }else if (nature != null && nature.equals("Direct MKT-Inst")) {
                            if (!saleDetail.getTEAM().equals("Other Institutional")) {
                                saleDetail.setREMARKS("Other MKT Institute");
                            }
                        }else if (prdgrpon.getGRP().equals("Nutraceutical")) {
                            //Sale belongs to CHO
                            //We need to tag it to CHO through town staff mapping.
                            //We can have Town from SDMonthlyFinalData
                            String territory = sdMonthlyFinalData.getTERRITORY();
                            saleDetail.setGSM_REMARKS("Forcefully sales belongs to CHO");
                            //Fetching Employee information
                            saleDetail = getSaleDetailObject(saleDetail, "CHO", territory);
                        }else if (prdgrpon.getPRD_NAME().contains("OEM")
                                || prdgrpon.getPRD_NAME().contains("ZINKUP")) {

                            saleDetail.setGSM_REMARKS("POSITION CODE required against CHO ERROR (Lawaris)");

                        } else if (sdMonthlyFinalData.getSGP() > 830
                                && prdgrpon.getGRP().equals("CONDOM")) {
                            String POSITION_CODE = "";
                            if(sdMonthlyFinalData.getPRD_NAME().toLowerCase().contains("do") ||
                                    sdMonthlyFinalData.getPRD_NAME().toLowerCase().contains("sathi")){
                                POSITION_CODE = HibernateUtil.getSingleString("SELECT POSITIONCODE FROM BASE_DEPOT_SECTION_TO_POSITION WHERE POSITIONCODE IN ('FMCG', 'FMCG01') AND NewSectionCode = '"+saleDetail.getDEPOT()+saleDetail.getCUST_NAME()+"'");

                            }else if(sdMonthlyFinalData.getPRD_NAME().toLowerCase().contains("touch")){
                                POSITION_CODE = HibernateUtil.getSingleString("SELECT POSITIONCODE FROM BASE_DEPOT_SECTION_TO_POSITION WHERE POSITIONCODE IN ('FMCG', 'FMCG02') AND NewSectionCode = '"+saleDetail.getDEPOT()+saleDetail.getCUST_NAME()+"'");
                            }
                            saleDetail = saveEmployeeDetailsFromPositionCode(POSITION_CODE, saleDetail);
                            saleDetail.setREMARKS("Tagging from new Section Mapping with concatenation");
                        }else if(sdMonthlyFinalData.getSGP() < 830 &&
                                prdgrpon.getGRP().equals("CONDOM")){
                            String POSITION_CODE = "";
                            if(sdMonthlyFinalData.getPRD_NAME().toLowerCase().contains("do") ||
                                    sdMonthlyFinalData.getPRD_NAME().toLowerCase().contains("sathi")){
                                saleDetail = getSaleDetailObject(saleDetail, "SPO", saleDetail.getTERRITORY());
                                if (saleDetail.getPOSITION_CODE().equals("")) {
                                    saleDetail = getSaleDetailObject(saleDetail, "ASM", saleDetail.getTERRITORY());
                                    if(saleDetail.getPOSITION_CODE().equals("")){
                                        saleDetail.setREMARKS("Territory mapping required with ASM or SPO");
                                    }
                                }
                            }else {
                                saleDetail = getSaleDetailObject(saleDetail, "MIO", saleDetail.getTERRITORY());
                            }
                        }
                        saleDetail = saveEmployeeDetailsFromPositionCode(saleDetail.getPOSITION_CODE(),saleDetail);
                    }

                    saleDetail = getManagedChannel(saleDetail);
                    HibernateUtil.save(saleDetail);
                } catch (Exception e) {
                    LOG.severe(e.getMessage());
                    printError(e, sdMonthlyFinalData.getHUID());
                }

            }
        }

        long endCurrentMilis = Calendar.getInstance().getTimeInMillis();
        long duration = endCurrentMilis - startCurrentMilis;

        return String.valueOf(duration);
    }

    private String getPOSITION_CODEFromTerritoryMapping(String territory) {
        String positionCode = "";
        positionCode = HibernateUtil.getSingleString("SELECT MAX(EMP_ID) FROM base_territory_emp_mapping where EMP_ID like '%MIO%' AND territory_code ='"+territory+"'");
        return positionCode;
    }

    private SaleDetailTemp getPOSITIONCODE(String booked_by, String CUST_NUMBER, double depot, SaleDetailTemp saleDetail, String PRD_GRP) {
        String team = "";
        team  = getTeam(PRD_GRP);

        String POSITION_ID = getPOSITION_CODEFromPJP(booked_by, team);

        if(POSITION_ID!=null && !POSITION_ID.equals("")){
            saleDetail.setPOSITION_CODE(POSITION_ID);
            saleDetail.setGSM_REMARKS("Sales belongs to Tagged SPO. From PJP Mapping");
        }else{
            POSITION_ID = getPOSITION_CODEFromCustomer(CUST_NUMBER, team);
            if(POSITION_ID!=null && !POSITION_ID.equals("")){
                saleDetail.setPOSITION_CODE(POSITION_ID);
                saleDetail.setGSM_REMARKS("Sales belongs to Tagged SPO. From Customer Mapping");
            }else {
                POSITION_ID = getPOSITION_CODEFromDepot(depot,team);

                if(POSITION_ID!=null && !POSITION_ID.equals("")){
                    saleDetail.setPOSITION_CODE(POSITION_ID);
                    saleDetail.setGSM_REMARKS("Sales belongs to Tagged SPO. From Depot Mapping");
                }else {
                    saleDetail.setGSM_REMARKS("Sales belongs to Tagged SPO. No mapping found");
                }


            }
        }

        return saleDetail;
    }

    private String getTeam(String prd_grp) {
        String team = "";
        if(prd_grp.contains("Sathi") ||
                prd_grp.contains("Do")){
            team = "FMCG01";

        }else if(prd_grp.contains("Touch")){
            team = "FMCG02";
        }
        return team;
    }

    private String getPOSITION_CODEFromPJP(String booked_by, String team) {
        String POSITION_ID = HibernateUtil.getSingleString("SELECT POSITION_CODE FROM BASE_EMP_PJP where PJP_CODE='"+booked_by+"' and POSITION_CODE LIKE '%"+team+"%'");

        return POSITION_ID;
    }

    private String getPOSITION_CODEFromCustomer(String customerNumber, String team) {
        String POSITION_ID = HibernateUtil.getSingleString("SELECT POSITION_CODE FROM BASE_EMP_CUSTOMER where CUSTOMER_CODE='"+customerNumber+"'  and POSITION_CODE LIKE '%"+team+"%'");

        return POSITION_ID;
    }

    private String getPOSITION_CODEFromDepot(double depot, String team) {
        String POSITION_ID = HibernateUtil.getSingleString("SELECT POSITION_CODE FROM BASE_EMP_DEPOT_MAPPING where POSITION_CODE LIKE '%"+team+"%' and DEPOT_CODE="+depot);

        return POSITION_ID;
    }

    private SaleDetailTemp setLocationDetailsFromTehsilID(String tehsil_id, SaleDetailTemp saleDetail, String CLASS ) {
        String TEHSIL = HibernateUtil.getSingleString("SELECT name from base_tehsil_master where id = " + tehsil_id);
        String dist_id = HibernateUtil.getSingleString("SELECT dist_id from base_dist_tehsil where tehsil_id = " + tehsil_id);
        String DISTRICT = HibernateUtil.getSingleString("SELECT name from base_district_master where id = " + dist_id);
        String region_id = HibernateUtil.getSingleString("SELECT region_id from base_dist_region_mapping where dist_id = '" + dist_id + "'");
        String region = HibernateUtil.getSingleString("SELECT name from base_region_master where id = '" + region_id + "'");
        String province_id = HibernateUtil.getSingleString("SELECT province_id from base_dist_province where dist_id = '" + dist_id + "'");
        String province = HibernateUtil.getSingleString("SELECT name from base_province_master where id = '" + province_id + "'");
        String channel = HibernateUtil.getSingleString("SELECT channel from base_mnp_channel_mapping where class_code = '" + CLASS + "'");

        saleDetail.setDISTRICT(DISTRICT);
        saleDetail.setTEHSIL(TEHSIL);
        saleDetail.setPROVINCE(province);
        saleDetail.setREGION(region);
        saleDetail.setCHANNEL(channel);
        return saleDetail;
    }

    private SaleDetailTemp saveEmployeeDetailsFromPositionCode(String POSITION_ID, SaleDetailTemp saleDetail) {
        String sas_id = HibernateUtil.getSingleString("SELECT sas_id from BASE_EMPID_POSITIONID_MAPPING where position_id = '" + POSITION_ID + "'");
        String EMPLOYEE_ID = HibernateUtil.getSingleString("SELECT EMPLOYEE_ID from BASE_EMPID_POSITIONID_MAPPING where position_id = '" + POSITION_ID + "'");
        String teamregion_id = HibernateUtil.getSingleString("SELECT teamregion_id from base_emp_position_teamregion where position_id = '" + POSITION_ID + "'");
        String teamregion = HibernateUtil.getSingleString("SELECT NAME from base_team_region where id =" + teamregion_id);
        String TEAM_ID = HibernateUtil.getSingleString("SELECT TEAM_ID from BASE_EMP_POSITION_TEAM where POSITION_ID = '" + POSITION_ID + "'");
        String TEAM = HibernateUtil.getSingleString("SELECT name from BASE_TEAM_DEPT where id = " + TEAM_ID);
        String zone_id =HibernateUtil.getSingleString("SELECT zone_id from base_emp_zone_mapping where position_id = '" + POSITION_ID + "'");
        String zone = HibernateUtil.getSingleString("SELECT zone from BASE_ZONE where id = " + zone_id);
        String subzone = HibernateUtil.getSingleString("SELECT subzone from BASE_ZONE where id = " + zone_id);

        saleDetail.setSASCODE(sas_id);
        saleDetail.setEMPLOYEEID(EMPLOYEE_ID);
        saleDetail.setTEAMREGION(teamregion);
        saleDetail.setZONE(zone);
        saleDetail.setSUBZONE(subzone);
        saleDetail.setTEAM(TEAM);

        return saleDetail;
    }

    private void printError(Exception e, double huid) {
        LogToFile.log(null,"severe", "e.getMessage() : "+ e.getMessage() +
                " \n e.getCause() : "+ e.getCause() +
                " \n e.e.getStackTrace()"+e.getStackTrace().toString() +
                " \n HUID : "+huid
        );

    }

    private SaleDetailTemp getManagedChannel(SaleDetailTemp saleDetail) {
        if(saleDetail.getPROVIDER_CODE()!=null && !saleDetail.getPROVIDER_CODE().equals("")
                && !saleDetail.getCHANNEL().equals("Provider")){
            saleDetail.setCHANNEL("Provider");
        } else if(saleDetail.getCHANNEL().equals("Provider")
                && saleDetail.getPROVIDER_CODE()==null){
            saleDetail.setCHANNEL("Pharmacies");
        }
        return saleDetail;
    }

    private SaleDetailTemp getSaleDetailObject(SaleDetailTemp saleDetail, String saleTo, String territory){
        String employeePositionId = "";
        //Fetching Employee information

        String countSharing =  HibernateUtil.getSingleString("SELECT COUNT(*) FROM BASE_TERRITORY_EMP_MAPPING where emp_id like '%"+saleTo+"%' and territory_code='"+territory+"'");
        if(Integer.valueOf(countSharing)==0){
            employeePositionId = HibernateUtil.getSingleString("SELECT POSITION_CODE FROM BASE_EMP_DEPOT_MAPPING WHERE POSITION_CODE like '%"+saleTo+"%' and DEPOT_CODE="+saleDetail.getDEPOT());
            saleDetail = setLocationInSales(employeePositionId, saleDetail);
        }else if(Integer.valueOf(countSharing)==1){
            employeePositionId = HibernateUtil.getSingleString("SELECT EMP_ID FROM BASE_TERRITORY_EMP_MAPPING where emp_id like '%"+saleTo+"%' and territory_code='"+territory+"'");
            saleDetail = setLocationInSales(employeePositionId, saleDetail);
        }else{
            boolean isFirstTime = true;
            double id = 0;
            ArrayList<TerritoryEmployeeMapping> territoryEmployeeMappings = (ArrayList<TerritoryEmployeeMapping>) HibernateUtil.getDBObjects("from TerritoryEmployeeMapping where EMP_ID like '%"+saleTo+"%' and TERRITORY_CODE='"+territory+"'");
            for(int i =0 ; i<territoryEmployeeMappings.size();i++){
                SaleDetailTemp copySale = new SaleDetailTemp();
                try {
                    copySale = (SaleDetailTemp)saleDetail.clone();
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
                TerritoryEmployeeMapping territoryEmployeeMapping = new TerritoryEmployeeMapping();
                territoryEmployeeMapping = territoryEmployeeMappings.get(i);
                copySale = breakSalesOnPercentage(territoryEmployeeMapping, copySale);
                copySale = setLocationInSales(territoryEmployeeMapping.getEMP_ID(), copySale);
                autoIncrement ++;
                if(isFirstTime){
                    id = 1000000000 + saleDetail.getHUID();
                    isFirstTime = false;
                }
                id += autoIncrement;
                copySale.setHUID(id);
                if(i+1<territoryEmployeeMappings.size()){
                    copySale = getManagedChannel(copySale);
                    HibernateUtil.save(copySale);
                }else{
                    saleDetail = copySale;
                }
            }
        }
        return saleDetail;
    }

    private SaleDetailTemp setLocationInSales(String employeePositionId, SaleDetailTemp saleDetail) {

        String employeeID = HibernateUtil.getSingleString("SELECT EMPLOYEE_ID FROM BASE_EMPID_POSITIONID_MAPPING where POSITION_ID='"+employeePositionId+"'");
        ArrayList<Employee> employees =
                (ArrayList<Employee>) HibernateUtil.getDBObjects("from Employee where ID='"+employeeID+"'");
        Employee employee = new Employee();
        if(employees!=null && employees.size()>0){
            employee = employees.get(0);
        }
        String teamRegionId = HibernateUtil.getSingleString("SELECT TEAMREGION_ID FROM BASE_EMP_POSITION_TEAMREGION where POSITION_ID='"+employeePositionId+"'");
        String teamRegion = HibernateUtil.getSingleString("SELECT NAME FROM BASE_TEAM_REGION where id= "+teamRegionId);
        String zoneId =  HibernateUtil.getSingleString("SELECT ZONE_ID from BASE_EMP_ZONE_MAPPING where POSITION_ID='"+employeePositionId+"'");
        ArrayList<Zone> zoneList = (ArrayList<Zone>) HibernateUtil.getDBObjects("from Zone where id =  "+zoneId);
        Zone zone = new Zone();
        if(zoneList!=null && zoneList.size()>0){
            zone = zoneList.get(0);
        }
        String teamId = HibernateUtil.getSingleString("SELECT TEAM_ID FROM BASE_EMP_POSITION_TEAM where POSITION_ID='"+employeePositionId+"'");
        String team = HibernateUtil.getSingleString("SELECT NAME FROM BASE_TEAM_DEPT where id= "+teamId);
        String strZone = zone.getZONE();
        String strSubZone = zone.getSUBZONE();
        saleDetail.setTEAM(team);
        saleDetail.setZONE(strZone);
        saleDetail.setSUBZONE(strSubZone);
        saleDetail.setPOSITION_CODE(employeePositionId);
        saleDetail.setEMPLOYEEID(employeeID);
        saleDetail.setTEAMREGION(teamRegion);

        return saleDetail;
    }
    private double roundOff(double val)
    {
        DecimalFormat f = new DecimalFormat("##.00");
        return Double.valueOf(f.format(val));
    }

    private SaleDetailTemp breakSalesOnPercentage(TerritoryEmployeeMapping territoryEmployeeMapping, SaleDetailTemp saleDetail) {
        double percSharing = Double.valueOf(territoryEmployeeMapping.getSHARING())/100;
        saleDetail.setPRICE(roundOff(saleDetail.getPRICE()*percSharing));
        saleDetail.setNET_QTY(roundOff(saleDetail.getNET_QTY() * percSharing));
        saleDetail.setNET_VALUE(roundOff(saleDetail.getNET_VALUE() * percSharing));
        saleDetail.setBONUS(roundOff(saleDetail.getBONUS()*percSharing));
        saleDetail.setBONUS_VALUE(roundOff(saleDetail.getBONUS_VALUE()*percSharing));
        saleDetail.setDISCOUNTS(roundOff(saleDetail.getDISCOUNTS()*percSharing));
        saleDetail.setCYP(roundOff(saleDetail.getCYP()*percSharing));
        saleDetail.setBONUS_DISCOUNT(roundOff(saleDetail.getBONUS_DISCOUNT()*percSharing));
        saleDetail.setFOC_DISCOUNT(roundOff(saleDetail.getFOC_DISCOUNT()*percSharing));
        saleDetail.setE_QTY(roundOff(saleDetail.getE_QTY()*percSharing));
        saleDetail.setTP_SALE_VALUE(roundOff(saleDetail.getTP_SALE_VALUE()*percSharing));
        saleDetail.setNET_SALE_VALUE(roundOff(saleDetail.getNET_SALE_VALUE()*percSharing));
        return saleDetail;
    }
}
