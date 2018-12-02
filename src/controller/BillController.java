/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import enums.EnumBillStatus;
import enums.EnumPosition;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author tvcpr
 */
public class BillController extends BaseController {

    public BillController(Connection connect) {
        super(connect);
    }

    public void showBillEditor() {
        makeSpace(EnumPosition.DASH_TOP);
        System.out.println("---------Bills infomation Management-----------");
        System.out.println("1.Undeliverd bills");
        System.out.println("2.Delivering bills");
        System.out.println("3.Delivered bills");
        System.out.println("4.Back to previous menu");
    }

    public void manageMenu() {
        int choice;
        do {
            showBillEditor();
            choice = enterNumber("Option");
            switch (choice) {
                case 1:
                    manageUndelivered();
                    break;
                case 2:
                    manageDelivering();
                    break;
                case 3:
                    manageDelivered();
                    break;
                default:
                    System.out.println("Option is invalid!");
                    break;
            }
        } while (choice != 3);
    }

    public void showUndelivered() {
        try {

            Statement st = connection.createStatement();
            ResultSet r = st.executeQuery("select * from Bills where StatusId=" + EnumBillStatus.UNPAID);
            if (r.isBeforeFirst()) {
                System.out.println("Unresolved orders:");
                while (r.next()) {
                    System.out.println("Id: " + r.getString(1) + "\tCreated date: " + r.getString(5));
                }
            } else {
                makeSpace(EnumPosition.DASH_TOP);
                System.out.println("Store has no new order!");
            }

        } catch (SQLException ex) {
            System.out.println("Connection Fail! Program is existed!");
            System.exit(0);
        }
    }

    public void showUndeliveredMenu() {
        makeSpace(EnumPosition.DASH_TOP);
        System.out.println("----- Undelivered Bills Management --------");
        showUndelivered();
        System.out.println("1. Bill Detail");
        System.out.println("2. Sent Bill to Shipper");
        System.out.println("3. Delete Bill");
        System.out.println("4. Back to previous menu");
    }

    public void manageUndelivered() {
        int choice;
        do {
            showUndeliveredMenu();
            choice = enterNumber("Option");
            switch (choice) {
                case 1:
                    System.out.println("1. Bill Detail");
                    break;
                case 2:
                    System.out.println("2. Sent Bill to Shipper");
                    break;
                case 3:
                    System.out.println("3. Delete Bill");
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Option is invalid!");
                    break;
            }
        } while (choice != 4);
    }

    public void showDelivered() {
        try {

            Statement st = connection.createStatement();
            ResultSet r = st.executeQuery("select * from Bills where StatusId=" + EnumBillStatus.PAID);
            if (r.isBeforeFirst()) {
                System.out.println("Resolved orders:");
                while (r.next()) {
                    System.out.println("Id: " + r.getString(1) + "\tCreated date: " + r.getString(5));
                }
            } else {
                makeSpace(EnumPosition.DASH_TOP);
                System.out.println("Bill history is empty!");
            }

        } catch (SQLException ex) {
            System.out.println("Connection Fail! Program is existed!");
            System.exit(0);
        }
    }

    public void showDeliveredMenu() {
        makeSpace(EnumPosition.DASH_TOP);
        System.out.println("----- Delivered Bills Management --------");
        showDelivered();
        System.out.println("1. Bill Detail");
        System.out.println("2. Back to previous menu");
    }

    public void manageDelivered() {
        int choice;
        do {
            showDeliveredMenu();
            choice = enterNumber("Option");
            switch (choice) {
                case 1:
                    System.out.println("1. Bill Detail");
                    break;
                case 2:
                    break;
                default:
                    System.out.println("Option is invalid!");
                    break;
            }
        } while (choice != 2);
    }

    public void showDelivering() {
        try {
            Statement st = connection.createStatement();
            ResultSet r = st.executeQuery("select * from Bills where StatusId=" + EnumBillStatus.DELIVERING);
            if (r.isBeforeFirst()) {
                System.out.println("Delivering orders:");
                while (r.next()) {
                    System.out.println("Id: " + r.getString(1) + "\tCreated date: " + r.getString(5));
                }
            } else {
                makeSpace(EnumPosition.DASH_TOP);
                System.out.println("No product in Delivering");
            }

        } catch (SQLException ex) {
            System.out.println("Connection Fail! Program is existed!");
            System.exit(0);
        }
    }

    public void showDeliveringMenu() {
        makeSpace(EnumPosition.DASH_TOP);
        System.out.println("----- Delivering Bills Management --------");
        showDelivered();
        System.out.println("1. Bill Detail");
        System.out.println("2. Back to previous menu");
    }

    public void manageDelivering() {
        int choice;
        do {
            showDeliveredMenu();
            choice = enterNumber("Option");
            switch (choice) {
                case 1:
                    System.out.println("1. Bill Detail");
                    break;
                case 2:
                    break;
                default:
                    System.out.println("Option is invalid!");
                    break;
            }
        } while (choice != 2);
    }

}
