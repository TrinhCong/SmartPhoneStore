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

/**
 *
 * @author tvcpr
 */
public class AdminController {

    private Connection connection;
    private Scanner scanner;

    public AdminController(Connection connect) {
        connection = connect;
        scanner = new Scanner(System.in);
    }

    public void login() {
        System.out.println("__Login__");
        boolean isValid = false;
        do {
            System.out.print("Enter user name:");
            String name = scanner.nextLine();
            System.out.print("Enter password:");
            String pass = scanner.nextLine();
            try {
                Statement stm = connection.createStatement();
                ResultSet rs = stm.executeQuery("SELECT * FROM Admins WHERE UserName='" + name + "' and PasWord='" + pass + "' ");
                if (rs.getRow() > 0) {
                    System.out.println("Login succesfully!");
                    isValid = true;
                } else {
                    System.out.println("Wrong user name or password!");
                    System.out.print("Do you want to retry? (y/n): ");
                    String choice = scanner.nextLine();
                    if (!choice.equalsIgnoreCase("y")) {
                        break;
                    }
                }

            } catch (SQLException ex) {
                System.out.println("Connection fail!");
            }
        } while (!isValid);

    }
}
