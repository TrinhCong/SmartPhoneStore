package view;

import controller.AdminController;
import controller.BaseController;
import controller.HomeController;
import controller.ProductController;
import controller.QuickSearchController;
import java.sql.Connection;
import model.DBConnection;

public class SmartPhoneStore {

    static DBConnection connection = new DBConnection();

    public static void main(String[] args) throws Exception {
        Connection connect = connection.open();
        BaseController _baseManager=new BaseController(connect);
        AdminController _adminManager = new AdminController(connect);
        HomeController _homeManager = new HomeController(connect);
        ProductController _productManager = new ProductController(connect);
        QuickSearchController _quickSearchManager = new QuickSearchController(connect);
        int choice;
        do {
            _baseManager.showMainMenu();
            choice=_baseManager.enterNumber("Options");
            switch (choice) {
                case 1:
                    _adminManager.login();
                    break;
                case 2:
                    _homeManager.manageMenu();
                    break;
                case 3:
                    _quickSearchManager.showMenu();
                    break;
                case 4:
                    _productManager.manageFilterMenu();
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
