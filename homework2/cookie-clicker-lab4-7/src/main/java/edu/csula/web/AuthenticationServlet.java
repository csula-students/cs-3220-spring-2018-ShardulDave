package edu.csula.web;

import edu.csula.storage.UsersDAO;
import edu.csula.storage.servlet.UsersDAOImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin/auth")
public class AuthenticationServlet extends HttpServlet {
	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");

            PrintWriter out = response.getWriter();
            // TODO: render the authentication page HTML

            String cssTag = "<link rel='stylesheet' type='text/css' href='/app.css'>";
            String html = "<html><head><title>Login</title>" + cssTag + "</head><body>";
            html += "<h1>Incremental Game Framework</h1>";
            html += "     <form action='/admin/auth' method='POST'>";
            html += "        <label for='username'>Username</label>";
            html += "        <input type='text' name='username' id='uname'>";
            html += "        <label for='password'>Password</label>";
            html += "        <input type='text' name='password' id='pass'>";
            html += "        <button>Log In</button>";
            html += "     </form>";
            html += "</body></html>";
            out.println(html);

	}

	@Override
	public void doPost( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO: handle login
        // TODO: handle upsert transaction
        String username=request.getParameter("username");
        String password=request.getParameter("password");

        UsersDAO daosession=new UsersDAOImpl(request.getSession());
        //Check is username and password are correct, redirect to members
        if(daosession.authenticate(username,password)){
            response.sendRedirect("/admin/events");
        }

        //redirect to login
        else{
            response.sendRedirect("/admin/auth");
        }
	}

    @Override
    public void doDelete( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO: handle logout
        UsersDAO daosession=new UsersDAOImpl(request.getSession());
        daosession.logout();
    }
}
