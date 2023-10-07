package application;
	
import java.time.LocalDate;


import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,1000,700);
			//set title for home page
			primaryStage.setTitle("Home Page");
	        Label homeLabel = new Label("Welcome to the Home Page");
	        BorderPane.setAlignment(homeLabel, Pos.CENTER);
	        Button newProject = new Button("Create a new project");
	        root.setTop(homeLabel);
	        root.setCenter(newProject);
	        
	        //action performed when newProject button is clicked
	        newProject.setOnAction(event -> {
	        	//setup vbox layout for project form
	    		VBox vbox = new VBox(20);
	    		Scene projScene = new Scene(vbox,1000,700);
	    		vbox.setAlignment(Pos.TOP_LEFT);
	    		
	    		//components for project form: projName, date, descrip, save/cancel
	    		Label title = new Label("Enter Project Name: ");
	    		TextField projName = new TextField();
	    		projName.setPromptText("Project name");
	    	    projName.setMaxWidth(500);
	    	    
	    	    //date selection for project date. set to current date but can be modified
	    	    Label dateSelect = new Label("Project's Starting Date");
	    	    DatePicker date = new DatePicker(LocalDate.now());
	    	    
	    	    //text area for project description
	    	    Label description = new Label("Description");
	    	    TextArea descrip = new TextArea();
	    	    descrip.setPromptText("Optional");
	    	    
	    	    Button save = new Button("Save Project");
	    	    Button cancel = new Button("Cancel");
	    	    
	    	    //horizontal layout for save/cancel buttons
	    	    HBox hbox = new HBox(20);
	    	    hbox.setAlignment(Pos.CENTER);
	    	    
	    	    //align components to hbox layout
	    	    hbox.getChildren().addAll(cancel,save);
	    	    //align components to vbox layout
	    		vbox.getChildren().addAll(title,projName,dateSelect,date,description,descrip,hbox);
	    		
	    		//Set title and display project page
	    		primaryStage.setTitle("Project Page");
	    		primaryStage.setScene(projScene);
	    		primaryStage.show();
	        });

	        //display home page
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
