/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;

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
        int choice;
        do {

            int length = makeMenuHeader("QUICK SEARCH");
            String keyword = enterString("a keyword");
            String condition = "b.Name like '%" + keyword + "%' or p.Name like '%" + keyword + "%' or p.Price like '%" + keyword + "%' or p.ImagePath like '%" + keyword + "%' or p.Quantity like '%" + keyword + "%'  or p.Dimension like '%" + keyword + "%' or p.Weight like '%" + keyword + "%' or p.Color like '%" + keyword + "%' or p.Warranty like '%" + keyword + "%' or p.SoundType like '%" + keyword + "%' or p.StartPromotion like '%" + keyword + "%' or p.EndPromotion like '%" + keyword + "%' or p.ConnectionType like '%" + keyword + "%' or p.Memory like '%" + keyword + "%' or p.PromotionPrice like '%" + keyword + "%' or p.Battery like '%" + keyword + "%' or p.OS like '%" + keyword + "%' or p.SDCard like '%" + keyword + "%' or p.Camera like '%" + keyword + "%' or p.BrandId like '%" + keyword + "%'";

            do {
                _productManager.setRowLength(length);
                _productManager.displaySimple(_productManager.getProducts(condition));
                makeRow("Options:");
                makeRow("  1. Continue search");
                makeRow("  2. Show product detail");
                makeRow("  3. Back to main menu");
                makeMenuFooter();
                choice = enterNumber("an option");
                switch (choice) {
                    case 1:
                        clearConsole();
                        break;
                    case 2:
                        _productManager.showDetailById();
                        break;
                    case 3:
                        break;
                    default:
                        makeRow("The option is invalid!");
                        break;
                }
            } while (choice != 1 && choice != 3);
        } while (choice != 3);
    }

}
