package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;

import dao.IngredientDao;

/**
 * Cette classe de gerer les demandes d'ingrédients *
 * 
 * @author Kevin.s
 *
 */
@WebServlet(urlPatterns = "/ingredients")
public class IngredientController extends HttpServlet {

	/**
	 * Constructeur
	 * 
	 */
	public IngredientController() {
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf8");
		String idProduit = req.getParameter("idProduit");
		IngredientDao ingredientDao = new IngredientDao();
		List<String> listeIngredients = ingredientDao.recupererIngredientParProduit(idProduit);
		JsonArray ingredientsJson = new JsonArray();
		for (String ingredient : listeIngredients) {
			ingredientsJson.add(ingredient);
		}
		resp.setCharacterEncoding("utf8");
		resp.getWriter().write(ingredientsJson.toString());
	}

}
