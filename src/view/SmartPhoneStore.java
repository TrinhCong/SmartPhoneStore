package view;

import controller.AdminController;
import controller.HomeController;
import controller.ProductController;
import controller.QuickSearchController;
import controller.UtilityController;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import model.DBConnection;

public class SmartPhoneStore {

    static DBConnection connection = new DBConnection();
    static UtilityController util = new UtilityController();
    static AdminController auth = new AdminController();
    static HomeController home = new HomeController();
    static ProductController productControl = new ProductController();
    static QuickSearchController quickSearch = new QuickSearchController();

    public static void main(String[] args) throws Exception {
        Connection connect = connection.open();
        Scanner sc = new Scanner(System.in);
        try {
            Statement stm = connect.createStatement();
            String choiceStr = "";
            util.showMainMenu();
            int choice = 0;
            do {
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
                        quickSearch.ShowMenu();
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

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

}
