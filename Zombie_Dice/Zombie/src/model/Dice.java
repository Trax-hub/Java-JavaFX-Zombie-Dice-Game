package model;

import java.util.ArrayList;
import java.util.Random;

public class Dice {
	private int nbBrain;
	private int nbFootprint;
	private int nbShotgun;
	private String imageAdress;
	private Face frontFace;
	private ArrayList<Face> faceList = new ArrayList();
	
	//Constructor
	public Dice(int nbBrain, int nbFootprint, int nbShotgun, String color) {
		super();
		this.nbBrain = nbBrain;
		this.nbFootprint = nbFootprint;
		this.nbShotgun = nbShotgun;
		
		switch(color) {
		case "green" : 
			setImageAdress("/ressources/images/green_dice.png");
			break;
		case "yellow" : 
			setImageAdress("/ressources/images/yellow_dice.png");
			break;
		case "red" : 
			setImageAdress("/ressources/images/red_dice.png");
			break;
		}
		
		//Filling the dice with BrainFaces
		for(int i = 0; i < nbBrain; i++) {
			faceList.add(new BrainFace(color));
		}
		
		//Filling the dice with FootprintFaces
		for(int i = 0; i < nbFootprint; i++) {
			faceList.add(new FootprintFace(color));
		}
		
		//Filling the dice with ShotgunFaces
		for(int i = 0; i < nbShotgun; i++) {
			faceList.add(new ShotgunFace(color));
		}
	}
	
	public Dice() {
		super();
	}
	
	//Getters & Setters
	public Face getFace(int face) {
		return (faceList.get(face));
	}
	
	public void setFrontFace(Face face) {
		this.frontFace = face;
	}
	
	public Face getFrontFace() {
		return frontFace;
	}
	
	public ArrayList<Face> getFaceList(){
		return faceList;
	}
	
	public String getImageAdress() {
		return imageAdress;
	}
	
	public void setImageAdress(String imageAdress) {
		this.imageAdress = imageAdress;
	}
	
	//Method
	public void throwDice() {
		Random random = new Random();
		int n = random.nextInt(6);
		setFrontFace(faceList.get(n));
	}
}
