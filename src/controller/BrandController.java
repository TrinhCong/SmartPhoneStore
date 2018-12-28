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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tvcpr
 */
public class BrandController extends BaseController {

    //menu BrandController    
    public BrandController(Connection connect) {
        super(connect);
    }

    // method xem chi tiet san pham
    public void brandDetails() {
        int ID = enterNumber("ID");
        try {
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("Select * from BrandCategory where ID=" + ID);
            while (rs.next()) {
                makeRow("id: " + rs.getString(1) + "Name: " + rs.getString(2) + "Price: " + rs.getString(3) + "BrandId: " + rs.getString(4) + "BrandName: " + rs.getString(5) + "");
            }
        } catch (SQLException e) {
            exitByError();
        }

    }

    public void manageMenu() {
        int choice;
        do {
            makeMenuHeader("Brand Category infomation Editor");
            showAll();
            makeMenuRow("Options:");
            makeMenuRow("   1.Add Brand");
            makeMenuRow("   2.Edit Brand");
            makeMenuRow("   3.Delete Brand");
//            makeMenuRow("   4.Watch Brand Detail");
            makeMenuRow("   4.Back to previous page");
            makeMenuFooter();
            choice = enterNumber("an option");
            clearConsole();
            switch (choice) {
                case 1:
                    showAddMenu();
                    break;
                case 2:
                    showEditMenu();
                    break;
                case 3:
                    showDeleteMenu();
                    break;
                case 4:
                    break;
                default:
                    makeRow("Option is invalid!");
                    break;
            }
        } while (choice != 4);
    }

    public void showAll() {
        makeDivider();
        try {
            ResultSet r = statement.executeQuery("select * from BrandCategory");
            if (r.isBeforeFirst()) {
                makeRow("Store's brand list:");
                while (r.next()) {
                    if (r.getInt("Id") > 9) {
                        makeRow("   ID: " + r.getInt("Id") + "  Name: " + r.getString("Name"));
                    }
                    else 
                        makeRow("   ID: " + r.getInt("Id") + "   Name: " + r.getString("Name"));
                }
            } else {
                makeRow("Store has no brand!");
            }

        } catch (SQLException ex) {
            exitByError();
        }
        makeDivider();
    }

    public void showAddMenu() {
        int choice;
        do {
            makeMenuHeader("Add Brand Menu");
            showAll();
            makeMenuRow("Options:");
            makeMenuRow("   1.Add Brand");
            makeMenuRow("   2.Back to previous page");
            choice = enterNumber("an option");
            switch (choice) {
                case 1:
                    add();
                    break;
                case 2:
                    break;
                default:
                    makeRow("Option is invalid!");
                    break;
            }
        } while (choice != 2);
    }

    public void showDeleteMenu() {
        int choice;
        do {
            makeMenuHeader("Delete Brand Menu");
            showAll();
            makeMenuRow("Options:");
            makeMenuRow("   1.Delete Brand");
            makeMenuRow("   2.Back to previous page");
            choice = enterNumber("an option");
            switch (choice) {
                case 1:
                    delete();
                    break;
                case 2:
                    break;
                default:
                    makeRow("Option is invalid!");
                    break;
            }
        } while (choice != 2);
    }

    public void showEditMenu() {
        int choice;
        do {
            makeMenuHeader("Edit Brand Menu");
            showAll();
            makeMenuRow("Options:");
            makeMenuRow("   1.Edit Brand");
            makeMenuRow("   2.Back to previous page");
            choice = enterNumber("an option");
            switch (choice) {
                case 1:
                    edit();
                    break;
                case 2:
                    break;
                default:
                    makeRow("Option is invalid!");
                    break;
            }
        } while (choice != 2);
    }

    public void add() {
        do {
            try {
                String brandName = enterString("Brand Name");
                ResultSet rs = statement.executeQuery("select * from BrandCategory where Name='" + brandName + "'");
                if (!rs.isBeforeFirst()) {
                    int rowAffect = statement.executeUpdate("INSERT INTO BrandCategory(Name) VALUES ('" + brandName + "')");
                    if (rowAffect > 0) {
                        makeDivider();
                        makeRow("Add successfull!");
                        makeDivider();
                        showAll();
                        makeRow("Do you wanna add more?(y/n)");
                        String choice = enterString("Choice");
                        if (!choice.equalsIgnoreCase("y")) {
                            break;
                        }
                    } else {
                        makeDivider();
                        makeRow("Insert brand failed");
                        makeDivider();
                    }
                } else {
                    makeRow("Brand Name already exist!");
                    makeRow("Do you wanna continue to add?(y/n)");
                    String choice = enterString("Choice");
                    if (!choice.equalsIgnoreCase("y")) {
                        break;
                    }
                }

            } catch (SQLException ex) {
                exitByError();
            }
        } while (true);

    }

    public void delete() {
        do {
            try {
                int Id = enterNumber("Brand Id");
                ResultSet rs = statement.executeQuery("select * from BrandCategory where Id=" + Id);
                if (rs.isBeforeFirst()) {
                    rs.next();
                    if (Id == rs.getInt("Id")) {
                        int check = statement.executeUpdate("DELETE FROM BrandCategory WHERE ID=" + Id);
                        if (check > 0) {
                            makeDivider();
                            makeRow("Delete brand successfull!");
                            makeDivider();
                        } else {
                            makeDivider();
                            makeRow("Delete brand failed!");
                            makeDivider();
                        }
                        showAll();
                        makeRow("Do you wanna remove more?(y/n)");
                        String choice = enterString("Choice");
                        if (!choice.equalsIgnoreCase("y")) {
                            break;
                        }
                    }

                } else {
                    makeRow("Invalid ID");
                }
            } catch (SQLException ex) {
                exitByError();
            }
        } while (true);

    }

    public void edit() {
        do {
            try {
                int id = enterNumber("Brand Id");
                ResultSet r = statement.executeQuery("select * from BrandCategory where Id=" + id);
                if (r.isBeforeFirst()) {
                    r.next();
                    if (id == r.getInt("Id")) {
                        String updateName = enterString("Brand Name");
                        ResultSet rs = statement.executeQuery("select * from BrandCategory where Name='" + updateName + "' and Id<>" + id);
                        if (!rs.isBeforeFirst()) {
                            int check = statement.executeUpdate(" Update BrandCategory Set Name =N'" + updateName + "' Where Id =" + id);
                            if (check > 0) {
                                makeDivider();
                                makeRow("Add successfull!");
                                makeDivider();
                                showAll();
                                makeRow("Do you wanna edit more?(y/n)");
                                String choice = enterString("Choice");
                                if (!choice.equalsIgnoreCase("y")) {
                                    break;
                                }
                            } else {
                                makeDivider();
                                makeRow("Insert brand failed");
                                makeDivider();
                            }
                        } else {
                            makeRow("Brand Name already exist!");
                            makeRow("Do you wanna continue to edit another brand?(y/n)");
                            String choice = enterString("Choice");
                            if (!choice.equalsIgnoreCase("y")) {
                                break;
                            }
                        }
                    }
                } else {
                    makeRow("Record doesn't exist!");
                    makeRow("Do you wanna enter another Brand Id?(y/n)");
                    String choice = enterString("Choice");
                    if (!choice.equalsIgnoreCase("y")) {
                        break;
                    }

                }
            } catch (SQLException ex) {
                Logger.getLogger(BrandController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);
    }

    public void showDetail() {
        int Id = enterNumber("Brand Id");
        showDetailById(Id);
    }

    public void showDetailById(int Id) {
        try {
            ResultSet r = statement.executeQuery("select * from BrandCategory");
            if (r.isBeforeFirst()) {
                while (r.next()) {
                    makeRow("Brand detail:");
                    makeRow(" ID: " + r.getString(1)
                            + " Name: " + r.getString(2));
                }
            } else {
                makeRow("The brand has Id=" + Id + " doesn't exist!");
            }

        } catch (SQLException ex) {
            exitByError();
        }
    }

    public boolean isExist(int Id) {
        try {
            ResultSet r = statement.executeQuery("select * from BrandCategory where Id=" + Id);
            return r.isBeforeFirst();
        } catch (SQLException ex) {
            return false;
        }
    }
}
