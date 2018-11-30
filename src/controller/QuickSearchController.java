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
            ResultSet rs = stm.executeQuery("select * from Products where Name like '%" + keyword + "%' or Price like '%" + keyword + "%' or ImagePath like '%" + keyword + "%' or Quantity like '%" + keyword + "%'  or Dimension like '%" + keyword + "%' or Weight like '%" + keyword + "%' or Color like '%" + keyword + "%' or Warranty like '%" + keyword + "%' or SoundType like '%" + keyword + "%' or StartPromotion like '%" + keyword + "%' or EndPromotion like '%" + keyword + "%' or ConnectionType like '%" + keyword + "%' or Memory like '%" + keyword + "%' or PromotionPrice like '%" + keyword + "%' or Battery like '%" + keyword + "%' or OS like '%" + keyword + "%' or SDCard like '%" + keyword + "%' or Camera like '%" + keyword + "%' or BrandId like '%" + keyword + "%'");
            while (rs.next()) {
                System.out.println("id: "+rs.getString(1) +"\tName: "+rs.getString(2) +"\tPrice: "+rs.getString(3) +"\tImagePath: "+rs.getString(4) +"\tQuantity: "+rs.getString(5) +"\tDimension: "+rs.getString(7) +"\tWeight: "+rs.getString(7) +"\tColor: "+rs.getString(8) +"\tWarranty: "+rs.getString(9) +"\tSoundType: "+rs.getString(10) +"\tStartPromotion: "+rs.getString(11) +"\tEndPromotion: "+rs.getString(12)+"\tConnectionType: "+rs.getString(13)+"\tMemory: "+rs.getString(14)+"\tPromotionPrice: "+rs.getString(15)+"\tBattery: "+rs.getString(16)+"\tOS: "+rs.getString(17)+"\tSDCard: "+rs.getString(18)+"\tCamera: "+rs.getString(19)+"\tBrandId: "+rs.getString(20));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuickSearchController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
