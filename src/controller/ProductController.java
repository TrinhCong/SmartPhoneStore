/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import enums.EnumString;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Bill;
import model.Product;

/**
 *
 * @author tvcpr
 */
public class ProductController extends BaseController {

    private final BrandController _brandManager;

    //menu ProductController    
    public ProductController(Connection connect) {
        super(connect);
        _brandManager = new BrandController(connect);
    }

    public void manageFilterMenu() {
        int choice;
        do {
            makeMenuHeader("Filter Products");
            makeMenuRow("1.Filter by Price");
            makeMenuRow("2.Filter by Brand");
            makeMenuRow("3.Back to previous menu");
            makeMenuFooter();
            choice = enterNumber("an option");
            clearNetbeanConsole();
            switch (choice) {
                case 1:
                    filterByPrice();
                    break;
                case 2:
                    filterByBrand();
                    break;
                case 3:
                    break;
                default:
                    makeRow("Option is invalid!");
                    break;
            }
        } while (choice != 3);
    }

    //Loc theo gia    
    public void filterByPrice() {
        int choice;
        do {
            do {
                makeMenuHeader("Filter by price");
                double minprice = enterNumber("Min price");
                double maxprice = enterNumber("Max price");
                //bat loi minprice lon hon maxprice           	        	
                if (minprice > maxprice) {
                    makeRow("Min price must be less or equal than max price!");
                    makeRow("Re-enter:");
                } else {
                    displaySimple(getProducts("p.Price>=" + minprice + " and p.Price<=" + maxprice));
                    break;
                }
            } while (true);
            do {
                makeRow("Options:");
                makeRow("  1.See product's details");
                makeRow("  2.Continue to filter by price");
                makeRow("  3.Back to previous menu");
                makeMenuFooter();
                choice = enterNumber("an option");
                switch (choice) {
                    case 1:
                        showDetailById();
                        break;
                    case 2:
                        clearNetbeanConsole();
                        break;
                    case 3:
                        clearNetbeanConsole();
                        break;
                    default:
                        makeRow("Option is invalid!");
                        break;
                }
            } while (choice != 2 && choice != 3);
        } while (choice != 3);

    }

    public void filterByBrand() {
        int choice;
        do {
           int length= makeMenuHeader("filter by brand");
           _brandManager.setRowLength(length);
            _brandManager.showAll();
            int brandId = enterNumber("Brand Id");
            displaySimple(getProducts("p.BrandId=" + brandId));
            do {
                makeRow("Options:");
                makeRow("  1.See product's details");
                makeRow("  2.Continue to filter by brand");
                makeRow("  3.Back to previous menu");
                makeMenuFooter();
                choice = enterNumber("an option");
                clearNetbeanConsole();
                switch (choice) {
                    case 1:
                        showDetailById();
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    default:
                        makeRow("Option is invalid!");
                        break;
                }
            } while (choice != 2 && choice != 3);
        } while (choice != 3);

    }

    public void showProductEditor() {
        makeMenuHeader("Products infomation Editor");
        showAll();
        makeMenuRow("Options:");
        makeMenuRow("   1.Add Product");
        makeMenuRow("   2.Edit Product");
        makeMenuRow("   3.Delete Product");
        makeMenuRow("   4.Watch Product Detail");
        makeMenuRow("   5.Back to previous page");
        makeMenuFooter();
    }
    public void showAll(){
        displaySimple(getProducts("(1=1)"));
    }
    //menu quan li san pham
    public void manageMenu() {
        int choice;
        do {
            showProductEditor();
            choice = enterNumber("an option");
            clearNetbeanConsole();
            switch (choice) {
                case 1:
                    add();
                    break;
                case 2:
                    edit();
                    break;
                case 3:
                    delete();
                    break;
                case 4:
                    showDetailById();
                    break;
                case 5:
                    break;
                default:
                    makeRow("Option is invalid!");
                    break;
            }
        } while (choice != 5);
    }


    //them san pham
    public void add() {
        makeRow("--------------");
        String product = enterString("Product name");
        try {
            statement.executeUpdate("INSERT INTO Products(Name) VALUES(N'" + product + "')");
        } catch (SQLException e) {
            exitByError();
        }
        makeRow("Add Product successfull");
    }

    // xoa san pham
    public void delete() {
        makeRow("--------------");
        int ID = enterNumber("Product Id");
        try {
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("DELETE FROM Products WHERE ID=" + ID);
        } catch (SQLException e) {
            exitByError();
        }
        makeRow("Delete Product successfull");
    }

    //edit san pham
    public void edit() {
        makeRow("--------------");
        makeRow("Input product's ID you want edit: ");
        int ID = enterNumber("ID");
        makeRow("Input product's name you want edit: ");
        String editname;
        editname = scanner.nextLine();
        try {
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("UPDATE Products SET Name=\"" + editname + "\" WHERE ID=" + ID);
        } catch (SQLException e) {
            exitByError();
        }
        makeRow("Edit Product done");
    }

    public void showDetailById() {
        int Id = enterNumber("Product Id");
        display(getProducts("p.Id=" + Id));
    }

    public void displaySimple(ArrayList<Product> products) {
        makeDivider();
        if (products.size() > 0) {
            makeRow("Product list:");
            for (Product item : products) {
                makeRow("   ID: " + item.getId() + ", Name: " + item.getName() + ", Price" + item.getPrice());
            }
        } else {
            makeRow("No product found!");
        }
        makeDivider();
    }

    public void display(ArrayList<Product> products) {
        makeDivider();
        if (products.size() > 0) {
            for (Product item : products) {
                makeRow("Product detail:");
                makeRow("   ID: " + item.getId());
                makeRow("   Name: " + item.getName());
                makeRow("   Brand Name: " + item.BrandName);
                makeRow("   Price: " + item.getPrice());
                makeRow("   ImagePath: " + item.getImagePath());
                makeRow("   Quantity: " + item.getQuantity());
                makeRow("   Dimension: " + item.getDimension());
                makeRow("   Weight: " + item.getWeight());
                makeRow("   Color: " + item.getColor());
                makeRow("   Warranty: " + (item.isWarranty() ? "yes" : "no"));
                makeRow("   Sound Type: " + item.getSoundType());
                makeRow("   Start Promotion: " + item.getStartPromotion());
                makeRow("   End Promotion: " + item.getEndPromotion());
                makeRow("   Connection Type: " + item.getConnectionType());
                makeRow("   Memory: " + item.getMemory());
                makeRow("   Promotion Price: " + item.getPromotionPrice());
                makeRow("   Battery: " + item.getBattery());
                makeRow("   OS: " + item.getOS());
                makeRow("   SDCard: " + item.getSDCard());
                makeRow("   Camera: " + item.getCamera());
            }
        } else {
            makeRow("No product found!");
        }
        makeDivider();
    }

    public ArrayList<Product> getProducts(String condition) {
        ArrayList<Product> products = new ArrayList<Product>();
        try {
            ResultSet rs = statement.executeQuery("select p.*,b.Name as BrandName from Products p,BrandCategory b where p.BrandId=b.Id and " + condition);
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("Id"));
                product.setName(rs.getString("Name"));
                product.setPrice(rs.getDouble("Price"));
                product.setImagePath(rs.getString("ImagePath"));
                product.setQuantity(rs.getInt("Quantity"));
                product.setDimension(rs.getString("Dimension"));
                product.setWeight(rs.getDouble("Weight"));
                product.setColor(rs.getString("Color"));
                product.setWarranty(rs.getBoolean("Warranty"));
                product.setSoundType(rs.getString("SoundType"));
                product.setStartPromotion(rs.getDate("StartPromotion"));
                product.setEndPromotion(rs.getDate("EndPromotion"));
                product.setConnectionType(rs.getString("ConnectionType"));
                product.setMemory(rs.getString("Memory"));
                product.setPromotionPrice(rs.getDouble("PromotionPrice"));
                product.setBattery(rs.getString("Battery"));
                product.setOS(rs.getString("OS"));
                product.setSDCard(rs.getString("SDCard"));
                product.setCamera(rs.getString("Camera"));
                product.setBrandId(rs.getInt("BrandId"));
                product.BrandName = rs.getString("BrandName");
                products.add(product);
            }
        } catch (SQLException ex) {
            exitByError();
        }
        return products;
    }
}
