/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.mrc.bookwebapp.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author MCENDROWSKI
 */
public class MySqlDBStrategy implements DBStrategy {

    private Connection conn;

    @Override
    public void openConnection(String driverClass, String url,
            String userName, String password) throws ClassNotFoundException, SQLException {

        Class.forName(driverClass);
        conn = DriverManager.getConnection(url, userName, password);

    }

    @Override
    public void closeConnection() throws SQLException {
        conn.close();
    }

    /**
     * make sure you open and close connection when using this method. Future
     * optimization may include change the return type an array.
     *
     * @param tableName
     * @param maxRecords - limits records found to first maxRecords or if
     * maxRecords is zero (0) then no limit.
     * @return
     * @throws java.sql.SQLException
     */
    public List<Map<String, Object>> findAllRecords(String tableName, int maxRecords) throws SQLException {
        String sql;
        if (maxRecords < 1) {
            sql = "SELECT * FROM " + tableName;

        } else {
            sql = "SELECT * FROM " + tableName + " LIMIT " + maxRecords;
        }
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();
        List<Map<String, Object>> records = new ArrayList<>();
        while (rs.next()) {
            Map<String, Object> record = new HashMap<>();
            for (int colNo = 1; colNo <= columnCount; colNo++) {
                Object colData = rs.getObject(colNo);
                String colName = rsmd.getColumnName(colNo);
                record.put(colName, colData);

            }
            records.add(record);
        }
        return records;
    }

    public int deleteRecord(String tableName, String primaryKey, int recordId) throws SQLException {
        String sql;
        int recordsCount;
        sql = "DELETE FROM " + tableName + " WHERE " + primaryKey + " = " + recordId;
        Statement stmt = conn.createStatement();
//        ResultSetMetaData rsmd = stmt.getResultSet().getMetaData();
//        int columnCount = rsmd.getColumnCount();
//        rsmd.getColumnName(0).
//        while (rs.)
        recordsCount = stmt.executeUpdate(sql);
        return recordsCount;
    }

//    public String createWhereClause(Map<String, String> listOfParameters) {
//        String clause = "WHERE ";          
//            
//             Set<String> keys = listOfParameters.keySet();        
//        
//        for(String key : keys) {
//            clause+=key+" = " + listOfParameters.get(key);            
//        }          
//        
//        return clause;
//    }

    public int insertRecord(String tableName) throws SQLException {
        String sql;
        int recordsCount;
        sql = "INSERT INTO " + tableName + " (author_name,date_added) VALUES ('Bob','20111231')";
        Statement stmt = conn.createStatement();
//        ResultSetMetaData rsmd = stmt.getResultSet().getMetaData();
//        int columnCount = rsmd.getColumnCount();
//        rsmd.getColumnName(0).
//        while (rs.)
        recordsCount = stmt.executeUpdate(sql);
        return recordsCount;
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
//        testFindAllRecords();
        testInsertRecord();
//        testDeleteRecord();
    }

    public static void testFindAllRecords() throws ClassNotFoundException, SQLException {
        DBStrategy db = new MySqlDBStrategy();
        db.openConnection("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/book", "root", "admin");
        List<Map<String, Object>> rawData = db.findAllRecords("author", 0);

        db.closeConnection();
        System.out.println(rawData);
    }

    public static void testInsertRecord() throws ClassNotFoundException, SQLException {
        DBStrategy db = new MySqlDBStrategy();
        db.openConnection("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/book", "root", "admin");
        int recordsCount = db.insertRecord("author");

        db.closeConnection();
        System.out.println(recordsCount);
    }

    public static void testDeleteRecord() throws ClassNotFoundException, SQLException {
        DBStrategy db = new MySqlDBStrategy();
        db.openConnection("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/book", "root", "admin");
        int recordsCount = db.deleteRecord("author", "author_id", 4);
        db.closeConnection();
        System.out.println(recordsCount);
    }
    
//    public static void testCreateWhereClause(){
//        Map<String,String> map = new HashMap<>();        
//        
//        map.put("author_id", "4");
//        map.put("author_name", "Bob");
//        
//        String clause = createWhereClause(map);
//        
//        System.out.println(clause);
//        
//    }
}
