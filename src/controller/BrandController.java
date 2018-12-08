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
                    add();
                    break;
                case 2:
                    edit();
                    break;
                case 3:
                    delete();
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

    public void add() {
        System.out.println("Add Brand done");
    }

    public void delete() {
        System.out.println("Delete Brand done");
    }

    public void edit() {
        System.out.println("Edit Brand done");
    }
    public void showDetail(){
        int Id=enterNumber("Brand Id");
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
            }
            else
            {
                System.out.println("The brand has Id="+Id+" doesn't exist!");
            }

        } catch (SQLException ex) {
            exitByError();
        }
    }
}
