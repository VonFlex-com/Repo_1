package com.image_management.model;

import java.io.InputStream;
import java.sql.Timestamp;

public class Bd_item {

	
	protected int bd_id;
	protected String serie;
	protected String titre;
	protected String vignette;
	protected InputStream inputStream;
	protected String resume;
	protected String langue;
	protected Timestamp date_crea;
	protected Timestamp date_upload;
	protected int note_id;
	protected int auteur_id;
	protected int genre_id;
	
	public Bd_item() {

	}
	
	public Bd_item(String serie, String titre, InputStream inputStream, String resume, String langue, Timestamp date_crea,
			Timestamp date_upload, int note_id, int auteur_id, int genre_id) {
		super();
			this.serie = serie;
			this.titre = titre;
			this.inputStream = inputStream;
			this.resume = resume;
			this.langue = langue;
			this.date_crea = date_crea;
			this.date_upload = date_upload;
			this.note_id = note_id;
			this.auteur_id = auteur_id;
			this.genre_id = genre_id;
			}

	public Bd_item(int bd_id, String serie, String titre, String vignette, String resume, String langue, Timestamp date_crea,
			Timestamp date_upload, int note_id, int auteur_id, int genre_id) {
		super();
		this.bd_id = bd_id;
		this.serie = serie;
		this.titre = titre;
		this.vignette = vignette;
		this.resume = resume;
		this.langue = langue;
		this.date_crea = date_crea;
		this.date_upload = date_upload;
		this.note_id = note_id;
		this.auteur_id = auteur_id;
		this.genre_id = genre_id;
	}
	
	public int getBd_id() {
		return bd_id;
	}
	public void setBd_id(int bd_id) {
		this.bd_id = bd_id;
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
	public Timestamp getDate_crea() {
		return date_crea;
	}
	public void setDate_crea(Timestamp date_crea) {
		this.date_crea = date_crea;
	}
	public Timestamp getDate_upload() {
		return date_upload;
	}
	public void setDate_upload(Timestamp date_upload) {
		this.date_upload = date_upload;
	}
	public int getNote_id() {
		return note_id;
	}
	public void setNote_id(int note_id) {
		this.note_id = note_id;
	}
	public int getAuteur_id() {
		return auteur_id;
	}
	public void setAuteur_id(int auteur_id) {
		this.auteur_id = auteur_id;
	}
	public int getGenre_id() {
		return genre_id;
	}
	public void setGenre_id(int genre_id) {
		this.genre_id = genre_id;
	}


}
