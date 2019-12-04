package dao_modele;

import java.sql.Connection;
//import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
//import java.util.Calendar;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import beans_modele.BeanException;
import beans_modele.Utilisateur;

public class UserDaoImpl implements UserDao {

	public ConnexionDao connexionDao;

	UserDaoImpl(ConnexionDao connexionDao) {
		this.connexionDao = connexionDao;
	}	

	
	public boolean isUserDB(Utilisateur utilisateur) throws SQLException {
		
		boolean result=false;		
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		try {
		
			connexion = connexionDao.getConnection();
			
			preparedStatement = connexion.prepareStatement("SELECT COUNT(*) as total FROM utilisateurs WHERE email=?;");
			preparedStatement.setString(1, utilisateur.getEmail());
			rs = preparedStatement.executeQuery();
			connexion.commit();				
			rs.next();
			
			
			if(rs.getInt("total")==0) {				
				System.out.println("faux pas dans base");
			}else {
				result=true;
				System.out.println("vrai deja dans base");
			}
			
			rs.close();
			

			
		}catch(SQLException e) {
			e.printStackTrace();
			printSQLException(e);
		}
		System.out.println(result);
		return result;
	
	}

	public boolean addUserDB(Utilisateur utilisateur) throws SQLException {

		boolean result=false;
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		int ps;
		Timestamp tmp = new Timestamp(System.currentTimeMillis());

		try {			
			connexion = connexionDao.getConnection();
			preparedStatement = connexion.prepareStatement("INSERT INTO utilisateurs(email, pseudo, password, nb_connexion, date_inscription, date_connexion) VALUES  (?,?,?,?,?,?);");
			preparedStatement.setString(1, utilisateur.getEmail());
			preparedStatement.setString(2, utilisateur.getPseudo());
			preparedStatement.setString(3, utilisateur.getPassword());
			preparedStatement.setInt(4, utilisateur.getNbConnexion());
			preparedStatement.setTimestamp(5, tmp);
			preparedStatement.setTimestamp(6,null);
			ps = preparedStatement.executeUpdate();
			connexion.commit();	
			if(ps==1) {
				result=true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
			printSQLException(e);
		}
		return result;
	}




	@Override
	public int ajouter(Utilisateur utilisateur) throws DaoException  { 
		
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		Timestamp tmp = new Timestamp(System.currentTimeMillis());


		try {
			connexion = connexionDao.getConnection();
			preparedStatement = connexion.prepareStatement("SELECT COUNT(*) FROM utilisateurs WHERE email=?;");
			preparedStatement.setString(1, utilisateur.getEmail());
			ResultSet resultA = preparedStatement.executeQuery();
System.out.println(resultA.getRow());
			if(resultA.getRow()==0) {

				try {
					preparedStatement = connexion.prepareStatement("INSERT INTO utilisateurs(email, pseudo, password, nb_connexion, date_inscription, date_connexion) VALUES  (?,?,?,?,?,?);");
					preparedStatement.setString(1, utilisateur.getEmail());
					preparedStatement.setString(2, utilisateur.getPseudo());
					preparedStatement.setString(3, utilisateur.getPassword());
					preparedStatement.setInt(4, utilisateur.getNbConnexion());
					preparedStatement.setTimestamp(5, tmp);
					preparedStatement.setTimestamp(6,null);
					result = preparedStatement.executeUpdate(); 

					connexion.commit();	
				}catch(SQLException e){
					e.printStackTrace();
					printSQLException(e);
				}				
			}
		} catch (SQLException e) {
			try {
				if (connexion != null) {
					connexion.rollback();
				}
			}catch(SQLException e2) {

			}
			e.printStackTrace();
			printSQLException(e);
			throw new DaoException("Communication avec la base de données impossible");
		}
		finally {
			try {
				if(connexion != null) {
					connexion.close();
					System.out.println("closeclose");
				}
			}catch (SQLException e) {
				e.printStackTrace();
				printSQLException(e);
				throw new DaoException("Communication avec la base de données impossible");
			}
		}

		return result;
	}



	public void updateUserData(Utilisateur utilisateur) throws SQLException, DaoException{
		Connection connexion=null;
		PreparedStatement preparedStatement = null;
		Timestamp tmp = new Timestamp(System.currentTimeMillis());

		try {
			connexion = connexionDao.getConnection();
			String UPDATE_SQL ="UPDATE utilisateurs SET nb_connexion = ?, date_connexion=? WHERE email = ?;";
			preparedStatement = connexion.prepareStatement(UPDATE_SQL);
			preparedStatement.setInt(1, utilisateur.getNbConnexion()+1);
			preparedStatement.setTimestamp(2,tmp);//Timestamp(2,new Timestamp(time));
			preparedStatement.setString(3, utilisateur.getEmail());
			preparedStatement.executeUpdate();
			connexion.commit();	

		}catch(SQLException e) {			
			e.printStackTrace();
			printSQLException(e);
			try {
				if (connexion != null) {
					connexion.rollback();
				}
			}catch(SQLException e2) {
				e.printStackTrace();    
				printSQLException(e);
				throw new DaoException("Communication avec la base de données impossible");
			}			
		}finally {
			try {
				if(connexion != null) {
					connexion.close();
					System.out.println("closeclose");
				}
			}catch (SQLException e) {
				e.printStackTrace();
				printSQLException(e);
				throw new DaoException("Communication avec la base de données impossible");
			}
		}
	}


	@Override
	public boolean validLogin(Utilisateur utilisateur) throws DaoException, BeanException, SQLException {

		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		boolean status = false;

		try {
			connexion = connexionDao.getConnection();
			String SELECT_USERS_SQL = "select * from utilisateurs where email=?";
			preparedStatement = connexion.prepareStatement(SELECT_USERS_SQL);
			preparedStatement.setString(1, utilisateur.getEmail());
			ResultSet resultSet =  preparedStatement.executeQuery();
			status = resultSet.next();

			String cryptedPW = resultSet.getString("password");

			if(BCrypt.checkpw(utilisateur.getLogPW(), cryptedPW)) { //return true				
				String pseudo = resultSet.getString("pseudo");
				Timestamp dateInscription = resultSet.getTimestamp("date_inscription");
				Timestamp dateConnexion = resultSet.getTimestamp("date_connexion");
				int nbConnexion = resultSet.getInt("nb_connexion");
				utilisateur.setPseudo(pseudo);
				utilisateur.setDateInscription(dateInscription);
				utilisateur.setDateConnexion(dateConnexion);
				System.out.println("jj");
				utilisateur.setNbConnexion(nbConnexion);
				status = true;
			}else {
				status = false;
			}
		}catch(SQLException e) {			
			e.printStackTrace();
			printSQLException(e);
		}
		return status;
	}


	@Override
	public List<Utilisateur> lister() throws BeanException {
		List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;

		try {
			connexion = connexionDao.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT email, pseudo, nb_connexion, date_inscription FROM utilisateurs;");

			while (resultat.next()) {
				String email = resultat.getString("email");
				String pseudo = resultat.getString("pseudo");
				Timestamp dateInscription = resultat.getTimestamp("date_inscription");
				int nbConnexion = resultat.getInt("nb_connexion");

				Utilisateur utilisateur = new Utilisateur();
				utilisateur.setEmail(email);
				utilisateur.setPseudo(pseudo);
				utilisateur.setDateInscription(dateInscription);
				utilisateur.setNbConnexion(nbConnexion);

				utilisateurs.add(utilisateur);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			printSQLException(e);
		}
		return utilisateurs;
	}


	private void printSQLException(SQLException ex) {
		for (Throwable e: ex) {
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

	@Override
	public void selectNbConnexion(Utilisateur utilisateur) {

		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = connexionDao.getConnection();
			String SELECT_USERS_SQL = "select * from utilisateurs where email=?";
			preparedStatement = connexion.prepareStatement(SELECT_USERS_SQL);
			preparedStatement.setString(1, utilisateur.getEmail());
			ResultSet resultSet =  preparedStatement.executeQuery();
			resultSet.next();

			int nbConnexion = resultSet.getInt("nb_connexion");
			Timestamp tmp = resultSet.getTimestamp("date_connexion");
			utilisateur.setNbConnexion(nbConnexion);
			utilisateur.setDateConnexion(tmp);

		}catch(SQLException e) {			
			e.printStackTrace();
			printSQLException(e);
		}

	}



}