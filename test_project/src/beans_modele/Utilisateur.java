package beans_modele;

import java.io.Serializable;
import java.sql.Timestamp;
//import java.text.DateFormat;
//import java.util.Date;

import org.mindrot.jbcrypt.BCrypt;

public class Utilisateur implements Serializable {

	private static final long serialVersionUID = 1L;

	private String email;
	private String pseudo;
	private String password;
	private String logPW;
	
	private int nbConnexion;	
	
	private Timestamp dateInscription;
	private Timestamp dateConnexion;

	public int lgPw = 10;             // exemple d'utilisation, on limite à 10 caractères...
	public int lgPseudo = 10;
	

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) throws BeanException {
		if(pseudo.length()>lgPseudo) {
			throw new BeanException("Votre PSEUDO est trop grand! (" + lgPseudo +" caractères maximum)");
		}else {
			this.pseudo = pseudo;
		}
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) throws BeanException {

		if(password.length()>lgPw) {
			throw new BeanException("Votre PASSWORD est trop grand! (" + lgPw +" caractères maximum)");
		}else{
			String hashed = BCrypt.hashpw(password, BCrypt.gensalt(12));
			this.password = hashed;
		}
	}
	public String getLogPW() {
		return logPW;
	}
	public void setLogPW(String logPW) {
		this.logPW = logPW;
	}

	public int getNbConnexion() {
		return nbConnexion;
	}
	public void setNbConnexion(int nbConnexion) {
		this.nbConnexion = nbConnexion;
	}
	
	//=========================================  les dates ===================================
	// Date d'inscription
	public Timestamp getDateInscription() {		
		return dateInscription;
	}
	public void setDateInscription(Timestamp dateInscription) {
		this.dateInscription = dateInscription;
	}
	/*//stockage en string
	public String getDateInscriptionString() {
		return dateInscriptionString;
	}
	public void setDateInscriptionString(String dateInscriptionString) {
		this.dateInscriptionString = dateInscriptionString;
	}
	*/
	
	// Date de connexion
	public Timestamp getDateConnexion() {
		return dateConnexion;
	}
	public void setDateConnexion(Timestamp dateConnexion) {
		this.dateConnexion = dateConnexion;
	}
	/*//stockage en string
	public String getDateConnexionString() {
		return dateConnexionString;
	}
	public void setDateConnexionString(String dateConnexionString) {
		this.dateConnexionString = dateConnexionString;
	}
	
	//Convertisseur de date en string	
	public String getConvertDateToString() {		
		return convertDateToString;		
	}
	
	public void setConvertDateToString(Date date) {
		String dateConverted = DateFormat.getDateInstance().format(date);		
	this.convertDateToString = dateConverted;
	}
	
	*/
	
	
	
	

}
