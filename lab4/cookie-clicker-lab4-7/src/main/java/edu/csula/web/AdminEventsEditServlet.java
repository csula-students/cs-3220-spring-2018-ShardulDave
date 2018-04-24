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

@WebServlet("/admin/events/edit")
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
        String cssTag="<link rel='stylesheet' type='text/css' href='/app.css'>";
        String html="<html><head><title>Incremental Game</title></head>"+cssTag+"</head><body>";
        html+="<h1>Incremental Game Framework</h1>";
        html+="<h3><a href=''>Game Information</a> | <a href=''>Generators</a> | <a href='/admin/events'>Events</a> ";
        html+="     <form method='POST'>";
        html+="        <label for='EventName'>Event Name</label>";
        html+="        <input type='text' name='evename' id='EventName' value='"+ e1.getName()+"'>";
        html+="        <label for='EventDescription'>Event Description</label>";
        html+="        <textarea name='EventDescription'>"+e1.getDescription()+"</textarea>";
        html+="        <label for='TriggerName'>Trigger At</label>";
        html+="        <input type='number' name='triggname' id='TriggerNameName' value='"+e1.getTriggerAt()+"'>";
        html+="        <button>Edit</button>";
        html+="     </form>";
        html+="</body></html>";


        out.println(html);
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
        response.sendRedirect("/admin/events");
    }
}
