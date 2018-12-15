/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import enums.EnumPosition;
import java.awt.AWTException;
import java.awt.Robot;
import java.io.IOException;
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
        int length = makeMenuHeader("SMARTPHONE STORE MANAGEMENT APPLICATION");
        makeMenuRow("1. Login with system admin role.", length);
        makeMenuRow("2. Home.", length);
        makeMenuRow("3. Quick search.", length);
        makeMenuRow("4. Filter products.", length);
        makeMenuRow("5. Exit.", length);
        makeMenuFooter(length);
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
        String choiceStr = "";
        do {
            System.out.print("  => Enter " + option + ":");
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
        Pattern emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = emailPattern.matcher(emailStr);
        return matcher.find();
    }

    public int makeMenuHeader(String name) {
        System.out.println("");
        String header = ".·★¸.·'´*¤ " + name.toUpperCase() + " ¤*`'·.¸★·.";
        if (name.length() < 10) {
            header = ".·★¸.·'´*¤.·★¸.·'´*¤ " + name.toUpperCase() + " ¤*`'·.¸★·.¤*`'·.¸★·.";
        }
        System.out.println(header);
        return header.length();
    }

    public void makeMenuRow(String option, int length) {
        int remainSpace = length - option.length() - 4;
        String space = "";
        for (int i = 0; i <= remainSpace - 6; i++) {
            space += " ";
        }
        System.out.println("¤       " + option + space + " ¤");
    }

    public void makeMenuFooter(int length) {
        String space = "";
        for (int i = 0; i < length * 0.67; i++) {
            space += "☆";
        }
        System.out.println(space);
        System.out.println("");
    }

    public void makeRow(String option, int length) {
        int remainSpace = length - option.length() - 4;
        String space = "";
        for (int i = 0; i <= remainSpace; i++) {
            space += " ";
        }
        System.out.println("¤ " + option + space + " ¤");
    }

    public void makeDivider(int length) {
        String space = "";
        for (int i = 0; i <= length - 2; i++) {
            space += "=";
        }
        System.out.println("¤" + space + "¤");
    }

    public static void clrscr() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (IOException | InterruptedException ex) {
        }
    }

    public void clearNetbeanConsole() {
        try {
            Robot pressbot = new Robot();
            pressbot.keyPress(17); // Holds CTRL key.
            pressbot.keyPress(76); // Holds L key.
            pressbot.keyRelease(17); // Releases CTRL key.
            pressbot.keyRelease(76); // Releases L key.
        } catch (AWTException ex) {
            exitByError();
        }
    }
}
