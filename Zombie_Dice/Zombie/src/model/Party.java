package model;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



public class Party {
	private ArrayList<Player> playerList = new ArrayList();
	ObservableList<String> nameList = FXCollections.observableArrayList();
	private int difficulty;
	private int actualPlayerIndex;

	//Constructeur
	public Party() {
		super();
	}

	//Setter & Getter
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	public int getNbPlayer() {
		return playerList.size();
	}

	public ObservableList<String> getNameList(){
		return nameList;
	}
	
	public ArrayList<Player> getPlayerList(){
		return playerList;
	}

	public Player getActualPlayer() {
		return playerList.get(actualPlayerIndex);
	}
	
	public int getActualPlayerIndex() {
		return actualPlayerIndex;
	}
	
	public void setNameList(ObservableList<String> nameList) {
		this.nameList = nameList;
	}
	
	public void setActualPlayerIndex(int actualPlayerIndex) {
		this.actualPlayerIndex = actualPlayerIndex;
	}

	//Methods
	public void addPlayer(String name) {
		playerList.add(new Player(name));
		nameList.add(name);
	}

	public void initDiceList() {
		ArrayList<Dice> diceList = new ArrayList();

		switch(difficulty) {
		case 1: 
			//Setting green dices
			for(int i = 0; i < 6; i++) {
				diceList.add(new GreenDice());
			}
			//Setting yellow dices
			for(int i = 0; i < 4; i++) {
				diceList.add(new YellowDice());
			}
			//Setting red dices
			for(int i = 0; i < 3; i++) {
				diceList.add(new RedDice());
			}

			break;
		case 2:
			//Setting green dices
			for(int i = 0; i < 4; i++) {
				diceList.add(new GreenDice());
			}
			//Setting yellow dices
			for(int i = 0; i < 4; i++) {
				diceList.add(new YellowDice());
			}
			//Setting red dices
			for(int i = 0; i < 5; i++) {
				diceList.add(new RedDice());
			}
			break;
		case 3:
			//Setting green dices
			for(int i = 0; i < 3; i++) {
				diceList.add(new GreenDice());
			}
			//Setting yellow dices
			for(int i = 0; i < 4; i++) {
				diceList.add(new YellowDice());
			}
			//Setting red dices
			for(int i = 0; i < 6; i++) {
				diceList.add(new RedDice());
			}
			break;
		}
		//Setting the diceList for all players
		for(Player player : playerList) {
			player.setDiceList(diceList);
		}
	}
	
	public void actualizeNameList() {
		ObservableList<String> newNameList = FXCollections.observableArrayList();
		for(Player player : playerList){
			newNameList.add(player.toString());
		}
		setNameList(newNameList);
	}

	public void init() {
		setActualPlayerIndex(0);
	}

	public void nextPlayer() {
		playerList.get(actualPlayerIndex).stop();
		initDiceList();
		if(actualPlayerIndex == playerList.size() - 1) {
			setActualPlayerIndex(0);
		}
		else {
			setActualPlayerIndex(getActualPlayerIndex() + 1);
		}
	}

}
