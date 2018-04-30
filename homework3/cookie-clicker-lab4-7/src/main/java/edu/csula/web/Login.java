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

@WebServlet("/login")
public class Login extends HttpServlet {

    public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        // TODO: render the events page HTML

        String html="<html><head><title>Login</title></head><body>";
        html+="<h1>Login</h1>";
        html+="     <form action='login' method='POST'>";
        html+="        <label for='username'>Username</label>";
        html+="        <input type='text' name='username' id='EventName'>";
        html+="        <label for='password'>Password</label>";
        html+="        <input type='text' name='password' id='TriggerNameName'>";
        html+="        <button>Login</button>";
        html+="     </form>";
        html+="</body></html>";


        out.println(html);
    }

    @Override
    public void doPost( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO: handle upsert transaction
        String username=request.getParameter("username");
        String password=request.getParameter("password");

        //Check is username and password are correct, redirect to members
        if(username.equals("shardul") && password.equals("12345")){
            request.getSession().setAttribute("user",username);
            response.sendRedirect("members");
        }
        //redirect to login
        else{
            response.sendRedirect("login");
        }
    }
}

