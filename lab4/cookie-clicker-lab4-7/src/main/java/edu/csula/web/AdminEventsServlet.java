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

@WebServlet("/admin/events")
public class AdminEventsServlet extends HttpServlet {
	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		// TODO: render the events page HTML
		EventsDAO dao = new EventsDAOImpl(getServletContext());
		Collection<Event> events = dao.getAll();
		events.add(new Event(1,"Hehe","wdwqd",1));
        events.add(new Event(2,"Hehe","wdwqd",2));
		System.out.println(events);
        String cssTag="<link rel='stylesheet' type='text/css' href='/app.css'>";
        String html="<html><head><title>Incremental Game</title></head>"+cssTag+"</head><body>";
        html+="<h1>Incremental Game Framework</h1>";
        html+="<h3><a href=''>Game Information</a> | <a href=''>Generators</a> | <a href='/admin/events'>Events</a> ";
        html+="     <form method='POST>";
        html+="        <label for='EventName'>Event Name</label>";
        html+="        <input type='text' name='evename' id='EventName'";
        html+="        <label for='EventDescription'>Event Description</label>";
        html+="        <textarea name='EventDescription'></textarea>";
        html+="        <label for='TriggerName'>Trigger At</label>";
        html+="        <input type='number' name='triggname' id='TriggerNameName'>";
        html+="        <button>Submit</button>";
        html+="     </form>";
        html+="     <table border='1'>";
        html+="         <tr><th>Name</th><th>Description</th><th>Trigger At</th><th>Actions</th></tr>";
                    for(Event e:events){
                        html+="<tr>";
                        html+="<td>"+e.getName()+"</td>"+"<td>"+e.getDescription()+"</td>"+"<td>"+e.getTriggerAt()+"</td>"+"<td>edit|delete"+"</td>";
                        html+="</tr>";
                    }
        html+="     </table>";
        html+="</body></html>";


		out.println(html);
	}


	@Override
	public void doPost( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO: handle upsert transaction
        EventsDAO dao = new EventsDAOImpl(getServletContext());
        String name=request.getParameter("evename");
        String description=request.getParameter("EventDescription");
        int triggerAt=Integer.parseInt(request.getParameter("triggname"));
        Event e=new Event(1,name,description,triggerAt);
        dao.add(e);
        response.sendRedirect("/admin/events");

	}
}
