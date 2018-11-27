package view;

import controller.AdminController;
import controller.HomeController;
import controller.ProductController;
import controller.QuickSearchController;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import model.DBConnection;

public class SmartPhoneStore {

    static DBConnection connection = new DBConnection();

    public static void main(String[] args) throws Exception {
        Connection connect = connection.open();
        AdminController auth = new AdminController(connect);
        HomeController home = new HomeController(connect);
        ProductController productControl = new ProductController(connect);
        QuickSearchController quickSearch = new QuickSearchController(connect);
        Scanner sc = new Scanner(System.in);
        //            Statement stm = connect.createStatement();
//            ResultSet rs= stm.executeQuery("select * from Admins");
//            while(rs.next()){
//                System.out.println("Name: "+rs.getString(2));
//            }
        int choice;
        do {
            auth.showMainMenu();
            choice=auth.enterChoice();
            switch (choice) {
                case 1:
                    auth.login();
                    break;
                case 2:
                    home.ShowMenu();
                    break;
                case 3:
                    quickSearch.showMenu();
                    break;
                case 4:
                    productControl.showFilterMenu();
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Option is invalid!");
                    break;
            }
        } while (choice != 5);
        connection.close();
    }

}
