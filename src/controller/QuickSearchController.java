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
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.DBConnection;
import view.SmartPhoneStore;


/**
 *
 * @author tvcpr
 */
public class QuickSearchController extends BaseController {

    public QuickSearchController(Connection connect) {
        super(connect);
    }

    public void showMenu() {
        System.out.println("Option 3: QUICK SEARCH");
        System.out.print("     Enter a keyword: ");
        Scanner sc = new Scanner(System.in);
        String keyword = sc.nextLine();
        quickSearch(keyword);
    }

    public void quickSearch(String keyword) {

        try {
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("select * from Products AS pr, BrandCategory AS bc where pr.BrandId = bc.Id and bc.Name like '%" + keyword + "%' or pr.Name like '%" + keyword + "%' or pr.Price like '%" + keyword + "%' or pr.ImagePath like '%" + keyword + "%' or pr.Quantity like '%" + keyword + "%'  or pr.Dimension like '%" + keyword + "%' or pr.Weight like '%" + keyword + "%' or pr.Color like '%" + keyword + "%' or pr.Warranty like '%" + keyword + "%' or pr.SoundType like '%" + keyword + "%' or pr.StartPromotion like '%" + keyword + "%' or pr.EndPromotion like '%" + keyword + "%' or pr.ConnectionType like '%" + keyword + "%' or pr.Memory like '%" + keyword + "%' or pr.PromotionPrice like '%" + keyword + "%' or pr.Battery like '%" + keyword + "%' or pr.OS like '%" + keyword + "%' or pr.SDCard like '%" + keyword + "%' or pr.Camera like '%" + keyword + "%' or pr.BrandId like '%" + keyword + "%'");
            
                while (rs.next()) {
                    System.out.println("\t id: " + rs.getString(1) + "\tName: " + rs.getString(2));
                }
            tuychon();
        } catch (SQLException ex) {
            Logger.getLogger(QuickSearchController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void tuychon() {
        String choice = "";
        int choice1;
        do {
            System.out.println("= 1. tim kiem tiep");
            System.out.println("= 2. xem chi tiet");
            System.out.println("= 3. trang chu");
            Scanner sc = new Scanner(System.in);
            do {
                System.out.print("Please enter an option: ");
                choice = sc.nextLine();
                if (isNumeric(choice)) {
                    break;
                } else {
                    System.out.println("choice must be a number!");
                }
            } while (true);
            choice1 = (int) Double.parseDouble(choice);
            switch (choice1) {
                case 1:
                    showMenu();
                    break;
                case 2:
                    ShowDetail();
                    break;
                case 3:
                    
                    break;
                default:
                    System.out.println("the choice is wrong!");
                    break;
            }
        } while (choice1 != 3);
    }

    public void ShowDetail() {
        String idStr = "";
        double id;
        do {
            System.out.print("\tplease enter ID of product you want to see details: ");
            Scanner sc = new Scanner(System.in);
            idStr = sc.nextLine();
            if (isNumeric(idStr)) {
                break;
            } else {
                System.out.println("ID must be number!");
            }
        } while (true);
        id = Integer.parseInt(idStr);
        try {

            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from Products");
            while (rs.next()) {
                if (rs.getDouble(1) == id) {
                    System.out.println("\t\t id: " + rs.getString(1) + "\tName: " + rs.getString(2) + "\tPrice: " + rs.getString(3) + "\tImagePath: " + rs.getString(4) + "\tQuantity: " + rs.getString(5) + "\tDimension: " + rs.getString(6) + "\tWeight: " + rs.getString(7) + "\tColor: " + rs.getString(8) + "\tWarranty: " + rs.getString(9) + "\tSoundType: " + rs.getString(10) + "\tStartPromotion: " + rs.getString(11) + "\tEndPromotion: " + rs.getString(12) + "\tConnectionType: " + rs.getString(13) + "\tMemory: " + rs.getString(14) + "\tPromotionPrice: " + rs.getString(15) + "\tBattery: " + rs.getString(16) + "\tOS: " + rs.getString(17) + "\tSDCard: " + rs.getString(18) + "\tCamera: " + rs.getString(19) + "\tBrandId: " + rs.getString(20));
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
