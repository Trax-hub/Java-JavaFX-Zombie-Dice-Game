package model;

import java.util.ArrayList;

public class GreenDice extends Dice{
	private static final int NB_BRAIN = 3;
	private static final int NB_FOOTPRINT = 2;
	private static final int NB_SHOTGUN = 1;
	private static final String color = "green";
	
	//Constructor
	public GreenDice() {
		super(NB_BRAIN, NB_FOOTPRINT, NB_SHOTGUN, color);
	}
	

}
