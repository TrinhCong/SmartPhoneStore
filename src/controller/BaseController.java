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
public class BaseController {

    protected Connection connection;
    protected Scanner scanner;

    public BaseController(Connection connect) {
        connection = connect;
        scanner = new Scanner(System.in);
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

    public int enterNumber(String option) {
        System.out.print("==> Enter " + option + ":");
        String choiceStr = "";
        do {
            choiceStr = scanner.nextLine();
            if (isNumeric(choiceStr)) {
                break;
            } else {
                System.out.println(option + " must be a number!");
            }
        } while (true);
        return (int) Double.parseDouble(choiceStr);
    }

    public void makeSpace(String position) {
        if (position.equals(EnumPosition.DASH_TOP)) {
            System.out.println("\n");
            System.out.println("------------------------------------");
        } else if (position.equals(EnumPosition.DASH_BOTTOM)) {
            System.out.println("------------------------------------");
            System.out.println("\n");
        }
    }

    public void exitByError() {
        System.out.println("Connection Fail! Program is exited!");
        System.exit(0);
    }

}
