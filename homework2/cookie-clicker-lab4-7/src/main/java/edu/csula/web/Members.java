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

import edu.csula.storage.servlet.EventsDAOImpl;
import edu.csula.storage.EventsDAO;
import edu.csula.models.Event;

@WebServlet("/members")
public class Members extends HttpServlet {

    public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String user=(String) request.getSession().getAttribute("user");
        //Check id there is a session attribute "user", if yes display

        if(user != null) {


            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            // TODO: render the events page HTML

            String html = "<html><head><title>Members</title></head><body>";
            html += "<h1>HELLO MEMBERS</h1>";
            html += "<p>Welcome user" + user + "</p>";
            html += "<p><a href='/logout'>Logout</a></p>";
            html += "</body></html>";


            out.println(html);
        }

        else{
            response.sendRedirect("members");
        }

        //if not redirect to login page
    }

}