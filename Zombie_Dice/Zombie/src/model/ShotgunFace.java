package model;

public class ShotgunFace extends Face{

	//Constructeur
	public ShotgunFace(String color) {
		super();
		switch(color) {
		case "green" : 
			setImageAdress("/ressources/images/green_shotgun.png");
			break;
		case "yellow" : 
			setImageAdress("/ressources/images/yellow_shotgun.png");
			break;
		case "red" : 
			setImageAdress("/ressources/images/red_shotgun.png");
			break;
		}
	}
}
