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

@WebServlet("edit")
public class AdminEventsEditServlet extends HttpServlet {

    public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        // TODO: render the events page HTML
        EventsDAO dao = new EventsDAOImpl(getServletContext());
        Collection<Event> events = dao.getAll();
        int id=Integer.parseInt(request.getParameter("id"));
        Event e1=null;
        for(Event e:events){
            if(e.getId()==id){
                e1=e;
            }
        }

        request.setAttribute("e1",e1);
        request.getRequestDispatcher("/WEB-INF/admin-events-edit.jsp").forward(request, response);
    }

    @Override
    public void doPost( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO: handle upsert transaction
        EventsDAO dao = new EventsDAOImpl(getServletContext());
        int id=Integer.parseInt(request.getParameter("id"));
        String name=request.getParameter("evename");
        String description=request.getParameter("EventDescription");
        int triggerAt=Integer.parseInt(request.getParameter("triggname"));
        Event e=new Event(id,name,description,triggerAt);
        dao.set(id,e);
        response.sendRedirect("events");
    }
}
