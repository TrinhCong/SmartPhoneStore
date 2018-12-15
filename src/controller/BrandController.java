/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import enums.EnumPosition;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.ShortMessage;

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
                System.out.println("id: " + rs.getString(1) + "\nName: " + rs.getString(2) + "\nPrice: " + rs.getString(3) + "\nBrandId: " + rs.getString(4) + "\nBrandName: " + rs.getString(5) + "\n");
            }
        } catch (SQLException e) {
            exitByError();
        }

    }

    public void showBrandEditor() {
        makeSpace(EnumPosition.DASH_TOP);
        System.out.println("-----Brand Category infomation Editor-------");
        showAll();
        System.out.println("Options:");
        System.out.println("\t1.Add Brand");
        System.out.println("\t2.Edit Brand");
        System.out.println("\t3.Delete Brand");
        System.out.println("\t4.Watch Brand Detail");
        System.out.println("\t5.Back to previous page");
    }

    public void manageMenu() {
        int choice;
        do {
            showBrandEditor();
            choice = enterNumber("Option");
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
                    showDetail();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Option is invalid!");
                    break;
            }
        } while (choice != 5);
    }

    public void showAll() {
        try {

            Statement st = connection.createStatement();
            ResultSet r = st.executeQuery("select * from BrandCategory");
            if (r.isBeforeFirst()) {
                System.out.println("\nStore's brand list:");
                while (r.next()) {
                    System.out.println("\tid: " + r.getString(1) + "\tName: " + r.getString(2));
                }

            } else {
                makeSpace(EnumPosition.DASH_TOP);
                System.out.println("Store has no brand!");
            }

        } catch (SQLException ex) {
            exitByError();
        }
        System.out.println("\n");
    }

    public void showAddMenu() {
        int choice;
        do {
            makeSpace(EnumPosition.DASH_TOP);
            System.out.println("=======Add Menu======");
            showAll();
            System.out.println("Options:");
            System.out.println("\t1.Add Brand");
            System.out.println("\t2.Back to previous page");
            choice = enterNumber("Option");
            switch (choice) {
                case 1:
                    add();
                    break;
                case 2:
                    break;
                default:
                    System.out.println("Option is invalid!");
                    break;
            }
        } while (choice != 2);
    }

    public void showDeleteMenu() {
        int choice;
        do {
            makeSpace(EnumPosition.DASH_TOP);
            System.out.println("=======Delete Menu======");
            showAll();
            System.out.println("Options:");
            System.out.println("\t1.Delete Brand");
            System.out.println("\t2.Back to previous page");
            choice = enterNumber("Option");
            switch (choice) {
                case 1:
                    delete();
                    break;
                case 2:
                    break;
                default:
                    System.out.println("Option is invalid!");
                    break;
            }
        } while (choice != 2);
    }

    public void showEditMenu() {
        int choice;
        do {
            makeSpace(EnumPosition.DASH_TOP);
            System.out.println("=======Edit Menu======");
            showAll();
            System.out.println("Options:");
            System.out.println("\t1.Edit Brand");
            System.out.println("\t2.Back to previous page");
            choice = enterNumber("Option");
            switch (choice) {
                case 1:
                    edit();
                    break;
                case 2:
                    break;
                default:
                    System.out.println("Option is invalid!");
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
                        System.out.println("Add successfull!");
                        showAll();
                        System.out.println("Do you wanna add more?(y/n)");
                        String choice = enterString("Choice");
                        if (!choice.equalsIgnoreCase("y")) {
                            break;
                        }
                    } else {
                        System.out.println("Insert brand failed");
                    }
                } else {
                    System.out.println("Brand Name already exist!");
                    System.out.println("Do you wanna continue to add?(y/n)");
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
                        System.out.println("remove successfull!");
                        showAll();
                        System.out.println("Do you wanna remove more?(y/n)");
                        String choice = enterString("Choice");
                        if (!choice.equalsIgnoreCase("y")) {
                            break;
                        }
                    }

                } else {
                    System.out.println("Invalid ID");
                    System.out.println("Do you wanna remove more?(y/n)");
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
                                System.out.println("Add successfull!");
                                showAll();
                                System.out.println("Do you wanna edit more?(y/n)");
                                String choice = enterString("Choice");
                                if (!choice.equalsIgnoreCase("y")) {
                                    break;
                                }
                            } else {
                                System.out.println("Insert brand failed");
                            }
                        } else {
                            System.out.println("Brand Name already exist!");
                            System.out.println("Do you wanna continue to edit another brand?(y/n)");
                            String choice = enterString("Choice");
                            if (!choice.equalsIgnoreCase("y")) {
                                break;
                            }
                        }
                    }
                } else {
                    System.out.println("Record doesn't exist!");
                    System.out.println("Do you wanna enter another Brand Id?(y/n)");
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
            Statement st = connection.createStatement();
            ResultSet r = st.executeQuery("select * from BrandCategory");
            if (r.isBeforeFirst()) {
                while (r.next()) {
                    System.out.println("Brand detail:");
                    System.out.println(" ID: " + r.getString(1)
                            + "\n Name: " + r.getString(2));
                }
            } else {
                System.out.println("The brand has Id=" + Id + " doesn't exist!");
            }

        } catch (SQLException ex) {
            exitByError();
        }
    }
}
