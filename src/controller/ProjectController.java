/* The primary controller for managing Project objects within the application. Establishes a form
 * where user can submit information on a project.
 */
package controller;

import java.time.LocalDate;

import application.Project;
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
	
	private TextField projName;
	private DatePicker date;
	private TextArea descript;
	private Label projNameError;
	
	public ProjectController(Stage primaryStage, Scene homeScene) {
		prevScene = homeScene;
		this.sqlControl = new SQLController();
		projectStage = primaryStage;
	}
/* Configure a form where user can add a project by entering a name, modifying the date either by clicking on the calendar
 * or just manually entering the date, and adding a description. When user saves the submission, the form will clear the fields
 * after successfully saving so user doesn't have to manually clear the fields to enter a new project.
 * Otherwise, if user does not want to do anything, the cancel
 * button will take them back to the home page.
 * 	
 */
	public void projectCreationForm() {
        VBox vbox = new VBox(20); //spacing for the entire form
        VBox nameBox = new VBox(5); //spacing for the name field and error message
        Scene projScene = new Scene(vbox, 1000, 700);
        projScene.getStylesheets().add(getClass().getResource("/message.css").toExternalForm());
        vbox.setAlignment(Pos.TOP_LEFT);
        projectStage.setTitle("Project Form");

        Label title = new Label("Enter Project Name: ");
        projName = new TextField();
        
        //Set the field as required. If user hasn't filled in field, error message becomes visible
        projNameError = new Label("Required");
        projNameError.setVisible(false);
        projNameError.getStyleClass().add("error-label");

        projName.setPromptText("Project name");
        projName.setMaxWidth(500);

        Label dateSelect = new Label("Project's Starting Date");
        date = new DatePicker(LocalDate.now());

        Label description = new Label("Description");
        descript = new TextArea();
        descript.setPromptText("Optional");
        

        Button saveButton = new Button("Save");
        saveButton.setOnAction(event -> save(projName.getText(), date.getValue(), descript.getText()));

        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(event -> cancel()); 
	    //horizontal layout for save/cancel buttons
	    HBox hbox = new HBox(20);
	    hbox.setAlignment(Pos.CENTER);
	    
	    nameBox.getChildren().addAll(projName,projNameError);
	    hbox.getChildren().addAll(cancelButton,saveButton);
        vbox.getChildren().addAll(title, nameBox, dateSelect, date, description, descript, hbox);
        projectStage.setScene(projScene);
    }

	private void cancel() {
		// TODO Auto-generated method stub
        projectStage.setScene(prevScene); 
	    projectStage.setTitle("Home Page");
	}

	private void save(String name, LocalDate projDate, String description) {
		// TODO Auto-generated method stub
	    boolean hasErrors = false;
	    /* If the field is empty, the error message becomes visible. The borders of the box will become
	     * red, prompting the user to fill in the field. Otherwise, it removes the message once user fills in
	     * field and saves the project. If user fails to enter the name, the save function does not add the project
	     * to the database unless user fixes their mistake. 
	     * 
	     */
	    if (name.isEmpty()) {
	        projName.getStyleClass().add("error-field");
	        projNameError.setVisible(true);
	        hasErrors = true;
	    } else {
	        projName.getStyleClass().remove("error-field");
	        projNameError.setVisible(false);
	    }

	    if (hasErrors) {
	        return;
	    }
        Project project = new Project(name, projDate, description);
        sqlControl.insertProject(project);
        
        //clears field after saving project
        projName.clear();
        date.setValue(LocalDate.now());
        descript.clear();
        
	}
	
	
}
