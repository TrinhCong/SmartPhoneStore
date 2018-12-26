/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
            clearConsole();
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
                        clearConsole();
                        break;
                    case 3:
                        clearConsole();
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
            int length = makeMenuHeader("filter by brand");
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
                clearConsole();
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

    public void showAll() {
        displaySimple(getProducts("(1=1)"));
    }

    //menu quan li san pham
    public void manageMenu() {
        int choice;
        do {
            showProductEditor();
            choice = enterNumber("an option");
            clearConsole();
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
        Product product = new Product();
        product.setName(enterString("Product Name"));
        product.setPrice(enterNumber("Price"));
        product.setImagePath(enterString("Image Path"));
        product.setQuantity(enterNumber("Quantity"));
        product.setDimension(enterString("Dimension"));
        product.setWeight(enterNumber("Weight"));
        product.setColor(enterString("Color"));
        product.setWarranty(enterBoolean("Warranty"));
        product.setSoundType(enterString("SoundType"));
        product.setStartPromotion(enterDate("StartPromotion"));
        product.setEndPromotion(enterDate("EndPromotion"));
        product.setConnectionType(enterString("ConnectionType"));
        product.setMemory(enterString("Memory"));
        product.setPromotionPrice(enterNumber("PromotionPrice"));
        product.setBattery(enterString("Battery"));
        product.setOS(enterString("OS"));
        product.setSDCard(enterString("SDCard"));
        product.setCamera(enterString("Camera"));
        _brandManager.showAll();
        do {

            int brandId = enterNumber("Brand Id");
            if (_brandManager.isExist(brandId)) {
                product.setBrandId(brandId);
                break;
            } else {
                makeRow("Brand ID doesn't exist! Please re-enter!");
            }
        } while (true);
        makeDivider();
        if (saveOrUpdate(product)) {
            makeRow("Add Product successfull!");
        } else {
            makeRow("Add Product failed!");
        }
        makeDivider();
    }

    public boolean saveOrUpdate(Product product) {
        try {
            String sql = "";
            if (product.getId() == 0) {
                sql = "INSERT INTO Products\n"
                        + "(Name, Price, ImagePath, Quantity, Dimension, Weight, Color, Warranty, SoundType, StartPromotion, EndPromotion, ConnectionType, Memory, PromotionPrice, Battery, OS, SDCard, Camera, BrandId)\n"
                        + "VALUES(N'" + product.getName() + "',\n"
                        + product.getPrice() + ",\n"
                        + "N'" + product.getImagePath() + "', \n"
                        + product.getQuantity() + ", \n"
                        + "N'" + product.getDimension() + "', \n"
                        + product.getWeight() + ", \n"
                        + "N'" + product.getColor() + "', \n"
                        + (product.isWarranty() ? 1 : 0) + ", \n"
                        + "N'" + product.getSoundType() + "',\n"
                        + "N'" + product.getStartPromotion() + "',\n"
                        + "N'" + product.getEndPromotion() + "', \n"
                        + "N'" + product.getConnectionType() + "', \n"
                        + "N'" + product.getMemory() + "',\n"
                        + product.getPrice() + ", \n"
                        + "N'" + product.getPromotionPrice() + "', \n"
                        + "N'" + product.getOS() + "', \n"
                        + "N'" + product.getSDCard() + "', \n"
                        + "N'" + product.getCamera() + "', \n"
                        + product.getBrandId() + ")";
            } else {
                sql = "UPDATE Products\n"
                        + "SET Name=N'" + product.getName() + "',\n"
                        + "Price=" + product.getPrice() + ",\n"
                        + "ImagePath=N'" + product.getImagePath() + "',\n"
                        + "Quantity=" + product.getQuantity() + ", Dimension='',\n"
                        + "Weight=" + product.getWeight() + ",\n"
                        + "Color=N'" + product.getColor() + "',\n"
                        + "Warranty=" + (product.isWarranty() ? 1 : 0) + ",\n"
                        + "SoundType=N'" + product.getSoundType() + "',\n"
                        + "StartPromotion=N'" + product.getStartPromotion() + "',\n"
                        + "EndPromotion=N'" + product.getEndPromotion() + "',\n"
                        + "ConnectionType=N'" + product.getConnectionType() + "',\n"
                        + "Memory=N'" + product.getMemory() + "',\n"
                        + "PromotionPrice=" + product.getPromotionPrice() + ",\n"
                        + "Battery=N'" + product.getBattery() + "',\n"
                        + "OS=N'" + product.getOS() + "',\n"
                        + "SDCard=N'" + product.getSDCard() + "',\n"
                        + "Camera=N'" + product.getCamera() + "',\n"
                        + "BrandId=" + product.getBrandId() + "\n"
                        + "WHERE Id=" + product.getId();
            }
            statement.executeUpdate(sql);
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    // xoa san pham
    public void delete() {
        do {
            try {
                int Id = enterNumber("Product ID");
                if (getProducts("p.Id=" + Id).size() > 0) {
                    int rows = statement.executeUpdate("DELETE FROM Products WHERE Id=" + Id);
                    if (rows > 0) {
                        makeDivider();
                        makeRow("Delete Product successfull");
                        makeDivider();
                    }
                    else
                    {
                        makeDivider();
                        makeRow("Delete Product failed!");
                        makeDivider();
                    }
                    break;
                } else {
                    makeRow("The product has ID = " + Id + " doesn't exist! Re-enter Product Id!");
                }
            } catch (SQLException e) {
                exitByError();
            }
        } while (true);

    }

    //edit san pham
    public void edit() {
        Product product = new Product();
        do {
            int Id = enterNumber("Product ID");
            ArrayList<Product> products = getProducts("p.Id=" + Id);
            if (products.size() > 0) {
                product = products.get(0);
                break;
            } else {
                makeRow("Product has ID = " + Id + " is doesn't exist! Please re-enter!");
            }
        } while (true);

        makeDivider();
        makeRow("Product detail:");
        makeRow(" ID: " + product.getId());
        makeRow(" 1. Name: " + product.getName());
        makeRow(" 2. Price: " + product.getPrice());
        makeRow(" 3. ImagePath: " + product.getImagePath());
        makeRow(" 4. Quantity: " + product.getQuantity());
        makeRow(" 5. Dimension: " + product.getDimension());
        makeRow(" 6. Weight: " + product.getWeight());
        makeRow(" 7. Color: " + product.getColor());
        makeRow(" 8. Warranty: " + (product.isWarranty() ? "yes" : "no"));
        makeRow("09. Sound Type: " + product.getSoundType());
        makeRow("10. Start Promotion: " + product.getStartPromotion());
        makeRow("11. End Promotion: " + product.getEndPromotion());
        makeRow("12. Connection Type: " + product.getConnectionType());
        makeRow("13. Memory: " + product.getMemory());
        makeRow("14. Promotion Price: " + product.getPromotionPrice());
        makeRow("15. Battery: " + product.getBattery());
        makeRow("16. OS: " + product.getOS());
        makeRow("17. SDCard: " + product.getSDCard());
        makeRow("18. Camera: " + product.getCamera());
        makeDivider();
        int index = enterNumber("index number you want to edit infomation");
        switch (index) {
            case 1:
                product.setName(enterString("Product Name"));
                break;
            case 2:
                product.setPrice(enterNumber("Price"));
                break;
            case 3:
                product.setImagePath(enterString("Image Path"));
                break;
            case 4:
                product.setQuantity(enterNumber("Quantity"));
                break;
            case 5:
                product.setDimension(enterString("Dimension"));
                break;
            case 6:
                product.setWeight(enterNumber("Weight"));
                break;
            case 7:
                product.setColor(enterString("Color"));
                break;
            case 8:
                product.setWarranty(enterBoolean("Warranty"));
                break;
            case 9:
                product.setSoundType(enterString("Sound Type"));
                break;
            case 10:
                product.setStartPromotion(enterDate("Start Promotion"));
                break;
            case 11:
                product.setEndPromotion(enterDate("Start Promotion"));
                break;
            case 12:
                product.setConnectionType(enterString("Connection Type"));
                break;
            case 13:
                product.setMemory(enterString("Memory"));
                break;
            case 14:
                product.setPromotionPrice(enterNumber("Promotion Price"));
                break;
            case 15:
                product.setBattery(enterString("Battery"));
                break;
            case 16:
                product.setOS(enterString("OS"));
                break;
            case 17:
                product.setSDCard(enterString("SD Card"));
                break;
            case 18:
                product.setCamera(enterString("Camera"));
                break;
            default:
                break;
        }
        makeDivider();
        if (saveOrUpdate(product)) {
            makeRow("Update Product successfull!");
        } else {
            makeRow("Update Product failed!");
        }
        makeDivider();
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
                product.setStartPromotion(rs.getString("StartPromotion"));
                product.setEndPromotion(rs.getString("EndPromotion"));
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
