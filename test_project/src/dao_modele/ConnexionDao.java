package dao_modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionDao{
	private String url;
	private String username;
	private String password;

	ConnexionDao(String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;
	}

	public static ConnexionDao getInstance() {		
		//chargement du driver, unique au chargement de l'application
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
		}		
		//connexion à la base de données
		ConnexionDao instance = new ConnexionDao(
				"jdbc:mysql://localhost:3306/comics?useOldAliasMetadataBehavior=true&useUnicode=true&characterEncoding=UTF-8", "root", "");
		return instance;
	}

	public Connection getConnection() throws SQLException {
		Connection connect = DriverManager.getConnection(url, username, password);
		connect.setAutoCommit(false);
		return connect;
	}

	// Récupération du Dao
	public UserDao getUtilisateurDao() {
		return new UserDaoImpl(this);
	}
	
	public GenreDao getGenreDao() {
		return new GenreDao(this);
	}
	
	public BDItemDao getBDItemDao() {
		return new BDItemDao(this);
	}
	
	public BDImageDao getBDImageDao() {
		return new BDImageDao(this);
	}
	
}