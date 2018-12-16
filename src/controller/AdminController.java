package controller;

import enums.EnumSpecialCharacter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        _brandManager = new BrandController(connect);
    }

    public void login() {
//        makeMenuHeader("login");
//        boolean isValid = false;
//        do {
//            String name = enterString("user name");
//            String pass = enterString("password");
//            try {
//                ResultSet rs = statement.executeQuery("SELECT * FROM Admins WHERE UserName='" + name.trim() + "' and Password='" + pass.trim() + "' ");
//                if (rs.isBeforeFirst()) {
//                    rs.next();
//                    makeDivider(EnumSpecialCharacter.STAR);
//                    makeMenuRow("Welcome Admin: " + rs.getString("DisplayName"));
//                    makeDivider(EnumSpecialCharacter.STAR);
                    interact();
//                    isValid = true;
//                } else {
//                    System.out.println("¤ Wrong user name or password!");
//                    System.out.print("¤ Do you want to retry? (y/n): ");
//                    String choice = scanner.nextLine();
//                    if (!choice.equalsIgnoreCase("y")) {
//                        break;
//                    }
//                }
//
//            } catch (SQLException ex) {
//                exitByError();
//            }
//        } while (!isValid);

    }

    public void interact() {
        int choice;
        do {
            makeMenuHeader("System management window");
            makeMenuRow("1.Manage Products");
            makeMenuRow("2.Manage Brands");
            makeMenuRow("3.Manage Product Bills");
            makeMenuRow("4.Sign out and back to main menu");
            makeMenuFooter();
            choice = enterNumber("an option");
            clearNetbeanConsole();
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
                    System.out.println("¤ Option is invalid!");
                    break;
            }
        } while (choice != 4);
    }

}
