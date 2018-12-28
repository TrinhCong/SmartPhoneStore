/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.util.ArrayList;
import model.Product;
import viewmodel.BillInfo;

/**
 *
 * @author tvcpr
 */
public class HomeController extends BaseController {

    private final ProductController _productManager;
    private final BillController _billManager;

    public HomeController(Connection connect) {
        super(connect);
        _productManager = new ProductController(connect);
        _billManager = new BillController(connect);
    }

    public void manageMenu() {
        int choice;
        do {
            int length = makeMenuHeader("Home");
            _productManager.setRowLength(length);
            _productManager.showAll();
            makeMenuRow("Options");
            makeMenuRow("  1.Show product detail");
            makeMenuRow("  2.Order products");
            makeMenuRow("  3.Back to main menu");
            makeMenuFooter();
            choice = enterNumber("an option");
            switch (choice) {
                case 1:
                    showDetail();
                    break;
                case 2:
                    orderProduct();
                    break;
                case 3:
                    break;
                default:
                    makeRow("Option is invalid!");
                    break;
            }
        } while (choice != 3);
    }

    public void showDetail() {
        int Id = enterNumber("Product ID");
        _productManager.display(_productManager.getProducts("p.Id=" + Id));
    }

    public void orderProduct() {
        makeMenuHeader("Product order menu");
        BillInfo billInfo = new BillInfo();
        billInfo.Customer.setName(enterString("Your Name"));
        billInfo.Customer.setPhoneNumber(enterPhoneNumber());
        billInfo.Customer.setEmail(enterEmail());
        billInfo.Bill.setReceivedAddress(enterString("Receiving Address"));
        int choice;
        do {
            billInfo = addToCart(billInfo);
            do {
                makeRow("Options:");
                makeRow("  1.Buy more product.");
                makeRow("  2.Cut off products in your cart.");
                makeRow("  3.Confirm order and back to previous menu.");
                makeRow("  4.Cancel order and back to previous menu.");
                makeMenuFooter();
                choice = enterNumber("an option");
                switch (choice) {
                    case 1:
                        clearConsole();
                        break;
                    case 2:
                        billInfo = removeFromCart(billInfo);
                        break;
                    case 3:
                        _billManager.saveAll(billInfo);
                        clearConsole();
                        break;
                    case 4:
                        clearConsole();
                        break;
                    default:
                        makeRow("Option is invalid!");
                        break;
                }
            } while (choice != 1 && choice != 3 && choice != 4);
        } while (choice != 3 && choice != 4);
        clearConsole();
    }

    public BillInfo addToCart(BillInfo billInfo) {
        makeRow("Enter ID of product you want to buy: ");
        int id = enterNumber("Product ID");
        ArrayList<Product> items = _productManager.getProducts("p.Id=" + id);
        if (items.size() > 0) {
            int quantity = enterNumber("Quantity");
            billInfo.addToCart(items.get(0), quantity);
            makeRow("Add to cart successfull!");
            clearConsole();
        } else {
            makeRow("Product has ID=" + id + " doesn't exist!");
        }
        int length = makeMenuHeader("Product order menu");
        _billManager.setRowLength(length);
        double total = _billManager.showBillInfo(billInfo.BillDetails);
        billInfo.Bill.setTotal(total);
        return billInfo;
    }

    public BillInfo removeFromCart(BillInfo billInfo) {
        makeRow("Enter ID of product you want to cut off: ");
        int id = enterNumber("Product ID");
        ArrayList<Product> items = _productManager.getProducts("p.Id=" + id);
        if (items.size() > 0) {
            int quantity = enterNumber("Quantity");
            boolean result = billInfo.removeFromCart(items.get(0), quantity);
            if (!result) {
                makeRow("This product doesn't exist in your cart");
            } else {
                makeRow("Cut off Sucessfull");
            }
        } else {
            makeRow("Product has ID=" + id + " doesn't exist!");
        }
        int length = makeMenuHeader("Product order menu");
        _billManager.setRowLength(length);
        double total = _billManager.showBillInfo(billInfo.BillDetails);
        billInfo.Bill.setTotal(total);
        return billInfo;
    }

}
