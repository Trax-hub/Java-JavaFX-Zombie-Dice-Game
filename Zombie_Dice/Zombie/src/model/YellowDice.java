package model;

import java.util.ArrayList;

public class YellowDice extends Dice {
	private static final int NB_BRAIN = 2;
	private static final int NB_FOOTPRINT = 2;
	private static final int NB_SHOTGUN = 2;
	private static final String color = "yellow";
	
	//Constructor
	public YellowDice() {
		super(NB_BRAIN, NB_FOOTPRINT, NB_SHOTGUN, color);
	}
}
