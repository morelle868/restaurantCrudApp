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
//import com.mycompany.reataurantcrud.resources.signup;
import java.util.*;
import java.sql.*;

/**
 *
 * @author HP
 */
@WebServlet(name = "signupServlet", urlPatterns = {"/signup"})
public class signupServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
@Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve user information from request parameters
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Call the register method to insert the user into the database
        try {
            PreparedStatement p = DatabaseConnection.connect().prepareStatement("select * from user where email='"+email+"'");
            if ( p != null ){
                System.out.println("User already exist");
               
            }
            //else{
           
            PreparedStatement ps = DatabaseConnection.connect().prepareStatement("INSERT INTO user (name, email, password) VALUES (?, ?, ?)");
            ps.setString(1,name);
            ps.setString(2, email);
            ps.setString(3, password); 

            ps.executeUpdate();
            response.sendRedirect("login.html");
            //}
            // Redirect after successful insertion
        } catch ( SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            // Handle database errors here
            // You may want to display an error message to the user or log the error
        }
    }

public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
