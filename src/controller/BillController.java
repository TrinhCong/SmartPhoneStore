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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import model.Bill;
import model.BillDetail;
import model.Customer;
import model.Shipper;
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
            clearConsole();
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

    public void displaySimple(int statusId) {
        makeDivider();
        try {
            ResultSet r = statement.executeQuery("select * from Bills where StatusId=" + statusId);
            if (r.isBeforeFirst()) {
                switch (statusId) {
                    case EnumBillStatus.DELIVERING:
                        makeRow("delivering orders:");
                        break;
                    case EnumBillStatus.UNPAID:
                        makeRow("Unresolved orders:");
                        break;
                    case EnumBillStatus.PAID:
                        makeRow("Resolved orders:");
                        break;
                    default:
                        break;
                }
                while (r.next()) {
                    makeRow("Id: " + r.getString("Id") + "   Created date: " + r.getString("CreatedDate"));
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
            displaySimple(EnumBillStatus.UNPAID);
            makeRow(" Options:");
            makeMenuRow("1. Bill Detail");
            makeMenuRow("2. Sent Bill to Shipper");
            makeMenuRow("3. Delete Bill");
            makeMenuRow("4. Back to previous menu");
            makeMenuFooter();
            choice = enterNumber("an option");
            clearConsole();
            switch (choice) {
                case 1:
                    displayByStatusId(EnumBillStatus.UNPAID);
                    break;
                case 2:
                    sendBillToShipper();
                    break;
                case 3:
                    deleteBill(EnumBillStatus.UNPAID);
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Option is invalid!");
                    break;
            }
        } while (choice != 4);
    }

    public void deleteBill(int statusId) {
        do {
            int billId=enterNumber("Bill ID");
            ArrayList<Bill> bills=getBills("b.Id="+billId+" and StatusId="+statusId);
            if(bills.size()>0){
                try {
                    int success=statement.executeUpdate("delete from Bills where Id="+billId);
                    if(success>0){
                        makeDivider();
                        makeRow("Delete bill successful!");
                        makeDivider();
                        
                    }
                    else
                    {
                        makeDivider();
                        makeRow("Delete bill fail!");
                        makeDivider();
                    }
                } catch (SQLException ex) {
                    exitByError();
                }
                break;
            }
            else{
                makeRow("Bill doesn't exist! Please re-enter!");
            }
        } while (true);
        
    }

    public void sendBillToShipper() {
        Bill bill = new Bill();
        do {
            int billId = enterNumber("Bill Id");
            ArrayList<Bill> bills = getBills("b.Id=" + billId);
            if (bills.size() > 0) {
                bill = bills.get(0);
                break;
            } else {
                makeRow("Bill doesn't exist! Please re-enter!");
            }
        } while (true);

        ShowShippers();
        ArrayList<Shipper> shippers = new ArrayList<Shipper>();
        do {
            bill.setShipperId(enterNumber("Shipper ID"));
            shippers = GetShippers("Id=" + bill.getShipperId());
            if (shippers.size() > 0) {
                break;
            } else {
                makeRow("Shipper doesn't exist! Please re-enter!");
            }
        } while (true);
        bill.setStatusId(EnumBillStatus.DELIVERING);
        makeDivider();
        if (update(bill)) {
            makeRow("Bill sent successfull!");
        } else {
            makeRow("Sent Bill failed!");
        }
        makeDivider();
    }

    public void ShowShippers() {
        makeDivider();
        ArrayList<Shipper> shippers = GetShippers("(1=1)");
        if (shippers.size() > 0) {
            for (Shipper shipper : shippers) {
                makeRow("ID: " + shipper.getId() + "  Name: " + shipper.getName());
            }
        } else {
            makeRow("No shipper found!");
        }
        makeDivider();
    }

    public ArrayList<Shipper> GetShippers(String condition) {
        ArrayList<Shipper> shippers = new ArrayList<Shipper>();
        try {
            ResultSet rs = statement.executeQuery("select * from Shippers where " + condition);
            while (rs.next()) {
                Shipper shipper = new Shipper();
                shipper.setId(rs.getInt("Id"));
                shipper.setName(rs.getString("Name"));
                shipper.setPhoneNumber1(rs.getString("PhoneNumber1"));
                shipper.setPhoneNumber2(rs.getString("PhoneNumber2"));
                shippers.add(shipper);
            }
        } catch (SQLException ex) {
            exitByError();
        }
        return shippers;
    }

    public void displayByStatusId(int statusId) {
        int billId = enterNumber("Bill Id");
        String condition = "b.Id=" + billId + " and StatusId=" + statusId;
        display(getBills(condition));
    }

    public void manageDelivered() {
        int choice;
        do {
            makeMenuHeader("Delivered Bills Management");
            displaySimple(EnumBillStatus.PAID);
            makeRow(" Options:");
            makeMenuRow("1. Bill Detail");
            makeMenuRow("2. Back to previous menu");
            makeMenuFooter();
            choice = enterNumber("an option");
            clearConsole();
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
        ArrayList<Bill> bills = new ArrayList<Bill>();
        do {
            int id = enterNumber("Bill Id");
            String condition = "b.Id=" + id + " and b.StatusId=" + typeId;
            bills = getBills(condition);
            if (bills.size() > 0) {
                break;
            } else {
                makeRow("bill doesn't exist! please  re-enter!");
            }
        } while (true);
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

    public void manageDelivering() {
        int choice;
        do {
            makeMenuHeader("Delivering Bills Management");
            displaySimple(EnumBillStatus.DELIVERING);
            makeRow(" Options:");
            makeMenuRow("1. Bill Detail");
            makeMenuRow("2. Delete Bill");
            makeMenuRow("3. Back to previous menu");
            makeMenuFooter();
            choice = enterNumber("an option");
            clearConsole();
            switch (choice) {
                case 1:
                    displayByStatusId(EnumBillStatus.DELIVERING);
                    break;
                case 2:
                    deleteBill(EnumBillStatus.DELIVERING);
                    break;
                case 3:
                    break;
                default:
                    makeRow("Option is invalid!");
                    break;
            }
        } while (choice != 3);
    }

    public ArrayList<Bill> getBills(String condition) {
        ArrayList<Bill> bills = new ArrayList<>();
        try {
            ResultSet rs = statement.executeQuery("select b.*,bc.Description  StatusName,ct.Name CustomerName,sp.Name ShipperName \n"
                    + "from ((Bills b left join BillStatusCategory bc on b.StatusId=bc.Id ) left join Shippers sp on sp.Id=b.ShipperId) left join Customers ct on ct.Id=b.CustomerId Where " + condition);
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

    public boolean saveAll(BillInfo info) {
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

    public boolean update(Bill bill) {
        int success = 0;
        try {
            success = statement.executeUpdate("UPDATE SmartPhoneStore.dbo.Bills\n"
                    + "SET CustomerId=" + bill.getCustomerId() + ", StatusId=" + bill.getStatusId() + ", ShipperId=" + bill.getShipperId() + ", CreatedDate='" + bill.getCreatedDate() + "', Total=" + bill.getTotal() + ", ReceivedAddress=N'" + bill.getReceivedAddress() + "', Note=N'" + bill.getNote() + "'\n"
                    + "WHERE Id=" + bill.getId());

        } catch (SQLException ex) {
            exitByError();
        }
        return success > 0;
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
