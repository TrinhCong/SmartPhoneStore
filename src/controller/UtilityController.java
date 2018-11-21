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
public class UtilityController {

    private Connection connection;

    public UtilityController(Connection connect) {
        connection = connect;
    }

    public void showMainMenu() {
        System.out.println("-----SMARTPHONE STORE MANAGEMENT WEBSITE -----");
        System.out.println("1. Login with system admin role.");
        System.out.println("2. Home.");
        System.out.println("3. Quick search.");
        System.out.println("4. Filter products.");
        System.out.println("5. Exit.");
        System.out.println("-------------------------------------------------------");
    }

    public boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}
