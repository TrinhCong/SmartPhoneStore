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

/**
 *
 * @author tvcpr
 */
public class AdminController extends BaseController {


    public AdminController(Connection connect) {
        super(connect);
    }

    public void login() {
        System.out.println("__Login__");
        boolean isValid = false;
        do {
            System.out.print("Enter user name:");
            String name = scanner.nextLine();
            System.out.print("Enter password:");
            String pass = scanner.nextLine();
            try {
                Statement stm = connection.createStatement();
                ResultSet rs = stm.executeQuery("SELECT * FROM Admins WHERE UserName='" + name.trim() + "' and Password='" + pass.trim() + "' ");
                if (rs.isBeforeFirst()) {
                    System.out.println("Welcome Admin: " + name.trim());
                    interact();
                    isValid = true;
                } else {
                    System.out.println("Wrong user name or password!");
                    System.out.print("Do you want to retry? (y/n): ");
                    String choice = scanner.nextLine();
                    if (!choice.equalsIgnoreCase("y")) {
                        break;
                    }
                }

            } catch (SQLException ex) {
                System.out.println("Connection fail!");
                System.exit(0);
            }
        } while (!isValid);

    }
    
    public void showMenu() {
        System.out.println("----- System management -----");
        System.out.println("1.Product Information Editor");
        System.out.println("2.Manage Product Bills");
        System.out.println("3.Back to previous menu\n");
    }
    public void interact(){
    int choice;
        do {
            showMenu();
            System.out.print("==> Enter an option:");
            choice=enterChoice();
            switch (choice) {
                case 1:
                    showEditorMenu();
                    break;
                case 2:
                    showBillMenu();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Option is invalid!");
                    break;
            }
        } while (choice != 3);
    }
    public void showEditorMenu(){
        makeSpace(EnumPosition.DASH_TOP);
        System.out.println("Editor menu is completing!");
        makeSpace(EnumPosition.DASH_BOTTOM);
    }
    public void showBillMenu(){
        makeSpace(EnumPosition.DASH_TOP);
        System.out.println("Bills management menu is completing!");
        makeSpace(EnumPosition.DASH_BOTTOM);
    }
}
