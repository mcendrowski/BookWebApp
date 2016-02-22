/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.mrc.bookwebapp.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author MCENDROWSKI
 */
public interface DBStrategy {
     public abstract void openConnection(String driverClass, String url, 
            String userName, String password) 
            throws ClassNotFoundException, SQLException;
    
    public abstract void closeConnection() throws SQLException;
    
    public abstract List<Map<String,Object>> findAllRecords(String tableName,int maxRecords) throws SQLException;
//    public abstract int insertRecord(String tableName) throws SQLException;
    public int insertRecord(String tableName, List<String> colNames, List<Object> colValues) throws SQLException, Exception;
    public abstract int deleteRecord(String tableName,String primaryKey,int recordId) throws SQLException;
    public abstract int deleteById(String tableName, String pkColName, Object value) throws SQLException;
//    public abstract int updateRecordById(String tableName,List<String> pkColNames,List<String> colNames,List<Object> colValues, Object value) throws SQLException, Exception;
    public abstract int updateRecordById(String tableName, List<String> colNames, List<Object> colValues, String fieldName, Object value) throws SQLException, Exception;
}
