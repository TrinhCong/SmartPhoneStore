/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.DBConnection;

/**
 *
 * @author tvcpr
 */
public class QuickSearchController {
    
    private Connection connection;

    public QuickSearchController(Connection connect) {
        connection = connect;
    }
    public void showMenu(){
        System.out.println("Option 3: QUICK SEARCH");
        System.out.print("     Enter a keyword: ");
        Scanner sc=new Scanner(System.in);
        String keyword=sc.nextLine();
        quickSearch(keyword);
        
    }
    
    public void quickSearch(String keyword){
//        try {
//            Statement stm = connection.createStatement();
//            ResultSet rs= stm.executeQuery("select * from Admins");
//            while(rs.next()){
//                System.out.println("Name: "+rs.getString(2));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(QuickSearchController.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
}
