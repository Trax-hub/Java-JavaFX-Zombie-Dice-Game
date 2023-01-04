package model;

public class FootprintFace extends Face{

	//Constructeur
	public FootprintFace(String color) {
		super();
		switch(color) {
		case "green" : 
			setImageAdress("/ressources/images/green_footprint.png");
			break;
		case "yellow" : 
			setImageAdress("/ressources/images/yellow_footprint.png");
			break;
		case "red" : 
			setImageAdress("/ressources/images/red_footprint.png");
			break;
		}
	}
}
