package edu.csula.web;

import edu.csula.models.GuestBookEntry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/guestbook/delete")
public class GuestBookDeleteServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<GuestBookEntry> entries=(ArrayList<GuestBookEntry>)  getServletContext().getAttribute("guestbook-entries");
        int id=Integer.parseInt(request.getParameter("id"));

        for(int i=0;i<entries.size();i++){
            if(entries.get(i).getId()==id){
                entries.remove(i);
            }
        }

        response.sendRedirect("/guestbook");
    }
}
