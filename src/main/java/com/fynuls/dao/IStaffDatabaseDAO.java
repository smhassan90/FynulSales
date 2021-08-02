package com.fynuls.dao;

import org.json.JSONObject;

public interface IStaffDatabaseDAO {
    public void insertRecord(String username, int status, String token);

    public boolean isExist(String username);

    public void updateInformation(String username, int status, String token);

    public boolean isLoggedIn(String username);

    public boolean logout(String username);

    public String getToken(String username);

    /*
    This method is to check if this user code exist in Database
     */
    public String isCorrect(String username, String password);

    public JSONObject performSync(String username);

    public String getName(String username);
}
