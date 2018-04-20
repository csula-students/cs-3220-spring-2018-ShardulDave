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

import edu.csula.models.Generator;
import edu.csula.storage.GeneratorsDAO;
import edu.csula.storage.servlet.GeneratorsDAOImpl;

@WebServlet("/admin/generators/edit")
public class AdminGeneratorsEditServlet extends HttpServlet {

    public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        // TODO: render the events page HTML
        GeneratorsDAO dao = new GeneratorsDAOImpl(getServletContext());
        Collection<Generator> generators = dao.getAll();
        int id=Integer.parseInt(request.getParameter("id"));
        Generator g1=null;
        for(Generator g:generators){
            if(g.getId()==id){
                g1=g;
            }
        }
        String cssTag="<link rel='stylesheet' type='text/css' href='/app.css'>";
        String html="<html><head><title>Incremental Game</title>"+cssTag+"</head><body>";
        html+="<h1>Incremental Game Framework</h1>";
        html+="<h3><a href=''>Game Information</a> | <a href='/admin/generators'>Generators</a> | <a href='/admin/events'>Events</a> ";
        html+="     <form method='POST'>";
        html+="        <label for='GeneratorName'>Event Name</label></div>";
        html+="        <input type='text' name='genname' id='GeneratorName' value='"+g1.getName()+"'>";
        html+="        <label for='GeneratorRate'>Generator Rate</label>";
        html+="        <input type='number' name='genrate' id='GeneratorRate' value='"+g1.getRate()+"'>";
        html+="        <label for='BaseCost'>Base Cost</label>";
        html+="        <input type='number' name='baseecost' id='BaseCost' value='"+g1.getBaseCost()+"'>";
        html+="        <label for='UnlockAt'>Unlock At</label>";
        html+="        <input type='number' name='unlockatt' id='UnlockAt' value='"+g1.getUnlockAt()+"'>";
        html+="        <label for='EventDescription'>Event Description</label>";
        html+="        <textarea name='EventDescription'>"+g1.getDescription()+"</textarea>";
        html+="        <button>Submit</button>";
        html+="     </form>";
        html+="</body></html>";


        out.println(html);
    }

    @Override
    public void doPost( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO: handle upsert transaction
        GeneratorsDAO dao = new GeneratorsDAOImpl(getServletContext());
        Collection<Generator> generators = dao.getAll();
        int id=Integer.parseInt(request.getParameter("id"));
        String name=request.getParameter("genname");
        int generatorRate=Integer.parseInt(request.getParameter("genrate"));
        int basecost=Integer.parseInt((request.getParameter("baseecost")));
        int unlockAt=Integer.parseInt((request.getParameter("unlockatt")));
        String description=request.getParameter("EventDescription");
        Generator g=new Generator(generators.size(),name,description,generatorRate,basecost,unlockAt);
        dao.set(id,g);
        response.sendRedirect("/admin/generators");
    }
}