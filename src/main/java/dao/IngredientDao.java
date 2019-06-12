package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import exception.TechnicalException;
import utils.ConnectionUtils;

/**
 * Dao gérant les ingrédients
 * 
 * @author Kevin.s
 *
 */
public class IngredientDao {
	/** SERVICE_LOG : Logger */
	private static final Logger SERVICE_LOG = LoggerFactory.getLogger(IngredientDao.class);

	/**
	 * Constructeur
	 * 
	 */
	public IngredientDao() {
		super();
	}

	/**
	 * récupère l'identifiant d'un ingrédient par rapport à son nom
	 * 
	 * @param nom
	 *            nom de l'ingrédients
	 * @return l'identifiant de l'ingrédient
	 */
	public Integer recupererIdIngredient(String nom) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Integer id = null;

		try {
			preparedStatement = ConnectionUtils.getInstance().prepareStatement(
					"select ING_ID from ingredient where ING_NOM=?");
			preparedStatement.setString(1, nom);
			resultSet = preparedStatement.executeQuery();
			ConnectionUtils.doCommit();
			if (resultSet.next()) {
				id = resultSet.getInt("ING_ID");
			}
			return id;
		} catch (SQLException e) {
			SERVICE_LOG.error("probleme de selection en base", e);
			throw new TechnicalException("probleme de selection en base", e);

		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					SERVICE_LOG.error("impossible de fermer le resultSet", e);
					throw new TechnicalException("impossible de fermer le resultSet", e);
				}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					SERVICE_LOG.error("impossible de fermer le statement", e);
					throw new TechnicalException("impossible de fermer le statement", e);
				}
			}
			ConnectionUtils.doClose();
		}
	}

	/**
	 * @param idProduit
	 *            id d'un produit
	 * @return List<String> liste contenant le nom de tous les ingrédients
	 *         contenant le produit
	 */
	public List<String> recupererIngredientParProduit(String idProduit) {

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<String> listeIngredient = new ArrayList<>();

		try {
			preparedStatement = ConnectionUtils.getInstance().prepareStatement(
					"SELECT ing_nom FROM ingredient inner join produit_ingredient on ING_ID = PI_IDINGREDIENT where PI_IDPRODUIT ="
							+ idProduit);
			resultSet = preparedStatement.executeQuery();
			ConnectionUtils.doCommit();
			while (resultSet.next()) {
				String nom = resultSet.getString("ING_NOM");
				listeIngredient.add(nom);
			}
			return listeIngredient;
		} catch (SQLException e) {
			SERVICE_LOG.error("probleme de selection en base", e);
			throw new TechnicalException("probleme de selection en base", e);

		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					SERVICE_LOG.error("impossible de fermer le resultSet", e);
					throw new TechnicalException("impossible de fermer le resultSet", e);
				}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					SERVICE_LOG.error("impossible de fermer le statement", e);
					throw new TechnicalException("impossible de fermer le statement", e);
				}
			}
			ConnectionUtils.doClose();
		}

	}
}
