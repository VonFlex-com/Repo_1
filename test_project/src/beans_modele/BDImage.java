package beans_modele;

import java.io.InputStream;

public class BDImage {

	private int idBd;
	private String serie;
	private String titre;
	protected String bdImage;
	private InputStream inputStream;
	private int idImage;


	public BDImage(int idBd) {
		super();
		this.idBd = idBd;
	}


	public BDImage( String serie, String titre, String bdImage, int idImage ){
		super();

		this.serie = serie;
		this.titre = titre;
		this.bdImage = bdImage;
		this.idImage = idImage;

	}


	public int getIdBd() {
		return idBd;
	}


	public void setIdBd(int idBd) {
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


	public InputStream getInputStream() {
		return inputStream;
	}


	public void setInputStream(InputStream InputStream) {
		this.inputStream = InputStream;
	}
	public String getBdImage() {
		return bdImage;
	}


	public void setBdImage(String bdImage) {
		this.bdImage = bdImage;
	}


	public int getIdImage() {
		return idImage;
	}


	public void setIdImage(int idImage) {
		this.idImage = idImage;
	}

}