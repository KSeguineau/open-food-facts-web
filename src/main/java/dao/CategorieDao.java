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
 * Dao gérant les catégories
 * 
 * @author Kevin.s
 *
 */
public class CategorieDao {
	// TODO recuperation marque et categorie en liste

	/** SERVICE_LOG : Logger */
	private static final Logger SERVICE_LOG = LoggerFactory.getLogger(CategorieDao.class);

	/**
	 * Constructeur
	 * 
	 */
	public CategorieDao() {
		super();
	}

	/**
	 * récupère l'id d'une catégorie par rapport à son nom
	 * 
	 * @param nom
	 *            nom de la catégorie
	 * @return l'identifiant de la catégorie
	 */
	public Integer recupererIdCategorie(String nom) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Integer id = null;

		try {
			preparedStatement = ConnectionUtils.getInstance().prepareStatement(
					"select CTG_ID from categorie where CTG_NOM=?");
			preparedStatement.setString(1, nom);
			resultSet = preparedStatement.executeQuery();
			ConnectionUtils.doCommit();
			if (resultSet.next()) {
				id = resultSet.getInt("CTG_ID");
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
	 * premet de recuperer toutes les catégories
	 * 
	 * @return List<String> contenant le nom de toutes les catégories
	 */
	public List<String> recupererAllCategorie() {

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<String> listeCategorie = new ArrayList<>();

		try {
			preparedStatement = ConnectionUtils.getInstance().prepareStatement(
					"select * from categorie");
			resultSet = preparedStatement.executeQuery();
			ConnectionUtils.doCommit();
			while (resultSet.next()) {

				String nom = resultSet.getString("CTG_NOM");
				listeCategorie.add(nom);
			}
			return listeCategorie;
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
