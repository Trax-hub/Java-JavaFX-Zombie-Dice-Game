package model;

import java.util.ArrayList;

public class RedDice extends Dice {
	private static final int NB_BRAIN = 1;
	private static final int NB_FOOTPRINT = 2;
	private static final int NB_SHOTGUN = 3;
	private static final String color = "red";
	
	//Constructor
	public RedDice() {
		super(NB_BRAIN, NB_FOOTPRINT, NB_SHOTGUN, color);
	}
}
