package edu.csula.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.csula.models.User;
import edu.csula.storage.UsersDAO;
import edu.csula.storage.servlet.EventsDAOImpl;
import edu.csula.storage.EventsDAO;
import edu.csula.models.Event;
import edu.csula.storage.servlet.UsersDAOImpl;

@WebServlet("/admin/events")
public class AdminEventsServlet extends HttpServlet {
	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		// TODO: render the events page HTML
		EventsDAO dao = new EventsDAOImpl(getServletContext());
		Collection<Event> events = dao.getAll();

		UsersDAO daosession=new UsersDAOImpl(request.getSession());
		if(daosession != null) {

            String cssTag = "<link rel='stylesheet' type='text/css' href='/app.css'>";
            String html = "<html><head><title>Incremental Game</title>" + cssTag + "</head><body>";
            html += "<h1>Incremental Game Framework</h1>";
            html += "<h3><a href=''>Game Information</a> | <a href='/admin/generators'>Generators</a> | <a href='/admin/events'>Events</a> | <a href='/admin/auth'>Logout</a> ";
            html += "<div class='row'>";
            html += "<div class='column'>";
            html += "<div class='flex-container'>";
            html += "     <form method='POST'>";
            html += "        <div><label for='EventName'>Event Name</label></div>";
            html += "        <div><input type='text' name='evename' id='EventName'</div>";
            html += "        <div><label for='EventDescription'>Event Description</label></div>";
            html += "        <div><textarea name='EventDescription'></textarea></div>";
            html += "        <div><label for='TriggerName'>Trigger At</label></div>";
            html += "        <div><input type='number' name='triggname' id='TriggerName'></div>";
            html += "        <div><button>Submit</button></div>";
            html += "     </form>";
            html += "</div>";
            html += "</div>";
            html += "<div class='column'>";

            html += "</div>";
            html += "</div>";
            html += "     <table border='1' cellpadding='15'>";
            html += "         <tr><th>Name</th><th>Description</th><th>Trigger At</th><th>Actions</th></tr>";
            for (Event e : events) {
                html += "<tr>";
                html += "<td>" + e.getName() + "</td>" + "<td>" + e.getDescription() + "</td>" + "<td>" + e.getTriggerAt() + "</td>" + "<td><a href='/admin/events/edit?id=" + e.getId() + "'>edit</a>|<a href='/admin/events/remove?id=" + e.getId() + "'>delete</a>" + "</td>";
                html += "</tr>";
            }
            html += "     </table>";
            html += "</body></html>";


            out.println(html);
        }

        else{
		    daosession.logout();
            response.sendRedirect("/admin/auth");
        }
	}


	@Override
	public void doPost( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO: handle upsert transaction
		EventsDAO dao = new EventsDAOImpl(getServletContext());
		Collection<Event> events = dao.getAll();
		String name=request.getParameter("evename");
		String description=request.getParameter("EventDescription");
		int triggerAt=Integer.parseInt(request.getParameter("triggname"));
		Event e=new Event(events.size(),name,description,triggerAt);
		dao.add(e);
		response.sendRedirect("/admin/events");
	}
}