package layout;

import java.time.LocalDate;

import controller.ProjectController;
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

public class ProjectLayout implements LayoutInterface {
    private Stage projectStage;
    private ProjectController projControl;
	private TextField projName;
	private DatePicker date;
	private TextArea descript;
	private Label projNameError;
	
	private Scene projectScene;
	private VBox root;
    
    public ProjectLayout(Stage projectStage,ProjectController projControl) {
        this.projectStage = projectStage;
        this.projControl = projControl;
        root = new VBox(20);
        projectScene = new Scene(root, 1000, 700);
        GenerateForm();
    }
    
    /* Configure a form where user can add a project by entering a name, modifying the date either by clicking on the calendar
     * or just manually entering the date, and adding a description. When user saves the submission, the form will clear the fields
     * after successfully saving so user doesn't have to manually clear the fields to enter a new project.
     * Otherwise, if user does not want to do anything, the cancel
     * button will take them back to the home page.
     * 	
     */  
	public void GenerateForm() {
        VBox nameBox = new VBox(5); //spacing for the name field and error message

        projectScene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        root.setAlignment(Pos.TOP_LEFT);
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
        saveButton.setOnAction(event -> projControl.save());

        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(event -> projControl.cancel()); 
	    //horizontal layout for save/cancel buttons
	    HBox hbox = new HBox(20);
	    hbox.setAlignment(Pos.CENTER);
	    
	    nameBox.getChildren().addAll(projName,projNameError);
	    hbox.getChildren().addAll(cancelButton,saveButton);
        root.getChildren().addAll(title, nameBox, dateSelect, date, description, descript, hbox);
    }
	public Scene getScene() {
		return projectScene;
	}
	
	public TextField getProjName() {
	    return projName;
	}

	public Label getProjNameError() {
	    return projNameError;
	}

	public DatePicker getDatePicker() {
	    return date;
	}

	public TextArea getDescriptionArea() {
	    return descript;
	}

    
}
