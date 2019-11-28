package com.image_management.dao;

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

import com.image_management.connection.MyConnectionProvider;
import com.image_management.model.BDImage;

public class BDImageDAO {

	final static String tableName = "comics_assets";
	final String Id = "image_id";
	final String imageName = "image";
	final String Bd_id = "bd_id";
	private static final String SELECT_ALL_USERS = "select * from " + tableName;

	public BDImage get(int id) throws SQLException, IOException {
		BDImage book = null;

		String sql = "SELECT * FROM "+tableName +" WHERE "+ id +" = ?";
		
		try (Connection con = MyConnectionProvider.getCon()) {
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			
			if (result.next()) {
				book = new BDImage();
				int comicsName = result.getInt("bd_id");
				//String author = result.getString("author");
				Blob blob = result.getBlob("image");
				
				InputStream inputStream = blob.getBinaryStream();
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				byte[] buffer = new byte[4096];
				int bytesRead = -1;
				
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, bytesRead);					
				}
				
				byte[] imageBytes = outputStream.toByteArray();
				String base64Image = Base64.getEncoder().encodeToString(imageBytes);
				
				
				inputStream.close();
				outputStream.close();
				
				book.setComicsName(comicsName);
				book.setBase64Image(base64Image);
			}			
			
		} catch (SQLException | IOException ex) {
			ex.printStackTrace();
			throw ex;
		}		
		
		return book;
	}
	

    public List < BDImage > selectAllUsers() throws IOException {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List < BDImage > users = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try (Connection con = MyConnectionProvider.getCon();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = con.prepareStatement(SELECT_ALL_USERS);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt(Id);
                int bd_id = rs.getInt(Bd_id);
                
              	Blob blob = rs.getBlob("image");
				
				InputStream inputStream = blob.getBinaryStream();
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				byte[] buffer = new byte[4096];
				int bytesRead = -1;
				
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, bytesRead);					
				}
				
				byte[] imageBytes = outputStream.toByteArray();
				String base64Image = Base64.getEncoder().encodeToString(imageBytes);
				
				
				inputStream.close();
				outputStream.close();
                
                
                users.add(new BDImage(id, base64Image, bd_id));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return users;
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
