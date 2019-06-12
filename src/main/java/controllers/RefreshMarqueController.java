package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;

import dao.CategorieDao;
import dao.MarqueDao;

/**
 * cette classe gère le rafraichissement des marques en fonction de la catégorie
 * choisie
 * 
 * @author Kevin.s
 *
 */
@WebServlet(urlPatterns = "/refresh")
public class RefreshMarqueController extends HttpServlet {

	public RefreshMarqueController() {

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nomCategorie = req.getParameter("nomCategorie");
		CategorieDao categoieDao = new CategorieDao();
		Integer idCategorie = categoieDao.recupererIdCategorie(nomCategorie);
		MarqueDao marqueDao = new MarqueDao();
		List<String> listeMarque = marqueDao.recupererMarqueParCategorie(idCategorie);
		JsonArray marqueJson = new JsonArray();
		for (String marque : listeMarque) {
			marqueJson.add(marque);
		}
		resp.setCharacterEncoding("utf8");
		resp.getWriter().write(marqueJson.toString());

	}

}
