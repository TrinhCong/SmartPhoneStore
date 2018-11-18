/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartphonestore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tvcpr
 */
public class DBConnection {

    Connection connection;
    String driver;
    String connectionString;
    String dbUserName;
    String dbPassWord;
    public DBConnection(){
        driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
        connectionString="jdbc:sqlserver://127.0.0.1:1433;databaseName=SmartPhoneStore";
        dbUserName="sa";
        dbPassWord="superadmin";
    }
    public Connection open() {
        try {
            Class.forName(driver);
            connection=DriverManager.getConnection(connectionString,dbUserName,dbPassWord);
            System.out.println("Connection success");
        } catch (Exception ex) {
            System.out.println("Connection failed!");
        }
        return connection;
    }
    public void close(){
        try {
            if(connection!=null&&!connection.isClosed())
                connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        connection=null;
            
    }
}
