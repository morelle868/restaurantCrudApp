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
@WebServlet(name = "CategoryServlet", urlPatterns = {"/category"})
public class CategoryServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       // processRequest(request, response);
        PrintWriter out = response.getWriter();
        
        String name = request.getParameter("name");
        String des = request.getParameter("cat_des");
       String price = request.getParameter("price_racge");
        
        try {
            PreparedStatement stm = DatabaseConnection.connect().prepareStatement("SELECT * FROM Fcategory WHERE name='"+name+"' ");
            ResultSet Rs = stm.executeQuery();
            if (Rs.next()) {
                System.out.println("food already exists !");
                response.sendRedirect("catergory.html");
            }
            else {
                PreparedStatement insert =DatabaseConnection.connect().prepareStatement("INSERT INTO Fcategory(name,cat_des,price_racge) VALUES(?,?,?)");
                insert.setString(1, name);
                insert.setString(2, des);
                insert.setString(3, price);
                insert.executeUpdate();
                    response.sendRedirect("categoryList.html");
                    System.out.println("Success Inserting new food !");
            }
        }
        catch (Exception e) {
             System.out.println(e.getMessage());
            response.sendRedirect("register.html");
        }
    
    }
}