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
 * Dao gérant les marques
 * 
 * @author Kevin.s
 *
 */
public class MarqueDao {

	/** SERVICE_LOG : Logger */
	private static final Logger SERVICE_LOG = LoggerFactory.getLogger(MarqueDao.class);

	/**
	 * Constructeur
	 * 
	 */
	public MarqueDao() {
		super();
	}

	/**
	 * récupère l'id d'une marque à partir de son nom
	 * 
	 * @param nom
	 *            de la marque
	 * @return l'identifiant de la marque
	 */
	public Integer recupererIdMarque(String nom) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Integer id = null;

		try {
			preparedStatement = ConnectionUtils.getInstance().prepareStatement(
					"select MRQ_ID from marque where MRQ_NOM=?");
			preparedStatement.setString(1, nom);
			resultSet = preparedStatement.executeQuery();
			ConnectionUtils.doCommit();
			if (resultSet.next()) {
				id = resultSet.getInt("MRQ_ID");
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
	 * recupère toutes les marques
	 * 
	 * @return List<String> contenant le nom de toutes les marques
	 */
	public List<String> recupererAllMarque() {

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<String> listeMarque = new ArrayList();

		try {
			preparedStatement = ConnectionUtils.getInstance().prepareStatement(
					"select * from marque");
			resultSet = preparedStatement.executeQuery();
			ConnectionUtils.doCommit();
			while (resultSet.next()) {
				String nom = resultSet.getString("MRQ_NOM");
				listeMarque.add(nom);
			}
			return listeMarque;
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
	 * @param idCategorie
	 *            id de la catégorie
	 * @return List<String> contenant le nom des marques ayant un produit de la
	 *         catégorie indiquée
	 */
	public List<String> recupererMarqueParCategorie(Integer idCategorie) {

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<String> listeMarque = new ArrayList();

		try {
			preparedStatement = ConnectionUtils.getInstance().prepareStatement(
					"SELECT distinct MRQ_NOM FROM produit inner join marque on PDT_MARQUE=MRQ_ID where PDT_CATEGORIE="
							+ idCategorie);
			resultSet = preparedStatement.executeQuery();
			ConnectionUtils.doCommit();
			while (resultSet.next()) {
				String nom = resultSet.getString("MRQ_NOM");
				listeMarque.add(nom);
			}
			return listeMarque;
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
