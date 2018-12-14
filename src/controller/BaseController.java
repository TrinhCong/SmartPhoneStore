/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import enums.EnumPosition;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author tvcpr
 */
public class BaseController {

    protected Connection connection;
    protected Scanner scanner;
    protected Statement statement;

    public BaseController(Connection connect) {
        try {
            connection = connect;
            scanner = new Scanner(System.in);
            statement = connection.createStatement();
        } catch (SQLException ex) {
            System.out.println("Connection Fail! Program is exited!");
            System.exit(0);
        }
    }

    public void showMainMenu() {
        System.out.println("-----SMARTPHONE STORE MANAGEMENT WEBSITE -----");
        System.out.println("1. Login with system admin role.");
        System.out.println("2. Home.");
        System.out.println("3. Quick search.");
        System.out.println("4. Filter products.");
        System.out.println("5. Exit.");
        makeSpace(EnumPosition.DASH_BOTTOM);
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

    public String enterString(String option) {
        System.out.print("==> Enter " + option + ":");
        String choiceStr = "";
        do {
            choiceStr = scanner.nextLine();
            if (choiceStr.trim().isEmpty()) {
                System.out.println(option + " must has value!");
                System.out.print("Re-enter " + option + ": ");
            } else {
                break;
            }
        } while (true);
        return choiceStr;
    }

    public void makeSpace(String position) {
        if (position.equals(EnumPosition.DASH_TOP)) {
            System.out.println("------------------------------------");
        } else if (position.equals(EnumPosition.DASH_BOTTOM)) {
            System.out.println("------------------------------------");
        }
    }

    public void exitByError() {
        System.out.println("Connection Fail! Program is exited!");
        System.exit(0);
    }

    public boolean hasStringValue(String string) {
        return string != null && !"".equals(string.trim());
    }

    public static boolean isEmail(String emailStr) {
        Pattern emailPattern= Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = emailPattern.matcher(emailStr);
        return matcher.find();
    }
}
