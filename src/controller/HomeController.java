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
public class HomeController {
    
    private Connection connection;
    
    public HomeController(Connection connect) {
        connection = connect;
    }
    
    Scanner sc = new Scanner(System.in);
    public void ShowMenu(){
        System.out.println("-------------Cửa sổ trang chủ--------------");
        int choice;
        Statement stm = null;
        ResultSet rs = null;
        try {
            stm = connection.createStatement();
            rs = stm.executeQuery("select * from Products");
            while(rs.next()){
                System.out.println("id: "+rs.getString(1)+"\tName: " + rs.getString(2) + "\tPrice: "+rs.getString(3));
            }
        } catch (SQLException e) {
            System.out.println("loi gi do"+e);
        }
                
        String choice1Str="";
        int choice1;
        do {
            System.out.println("What do you do next?");
            System.out.println("1. See detalis of product");
            System.out.println("2. Back to mainpage");
            do {
                System.out.println("Please enter an option: ");
                choice1Str = sc.nextLine();
                if (isNumeric(choice1Str)){
                    break;
                }else{
                    System.out.println("choice must be a number!");
                }
            } while (true);
            choice1 = (int) Double.parseDouble(choice1Str);
            switch (choice1){
                case 1:
                    ShowDetail();
                    break;
                case 2:
                    
                    break;
                default:
                    System.out.println("the choice is wrong!");
                    break;
            }
        } while (choice1!=2);
        
        
    }
    
    public void ShowDetail(){        
        String idStr = "";
        double id;
        do {
            System.out.println("please enter ID of product you want to see details: ");
            idStr = sc.nextLine();
            if (isNumeric(idStr)){
                break;
            }else{
                System.out.println("ID must be number!");
            }
        } while (true); 
        id=Double.parseDouble(idStr);
        try {
            
            Statement st = connection.createStatement();
            ResultSet r = st.executeQuery("select * from Products");
            while(r.next()){
                if (r.getDouble(3)==id)
                System.out.println("id: "+r.getString(1)+"\tName: " + r.getString(2) + "\tPrice: "+r.getString(3) +"\t Weight: "+r.getString(7)+"\t Color: "+r.getString(8));
            }

        } catch (SQLException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

}
