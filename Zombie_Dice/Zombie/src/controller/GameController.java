package controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import model.Party;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.*;

//CONTROLLER
public class GameController implements Initializable{
	private Party party = new Party();
	@FXML private Label actualPlayer;
	@FXML private Label nbGreenDice;
	@FXML private Label nbYellowDice;
	@FXML private Label nbRedDice;
	@FXML private Label nbBrains;
	@FXML private Label sideBrain;
	@FXML private Label sideShotgun;
	@FXML private ListView<String> scoreBoard;
	@FXML HBox Hbox;
	@FXML ImageView dice1;
	@FXML ImageView dice2;
	@FXML ImageView dice3;
	@FXML Button pickDices;
	@FXML Button collect;
	@FXML Button stopPass;
	@FXML Button throwDices;
	@FXML private Button closeButton;

	public void receiveParty(Party partyReceived) {
		party = partyReceived;
		actualize();
	}

	public void actualize() {
		actualPlayer.setText(party.getActualPlayer().getName());
		
		nbGreenDice.setText(String.valueOf(party.getActualPlayer().getNbGreenDice()));
		nbYellowDice.setText(String.valueOf(party.getActualPlayer().getNbYellowDice()));
		nbRedDice.setText(String.valueOf(party.getActualPlayer().getNbRedDice()));
		nbBrains.setText(String.valueOf(party.getActualPlayer().getScore()));
		
		sideBrain.setText(String.valueOf(party.getActualPlayer().getSideBrain()));
		sideShotgun.setText(String.valueOf(party.getActualPlayer().getSideShotgun()));
		
		actualizeScoreBoard();
		
		if(party.getActualPlayer().isBusted()) {
			throwDices.setVisible(false);
		}
	}
	
	public void actualizeScoreBoard() {
		party.actualizeNameList();
		scoreBoard.setItems(party.getNameList());
	}
	
	public void actualizeDiceFace() {
		InputStream input1 = this.getClass().getResourceAsStream(party.getActualPlayer().getFaceDice(0).getImageAdress());
		dice1.setImage(new Image(input1));
		InputStream input2 = this.getClass().getResourceAsStream(party.getActualPlayer().getFaceDice(1).getImageAdress());
		dice2.setImage(new Image(input2));
		InputStream input3 = this.getClass().getResourceAsStream(party.getActualPlayer().getFaceDice(2).getImageAdress());
		dice3.setImage(new Image(input3));
	}
	
	public void actualizeDice() {
		if(party.getActualPlayer().getDiceList().get(0) != null) {
			InputStream input1 = this.getClass().getResourceAsStream(party.getActualPlayer().getHandList().get(0).getImageAdress());
			dice1.setImage(new Image(input1));
		}
		if(party.getActualPlayer().getDiceList().get(1) != null) {
			InputStream input2 = this.getClass().getResourceAsStream(party.getActualPlayer().getHandList().get(1).getImageAdress());
			dice2.setImage(new Image(input2));
		}
		if(party.getActualPlayer().getDiceList().get(2) != null) {
			InputStream input3 = this.getClass().getResourceAsStream(party.getActualPlayer().getHandList().get(2).getImageAdress());
			dice3.setImage(new Image(input3));
		}
	}

	public void checkEnd() throws IOException {
		party.getActualPlayer().checkEnd();
		if(party.getActualPlayer().isBusted()) {
			throwDices.setVisible(false);
			pickDices.setVisible(false);
			collect.setVisible(false);
			stopPass.setVisible(true);
		}
		if(party.getActualPlayer().isWin()) {
			stopPass.setVisible(false);
			throwDices.setVisible(false);
			collect.setVisible(false);
			pickDices.setVisible(false);
			win();
		}
	}
	
	public void win() throws IOException {
		party.getActualPlayer().setScore(13);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/ressources/view/scoreboard.fxml"));
		Parent root = loader.load();
		ScoreBoardController scoreboardController = loader.getController();
		scoreboardController.receiveParty(party);
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.setTitle("Zombie Dice");
		stage.show();
	}
	
	@FXML
	public void collect() throws IOException {
		party.getActualPlayer().collectDice();
		actualize();
		throwDices.setVisible(false);
		pickDices.setVisible(true);
		collect.setVisible(false);
		stopPass.setVisible(true);
		dice1.setVisible(false);
		dice2.setVisible(false);
		dice3.setVisible(false);
		checkEnd();
	}
	
	public void throwDices() throws IOException {
		party.getActualPlayer().throwDices();
		actualizeDiceFace();
		collect.setVisible(true);
		checkEnd();
		throwDices.setVisible(false);
	}

	@FXML
	public void leave() {
		Stage stage = (Stage) closeButton.getScene().getWindow();
	    stage.close();
	}
	
	@FXML
	public void pickDices() throws IOException {
		party.getActualPlayer().pickDice();
		actualize();
		actualizeDice();
		checkEnd();
		throwDices.setVisible(true);
		pickDices.setVisible(false);
		collect.setVisible(false);
		stopPass.setVisible(false);
		dice1.setVisible(true);
		dice2.setVisible(true);
		dice3.setVisible(true);
	}

	@FXML
	public void pass() {
		party.initDiceList();
		party.nextPlayer();
		actualize();
		actualizeScoreBoard();
		party.getActualPlayer().setBusted(false);
		sideBrain.setText("0");
		sideShotgun.setText("0");
		pickDices.setVisible(true);
		stopPass.setVisible(false);
		throwDices.setVisible(false);
		dice1.setVisible(false);
		dice2.setVisible(false);
		dice3.setVisible(false);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Hbox.setBorder(new Border(new BorderStroke(Color.BLACK, 
				BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		sideBrain.setText("O");
		sideShotgun.setText("0");
		collect.setVisible(false);
		stopPass.setVisible(false);
		throwDices.setVisible(false);
	}

}
