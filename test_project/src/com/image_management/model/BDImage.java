package com.image_management.model;

public class BDImage {
	private long id;
	private long comicsName;
	private String base64Image;
	
	public BDImage() {
		
	}
	
	public BDImage(int id, String image, int bd_id) {
		super();
		this.id = id;
		this.comicsName = bd_id;
		this.base64Image = image;
	}
	
	public BDImage(int id, int name) {
		super();
		this.id = id;
		this.comicsName = name;
	}
	
	public BDImage(int name, String image) {
		super();
		this.comicsName = name;
		this.base64Image = image;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getComicsName() {
		return comicsName;
	}
	public void setComicsName(long comicsName) {
		this.comicsName = comicsName;
	}
	public String getBase64Image() {
		return base64Image;
	}
	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}
}