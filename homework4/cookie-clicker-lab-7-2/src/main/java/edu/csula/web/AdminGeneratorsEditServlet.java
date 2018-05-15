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

import edu.csula.models.*;
import edu.csula.storage.GeneratorsDAO;
import edu.csula.storage.UsersDAO;
import edu.csula.storage.mysql.*;
import edu.csula.storage.servlet.UsersDAOImpl;

@WebServlet("/generatorsedit")
public class AdminGeneratorsEditServlet extends HttpServlet {

    public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        // TODO: render the events page HTML
        UsersDAO daosession=new UsersDAOImpl(request.getSession());
        if(daosession != null) {
            //GeneratorsDAO dao = new GeneratorsDAOImpl(getServletContext());
            GeneratorsDAO dao = new GeneratorsDAOImpl(new Database());
            Collection<Generator> generators = dao.getAll();
            int id = Integer.parseInt(request.getParameter("id"));
            Generator g1 = null;
            for (Generator g : generators) {
                if (g.getId() == id) {
                    g1 = g;
                }
            }
            request.setAttribute("g1",g1);
            request.getRequestDispatcher("/WEB-INF/admin-generators-edit.jsp").forward(request, response);
        }
        else{
            response.sendRedirect("auth");
        }
    }

    @Override
    public void doPost( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO: handle upsert transaction
        //GeneratorsDAO dao = new GeneratorsDAOImpl(getServletContext());
        GeneratorsDAO dao = new GeneratorsDAOImpl(new Database());
        Collection<Generator> generators = dao.getAll();
        int id=Integer.parseInt(request.getParameter("id"));
        String name=request.getParameter("genname");
        String description=request.getParameter("GeneratorDescription");
        int generatorRate=Integer.parseInt(request.getParameter("genrate"));
        int basecost=Integer.parseInt((request.getParameter("baseecost")));
        int unlockAt=Integer.parseInt((request.getParameter("unlockatt")));

        Generator g=new Generator();
        g.setName(name);
        g.setDescription(description);
        g.setRate(generatorRate);
        g.setBaseCost(basecost);
        g.setUnlockAt(unlockAt);
        dao.set(id,g);
        response.sendRedirect("generators");
    }
}