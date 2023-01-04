package model;

public class Face {
	private String imageAdress;
	
	//Constructor
	public Face(String imageAdress) {
		super();
		this.imageAdress = imageAdress;
	}
	
	public Face() {
		super();
	}
	
	//Getter & Setter
	public String getImageAdress() {
		return imageAdress;
	}
	
	public void setImageAdress(String imageAdress) {
		this.imageAdress = imageAdress;
	}
}
