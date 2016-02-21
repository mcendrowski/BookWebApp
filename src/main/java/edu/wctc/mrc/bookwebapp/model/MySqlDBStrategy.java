/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.mrc.bookwebapp.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
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

//    public List<Map<String,Object>> findAllRecords(String sql){
//        
//    }
    public int updateRecordById(String tableName, List<String> colNames, List<Object> colValues, String fieldName, Object value) throws SQLException, Exception {
//    public int updateRecords(String tableName, List colDescriptors, List colValues,
//							 String whereField, Object whereValue, boolean closeConnection)

        {
            PreparedStatement pstmt = null;
            int recsUpdated = 0;

            // do this in an excpetion handler so that we can depend on the
            // finally clause to close the connection
            try {
                pstmt = buildUpdateStatement(conn, tableName, colNames, fieldName);

                final Iterator i = colValues.iterator();
                int index = 1;
                Object obj = null;

                // set params for column values
                while (i.hasNext()) {
                    obj = i.next();
                    pstmt.setObject(index++, obj);
                }
                // and finally set param for wehere value
                pstmt.setObject(index, value);

                recsUpdated = pstmt.executeUpdate();

            } catch (SQLException sqle) {
                throw sqle;
            } catch (Exception e) {
                throw new SQLException(e.getMessage());
            } finally {
                try {
                    pstmt.close();
                    conn.close();

                } catch (SQLException e) {
                    throw e;
                } // end try
            } // end finally

            return recsUpdated;
        }
    }

    public int deleteById(String tableName, String pkColName, Object value) throws SQLException {
        String sql = "delete from " + tableName + " where "
                + pkColName + " ?";
        PreparedStatement psmt = conn.prepareStatement(sql);
        psmt.setObject(1, value);
        int result = psmt.executeUpdate();
        return result;
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
    private PreparedStatement buildUpdateStatement(Connection conn_loc, String tableName,
            List colDescriptors, String whereField) throws SQLException {
        StringBuffer sql = new StringBuffer("UPDATE ");
        (sql.append(tableName)).append(" SET ");
        final Iterator i = colDescriptors.iterator();
        while (i.hasNext()) {
            (sql.append((String) i.next())).append(" = ?, ");
        }
        sql = new StringBuffer((sql.toString()).substring(0, (sql.toString()).lastIndexOf(", ")));
        ((sql.append(" WHERE ")).append(whereField)).append(" = ?");
        final String finalSQL = sql.toString();
        return conn_loc.prepareStatement(finalSQL);
    }

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

    public static void main(String[] args) throws ClassNotFoundException, SQLException, Exception {
//        testFindAllRecords();
//        testInsertRecord();
//        testDeleteRecord();
        testUpdateById();
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

    public static void testUpdateById() throws ClassNotFoundException, SQLException, Exception {
        DBStrategy db = new MySqlDBStrategy();
        db.openConnection("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/book", "root", "admin");

        List<String> colNames = Arrays.asList("author_name", "date_added");
        List<Object> colValues = Arrays.asList("Lucifer", "2000-02-11");
        String idString = "author_id";
        List<Object> listString = Arrays.asList("author_id");
        int result = db.updateRecordById("author", colNames, colValues, idString, 1);
        db.closeConnection();

    }

//    public static void testDeleteById()throws ClassNotFoundException, SQLException{
//         DBStrategy db = new MySqlDBStrategy();
//        db.openConnection("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/book", "root", "admin");
//        List<Map<String,Object>> rawData = 
//                db.findAllRecords("author", 0);
//        System.out.println(rawData);
//        
//        int result
//        
//    }
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
