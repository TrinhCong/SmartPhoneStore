/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    private int _rowLength;

    public BaseController(Connection connect) {
        try {
            connection = connect;
            scanner = new Scanner(System.in);
            statement = connection.createStatement();
            _rowLength = 58;
        } catch (SQLException ex) {
            System.out.println("Connection Fail! Program is exited!");
            System.exit(0);
        }
    }

    public void showMainMenu() {
        makeMenuHeader("SMARTPHONE STORE MANAGEMENT APPLICATION");
        makeMenuRow("1. Login with system admin role.");
        makeMenuRow("2. Home.");
        makeMenuRow("3. Quick search.");
        makeMenuRow("4. Filter products.");
        makeMenuRow("5. Exit.");
        makeMenuFooter();
    }

    public boolean isPositiveNumber(String str) {
        try {
            double d = Double.parseDouble(str);
            if (d <= 0) {
                return false;
            }
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public int enterNumber(String option) {
        String choiceStr = "";
        do {
//            System.out.print("¤ Enter " + option + ":");
            System.out.print("* Enter " + option + ":");
            choiceStr = scanner.nextLine();
            if (isPositiveNumber(choiceStr)) {
                break;
            } else {
                System.out.println("* " + option + " must be a positive number!");
//                System.out.println("¤ " + option + " must be a positive number!");
            }
        } while (true);
        return (int) Double.parseDouble(choiceStr);

    }

    public String enterEmail() {
        String email = "";
        do {
            email = enterString("Email");
            if (isEmail(email)) {
                break;
            } else {
                makeRow("email is invalid! Please Re-enter!");
            }
        } while (true);
        return email;
    }

    public String enterPhoneNumber() {
        String phone = "";
        do {
            phone = enterString("Phone Number");
            if (isPhoneNumber(phone)) {
                break;
            } else {
                makeRow("Phone Number is invalid! Please Re-enter!");
            }
        } while (true);
        return phone;
    }

    public String enterString(String option) {
        System.out.print("* Enter " + option + ":");
//        System.out.print("¤ Enter " + option + ":");
        String choiceStr = "";
        do {
            choiceStr = scanner.nextLine();
            if (choiceStr.trim().isEmpty()) {
//                System.out.println("¤ " + option + " must has infomation!");
                System.out.println("* " + option + " must has infomation!");
                System.out.print("* Re-enter " + option + ": ");
//                System.out.print("¤ Re-enter " + option + ": ");
            } else {
                break;
            }
        } while (true);
        return choiceStr;
    }

    public boolean enterBoolean(String option) {
//        System.out.print("¤ Enter " + option + " (yes/no):");
        System.out.print("* Enter " + option + " (yes/no):");
        String choiceStr = scanner.nextLine();
        return choiceStr.contains("y") || choiceStr.contains("Y");
    }

    public String enterDate(String option) {
        boolean isValid = false;
        String date = "";
        do {
            try {
                System.out.print("* Enter " + option + " (dd/MM/yyyy HH:mm):");
//                System.out.print("¤ Enter " + option + " (dd/MM/yyyy HH:mm):");
                date = scanner.nextLine();
                SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                formatter1.parse(date);
                isValid = true;
            } catch (ParseException ex) {
                makeRow("Date is invalid! Please re-enter!");
            }
        } while (!isValid);

        return date;
    }

    public void exitByError() {
        makeDivider();
        makeRow("Connection Fail! Program is exited!");
        makeDivider();
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

    public static boolean isPhoneNumber(String emailStr) {
        Pattern emailPattern = Pattern.compile("^\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = emailPattern.matcher(emailStr);
        return matcher.find();
    }

    public int makeMenuHeader(String name) {
        System.out.println("");
//        String header = ".·★`.·'`*¤ " + name.toUpperCase() + " ¤*`'·.`★·.";
        String header = " .·*`*..·*`*..·'`** " + name.toUpperCase() + " **`'·..*`*·..*`*·.";
        if (name.length() < 20) {
//            header = ".·★`.·'`*¤.·★`.·'`*¤ " + name.toUpperCase() + " ¤*`'·.`★·.¤*`'·.`★·.";
            header = " .·*`..·*`..·'`**..·*`..·'`** " + name.toUpperCase() + " **`'·..`*·..**`'·..·*`..·*`.";
        }

        System.out.println(header);
        setRowLength(header.length());
        makeRow("");
        return _rowLength;
    }

    public void makeMenuRow(String option) {
        int remainSpace = getRowLength() - option.length() - 4;
        String space = "";
        for (int i = 0; i <= remainSpace - 18; i++) {
            space += " ";
        }
//        System.out.println("¤       " + option + space + " ¤");
        System.out.println("*                   " + option + space + " *");
    }

    public void makeMenuFooter() {
        String space = " ";
        for (int i = 0; i < getRowLength(); i++) {
//            space += "☆";
            space += "*";
        }
        makeRow("");
        System.out.println(space);
        System.out.println("");
    }

    public void makeRow(String option) {
        int remainSpace = getRowLength() - option.length() - 4;
        String space = "";
        for (int i = 0; i <= remainSpace; i++) {
            space += " ";
        }
//        System.out.println("¤ " + option + space + " ¤");
        System.out.println("* " + option + space + " *");
    }

    public void makeDivider() {
        String space = "";
        for (int i = 0; i <= getRowLength() - 2; i++) {
            space += "=";
        }
//        System.out.println("¤" + space + "¤");
        System.out.println("*" + space + "*");
    }

    public void makeDivider(String type) {
        String space = "";
        for (int i = 0; i <= getRowLength() - 2; i++) {
            space += type;
        }
        System.out.println("*" + space + "*");
//        System.out.println("¤" + space + "¤");
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

    public void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (IOException | InterruptedException ex) {
            exitByError();
        }
//        try {
//            Robot pressbot = new Robot();
//            pressbot.keyPress(17); // Holds CTRL key.
//            pressbot.keyPress(76); // Holds L key.
//            pressbot.keyRelease(17); // Releases CTRL key.
//            pressbot.keyRelease(76); // Releases L key.
//            TimeUnit.MILLISECONDS.sleep(10);
//        } catch (AWTException | InterruptedException ex) {
//            exitByError();
//        }
    }

    /**
     * @return the _rowLength
     */
    public int getRowLength() {
        return _rowLength;
    }

    /**
     * @param _rowLength the _rowLength to set
     */
    public void setRowLength(int _rowLength) {
        this._rowLength = _rowLength;
    }
}
