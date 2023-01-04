package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import model.Party;
import model.Player;

//CONTROLLER
public class ScoreBoardController implements Initializable {
	Party party = new Party();
	@FXML private ListView<String> playerList;
	@FXML private Button closeButton;
	@FXML private Label winner;
	
	public void receiveParty(Party partyReceived) {
		party = partyReceived;
		actualizeList();
	}
	
	public void actualizeList() {
		ArrayList<Player> partyPlayerList = party.getPlayerList();
		ObservableList<String> scoreList = FXCollections.observableArrayList();
		int max = 0;
		while(partyPlayerList.size() >= 1) {
			for(int i = 0; i < partyPlayerList.size(); i++) {
				if(partyPlayerList.get(i).getScore() >= partyPlayerList.get(max).getScore()) {
					max = i;
				}
			}
			scoreList.add(partyPlayerList.get(max).toString());
			partyPlayerList.remove(max);
			max = 0;
		}
		playerList.setItems(scoreList);
		winner.setText("Winner : " + scoreList.get(0));
	}

	@FXML
	public void leave() {
		Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}

}
