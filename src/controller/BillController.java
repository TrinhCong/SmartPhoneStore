/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import enums.EnumBillStatus;
import enums.EnumString;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import static java.time.LocalTime.now;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import model.Bill;
import model.BillDetail;
import model.Customer;
import viewmodel.BillInfo;

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
            makeMenuHeader("Bills infomation Management");
            makeMenuRow("1.Undelivered bills");
            makeMenuRow("2.Delivering bills");
            makeMenuRow("3.Delivered bills");
            makeMenuRow("4.Back to previous menu");
            makeMenuFooter();
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
                    System.out.println("¤ Option is invalid!");
                    break;
            }
        } while (choice != 4);
    }

    public void showUndelivered() {
        makeDivider();
        try {
            ResultSet r = statement.executeQuery("select * from Bills where StatusId=" + EnumBillStatus.UNPAID);
            if (r.isBeforeFirst()) {
                makeRow("Unresolved orders:");
                while (r.next()) {
                    makeRow("Id: " + r.getString(1) + "   Created date: " + r.getString(5));
                }
            } else {
                makeRow("Store has no new order!");
            }

        } catch (SQLException ex) {
            exitByError();
        }
        makeDivider();
    }

    public void manageUndelivered() {
        int choice;
        do {
            makeMenuHeader("Undelivered Bills Management");
            makeMenuRow("1. Bill Detail");
            makeMenuRow("2. Sent Bill to Shipper");
            makeMenuRow("3. Delete Bill");
            makeMenuRow("4. Back to previous menu");
            makeMenuFooter();
            choice = enterNumber("an option");
            clearNetbeanConsole();
            switch (choice) {
                case 1:
                    displayById(EnumBillStatus.UNPAID);
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

    public void displayById(int statusId) {
        int billId = enterNumber("Bill Id");
        String condition = "b.Id=" + billId + " and StatusId=" + statusId;
        display(getBills(condition));
    }

    public void showDelivered() {
        makeDivider();
        try {
            ResultSet r = statement.executeQuery("select * from Bills where StatusId=" + EnumBillStatus.PAID);
            if (r.isBeforeFirst()) {
                makeRow("Resolved orders:");
                while (r.next()) {
                    makeRow("Id: " + r.getString(1) + "   Created date: " + r.getString(5));
                }
            } else {
                makeRow("Bill history is empty!");
            }

        } catch (SQLException ex) {
            exitByError();
        }
        makeDivider();
    }

    public void manageDelivered() {
        int choice;
        do {
            makeMenuHeader("Delivered Bills Management");
            showDelivered();
            makeMenuRow("1. Bill Detail");
            makeMenuRow("2. Back to previous menu");
            makeMenuFooter();
            choice = enterNumber("an option");
            clearNetbeanConsole();
            switch (choice) {
                case 1:
                    showBillDetail(EnumBillStatus.PAID);
                    break;
                case 2:
                    break;
                default:
                    makeRow("Option is invalid!");
                    break;
            }
        } while (choice != 2);
    }

    public void showBillDetail(int typeId) {
        int id = enterNumber("Bill Id");
        String condition = "b.Id=" + id + " and b.StatusId=" + typeId;
        ArrayList<Bill> bills = getBills(condition);
        display(bills);
    }

    public void display(ArrayList<Bill> bills) {
        makeDivider();
        if (bills.size() > 0) {
            for (Bill item : bills) {
                makeRow("Bill Id: " + item.getId());
                makeRow("Customer Name: " + (hasStringValue(item.CustomerName) ? item.CustomerName : EnumString.NO_INFO));
                makeRow("Shipper Name: " + (hasStringValue(item.ShipperName) ? item.ShipperName : EnumString.NO_INFO));
                makeRow("Status Name: " + (hasStringValue(item.StatusName) ? item.StatusName : EnumString.NO_INFO));
                makeRow("Created Date: " + (item.getCreatedDate() != null ? item.getCreatedDate() : EnumString.NO_INFO));
                makeRow("Received Address: " + (hasStringValue(item.getReceivedAddress()) ? item.getReceivedAddress() : EnumString.NO_INFO));
                makeRow("Note: " + (hasStringValue(item.getNote()) ? item.getNote() : EnumString.NO_INFO));
                makeRow("Total: " + item.getTotal() + " VND");
            }
        } else {
            makeRow("Bill doesn't exist!");
        }
        makeDivider();
    }

    public void showDelivering() {
        try {
            ResultSet rs = statement.executeQuery("select * from Bills where StatusId=" + EnumBillStatus.DELIVERING);
            if (rs.isBeforeFirst()) {
                makeRow("Delivering orders:");
                while (rs.next()) {
                    makeRow("Id: " + rs.getString(1) + "   Created date: " + rs.getString(5));
                }
            } else {
                makeRow("No product in Delivering");
            }

        } catch (SQLException ex) {
            exitByError();
        }
    }

    public void manageDelivering() {
        int choice;
        do {
            makeMenuHeader("Delivering Bills Management");
            showDelivering();
            makeMenuRow("1. Bill Detail");
            makeMenuRow("2. Back to previous menu");
            makeMenuFooter();
            choice = enterNumber("an option");
            clearNetbeanConsole();

            switch (choice) {
                case 1:
                    System.out.println("1. Bill Detail");
                    break;
                case 2:
                    break;
                default:
                    makeRow("Option is invalid!");
                    break;
            }
        } while (choice != 2);
    }

    public ArrayList<Bill> getBills(String condition) {
        ArrayList<Bill> bills = new ArrayList<>();
        try {
            ResultSet rs = statement.executeQuery("select b.*,bc.Description  StatusName,ct.Name CustomerName,sp.Name ShipperName "
                    + "from Bills b, BillStatusCategory bc,Shippers sp,Customers ct"
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

    public double showBillInfo(ArrayList<BillDetail> billDetails) {
        makeDivider();
        double price = 0.0;
        makeRow("Your cart infomation:");
        for (BillDetail item : billDetails) {
            makeRow("  " + item.getQuantity() + " - " + item.ProductName + " => $" + item.getPrice());
            price += item.getPrice();
        }
        makeRow("Total: $" + price);
        makeDivider();
        return price;
    }

    public boolean save(BillInfo info) {
        try {
            statement.executeUpdate("insert Customers(Name,PhoneNumber,Email)"
                    + " VALUES(N'" + info.Customer.getName() + "','" + info.Customer.getPhoneNumber() + "','" + info.Customer.getEmail() + "')");
            int customerId = getCustomerId(info.Customer);
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm");
            String dateString = now.format(formatter);
            info.Bill.setCustomerId(customerId);
            info.Bill.setStatusId(EnumBillStatus.UNPAID);
            statement.executeUpdate("insert Bills(CustomerId,StatusId,CreatedDate,Total,ReceivedAddress)"
                    + " VALUES(" + customerId + "," + EnumBillStatus.UNPAID + ",'" + dateString + "'," + info.Bill.getTotal() + ",N'" + info.Bill.getReceivedAddress() + "')");
            int billId = getBillId(info.Bill);
            for (BillDetail detail : info.BillDetails) {
                statement.executeUpdate("insert BillsDetail(BillId,ProductId,Quantity,Price)"
                        + " values(" + billId + "," + detail.getProductId() + "," + detail.getQuantity() + "," + detail.getPrice() + ")");
            }

            return true;
        } catch (SQLException ex) {
            exitByError();
        }
        return true;
    }

    public int getCustomerId(Customer customer) {
        if (customer.getId() > 0) {
            return customer.getId();
        } else {
            try {
                ResultSet rs = statement.executeQuery("select * from Customers where Name=N'" + customer.getName() + "' and Email='" + customer.getEmail() + "' and PhoneNumber='" + customer.getPhoneNumber() + "'");
                if (rs.isBeforeFirst()) {
                    rs.next();
                    return rs.getInt("Id");
                } else {
                    return 0;
                }

            } catch (SQLException ex) {
                exitByError();
            }
        }
        return 0;
    }

    public int getBillId(Bill bill) {
        if (bill.getId() > 0) {
            return bill.getId();
        } else {
            try {
                ResultSet rs = statement.executeQuery("select * from Bills where CustomerId=" + bill.getCustomerId() + " and StatusId=" + bill.getStatusId());
                if (rs.isBeforeFirst()) {
                    rs.next();
                    return rs.getInt("Id");
                } else {
                    return 0;
                }

            } catch (SQLException ex) {
                exitByError();
            }
        }
        return 0;
    }
}
