/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;

/**
 *
 * @author tvcpr
 */
public class AdminController {
    
    private Connection connection;

    public AdminController(Connection connect) {
        connection = connect;
    }
    public void login(){
        System.out.println("Cửa sổ đăng nhập!");
    }
}
