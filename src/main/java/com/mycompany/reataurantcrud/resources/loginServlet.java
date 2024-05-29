/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.reataurantcrud.resources;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.*;
import java.sql.*;

/**
 *
 * @author HP
 */
@WebServlet(name = "loginServlet", urlPatterns = {"/login"})
public class loginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String pwd = request.getParameter("password");

        try {
            
            PreparedStatement stm = DatabaseConnection.connect().prepareStatement("SELECT * FROM user where name='"+name+"' and password='"+pwd+"'");
            ResultSet C = stm.executeQuery();
            
        
            if (C.next()) {
                PreparedStatement selectPage = DatabaseConnection.connect().prepareStatement("SELECT role FROM user where password=? and role='admin'");
                selectPage.setString(1, pwd);
                ResultSet rsRole = selectPage.executeQuery();
                if (rsRole.next()) {
                    response.sendRedirect("dashboard.html");
                } else {
                    response.sendRedirect("userFoodList.html");
                }
            } else {
                out.println("Wrong email or password");
            }
            
        } catch (SQLException e) {
            out.println("Can't connect");
            e.printStackTrace();
        }
    }
}