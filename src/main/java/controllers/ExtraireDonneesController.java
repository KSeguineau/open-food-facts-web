package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/extraire/*")
public class ExtraireDonneesController extends HttpServlet {

	public ExtraireDonneesController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String idCategorie = req.getPathInfo();
		idCategorie = idCategorie.substring(1);

		// String idCategorie = req.getParameter("idCategorie");
		resp.getWriter().write("<h1>Categorie à extraire</h1>"
				+ "<ul>"
				+ "<li>identifiant=" + idCategorie + "</li>"
				+ "</ul>");
	}

}
