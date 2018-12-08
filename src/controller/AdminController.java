package controller;

import enums.EnumPosition;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author tvcpr
 */
public class AdminController extends BaseController {

    private final BillController _billManager;
    private final ProductController _productManager;
    private final BrandController _brandManager;

    public AdminController(Connection connect) {
        super(connect);
        _billManager = new BillController(connect);
        _productManager = new ProductController(connect);
        _brandManager=new BrandController(connect);
    }

    public void login() {
        makeSpace(EnumPosition.DASH_TOP);
        System.out.println("__Login__");
        boolean isValid = false;
        do {
            System.out.print("Enter user name:");
            String name = scanner.nextLine();
            System.out.print("Enter password:");
            String pass = scanner.nextLine();
            try {
                Statement stm = connection.createStatement();
                ResultSet rs = stm.executeQuery("SELECT * FROM Admins WHERE UserName='" + name.trim() + "' and Password='" + pass.trim() + "' ");
                if (rs.isBeforeFirst()) {
                    makeSpace(EnumPosition.DASH_TOP);
                    rs.next();
                    System.out.println("Welcome Admin: " + rs.getString("DisplayName"));
                    interact();
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
                 exitByError();
            }
        } while (!isValid);

    }

    public void showMenu() {
        makeSpace(EnumPosition.DASH_TOP);
        System.out.println("----- System management -----");
        System.out.println("1.Manage Products");
        System.out.println("2.Manage Brands");
        System.out.println("3.Manage Product Bills");
        System.out.println("4.Sign out and back to main menu\n");
    }

    public void interact() {
        int choice;
        do {
            showMenu();
            choice = enterNumber("Option");
            switch (choice) {
                case 1:
                    _productManager.manageMenu();
                    break;
                case 2:
                    _brandManager.manageMenu();
                    break;
                case 3:
                    _billManager.manageMenu();
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Option is invalid!");
                    break;
            }
        } while (choice != 4);
    }

}
