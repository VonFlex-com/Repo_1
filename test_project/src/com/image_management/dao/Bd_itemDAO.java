package com.image_management.dao;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import com.image_management.connection.MyConnectionProvider;
import com.image_management.model.Bd_item;

public class Bd_itemDAO {
	private final static String TableName = "comics_bd";
	private final String SELECT_ALL_USERS = "select * from " + TableName;
	static Connection con;
	String message;
	
	  public List < Bd_item > selectAllUsers() throws IOException {

	        // using try-with-resources to avoid closing resources (boiler plate code)
	        List < Bd_item > users = new ArrayList < > ();
	        // Step 1: Establishing a Connection
	        try (Connection con = MyConnectionProvider.getCon();

	            // Step 2:Create a statement using connection object
	            PreparedStatement preparedStatement = con.prepareStatement(SELECT_ALL_USERS);) {
	            System.out.println(preparedStatement);
	            // Step 3: Execute the query or update query
	            ResultSet rs = preparedStatement.executeQuery();

	            // Step 4: Process the ResultSet object.
	            while (rs.next()) {
					int id = rs.getInt("bd_id");
					String serie = rs.getString("serie");		
					String titre = rs.getString("titre");
					Blob blob = rs.getBlob("vignette");
					
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
					
					String resume = rs.getString("resume");
					String lang = rs.getString("langue");
					Timestamp datcrea = rs.getTimestamp("date_de_creation");
					Timestamp datuplo = rs.getTimestamp("date_dupload");
					int noteID = rs.getInt("note_id");
					int auteurID = rs.getInt("auteur_id");
					int genreID = rs.getInt("genre_id");
					users.add(new Bd_item(id,serie,titre,base64Image,resume,lang,datcrea,datuplo,noteID,auteurID,genreID));
					}
	        } catch (SQLException e) {
	            printSQLException(e);
	        } finally {
				if (con != null) {
					// closes the database connection
					try {
						con.close();
					} catch (SQLException ex) {
						ex.printStackTrace();
						printSQLException(ex);
					}
				}
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