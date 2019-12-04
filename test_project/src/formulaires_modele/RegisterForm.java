package formulaires_modele;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans_modele.BeanException;
import beans_modele.Utilisateur;
import dao_modele.ConnexionDao;
import dao_modele.UserDao;

public class RegisterForm {

	private String messageErreur;
	private String messageOk;
	private UserDao userDao;

	public void verifierCreationCompte( HttpServletRequest request, HttpServletResponse reponse)
			throws ServletException, IOException, BeanException { 

		ConnexionDao connectDao = ConnexionDao.getInstance();
		this.userDao = connectDao.getUtilisateurDao();

		String email = request.getParameter("email");
		String pseudo = request.getParameter("pseudo");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		if(!password1.equals(password2)) {  

			messageErreur = "ERREURS! Vos 2 mots de passe ne correspondent pas!";

		}else {
			try {

				Utilisateur utilisateur = new Utilisateur();
				utilisateur.setEmail(email);
				utilisateur.setPseudo(pseudo);
				utilisateur.setPassword(password1);

				if(userDao.isUserDB(utilisateur)==false){
					if(userDao.addUserDB(utilisateur)) {					
						messageOk="Création de compte acceptée, veuillez vous connecter.";
					}
				}else{
					messageErreur="Attention, cette utilisateur existe deja! Veuiller changer d'email.";	
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public String getMessageErreur() {
		return messageErreur;
	}
	public void setMessageErreur(String messageErreur) {
		this.messageErreur = messageErreur;
	}
	public String getMessageOk() {
		return messageOk;
	}
	public void setMessageOk(String messageOk) {
		this.messageOk = messageOk;
	}
}
