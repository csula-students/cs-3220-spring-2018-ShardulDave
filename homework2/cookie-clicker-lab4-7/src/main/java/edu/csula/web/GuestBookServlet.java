package edu.csula.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.csula.models.GuestBookEntry;

@WebServlet(loadOnStartup=1, urlPatterns={"/guestbook"})
public class GuestBookServlet extends HttpServlet {

	public void init() {
		// init the application scope to have pre-set values
		Collection<GuestBookEntry> entries = new ArrayList<>();
		entries.add(new GuestBookEntry(entries.size(), "Eric", "Hello"));
		entries.add(new GuestBookEntry(entries.size(), "John", "Hi there"));
		getServletContext().setAttribute("guestbookentries", entries);
	}

	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		ArrayList<GuestBookEntry> entries=(ArrayList<GuestBookEntry>)  getServletContext().getAttribute("guestbookentries");
		request.getRequestDispatcher("WEB-INF/guestbook.jsp").forward(request, response);
	}

    @Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        ArrayList<GuestBookEntry> entries=(ArrayList<GuestBookEntry>)  getServletContext().getAttribute("guestbookentries");
        String username=request.getParameter("username");
        String comment=request.getParameter("comment");
        GuestBookEntry entry=new GuestBookEntry(entries.size(),username,comment);
        entries.add(entry);
        getServletContext().setAttribute("guestbookentries",entries);
        response.sendRedirect("/guestbook");
    }
}
