package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import exception.TechnicalException;
import model.Produit;
import utils.ConnectionUtils;
import utils.QueryUtils;

/**
 * Dao gérant les produits
 * 
 * @author Kevin.s
 *
 */
public class ProduitDao {

	/** SERVICE_LOG : Logger */
	private static final Logger SERVICE_LOG = LoggerFactory.getLogger(ProduitDao.class);

	/**
	 * Constructeur
	 * 
	 */
	public ProduitDao() {
		super();
	}

	/**
	 * methode retournant la liste des produits correspondant à la recheche de
	 * l'utilisateur
	 * 
	 * @param paramRecherche
	 *            correspond au parametre de recherche
	 * @return List<Produit> liste des produits correspondant à la recherche
	 */
	public List<Produit> rechercheProduit(HashMap<String, String> paramRecherche) {
		List<Produit> listeProduit = new ArrayList<>();
		PreparedStatement selectProduit = null;
		StringBuilder sb = new StringBuilder();
		sb.append(
				"select pdt_id,pdt_nom,mrq_nom,ctg_nom,pdt_nutritiongrade,pdt_energie,pdt_graisse from produit inner join marque on PDT_MARQUE = MRQ_ID inner join categorie on PDT_CATEGORIE=CTG_ID");
		try {
			QueryUtils.generationParamRecherche(paramRecherche, sb);
			sb.append(" limit 100");
			selectProduit = ConnectionUtils.getInstance().prepareStatement(sb.toString());
			ResultSet resultSet = selectProduit.executeQuery();
			ConnectionUtils.doCommit();

			while (resultSet.next()) {
				Integer id = resultSet.getInt("pdt_id");
				String nom = resultSet.getString("pdt_nom");
				String marque = resultSet.getString("mrq_nom");
				String categorie = resultSet.getString("ctg_nom");
				String grade = resultSet.getString("pdt_nutritiongrade");
				Double energie = resultSet.getDouble("pdt_energie");
				Double graisse = resultSet.getDouble("pdt_graisse");
				listeProduit.add(new Produit(id, nom, marque, categorie, grade, energie, graisse));
			}
			return listeProduit;
		} catch (SQLException e) {
			ConnectionUtils.doRollback();
			throw new TechnicalException("probleme de selection en base de données", e);
		} finally {
			if (selectProduit != null) {
				try {
					selectProduit.close();
				} catch (SQLException e) {
					throw new TechnicalException("impossible de fermer le preparedStatement", e);
				}
			}
			ConnectionUtils.doClose();
		}

	}

}
