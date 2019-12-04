package dao_modele;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import beans_modele.BDImage;



public class BDImageDao {


	private ConnexionDao connexionDao;

	BDImageDao(ConnexionDao connexionDao) {
		this.connexionDao = connexionDao;
	}	
	
    public List < BDImage > selectAllImages(int id) throws IOException {

    	BDImage bdImageObj = new BDImage(id);
        List<BDImage> bdImages = new ArrayList<BDImage>();
        Connection connexion = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultat = null;
		
      
        try {
        	connexion = connexionDao.getConnection();
			String SELECT_BD = "SELECT comics_bd.serie, " + 
					 "comics_bd.titre, " + 
					 "comics_assets.image_id, " +
					 "comics_assets.image, " +
					 "comics_assets.bd_id "+
					 "FROM comics_bd "+
					 "JOIN comics_assets ON comics_bd.bd_id = comics_assets.bd_id "+
					 "WHERE comics_assets.bd_id=?";
			prepareStatement = connexion.prepareStatement(SELECT_BD);
			prepareStatement.setInt(1, bdImageObj.getIdBd());
			resultat = prepareStatement.executeQuery();
			
            while (resultat.next()) {
            	
                String serie = resultat.getString("serie");
				String titre = resultat.getString("titre");
				int idImage = resultat.getInt("image_id");
                
              	Blob blob = resultat.getBlob("image");
				
				InputStream inputStream = blob.getBinaryStream();
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				byte[] buffer = new byte[4096];
				int bytesRead = -1;
				
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, bytesRead);					
				}
				
				byte[] imageBytes = outputStream.toByteArray();
				String bdImage = Base64.getEncoder().encodeToString(imageBytes);
				
				inputStream.close();
				outputStream.close();
                
				bdImages.add(new BDImage( serie, titre, bdImage, idImage));
                
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
       
        return bdImages;
    }
  
        
    
	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
}
