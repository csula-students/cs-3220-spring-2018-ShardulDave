package edu.csula.web;

import edu.csula.models.GuestBookEntry;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

@WebServlet("/guestbook/edit")

public class GuestBookEditServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<GuestBookEntry> entries=(ArrayList<GuestBookEntry>)  getServletContext().getAttribute("guestbook-entries");
        int id=Integer.parseInt(request.getParameter("id"));
        GuestBookEntry entry=null;

        for(GuestBookEntry e: entries){
            if(e.getId()==id){
                entry=e;
            }
        }
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String html="";
        html+="<form method='POST'>";
        html+="<h2>Edit comment<h2>";
        html+="<label for='name'>Name:</label>";
        html+="<input name='username' id='name'type='text' value='"+entry.getUsername()+"'/>";
        html+="<textarea name='comment'>"+entry.getComment()+"</textarea>";
        html+="<button>Submit</button>";
        html+="</form>";

        out.println(html);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<GuestBookEntry> entries=(ArrayList<GuestBookEntry>)  getServletContext().getAttribute("guestbook-entries");
        int id=Integer.parseInt(request.getParameter("id"));
        GuestBookEntry entry=null;

        for(GuestBookEntry e: entries){
            if(e.getId()==id){
                entry=e;
            }
        }

        String username=request.getParameter("username");
        String comment=request.getParameter("comment");
        entry.setUsername(username);
        entry.setComment(comment);

        response.sendRedirect("/guestbook");
    }
}
