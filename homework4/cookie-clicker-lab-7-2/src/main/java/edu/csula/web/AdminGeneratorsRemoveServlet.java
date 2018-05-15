package edu.csula.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.csula.storage.GeneratorsDAO;
import edu.csula.storage.UsersDAO;
import edu.csula.storage.mysql.*;
import edu.csula.storage.servlet.UsersDAOImpl;

@WebServlet("/generatorsremove")

public class AdminGeneratorsRemoveServlet extends HttpServlet {

    @Override
    public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO: handle upsert transaction
        UsersDAO dao1=new UsersDAOImpl(request.getSession());
        if(dao1 != null) {
            //GeneratorsDAO dao = new GeneratorsDAOImpl(getServletContext());
            GeneratorsDAO dao = new GeneratorsDAOImpl(new Database());
            int id = Integer.parseInt(request.getParameter("id"));
            dao.remove(id);
            response.sendRedirect("generators");
        }
        else{
            dao1.logout();
        }
    }
}