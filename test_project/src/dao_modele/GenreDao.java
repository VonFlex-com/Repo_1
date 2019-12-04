package dao_modele;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans_modele.Genre;
import printSQLExeption.PrintSQLException;


public class GenreDao {
	
	
	private ConnexionDao connexionDao;

	GenreDao(ConnexionDao connexionDao) {
		this.connexionDao = connexionDao;
	}	


	
	public List<Genre> listerGenre()  throws SQLException {
		List<Genre> genres = new ArrayList<Genre>();
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;

		try {
			connexion = connexionDao.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT * FROM genres;");

			while (resultat.next()) {
				String nom = resultat.getString("nom");
				Genre genre = new Genre();
				genre.setNom(nom);
				genres.add(genre);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			PrintSQLException.printSQLException(e);
		}
		return genres;
	}
}
