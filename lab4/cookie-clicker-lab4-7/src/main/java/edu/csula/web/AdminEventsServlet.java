package edu.csula.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.csula.storage.servlet.EventsDAOImpl;
import edu.csula.storage.EventsDAO;
import edu.csula.models.Event;

@WebServlet("/admin/events")
public class AdminEventsServlet extends HttpServlet {
	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		// TODO: render the events page HTML
		EventsDAO dao = new EventsDAOImpl(getServletContext());
		Collection<Event> events = dao.getAll();
		System.out.println(events);
        String cssTag="<link rel='stylesheet' type='text/css' href='/app.css'>";
        String html="<html><head><title>Incremental Game</title></head>"+cssTag+"</head><body>";
        html+="<h1>Incremental Game Framework</h1>";
        html+="<h3><a href=''>Game Information</a> | <a href=''>Generators</a> | <a href='/admin/events'>Events</a> ";
        html+="<div class='flex-container1'>";
        html+="  <div>";
        html+="     <form method='POST>";
        html+="  <div class='flex-container'>";
        html+="        <div><label for='EventName'>Event Name</label></div> ";
        html+="        <div><input type='text' name='evename' id='EventName'</div>";
        html+="        <div><label for='EventDescription'>Event Description</label></div>";
        html+="        <div><textarea name='EventDescription'></textarea></div>";
        html+="        <div><label for='TriggerName'>Trigger At</label></div>";
        html+="        <div><input type='text' name='triggname' id='TriggerNameName'</div>";
        html+="   </div>";
        html+="     </form>";
        html+="  </div>";
        html+="  <div class='column'>Column2</div>\n";
        html+="</div>";
        html+="</body></html>";

		out.println(html);
	}


	@Override
	public void doPost( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO: handle upsert transaction
	}
}