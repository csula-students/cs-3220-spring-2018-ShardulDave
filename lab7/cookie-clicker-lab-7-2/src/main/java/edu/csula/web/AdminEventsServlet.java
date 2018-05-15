package edu.csula.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import edu.csula.storage.EventsDAO;
import edu.csula.storage.mysql.*;
import edu.csula.models.*;


@WebServlet("/events")
public class AdminEventsServlet extends HttpServlet {
	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		// TODO: render the events page HTML
		//EventsDAO dao = new EventsDAOImpl(getServletContext());
		EventsDAO dao = new EventsDAOImpl(new Database());
		Collection<Event> events = dao.getAll();
		request.setAttribute("events",events);
		request.getRequestDispatcher("/WEB-INF/admin-events.jsp").forward(request, response);
	}


	@Override
	public void doPost( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO: handle upsert transaction
		//EventsDAO dao = new EventsDAOImpl(getServletContext());
		EventsDAO dao = new EventsDAOImpl(new Database());
		Collection<Event> events = dao.getAll();
		String name=request.getParameter("evename");
		String description=request.getParameter("EventDescription");
		int triggerAt=Integer.parseInt(request.getParameter("triggname"));
		Event e=new Event(events.size(),name,description,triggerAt);
		dao.add(e);
		response.sendRedirect("events");
	}
}