


package edu.csula.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.csula.models.GuestBookEntry;

@WebServlet(loadOnStartup = 1, urlPatterns = {"/guestbook"})
public class GuestBookServlet extends HttpServlet {

    public void init() {
        // init the application scope to have pre-set values
        Collection<GuestBookEntry> entries = new ArrayList<>();
        entries.add(new GuestBookEntry(entries.size(), "Eric", "Hello"));
        entries.add(new GuestBookEntry(entries.size(), "John", "Hi there"));
        getServletContext().setAttribute("guestbook-entries", entries);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String html = "<h1>Hello GuestBook</h1>";
        ArrayList<GuestBookEntry> entries = (ArrayList<GuestBookEntry>) getServletContext().getAttribute("guestbook-entries");
        html += "<table border='1'>";
        for (GuestBookEntry entry : entries) {
            html += "<tr>";
            html += "<td>" + entry.getUsername() + "says" + "</td>" + "<td>" + entry.getComment() + "</td>" + "<td><a href='/guestbook/edit?id=" + entry.getId() + "'> Edit</a>|<a href='/guestbook/delete?id=" + entry.getId() + "'> Delete</a></td>";
            html += "</tr>";
        }
        html += "</table>";

        html += "<form method='POST'>";
        html += "<h2>Add comment<h2>";
        html += "<label for='name'>Name:</label>";
        html += "<input name='username' id='name'type='text'/>";
        html += "<textarea name='comment'></textarea>";
        html += "<button>Submit</button>";
        html += "</form>";


        out.println(html);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<GuestBookEntry> entries = (ArrayList<GuestBookEntry>) getServletContext().getAttribute("guestbook-entries");
        String username = request.getParameter("username");
        String comment = request.getParameter("comment");
        GuestBookEntry entry = new GuestBookEntry(entries.size(), username, comment);
        entries.add(entry);
        getServletContext().setAttribute("guestbook-entries", entries);
        response.sendRedirect("/guestbook");
    }
}
