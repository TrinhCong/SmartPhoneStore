
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import enums.EnumPosition;
import java.sql.Connection;
import java.util.Scanner;

/**
 *
 * @author tvcpr
 */
public class HomeController extends BaseController {

    private final ProductController _productManager;

    public HomeController(Connection connect) {
        super(connect);
        _productManager = new ProductController(connect);
    }

    Scanner sc = new Scanner(System.in);


    public void showMenu() {
        makeSpace(EnumPosition.DASH_TOP);
        System.out.println("----- Home -------");
        _productManager.showAll();
        System.out.println("Options:");
        System.out.println("  1.Product details");
        System.out.println("  2.Back to main menu");
    }

    public void manageMenu() {
        int choice;
        do {
            showMenu();
            choice = enterNumber("Option");
            switch (choice) {
                case 1:
                    showDetail();
                    break;
                case 2:
                    break;
                default:
                    System.out.println("Option is invalid!");
                    break;
            }
        } while (choice != 2);
    }

    public void showDetail() {
        int Id = enterNumber("Product ID");
        _productManager.showDetailById(Id);
    }

}
