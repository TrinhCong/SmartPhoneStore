/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import enums.EnumPosition;
import java.sql.Connection;

/**
 *
 * @author tvcpr
 */
public class BillController extends BaseController{
    

    public BillController(Connection connect) {
        super(connect);
    }
    
    public void showBillEditor() {
        makeSpace(EnumPosition.DASH_TOP);
        System.out.println("---------Bills infomation Editor-----------");
        System.out.println("1.Current Bills");
        System.out.println("1.Edit Product");
        System.out.println("1.Delete Product");
        makeSpace(EnumPosition.DASH_BOTTOM);
    }
    
    public void manageBill() {
        int choice;
        do {
            showBillEditor();
            System.out.print("==> Enter an option:");
            choice = enterChoice();
            switch (choice) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Option is invalid!");
                    break;
            }
        } while (choice != 3);
    }

}
