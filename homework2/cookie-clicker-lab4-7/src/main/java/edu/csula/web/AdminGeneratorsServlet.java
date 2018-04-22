package edu.csula.web;

import edu.csula.models.Generator;
import edu.csula.storage.GeneratorsDAO;
import edu.csula.storage.UsersDAO;
import edu.csula.storage.servlet.GeneratorsDAOImpl;
import edu.csula.storage.servlet.UsersDAOImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/generators")
public class AdminGeneratorsServlet extends HttpServlet {
	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		// TODO: render the generators page HTML

        UsersDAO daosession=new UsersDAOImpl(request.getSession());
        if(daosession != null) {

            GeneratorsDAO dao = new GeneratorsDAOImpl(getServletContext());
            Collection<Generator> generators = dao.getAll();
            String cssTag = "<link rel='stylesheet' type='text/css' href='/app.css'>";
            String html = "<html><head><title>Incremental Game</title>" + cssTag + "</head><body>";
            html += "<h1>Incremental Game Framework</h1>";
            html += "<h3><a href=''>Game Information</a> | <a href='/generators'>Generators</a> | <a href='/events'>Events</a> | <a href='/auth'>Logout</a> ";
            html += "<div class='row'>";
            html += "<div class='column'>";
            html += "<div class='flex-container'>";
            html += "     <form method='POST'>";
            html += "        <div><label for='GeneratorName'>Event Name</label></div>";
            html += "        <div><input type='text' name='genname' id='GeneratorName'</div>";
            html += "        <div><label for='GeneratorRate'>Generator Rate</label></div>";
            html += "        <div><input type='number' name='genrate' id='GeneratorRate'></div>";
            html += "        <div><label for='BaseCost'>Base Cost</label></div>";
            html += "        <div><input type='number' name='baseecost' id='BaseCost'></div>";
            html += "        <div><label for='UnlockAt'>Unlock At</label></div>";
            html += "        <div><input type='number' name='unlockatt' id='UnlockAt'></div>";
            html += "        <div><label for='EventDescription'>Event Description</label></div>";
            html += "        <div><textarea name='EventDescription'></textarea></div>";
            html += "        <div><button>Submit</button></div>";
            html += "     </form>";
            html += "</div>";
            html += "</div>";
            html += "<div class='column'>";

            html += "</div>";
            html += "</div>";
            html += "     <table border='1' cellpadding='15'>";
            html += "         <tr><th>Name</th><th>Rate</th><th>Cost</th><th>Unlock At</th><th>Actions</th></tr>";
            for (Generator g : generators) {
                html += "<tr>";
                html += "<td>" + g.getName() + "</td>" + "<td>" + g.getRate() + "</td>" + "<td>" + g.getBaseCost() + "</td>" + "<td>" + g.getUnlockAt() + "</td>" + "<td><a href='/generatorsedit?id=" + g.getId() + "'>edit</a>|<a href='/generatorsremove?id=" + g.getId() + "'>delete</a>" + "</td>";
                html += "</tr>";
            }
            html += "     </table>";
            html += "</body></html>";


            out.println(html);
        }

        else{
            daosession.logout();
            response.sendRedirect("auth");
        }
	}


	@Override
	public void doPost( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO: handle upsert transaction
        GeneratorsDAO dao = new GeneratorsDAOImpl(getServletContext());
        Collection<Generator> generators = dao.getAll();
        String name=request.getParameter("genname");
        int generatorRate=Integer.parseInt(request.getParameter("genrate"));
        int basecost=Integer.parseInt((request.getParameter("baseecost")));
        int unlockAt=Integer.parseInt((request.getParameter("unlockatt")));
        String description=request.getParameter("EventDescription");
        Generator g=new Generator(generators.size(),name,description,generatorRate,basecost,unlockAt);
        dao.add(g);
        response.sendRedirect("generators");
	}
}
