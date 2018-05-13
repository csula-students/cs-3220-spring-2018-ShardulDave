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

import edu.csula.storage.UsersDAO;
import edu.csula.storage.servlet.EventsDAOImpl;
import edu.csula.storage.EventsDAO;
import edu.csula.models.Event;
import edu.csula.storage.servlet.UsersDAOImpl;

@WebServlet("/eventsremove")

public class AdminEventsRemoveServlet extends HttpServlet {

    @Override
    public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO: handle upsert transaction
        UsersDAO dao1=new UsersDAOImpl(request.getSession());
        if(dao1 != null) {
            EventsDAO dao = new EventsDAOImpl(getServletContext());
            int id = Integer.parseInt(request.getParameter("id"));
            dao.remove(id);
            response.sendRedirect("events");
        }
        else{
            dao1.logout();
        }
    }
}