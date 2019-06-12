package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CategorieDao;
import dao.ProduitDao;
import model.Produit;
import utils.TraitementParamRecherche;

/**
 * cette classe gère la recherche de produits
 * 
 * @author Kevin.s
 *
 */
@WebServlet(urlPatterns = "/recherche")
public class RechercheProduitController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ProduitDao pDao = new ProduitDao();
		req.setCharacterEncoding("utf8");
		List<Produit> listeProduit = pDao.rechercheProduit(TraitementParamRecherche.creationParamRecherche(req));
		req.setAttribute("listeProduit", listeProduit);
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/listeProduits.jsp");
		dispatcher.forward(req, resp);

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		CategorieDao categorieDao = new CategorieDao();
		List<String> listeCategorie = categorieDao.recupererAllCategorie();
		req.setAttribute("listeCategorie", listeCategorie);
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/index.jsp");
		dispatcher.forward(req, resp);
	}

}
