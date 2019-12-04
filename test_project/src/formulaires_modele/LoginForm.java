package formulaires_modele;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans_modele.BeanException;
import beans_modele.Utilisateur;
import dao_modele.ConnexionDao;
import dao_modele.UserDao;

public class LoginForm {

	private UserDao userDao;
	public String linkPage = "";
	private String messageErreur;
	private String messageOk;
	public HttpSession session;

	public void verifierLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, BeanException {

		ConnexionDao connectDao = ConnexionDao.getInstance();
		this.userDao = connectDao.getUtilisateurDao();

		
		String email = request.getParameter("email");
		String password1 = request.getParameter("password1");

		

		try {
			Utilisateur utilisateur = new Utilisateur();
			utilisateur.setEmail(email);
			utilisateur.setLogPW(password1);
			
			if (userDao.validLogin(utilisateur)) {

				userDao.updateUserData(utilisateur);				
				userDao.selectNbConnexion(utilisateur);
			
				session = request.getSession(true);
			
				session.setAttribute("sessionUtilisateur", utilisateur);
				messageOk = "Bienvenue à vous " + utilisateur.getPseudo() + "!!!";
				session.setAttribute("messageOkSession", messageOk);

			
				linkPage = request.getContextPath() + "/accueil";
			} else {

				
				System.out.println("login pas ok");
				session = request.getSession(false);	
				linkPage = "/WEB-INF/loginPage.jsp";
				messageErreur = "Vos informations ne sont pas compatibles, veuillez vous reconnecter";
			}
		} catch (Exception e) {
			e.printStackTrace();
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
