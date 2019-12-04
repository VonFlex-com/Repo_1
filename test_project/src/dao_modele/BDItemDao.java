package dao_modele;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import beans_modele.BDItemAccueil;
import printSQLExeption.PrintSQLException;

public class BDItemDao {


	private ConnexionDao connexionDao;

	BDItemDao(ConnexionDao connexionDao) {
		this.connexionDao = connexionDao;
	}	


	public List<BDItemAccueil> listerBDItem()  throws SQLException, IOException {

		List<BDItemAccueil> bdItems = new ArrayList<BDItemAccueil>();
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;

		try {
			connexion = connexionDao.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT comics_bd.bd_id,"
					+ "comics_bd.serie,"
					+ "comics_bd.titre,"
					+ "comics_bd.vignette,"
					+ "comics_bd.resume,"
					+ "comics_bd.langue, "
					+ "genres.nom,"
					+ "auteurs.nom,"
					+ "auteurs.prenom\r\n" + 
					"FROM comics_bd\r\n" + 
					"JOIN genres ON comics_bd.genre_id = genres.id_genre\r\n" + 
					"JOIN auteurs ON comics_bd.auteur_id = auteurs.id_auteur\r\n" + 
					"ORDER by comics_bd.bd_id;");

			while (resultat.next()) {
				int idBd = resultat.getInt("bd_id");
				String serie = resultat.getString("serie");
				String titre = resultat.getString("titre");
				Blob blob = resultat.getBlob("vignette");

				InputStream inputStream = blob.getBinaryStream();
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				byte[] buffer = new byte[4096];
				int bytesRead = -1;

				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, bytesRead);					
				}

				byte[] imageBytes = outputStream.toByteArray();
				String vignette = Base64.getEncoder().encodeToString(imageBytes);

				inputStream.close();
				outputStream.close();					

				String resume = resultat.getString("resume");
				String langue = resultat.getString("langue");
				String genre =  resultat.getString("genres.nom");
				String auteurNom =  resultat.getString("auteurs.nom");
				String auteurPrenom =  resultat.getString("auteurs.prenom");


				bdItems.add(new BDItemAccueil(idBd,serie,titre,vignette,resume,langue,genre,auteurNom,auteurPrenom));
			}


		} catch (SQLException e) {
			e.printStackTrace();
			PrintSQLException.printSQLException(e);
		}
		return bdItems;
	}
}

