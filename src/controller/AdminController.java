package controller;

import enums.EnumPosition;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tvcpr
 */
public class AdminController extends BaseController {

    private BillController _billManager;

    public AdminController(Connection connect) {
        super(connect);
        _billManager = new BillController(connect);
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
                ResultSet rs = stm.executeQuery("SELECT * FROM Admins WHERE UserName='" + name.trim() + "' and Password='" + pass.trim() + "' ");
                if (rs.isBeforeFirst()) {
                    System.out.println("Welcome Admin: " + name.trim());
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
                System.out.println("Connection fail!");
                System.exit(0);
            }
        } while (!isValid);

    }

    public void showMenu() {
        System.out.println("----- System management -----");
        System.out.println("1.Manage Products");
        System.out.println("2.Manage Product Bills");
        System.out.println("3.Back to previous menu\n");
    }

    public void interact() {
        int choice;
        do {
            showMenu();
            System.out.print("==> Enter an option:");
            choice = enterChoice();
            switch (choice) {
                case 1:
                    manageProduct();
                    break;
                case 2:
                    _billManager.manageBill();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Option is invalid!");
                    break;
            }
        } while (choice != 3);
    }

    public void showProductEditor() {
        makeSpace(EnumPosition.DASH_TOP);
        System.out.println("-----Products infomation Editor-------");
        showProducts();
        System.out.println("1.Add Product");
        System.out.println("2.Edit Product");
        System.out.println("3.Delete Product");
        System.out.println("4.Watch Product Detail");
        System.out.println("5.Back to previous page");
        makeSpace(EnumPosition.DASH_BOTTOM);
    }

    public void showProducts() {
        String idStr = "";
        double id;
        do {
            System.out.print("Enter product ID you want to see detail: ");
            idStr = scanner.nextLine();
            if (isNumeric(idStr)) {
                break;
            } else {
                System.out.println("ID must be number!");
            }
        } while (true);
        id = Double.parseDouble(idStr);
        try {

            Statement st = connection.createStatement();
            ResultSet r = st.executeQuery("select * from Products");
            if (r.isBeforeFirst()) {
                while (r.next()) {
                    if (r.getDouble(3) == id) {
                        System.out.println("id: " + r.getString(1) + "\tName: " + r.getString(2) + "\tPrice: " + r.getString(3) + "\t Weight: " + r.getString(7) + "\t Color: " + r.getString(8));
                    }
                }
            }
            else{
                
            }

        } catch (SQLException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void manageProduct() {
        int choice;
        do {
            showProductEditor();
            System.out.print("==> Enter an option:");
            choice = enterChoice();
            switch (choice) {
                case 1:
                    showProducts();
                    break;
                case 2:

                    break;
                case 3:
                    break;
                default:
                    System.out.println("Option is invalid!");
                    break;
            }
        } while (choice != 3);
    }

}
