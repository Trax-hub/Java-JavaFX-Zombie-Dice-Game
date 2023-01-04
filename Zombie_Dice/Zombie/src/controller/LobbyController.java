package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import model.Party;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

//CONTROLLER
public class LobbyController implements Initializable{
	private Party party = new Party();
	@FXML private BorderPane borderPane;
	@FXML private ComboBox<String> comboLevel;
	@FXML private TextField nameTextField;
	@FXML private ListView<String> playerList;
	@FXML private Button closeButton;
	@FXML private Label error1;
	@FXML private Label error2;

	@FXML
	public void leave() {
		Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
	}

	@FXML
	public void addPlayer() {
		if(nameTextField.getText() != "" && party.getNbPlayer() < 10) {
			party.addPlayer(nameTextField.getText());
			ObservableList<String> nameList = party.getNameList();
			playerList.setItems(nameList);
			nameTextField.setText("");
		}
	}

	@FXML
	public void setLevel() {
		switch(comboLevel.getValue()) {
		case "Level 1" : 
			party.setDifficulty(1);
			break;
		case "Level 2" : 
			party.setDifficulty(2);
			break;
		case "Level 3" : 
			party.setDifficulty(3);
			break;
		}
	}

	@FXML 
	public void play() throws IOException {
		if((comboLevel.getValue() == "Level 1" || comboLevel.getValue() == "Level 2" || comboLevel.getValue() == "Level 3") && (party.getPlayerList().size() >= 2)) {
			party.init();
			party.initDiceList();

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/ressources/view/game.fxml"));
			Parent root = loader.load();
			GameController gameController = loader.getController();
			gameController.receiveParty(party);
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.setTitle("Zombie Dice");
			stage.show();

			leave();
		}
		if(comboLevel.getValue() != "Level 1" && comboLevel.getValue() != "Level 2" && comboLevel.getValue() != "Level 3") {
			error2.setVisible(true);
		}
		if(party.getPlayerList().size() < 2) {
			error1.setVisible(true);
		}
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<String> levelList = FXCollections.observableArrayList();
		levelList.addAll("Level 1", "Level 2", "Level 3");
		comboLevel.setItems(levelList);
		error1.setVisible(false);
		error2.setVisible(false);
	}

}