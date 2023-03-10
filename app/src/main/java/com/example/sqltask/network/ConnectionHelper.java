package com.example.sqltask.network;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionHelper {
    String userName, userPassword, ip, port, database;

    public Connection connectionClass(){
        ip = "ngknn.ru";
        database = "Directory";
        userName = "41П";
        userPassword = "123456";
        port = "1433";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Connection connection = null;
        String ConnectionURL = null;
        try{
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL = "jdbc:jtds:sqlserver://" + ip + ":" + port + ";" + "databasename=" + database + ";user=" + userName + ";password=" + userPassword + ";";
            connection = DriverManager.getConnection(ConnectionURL);
        } catch (Exception ex){
            Log.e("Error", ex.getMessage());
        }
        return connection;
    }
}
