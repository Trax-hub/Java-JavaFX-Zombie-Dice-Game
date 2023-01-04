package model;

import java.util.ArrayList;
import java.util.Random;

public class Player {
	private String name;
	private int score;
	private int sideBrain;
	private int sideShotgun;
	private int nbGreenDice;
	private int nbYellowDice;
	private int nbRedDice;
	private boolean busted;
	private boolean win;
	private ArrayList<Dice> diceList = new ArrayList();
	private ArrayList<Dice> handList = new ArrayList();
	private ArrayList<Dice> sideList = new ArrayList();

	//Constructor
	public Player(String name) {
		super();
		this.name = name;
		this.score = 0;
		this.sideBrain = 0;
		this.sideShotgun = 0;
		this.busted = false;
	}

	//Setter
	public void setDiceList(ArrayList<Dice> diceList) {
		nbGreenDice = 0;
		nbYellowDice = 0;
		nbRedDice = 0;
		this.diceList = diceList;
		for(Dice dice : diceList) {
			if(dice instanceof GreenDice) {
				this.nbGreenDice++;
			}
			if(dice instanceof YellowDice) {
				this.nbYellowDice++;
			}
			if(dice instanceof RedDice) {
				this.nbRedDice++;
			}
		}
	}

	public String getName() {
		return name;
	}

	public int getSideBrain() {
		return sideBrain;
	}

	public int getScore() {
		return score;
	}

	public int getSideShotgun() {
		return sideShotgun;
	}

	public int getNbGreenDice() {
		return nbGreenDice;
	}

	public int getNbYellowDice() {
		return nbYellowDice;
	}

	public int getNbRedDice() {
		return nbRedDice;
	}

	public ArrayList<Dice> getDiceList(){
		return diceList;
	}

	public ArrayList<Dice> getHandList(){
		return handList;
	}

	public ArrayList<Dice> getSideList(){
		return sideList;
	}

	public boolean isBusted() {
		return busted;
	}

	public void setBusted(boolean busted) {
		this.busted = busted;
	}
	
	public boolean isWin() {
		return win;
	}

	public void setWin(boolean win) {
		this.win = win;
	}

	public void setScore(int score){
		this.score = score;
	}

	public void setSideBrain(int sideBrain){
		this.sideBrain = sideBrain;
	}

	public void setSideShotgun(int sideShotgun){
		this.sideShotgun = sideShotgun;
	}

	public void setNbGreenDice(int nbGreenDice) {
		this.nbGreenDice = nbGreenDice;
	}

	public void setNbYellowDice(int nbYellowDice) {
		this.nbYellowDice = nbYellowDice;
	}

	public void setNbRedDice(int nbRedDice) {
		this.nbRedDice = nbRedDice;
	}

	//Method
	public void pickDice() {
		if(diceList.size() > 0) {
			int i = 0;
			while(handList.size() < 3) {
				Random random = new Random();
				int n = random.nextInt(diceList.size());
				if(diceList.get(n) instanceof GreenDice) {
					setNbGreenDice(getNbGreenDice() - 1);
				}
				if(diceList.get(n) instanceof YellowDice) {
					setNbYellowDice(getNbYellowDice() - 1);
				}
				if(diceList.get(n) instanceof RedDice) {
					setNbRedDice(getNbRedDice() - 1);
				}
				handList.add(diceList.get(n));
				diceList.remove(n);
				i++;
			}
		}
	}

	public void throwDices() {
		for(int i = 0; i < handList.size(); i++) {
			handList.get(i).throwDice();
		}
		checkEnd();
	}

	public void collectDice() {
		for(int i = 0; i < 3 ;i++) {
			if(handList.get(i).getFrontFace() instanceof BrainFace) {
				sideList.add(handList.get(i));
				setSideBrain(getSideBrain() + 1);
			}
			if(handList.get(i).getFrontFace() instanceof ShotgunFace) {
				sideList.add(handList.get(i));
				setSideShotgun(getSideShotgun() + 1);
			}
		}
		int a = 3;
		for(int i = 0; i < a ;i++) {
			if(handList.get(i).getFrontFace() instanceof BrainFace) {
				handList.remove(i);
				i--;
				a--;
			}
			else if(handList.get(i).getFrontFace() instanceof ShotgunFace) {
				i--;
				a--;
			}
		}
		if(sideShotgun >= 3) {
			setBusted(true);
		}
		else {
			setBusted(false);
		}

	}

	public void stop() {
		if(!isBusted()) {
			setScore(getScore()+getSideBrain());
		}
		setSideBrain(0);
		setSideShotgun(0);
		resetSideList();
	}

	public void checkEnd() {
		if(sideShotgun >= 3 || handList.size() + diceList.size() < 3) {
			setBusted(true);
		}
		if(getScore() + getSideBrain() >= 13) {
			setWin(true);
		}
	}

	public Face getFaceDice(int index) {
		return handList.get(index).getFrontFace();
	} 

	public void setFaceDice(int index, Face face) {
		Dice dice;
		dice = handList.get(index);
		dice.setFrontFace(face);
		handList.set(index, dice);
	}

	public void resetSideList() {
		sideList = new ArrayList<Dice>();
	}

	@Override
	public String toString() {
		if(getScore() > 0) {
			return "(" + getScore() + ")  " + getName();
		}
		else {
			return getName();
		}

	}




}
