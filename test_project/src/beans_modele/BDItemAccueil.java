package beans_modele;

import java.io.InputStream;

public class BDItemAccueil {
	
	protected int idBd;	
	protected String serie;
	protected String titre;
	protected String vignette;
	protected InputStream inputStream;
	protected String resume;
	protected String langue;
	protected String genre;
	protected String auteurNom;
	protected String auteurPrenom;
	
	
	public BDItemAccueil(int idBd,String serie, String titre, String vignette, String resume,
			String langue, String genre, String auteurNom, String auteurPrenom) {
		super();
		this.idBd = idBd;
		this.serie = serie;
		this.titre = titre;
		this.vignette = vignette;		
		this.resume = resume;
		this.langue = langue;
		this.genre = genre;
		this.auteurNom = auteurNom;
		this.auteurPrenom = auteurPrenom;
	}
	
	public int getIdBd() {
		return idBd;
	}
	public void setId_bd(int idBd) {
		this.idBd = idBd;
	}
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getVignette() {
		return vignette;
	}
	public void setVignette(String vignette) {
		this.vignette = vignette;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	public String getResume() {
		return resume;
	}
	public void setResume(String resume) {
		this.resume = resume;
	}
	public String getLangue() {
		return langue;
	}
	public void setLangue(String langue) {
		this.langue = langue;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getAuteurNom() {
		return auteurNom;
	}

	public void setAuteurNom(String auteurNom) {
		this.auteurNom = auteurNom;
	}

	public String getAuteurPrenom() {
		return auteurPrenom;
	}

	public void setAuteurPrenom(String auteurPrenom) {
		this.auteurPrenom = auteurPrenom;
	}

	

}
