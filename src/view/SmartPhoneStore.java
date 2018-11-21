package view;

import controller.AdminController;
import controller.HomeController;
import controller.ProductController;
import controller.QuickSearchController;
import controller.UtilityController;
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
        UtilityController util = new UtilityController(connect);
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
        String choiceStr = "";
        int choice;
        do {
            util.showMainMenu();
            do {
                System.out.print("Nhập vào 1 lựa chọn:");
                choiceStr = sc.nextLine();
                if (util.isNumeric(choiceStr)) {
                    break;
                } else {
                    System.out.println("Vui lòng nhập lựa chọn là 1 số!");
                }
            } while (true);
            choice = (int) Double.parseDouble(choiceStr);
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
                    System.out.println("Lựa chọn không đúng!");
                    break;
            }
        } while (choice != 5);
        connection.close();
    }

}
