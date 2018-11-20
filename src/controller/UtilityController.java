/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author tvcpr
 */
public class UtilityController {

    public void showMainMenu() {
        System.out.println("-----PHẦN MỀM QUẢN LÝ CỬA HÀNG ĐTDD -----");
        System.out.println("1. Đăng nhập hệ thống với quyền Admin.");
        System.out.println("2. Trang chủ.");
        System.out.println("3. Tìm kiếm nhanh.");
        System.out.println("4. Lọc sản phẩm.");
        System.out.println("5. Thoát.");
        System.out.println("-------------------------------------------------------");
        System.out.print("Vui lòng nhập 1 lựa chọn:");
    }

    public boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
