module Zombie {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.desktop;
	requires javafx.graphics;
	
	exports controller;
	exports model;
	
	opens application to javafx.graphics, javafx.fxml;
	opens controller to javafx.fxml;
}
