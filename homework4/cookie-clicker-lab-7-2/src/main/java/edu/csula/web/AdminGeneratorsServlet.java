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
			request.setAttribute("generators",generators);
			request.getRequestDispatcher("/WEB-INF/admin-generators.jsp").forward(request, response);
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
		String description=request.getParameter("GeneratorDescription");
		int generatorRate=Integer.parseInt(request.getParameter("genrate"));
		int basecost=Integer.parseInt((request.getParameter("baseecost")));
		int unlockAt=Integer.parseInt((request.getParameter("unlockatt")));

		Generator g=new Generator(generators.size(),name,description,generatorRate,basecost,unlockAt);
		dao.add(g);
		response.sendRedirect("generators");
	}
}