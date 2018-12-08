
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import enums.EnumPosition;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tvcpr
 */
public class HomeController extends BaseController {

    private final ProductController _productManager;

    public HomeController(Connection connect) {
        super(connect);
        _productManager = new ProductController(connect);
    }

    Scanner sc = new Scanner(System.in);

    public void showMenu() {
        makeSpace(EnumPosition.DASH_TOP);
        System.out.println("----- Home -------");
        _productManager.showAll();
        System.out.println("Options:");
        System.out.println("  1.Product details");
        System.out.println("  2.Order");
        System.out.println("  3.Back to main menu");
    }

    public void manageMenu() {
        int choice;
        do {
            showMenu();
            choice = enterNumber("Option");
            switch (choice) {
                case 1:
                    showDetail();
                    break;
                case 2:
                    orderProduct();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Option is invalid!");
                    break;
            }
        } while (choice != 2);
    }

    public void showDetail() {
        int Id = enterNumber("Product ID");
        _productManager.showDetailById(Id);
    }

    public void orderProduct() {
        boolean isStop;
        int r;
        int r1;
        int r2;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        try {
            do {
                isStop = false;
                Statement st = connection.createStatement();
                Statement st1 = connection.createStatement();
                Statement st2 = connection.createStatement();
                System.out.println("Name: ");
                String name = sc.nextLine();
                System.out.println("Phone Number: ");
                String phonenumber = sc.nextLine();
                System.out.println("Email: ");
                String email = sc.nextLine();
                System.out.println("ID of product you want order: ");
                int Id = enterNumber("Product ID");
                r = st.executeUpdate("insert into Customers(Name,PhoneNumber,Email) values('" + name + "','" + phonenumber + "','" + email + "')");
                System.out.println("Insert Successfull: " + name + "\t" + phonenumber + "\n");
                ResultSet rs = st.executeQuery("select Id from Customers");
                while(rs.next()){
                    String customerid = rs.getString(1);
                    r1 = st1.executeUpdate("insert into Bills(CustomerId) values("+customerid+")");
                }
                rs1 = st.executeQuery("select Id from Bills");
                while(rs1.next()){
                    String billid = rs1.getString(1);
                    r2= st2.executeUpdate("insert into BillsDetail(BillId) values("+billid+")");
                }
                System.out.println("Do you want to order more? (Y/N)");
                String choice = sc.nextLine();
                if (!choice.equalsIgnoreCase("y")) {
                    isStop = true;
                }
            } while (!isStop);

        } catch (SQLException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
