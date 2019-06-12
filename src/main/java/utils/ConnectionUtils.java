package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import exception.TechnicalException;

/**
 * singleton permettant de récupérer une connexion à la base de données
 * 
 * @author Kevin.s
 *
 */
public class ConnectionUtils {

	/** SERVICE_LOG : Logger */
	private static final Logger SERVICE_LOG = LoggerFactory.getLogger(ConnectionUtils.class);

	/** conn : Connection */
	private static Connection conn = null;
	/** bddConf : ResourceBundle */
	private static ResourceBundle bddConf = ResourceBundle.getBundle("connectionOFF");

	/**
	 * Constructeur
	 * 
	 */
	private ConnectionUtils() {
	}

	/**
	 * Créer une Connection si elle n'éxiste pas, puis la retourne
	 * 
	 * @return Connection
	 */
	public static Connection getInstance() {

		try {
			if (conn == null || conn.isClosed()) {
				// initialisation du driver de la base de données
				try {
					String driverName = ConnectionUtils.getDriverName();
					Class.forName(driverName);
				} catch (ClassNotFoundException e1) {
					SERVICE_LOG.error("impossible de charger le driver", e1);
					throw new TechnicalException("impossible de charger le driver", e1);
				}

				String bddUrl = bddConf.getString("database.url");
				String bddUser = bddConf.getString("database.user");
				String bddPassword = bddConf.getString("database.password");

				conn = DriverManager.getConnection(bddUrl, bddUser, bddPassword);
				conn.setAutoCommit(false);
			}
			return conn;
		} catch (SQLException e) {
			SERVICE_LOG.error("probleme de récupération de connexion", e);
			throw new TechnicalException("probleme de récupération de connexion", e);
		}

	}

	/**
	 * @return le nom du driver de la base de données
	 */
	public static String getDriverName() {
		return bddConf.getString("database.driver");
	}

	/**
	 * effectue un commit
	 */
	public static void doCommit() {

		try {
			if (conn != null || conn.isClosed()) {
				conn.commit();
			}
		} catch (SQLException e) {
			SERVICE_LOG.error("impossible de commit", e);
			throw new TechnicalException("impossible de commit", e);
		}

	}

	/**
	 * effectue un rollback
	 */
	public static void doRollback() {
		try {
			if (conn != null || conn.isClosed()) {
				conn.rollback();
			}
		} catch (SQLException e) {
			SERVICE_LOG.error("impossible de rollback", e);
			throw new TechnicalException("impossible de rollback", e);
		}

	}

	/**
	 * ferme la connection
	 */
	public static void doClose() {
		try {
			if (conn != null || conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			SERVICE_LOG.error("impossible de rollback", e);
			throw new TechnicalException("impossible de rollback", e);
		}

	}
}
