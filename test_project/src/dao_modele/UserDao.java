package dao_modele;

import java.sql.SQLException;
import java.util.List;

import beans_modele.BeanException;
import beans_modele.Utilisateur;

public interface UserDao {
	
    int ajouter( Utilisateur utilisateur ) throws DaoException;
    List<Utilisateur> lister() throws BeanException;
	boolean validLogin(Utilisateur utilisateur) throws DaoException, BeanException, SQLException;
	void updateUserData(Utilisateur utilisateur) throws SQLException, DaoException;
	void selectNbConnexion(Utilisateur utilisateur);
	boolean isUserDB(Utilisateur utilisateur) throws SQLException;	
	boolean addUserDB(Utilisateur utilisateur) throws SQLException;
	
}