package controller;

import java.time.LocalDate;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ProjectController {
	private Stage projectStage; //project window
	private SQLController sqlControl;
	private Scene prevScene; //stores the previous window
/* 
 * 	
 */
	public ProjectController(Stage primaryStage, Scene homeScene) {
		prevScene = homeScene;
		this.sqlControl = new SQLController();
		projectStage = primaryStage;
	}
	
	public void projectCreationForm() {
        VBox vbox = new VBox(20);
        Scene projScene = new Scene(vbox, 1000, 700);
        vbox.setAlignment(Pos.TOP_LEFT);

        Label title = new Label("Enter Project Name: ");
        TextField projName = new TextField();
        projName.setPromptText("Project name");
        projName.setMaxWidth(500);

        Label dateSelect = new Label("Project's Starting Date");
        DatePicker date = new DatePicker(LocalDate.now());

        Label description = new Label("Description");
        TextArea descrip = new TextArea();
        descrip.setPromptText("Optional");
        

        Button saveButton = new Button("Save");
        saveButton.setOnAction(event -> save(projName.getText(), date.getValue(), descrip.getText()));

        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(event -> {
        	clear(); 
        	projectStage.setScene(prevScene); //takes user back to home window
        });
        
	    //horizontal layout for save/cancel buttons
	    HBox hbox = new HBox(20);
	    hbox.setAlignment(Pos.CENTER);
	    
	    hbox.getChildren().addAll(cancelButton,saveButton);
        vbox.getChildren().addAll(title, projName, dateSelect, date, description, descrip, hbox);
        projectStage.setScene(projScene);
    }
	
	
	
}
