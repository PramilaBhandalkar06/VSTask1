package com.app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegisterServlet")

public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
        	
        	Class.forName("com.mysql.cj.jdbc.Driver");


            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/registrationdb1",
                    "root",
                    "root"  // change to your MySQL password
            );

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO users(name,email,password) VALUES(?,?,?)"
            );

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);

            int i = ps.executeUpdate();
            con.close();

            if (i > 0) {
                // Redirect back to register.html with success
                response.sendRedirect("Register.html?status=success");
            } else {
                response.sendRedirect("Register.html?status=failure");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("Register.html?status=failure");
        }
    }
}

