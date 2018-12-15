/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import enums.EnumBillStatus;
import enums.EnumPosition;
import enums.EnumString;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Bill;

/**
 *
 * @author tvcpr
 */
public class BillController extends BaseController {

    public BillController(Connection connect) {
        super(connect);
    }

    public void manageMenu() {
        int choice;
        do {
            int length = makeMenuHeader("Bills infomation Management");
            makeMenuRow("1.Undelivered bills", length);
            makeMenuRow("2.Delivering bills", length);
            makeMenuRow("3.Delivered bills", length);
            makeMenuRow("4.Back to previous menu", length);
            makeMenuFooter(length);
            choice = enterNumber("an option");
            clearNetbeanConsole();
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
                case 4:
                    break;
                default:
                    System.out.println("Option is invalid!");
                    break;
            }
        } while (choice != 4);
    }

    public void showUndelivered() {
        try {
            ResultSet r = statement.executeQuery("select * from Bills where StatusId=" + EnumBillStatus.UNPAID);
            if (r.isBeforeFirst()) {
                System.out.println("Unresolved orders:");
                while (r.next()) {
                    System.out.println("Id: " + r.getString(1) + "   Created date: " + r.getString(5));
                }
            } else {
                makeSpace(EnumPosition.DASH_TOP);
                System.out.println("Store has no new order!");
            }

        } catch (SQLException ex) {
            exitByError();
        }
    }

    public void manageUndelivered() {
        int choice;
        do {
            int length = makeMenuHeader("Undelivered Bills Management");
            makeMenuRow("1. Bill Detail", length);
            makeMenuRow("2. Sent Bill to Shipper", length);
            makeMenuRow("3. Delete Bill", length);
            makeMenuRow("4. Back to previous menu", length);
            makeMenuFooter(length);
            choice = enterNumber("an option");
            clearNetbeanConsole();
            switch (choice) {
                case 1:
                    displayById(EnumBillStatus.UNPAID,length);
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

    public void displayById(int statusId,int length) {
        int billId = enterNumber("Bill Id");
        String condition = "b.Id=" + billId + " and StatusId=" + statusId;
        display(getBills(condition),length);
    }

    public void showDelivered() {
        try {
            ResultSet r = statement.executeQuery("select * from Bills where StatusId=" + EnumBillStatus.PAID);
            if (r.isBeforeFirst()) {
                System.out.println("Resolved orders:");
                while (r.next()) {
                    System.out.println("Id: " + r.getString(1) + "   Created date: " + r.getString(5));
                }
            } else {
                makeSpace(EnumPosition.DASH_TOP);
                System.out.println("Bill history is empty!");
            }

        } catch (SQLException ex) {
            exitByError();
        }
    }

    public void manageDelivered() {
        int choice;
        do {
            int length = makeMenuHeader("Delivered Bills Management");
            showDelivered();
            makeMenuRow("1. Bill Detail", length);
            makeMenuRow("2. Back to previous menu", length);
            makeMenuFooter(length);
            choice = enterNumber("an option");
            clearNetbeanConsole();
            switch (choice) {
                case 1:
                    showBillDetail(EnumBillStatus.PAID,length);
                    break;
                case 2:
                    break;
                default:
                    System.out.println("Option is invalid!");
                    break;
            }
        } while (choice != 2);
    }

    public void showBillDetail(int typeId,int length) {
        int id = enterNumber("Bill Id");
        String condition = "b.Id=" + id + " and b.StatusId=" + typeId;
        ArrayList<Bill> bills = getBills(condition);
        display(bills,length);
    }

    public void display(ArrayList<Bill> bills,int length) {
        makeDivider(length);
        if (bills.size() > 0) {
            for (Bill item : bills) {
                makeRow("Bill Id: " + item.getId(),length);
                makeRow("Customer Name: " + (hasStringValue(item.CustomerName) ? item.CustomerName : EnumString.NO_INFO),length);
                makeRow("Shipper Name: " + (hasStringValue(item.ShipperName) ? item.ShipperName : EnumString.NO_INFO),length);
                makeRow("Status Name: " + (hasStringValue(item.StatusName) ? item.StatusName : EnumString.NO_INFO),length);
                makeRow("Created Date: " + (item.getCreatedDate() != null ? item.getCreatedDate() : EnumString.NO_INFO),length);
                makeRow("Received Address: " + (hasStringValue(item.getReceivedAddress()) ? item.getReceivedAddress() : EnumString.NO_INFO),length);
                makeRow("Note: " + (hasStringValue(item.getNote()) ? item.getNote() : EnumString.NO_INFO),length);
                makeRow("Total: " + item.getTotal() + " VND",length);
            }
        } else {
            makeRow("Bill doesn't exist!",length);
        }
        makeDivider(length);
    }

    public void showDelivering() {
        try {
            ResultSet rs = statement.executeQuery("select * from Bills where StatusId=" + EnumBillStatus.DELIVERING);
            if (rs.isBeforeFirst()) {
                System.out.println("Delivering orders:");
                while (rs.next()) {
                    System.out.println("Id: " + rs.getString(1) + "   Created date: " + rs.getString(5));
                }
            } else {
                makeSpace(EnumPosition.DASH_TOP);
                System.out.println("No product in Delivering");
            }

        } catch (SQLException ex) {
            exitByError();
        }
    }

    public void manageDelivering() {
        int choice;
        do {
            int length = makeMenuHeader("Delivering Bills Management");
            showDelivering();
            makeMenuRow("1. Bill Detail", length);
            makeMenuRow("2. Back to previous menu", length);
            makeMenuFooter(length);
            choice = enterNumber("an option");
            clearNetbeanConsole();

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

    public ArrayList<Bill> getBills(String condition) {
        ArrayList<Bill> bills = new ArrayList<>();
        try {
            ResultSet rs = statement.executeQuery("select b.*,bc.Description  StatusName,ct.Name CustomerName,sp.Name ShipperName \n"
                    + "from Bills b, BillStatusCategory bc,Shippers sp,Customers ct\n"
                    + "where b.CustomerId=ct.Id and bc.Id=b.StatusId and sp.Id=b.ShipperId and ct.Id=b.CustomerId and " + condition);
            while (rs.next()) {
                Bill bill = new Bill();
                bill.setId(rs.getInt("Id"));
                bill.setCreatedDate(rs.getDate("CreatedDate"));
                bill.setCustomerId(rs.getInt("CustomerId"));
                bill.setNote(rs.getString("Note"));
                bill.setReceivedAddress(rs.getString("ReceivedAddress"));
                bill.setShipperId(rs.getInt("ShipperId"));
                bill.setStatusId(rs.getInt("StatusId"));
                bill.setTotal(rs.getDouble("Total"));
                bill.CustomerName = rs.getString("CustomerName");
                bill.ShipperName = rs.getString("ShipperName");
                bill.StatusName = rs.getString("StatusName");
                bills.add(bill);
            }
        } catch (SQLException ex) {
            exitByError();
        }
        return bills;
    }
}
