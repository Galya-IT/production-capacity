package com.galya.business.productioncapacity.persistence;

import java.sql.Connection;
import java.sql.SQLException;

public interface TableHelper {

    String getTableName();
    
    String getDatabaseName();
   
    void createTable(Connection connection) throws SQLException;
    
    void populateDefaultValues(Connection connection) throws SQLException;
    
}
