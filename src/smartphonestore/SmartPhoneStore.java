/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartphonestore;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SmartPhoneStore {

    
      static  DBConnection connection=new DBConnection();
    public static void main(String[] args) {
        Connection connect=connection.open();
        try {
            Statement stm=connect.createStatement();
            ResultSet rs=stm.executeQuery("Select * from Users");
            while(rs.next())
                System.out.println("Name: "+rs.getString(2));
        } catch (SQLException ex) {
            System.out.println("has error");
        }
    }
    
}
