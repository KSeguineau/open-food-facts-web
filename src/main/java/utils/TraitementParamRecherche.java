package utils;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

/**
 * cette classe gère les paramètres de la recherche de produits
 * 
 * @author Kevin.s
 *
 */
public class TraitementParamRecherche {

	/**
	 * Constructeur
	 * 
	 */
	private TraitementParamRecherche() {
	}

	/**
	 * recupere une requette http, en extrait les parametres et rempli une
	 * HashMap avec les différents parametres de recherche
	 * 
	 * @param req
	 *            requete http provenant du formulaire de recherche de produit
	 * @return HashMap<String,String> contient le nom l'attribut de la
	 *         table(BDD) en clé et le parametre de recherche en valeur
	 */
	public static HashMap<String, String> creationParamRecherche(HttpServletRequest req) {
		HashMap<String, String> paramRecherche = new HashMap<>();
		String marque = "";
		String categorie = req.getParameter("categorie").replaceAll("'", "''");
		if (req.getParameter("marque") != null) {
			marque = req.getParameter("marque").replaceAll("'", "''");
		}
		String nom = req.getParameter("nom").replaceAll("'", "''");
		String grade = req.getParameter("grade").replaceAll("'", "''");
		String minEnergie = req.getParameter("minEnergie").replaceAll("'", "''");
		String maxEnergie = req.getParameter("maxEnergie").replaceAll("'", "''");
		String minGraisse = req.getParameter("minGraisse").replaceAll("'", "''");
		String maxGraisse = req.getParameter("maxGraisse").replaceAll("'", "''");

		if (!categorie.isEmpty()) {
			paramRecherche.put("ctg_nom", "='" + categorie + "'");
		}
		if (!marque.isEmpty()) {
			paramRecherche.put("mrq_nom", "='" + marque + "'");
		}
		if (!nom.isEmpty()) {
			paramRecherche.put("pdt_nom", "='" + nom + "'");
		}
		if (!grade.isEmpty()) {
			paramRecherche.put("pdt_nutritiongrade", "='" + grade + "'");
		}
		if (!minEnergie.isEmpty() && !maxEnergie.isEmpty()) {
			paramRecherche.put("pdt_energie", " between '" + minEnergie + "' and '" + maxEnergie + "'");
		}
		if (!minGraisse.isEmpty() && !maxGraisse.isEmpty()) {
			paramRecherche.put("pdt_graisse", " between '" + minGraisse + "' and '" + maxGraisse + "'");
		}

		return paramRecherche;

	}

}
