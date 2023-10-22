/* The primary controller for managing Project objects within the application. Establishes a form
 * where user can submit information on a project.
 */
package controller;

import java.time.LocalDate;

import database.ProjectDatabase;
import entities.Project;
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
import layout.ProjectLayout;


public class ProjectController {
	private Stage projectStage; //project window
	//private SQLController sqlControl;
	private ProjectDatabase projdb;
	private Scene prevScene; //stores the previous window
	private ProjectLayout projLayout;
	
	
	public ProjectController(Stage primaryStage,Scene homeScene) {
		prevScene = homeScene;
		//sqlControl = new SQLController();
		projdb = new ProjectDatabase();
		projectStage = primaryStage;
		projLayout = new ProjectLayout(projectStage,this);
		
	}

	public void cancel() {
        projectStage.setScene(prevScene); 
	    projectStage.setTitle("Home Page");
	}
	public void save() {

	    /* If the field is empty, the error message becomes visible. The borders of the box will become
	     * red, prompting the user to fill in the field. Otherwise, it removes the message once user fills in
	     * field and saves the project. If user fails to enter the name, the save function does not add the project
	     * to the database unless user fixes their mistake. 
	     * 
	     */
	    TextField nameField = projLayout.getProjName();
	    DatePicker dateField = projLayout.getDatePicker();
	    TextArea descriptionArea = projLayout.getDescriptionArea();

	    if(!validField(nameField)) {
	    	return;
	    }

        Project project = new Project(nameField.getText(), dateField.getValue(), descriptionArea.getText());
        //sqlControl.insertProject(project);
	    //int generatedId = sqlControl.insertProject(project);
	    int generatedId = projdb.insertProject(project);
	    project.setId(generatedId);
        
        //clears field after saving project
        nameField.clear();
        dateField.setValue(LocalDate.now());
        descriptionArea.clear();
        
	}
	private boolean validField(TextField nameField) {
		boolean isFilled = true;
	    Label requiredField = projLayout.getProjNameError();
	    if (nameField.getText().isEmpty()) {
	        nameField.getStyleClass().add("error-field");
	        requiredField.setVisible(true);
	        isFilled = false;
	    } else {
	        nameField.getStyleClass().remove("error-field");
	        requiredField.setVisible(false);
	    }

	    return isFilled;
	}
	
	public void displayProjectForm() {
		projectStage.setScene(projLayout.getScene());
	}
	
	
}
