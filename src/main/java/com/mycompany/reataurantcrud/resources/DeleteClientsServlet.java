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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import java.sql.*;

/**
 *
 * @author HP
 */
@WebServlet(name = "DeleteClientsServlet", urlPatterns = {"/deleteClient"})
public class DeleteClientsServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DeleteClientsServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DeleteClientsServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }


    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        try {
     Class.forName("com.mysql.jdbc.Driver");
        PreparedStatement p = DatabaseConnection.connect().prepareStatement("DELETE * FROM user");
        ResultSet rs = p.executeQuery();
        
        if(rs.next()){
         response.sendRedirect("index.html");
        }
        else{
                  response.sendRedirect("error");
        }
       
    } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace(); 
    }
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
