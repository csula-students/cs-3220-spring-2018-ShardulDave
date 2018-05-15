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
import edu.csula.storage.servlet.EventsDAOImpl;
import edu.csula.storage.EventsDAO;
import edu.csula.models.Event;
import edu.csula.storage.servlet.GeneratorsDAOImpl;

@WebServlet("/generatorsremove")

public class AdminGeneratorsRemoveServlet extends HttpServlet {

    @Override
    public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO: handle upsert transaction
        GeneratorsDAO dao = new GeneratorsDAOImpl(getServletContext());
        int id=Integer.parseInt(request.getParameter("id"));
        dao.remove(id);
        response.sendRedirect("generators");
    }
}