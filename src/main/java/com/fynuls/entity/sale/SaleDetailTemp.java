package com.fynuls.entity.sale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author Syed Muhammad Hassan
 * 30th April,2021
 */

@Entity
@Table(name="SALE_DETAIL_TEMP")
public class SaleDetailTemp implements Cloneable{
    @Id
    @Column(name="HUID")
    private double HUID;
    @Column(name="SUID")
    private double SUID;
    @Column(name="SNDPOP")
    private String SNDPOP;
    @Column(name="CUST_NUMBER")
    private String CUST_NUMBER;
    @Column(name="CUST_NAME")
    private String CUST_NAME;
    @Column(name="ADDRESS")
    private String ADDRESS;
    @Column(name="PROVIDER_CODE")
    private String PROVIDER_CODE;
    @Column(name="LETTER")
    private String LETTER;
    @Column(name="TERRITORY")
    private String TERRITORY;
    @Column(name="TERRITORY_NAME")
    private String TERRITORY_NAME;
    @Column(name="DEPOT")
    private double DEPOT;
    @Column(name="SGP")
    private double SGP;
    @Column(name="GRP_NAME")
    private String GRP_NAME;
    @Column(name="PRODUCT_NO")
    private String PRODUCT_NO;
    @Column(name="PRD_NAME")
    private String PRD_NAME;
    @Column(name="PRD_SIZE")
    private String PRD_SIZE;
    @Column(name="PRICE")
    private double PRICE;
    @Column(name="TRANSACTION_DATE")
    private Date TRANSACTION_DATE;
    @Column(name="INVOICE_NO")
    private String INVOICE_NO;
    @Column(name="BATCH")
    private String BATCH;
    @Column(name="EXPIRY")
    private String EXPIRY;
    @Column(name="CLASS")
    private String CLASS;
    @Column(name="NET_QTY")
    private double NET_QTY;
    @Column(name="NET_VALUE")
    private double NET_VALUE;
    @Column(name="BONUS")
    private double BONUS;
    @Column(name="BONUS_VALUE")
    private double BONUS_VALUE;
    @Column(name="DISCOUNTS")
    private double  DISCOUNTS;
    @Column(name="CYP")
    private double CYP;
    @Column(name="GSM_PRODUCT_CODE")
    private String GSM_PRODUCT_CODE;
    @Column(name="GSM_TOWN")
    private String GSM_TOWN;
    @Column(name="SALE_DISTRICT")
    private String SALEDISTRICT;
    @Column(name="SALE_TEHSIL")
    private String SALETEHSIL;
    @Column(name="TYPE")
    private String TYPE;
    @Column(name="REMARKS")
    private String REMARKS;
    @Column(name="VID")
    private double VID;
    @Column(name="SYSTEM_DATE")
    private String SYSTEM_DATE;
    @Column(name="BONUS_DISCOUNT")
    private double BONUS_DISCOUNT;
    @Column(name="FOC_DISCOUNT")
    private double FOC_DISCOUNT;
    @Column(name="BOOKED_BY")
    private String BOOKED_BY;
    @Column(name="SECTION_CODE")
    private String SECTION_CODE;
    @Column(name="SECTION_NAME")
    private String SECTION_NAME;
    @Column(name="NATURE")
    private String NATURE;

    //View Table Columns
    @Column(name="POSITION_CODE")
    private String POSITION_CODE;
    @Column(name="SASCODE")
    private String SASCODE;
    @Column(name="EMPLOYEEID")
    private String EMPLOYEEID;
    @Column(name="TEAMREGION")
    private String TEAMREGION;
    @Column(name="ZONE")
    private String ZONE;
    @Column(name="SUBZONE")
    private String SUBZONE;
    @Column(name="DISTRICT")
    private String DISTRICT;
    @Column(name="TEHSIL")
    private String TEHSIL;
    @Column(name="PROVINCE")
    private String PROVINCE;
    @Column(name="REGION")
    private String REGION;
    @Column(name="TEAM")
    private String TEAM;
    @Column(name="CHANNEL")
    private String CHANNEL;
    @Column(name="PRODUCT")
    private String PRODUCT;
    @Column(name="GROUPON")
    private String GROUPON;
    @Column(name="GRP")
    private String GRP;
    @Column(name="GRP_CATEGORY")
    private String GRP_CATEGORY;
    @Column(name="PRODUCTGROUP")
    private String PRODUCTGROUP;
    @Column(name="PROVIDERCODE")
    private String PROVIDERCODE;
    @Column(name="GSM_REMARKS")
    private String GSM_REMARKS;
    @Column(name="E_QTY")
    private double E_QTY;

    @Column(name="TP_SALE_VALUE")
    private double TP_SALE_VALUE;

    @Column(name="NET_SALE_VALUE")
    private double NET_SALE_VALUE;

    public double getHUID() {
        return HUID;
    }

    public void setHUID(double HUID) {
        this.HUID = HUID;
    }

    public double getSUID() {
        return SUID;
    }

    public void setSUID(double SUID) {
        this.SUID = SUID;
    }

    public String getSNDPOP() {
        return SNDPOP;
    }

    public void setSNDPOP(String SNDPOP) {
        this.SNDPOP = SNDPOP;
    }

    public String getCUST_NUMBER() {
        return CUST_NUMBER;
    }

    public void setCUST_NUMBER(String CUST_NUMBER) {
        this.CUST_NUMBER = CUST_NUMBER;
    }

    public String getCUST_NAME() {
        return CUST_NAME;
    }

    public void setCUST_NAME(String CUST_NAME) {
        this.CUST_NAME = CUST_NAME;
    }

    public String getADDRESS() {
        return ADDRESS;
    }

    public void setADDRESS(String ADDRESS) {
        this.ADDRESS = ADDRESS;
    }

    public String getPROVIDER_CODE() {
        return PROVIDER_CODE;
    }

    public void setPROVIDER_CODE(String PROVIDER_CODE) {
        this.PROVIDER_CODE = PROVIDER_CODE;
    }

    public String getLETTER() {
        return LETTER;
    }

    public void setLETTER(String LETTER) {
        this.LETTER = LETTER;
    }

    public String getTERRITORY() {
        return TERRITORY;
    }

    public void setTERRITORY(String TERRITORY) {
        this.TERRITORY = TERRITORY;
    }

    public String getTERRITORY_NAME() {
        return TERRITORY_NAME;
    }

    public void setTERRITORY_NAME(String TERRITORY_NAME) {
        this.TERRITORY_NAME = TERRITORY_NAME;
    }

    public double getDEPOT() {
        return DEPOT;
    }

    public void setDEPOT(double DEPOT) {
        this.DEPOT = DEPOT;
    }

    public double getSGP() {
        return SGP;
    }

    public void setSGP(double SGP) {
        this.SGP = SGP;
    }

    public String getGRP_NAME() {
        return GRP_NAME;
    }

    public void setGRP_NAME(String GRP_NAME) {
        this.GRP_NAME = GRP_NAME;
    }

    public String getPRODUCT_NO() {
        return PRODUCT_NO;
    }

    public void setPRODUCT_NO(String PRODUCT_NO) {
        this.PRODUCT_NO = PRODUCT_NO;
    }

    public String getPRD_NAME() {
        return PRD_NAME;
    }

    public void setPRD_NAME(String PRD_NAME) {
        this.PRD_NAME = PRD_NAME;
    }

    public String getPRD_SIZE() {
        return PRD_SIZE;
    }

    public void setPRD_SIZE(String PRD_SIZE) {
        this.PRD_SIZE = PRD_SIZE;
    }

    public double getPRICE() {
        return PRICE;
    }

    public void setPRICE(double PRICE) {
        this.PRICE = PRICE;
    }

    public Date getTRANSACTION_DATE() {
        return TRANSACTION_DATE;
    }

    public void setTRANSACTION_DATE(Date TRANSACTION_DATE) {
        this.TRANSACTION_DATE = TRANSACTION_DATE;
    }

    public String getINVOICE_NO() {
        return INVOICE_NO;
    }

    public void setINVOICE_NO(String INVOICE_NO) {
        this.INVOICE_NO = INVOICE_NO;
    }

    public String getBATCH() {
        return BATCH;
    }

    public void setBATCH(String BATCH) {
        this.BATCH = BATCH;
    }

    public String getEXPIRY() {
        return EXPIRY;
    }

    public void setEXPIRY(String EXPIRY) {
        this.EXPIRY = EXPIRY;
    }

    public String getCLASS() {
        return CLASS;
    }

    public void setCLASS(String CLASS) {
        this.CLASS = CLASS;
    }

    public double getNET_QTY() {
        return NET_QTY;
    }

    public void setNET_QTY(double NET_QTY) {
        this.NET_QTY = NET_QTY;
    }

    public double getNET_VALUE() {
        return NET_VALUE;
    }

    public void setNET_VALUE(double NET_VALUE) {
        this.NET_VALUE = NET_VALUE;
    }

    public double getBONUS() {
        return BONUS;
    }

    public void setBONUS(double BONUS) {
        this.BONUS = BONUS;
    }

    public double getBONUS_VALUE() {
        return BONUS_VALUE;
    }

    public void setBONUS_VALUE(double BONUS_VALUE) {
        this.BONUS_VALUE = BONUS_VALUE;
    }

    public double getDISCOUNTS() {
        return DISCOUNTS;
    }

    public void setDISCOUNTS(double DISCOUNTS) {
        this.DISCOUNTS = DISCOUNTS;
    }

    public double getCYP() {
        return CYP;
    }

    public void setCYP(double CYP) {
        this.CYP = CYP;
    }

    public String getGSM_PRODUCT_CODE() {
        return GSM_PRODUCT_CODE;
    }

    public void setGSM_PRODUCT_CODE(String GSM_PRODUCT_CODE) {
        this.GSM_PRODUCT_CODE = GSM_PRODUCT_CODE;
    }

    public String getGSM_TOWN() {
        return GSM_TOWN;
    }

    public void setGSM_TOWN(String GSM_TOWN) {
        this.GSM_TOWN = GSM_TOWN;
    }

    public String getSALEDISTRICT() {
        return SALEDISTRICT;
    }

    public void setSALEDISTRICT(String SALEDISTRICT) {
        this.SALEDISTRICT = SALEDISTRICT;
    }

    public String getSALETEHSIL() {
        return SALETEHSIL;
    }

    public void setSALETEHSIL(String SALETEHSIL) {
        this.SALETEHSIL = SALETEHSIL;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }

    public String getREMARKS() {
        return REMARKS;
    }

    public void setREMARKS(String REMARKS) {
        this.REMARKS = REMARKS;
    }

    public double getVID() {
        return VID;
    }

    public void setVID(double VID) {
        this.VID = VID;
    }

    public String getSYSTEM_DATE() {
        return SYSTEM_DATE;
    }

    public void setSYSTEM_DATE(String SYSTEM_DATE) {
        this.SYSTEM_DATE = SYSTEM_DATE;
    }

    public double getBONUS_DISCOUNT() {
        return BONUS_DISCOUNT;
    }

    public void setBONUS_DISCOUNT(double BONUS_DISCOUNT) {
        this.BONUS_DISCOUNT = BONUS_DISCOUNT;
    }

    public double getFOC_DISCOUNT() {
        return FOC_DISCOUNT;
    }

    public void setFOC_DISCOUNT(double FOC_DISCOUNT) {
        this.FOC_DISCOUNT = FOC_DISCOUNT;
    }

    public String getBOOKED_BY() {
        return BOOKED_BY;
    }

    public void setBOOKED_BY(String BOOKED_BY) {
        this.BOOKED_BY = BOOKED_BY;
    }

    public String getSECTION_CODE() {
        return SECTION_CODE;
    }

    public void setSECTION_CODE(String SECTION_CODE) {
        this.SECTION_CODE = SECTION_CODE;
    }

    public String getSECTION_NAME() {
        return SECTION_NAME;
    }

    public void setSECTION_NAME(String SECTION_NAME) {
        this.SECTION_NAME = SECTION_NAME;
    }

    public String getNATURE() {
        return NATURE;
    }

    public void setNATURE(String NATURE) {
        this.NATURE = NATURE;
    }

    public String getPOSITION_CODE() {
        return POSITION_CODE;
    }

    public void setPOSITION_CODE(String POSITION_CODE) {
        this.POSITION_CODE = POSITION_CODE;
    }

    public String getSASCODE() {
        return SASCODE;
    }

    public void setSASCODE(String SASCODE) {
        this.SASCODE = SASCODE;
    }

    public String getEMPLOYEEID() {
        return EMPLOYEEID;
    }

    public void setEMPLOYEEID(String EMPLOYEEID) {
        this.EMPLOYEEID = EMPLOYEEID;
    }

    public String getTEAMREGION() {
        return TEAMREGION;
    }

    public void setTEAMREGION(String TEAMREGION) {
        this.TEAMREGION = TEAMREGION;
    }

    public String getZONE() {
        return ZONE;
    }

    public void setZONE(String ZONE) {
        this.ZONE = ZONE;
    }

    public String getSUBZONE() {
        return SUBZONE;
    }

    public void setSUBZONE(String SUBZONE) {
        this.SUBZONE = SUBZONE;
    }

    public String getDISTRICT() {
        return DISTRICT;
    }

    public void setDISTRICT(String DISTRICT) {
        this.DISTRICT = DISTRICT;
    }

    public String getTEHSIL() {
        return TEHSIL;
    }

    public void setTEHSIL(String TEHSIL) {
        this.TEHSIL = TEHSIL;
    }

    public String getPROVINCE() {
        return PROVINCE;
    }

    public void setPROVINCE(String PROVINCE) {
        this.PROVINCE = PROVINCE;
    }

    public String getREGION() {
        return REGION;
    }

    public void setREGION(String REGION) {
        this.REGION = REGION;
    }

    public String getTEAM() {
        return TEAM;
    }

    public void setTEAM(String TEAM) {
        this.TEAM = TEAM;
    }

    public String getCHANNEL() {
        return CHANNEL;
    }

    public void setCHANNEL(String CHANNEL) {
        this.CHANNEL = CHANNEL;
    }

    public String getGROUPON() {
        return GROUPON;
    }

    public void setGROUPON(String GROUPON) {
        this.GROUPON = GROUPON;
    }

    public String getGRP() {
        return GRP;
    }

    public void setGRP(String GRP) {
        this.GRP = GRP;
    }

    public String getGRP_CATEGORY() {
        return GRP_CATEGORY;
    }

    public void setGRP_CATEGORY(String GRP_CATEGORY) {
        this.GRP_CATEGORY = GRP_CATEGORY;
    }

    public String getPRODUCTGROUP() {
        return PRODUCTGROUP;
    }

    public void setPRODUCTGROUP(String PRODUCTGROUP) {
        this.PRODUCTGROUP = PRODUCTGROUP;
    }

    public String getPROVIDERCODE() {
        return PROVIDERCODE;
    }

    public void setPROVIDERCODE(String PROVIDERCODE) {
        this.PROVIDERCODE = PROVIDERCODE;
    }

    public String getPRODUCT() {
        return PRODUCT;
    }

    public void setPRODUCT(String PRODUCT) {
        this.PRODUCT = PRODUCT;
    }

    public String getGSM_REMARKS() {
        return GSM_REMARKS;
    }

    public void setGSM_REMARKS(String GSM_REMARKS) {
        this.GSM_REMARKS = GSM_REMARKS;
    }

    public double getE_QTY() {
        return E_QTY;
    }

    public void setE_QTY(double e_QTY) {
        E_QTY = e_QTY;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public double getTP_SALE_VALUE() {
        return TP_SALE_VALUE;
    }

    public void setTP_SALE_VALUE(double TP_SALE_VALUE) {
        this.TP_SALE_VALUE = TP_SALE_VALUE;
    }

    public double getNET_SALE_VALUE() {
        return NET_SALE_VALUE;
    }

    public void setNET_SALE_VALUE(double NET_SALE_VALUE) {
        this.NET_SALE_VALUE = NET_SALE_VALUE;
    }
}
