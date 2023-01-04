package model;

public class BrainFace extends Face{
	
	//Constructor
	public BrainFace(String color) {
		super();
		switch(color) {
		case "green" : 
			setImageAdress("/ressources/images/green_brain.png");
			break;
		case "yellow" : 
			setImageAdress("/ressources/images/yellow_brain.png");
			break;
		case "red" : 
			setImageAdress("/ressources/images/red_brain.png");
			break;
		}
	}
}
