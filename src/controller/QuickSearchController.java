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
public class QuickSearchController extends BaseController {

    private final ProductController _productManager;

    public QuickSearchController(Connection connect) {
        super(connect);
        _productManager = new ProductController(connect);
    }

    public void showMenu() {
        boolean isContinue;
        do {
            System.out.println("Option 3: QUICK SEARCH");
            System.out.print("   Enter a keyword: ");
            String keyword;
            do {
                keyword = scanner.nextLine();
                if (keyword.trim().isEmpty()) {
                    System.out.println("Keyword is not valid!\n Re-enter keyword:");
                }
            } while (keyword.trim().isEmpty());
            isContinue = quickSearch(keyword);
        } while (isContinue);

    }

    public boolean quickSearch(String keyword) {
        try {
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("select * from Products AS pr, BrandCategory AS bc where pr.BrandId = bc.Id and bc.Name like '%" + keyword + "%' or pr.Name like '%" + keyword + "%' or pr.Price like '%" + keyword + "%' or pr.ImagePath like '%" + keyword + "%' or pr.Quantity like '%" + keyword + "%'  or pr.Dimension like '%" + keyword + "%' or pr.Weight like '%" + keyword + "%' or pr.Color like '%" + keyword + "%' or pr.Warranty like '%" + keyword + "%' or pr.SoundType like '%" + keyword + "%' or pr.StartPromotion like '%" + keyword + "%' or pr.EndPromotion like '%" + keyword + "%' or pr.ConnectionType like '%" + keyword + "%' or pr.Memory like '%" + keyword + "%' or pr.PromotionPrice like '%" + keyword + "%' or pr.Battery like '%" + keyword + "%' or pr.OS like '%" + keyword + "%' or pr.SDCard like '%" + keyword + "%' or pr.Camera like '%" + keyword + "%' or pr.BrandId like '%" + keyword + "%'");
            if (rs.isBeforeFirst()) {
                System.out.println("Result list: ");
                while (rs.next()) {
                    System.out.println("\t id: " + rs.getString(1) + "\tName: " + rs.getString(2));
                }
            } else {
                System.out.println("\nNo product found!");
            }
            int choice;
            do {
                System.out.println("Options:");
                System.out.println("  1. Continue search");
                System.out.println("  2. Show product detail");
                System.out.println("  3. Back to main menu");
                choice = enterNumber("Option");
                switch (choice) {
                    case 1:
                        showMenu();
                        break;
                    case 2:
                        _productManager.showDetail();
                        break;
                    case 3:
                        break;
                    default:
                        System.out.println("the choice is wrong!");
                        break;
                }
            } while (choice != 3);
            return false;
        } catch (SQLException ex) {
            exitByError();
            return true;
        }
    }

}
