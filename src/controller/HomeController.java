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

    public void manageMenu() {
        int choice;
        do {
            int length = makeMenuHeader("Home");
            _productManager.showAll(length);
            makeMenuRow("Options", length);
            makeMenuRow("  1.Show product detail", length);
            makeMenuRow("  2.Order products", length);
            makeMenuRow("  3.Back to main menu", length);
            makeMenuFooter(length);
            choice = enterNumber("an option");
            clearNetbeanConsole();
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
        boolean isStop1;
        boolean yn = true;
        int r;
        int r1;
        int r2;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        try {

            Statement st = connection.createStatement();
            Statement st1 = connection.createStatement();
            Statement st2 = connection.createStatement();
            System.out.println("\t---------Please enter your information---------");
            String name = enterString("Name");
            int phonenumber = enterNumber("Phone Number");
            String email = "";
            do {
                email = enterString("Email");
                if (isEmail(email)) {
                    break;
                } else {
                    System.out.println("Your enter is not email! Re-enter!");
                }
            } while (true);

            do {
                isStop = false;
                System.out.println("\tPlease enter ID of product you want order: ");
                int id = enterNumber("Product ID");
                ResultSet rs3 = statement.executeQuery("select * from Products where Id=" + id);
                if (rs3.isBeforeFirst()) {
                    System.out.println("\tPlease enter number of product: ");
                    int amountofproduct = enterNumber("");
                    isStop1 = false;
                    System.out.println("\tDo you really want to order this product? (Y/N)");
                    String choice1 = enterString("option");
                    while (yn) {
                        switch (choice1) {
                            case "y":
                                yn = false;
                                break;
                            case "n":
                                yn = false;
                                break;
                            default:
                                System.out.println("Please enter your choice!");
                        }
                    }

                    System.out.println("\tDo you want to order more? (Y/N)");
                    String choice = enterString("option");
                    if (!choice.equalsIgnoreCase("y")) {
                        isStop = true;
                    }

                    r = st.executeUpdate("insert into Customers(Name,PhoneNumber,Email) values('" + name + "','" + phonenumber + "','" + email + "')");
                    ResultSet rs = st.executeQuery("select Id from Customers");
                    while (rs.next()) {
                        String customerid = rs.getString(1);
                        r1 = st1.executeUpdate("insert into Bills(CustomerId) values(" + customerid + ")");
                    }
                    rs1 = st.executeQuery("select Id from Bills");
                    while (rs1.next()) {
                        String billid = rs1.getString(1);
                        r2 = st2.executeUpdate("insert into BillsDetail(BillId) values(" + billid + ")");
                    }

                } else {
                    System.out.println("This id doesn't exist");
                    System.out.println("Do you wanna continue to add?(y/n)");
                    String choice = enterString("Choice");
                    if (!choice.equalsIgnoreCase("y")) {
                        break;
                    }
                }
                System.out.println("----------Successfully Order, our store will contact you and transfer products in the earliest time----------");
            } while (!isStop);

        } catch (SQLException ex) {
            exitByError();
        }
    }

}
