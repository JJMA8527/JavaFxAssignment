package layout;

import java.time.LocalDate;

import controller.ProjectController;
import entities.Project;
import javafx.geometry.Pos;
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
	private VBox root;
	private Project project;

	public ProjectLayout(Stage projectStage,ProjectController projControl) {
		this.projectStage = projectStage;
		this.projControl = projControl;
		root = new VBox(20);
		project = new Project("",LocalDate.now(),"");
		errorFields();
		GenerateForm();
	}


	/* Configure a form where user can add a project by entering a name, modifying the date either by clicking on the calendar
	 * or just manually entering the date, and adding a description. When user saves the submission, the form will clear the fields
	 * after successfully saving so user doesn't have to manually clear the fields to enter a new project.
	 * Otherwise, if user does not want to do anything, the cancel
	 * button will take them back to the home page.
	 * 	
	 */
	@Override
	public void GenerateForm() {
		VBox nameBox = new VBox(5); //spacing for the name field and error message

		//projectScene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
		root.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
		root.setAlignment(Pos.TOP_LEFT);
		projectStage.setTitle("Project Form");

		Label title = new Label("Enter Project Name: ");
		projName = new TextField();

		projName.setPromptText("Project name");
		projName.setMaxWidth(500);

		Label dateSelect = new Label("Project's Starting Date");
		date = new DatePicker(LocalDate.now());

		Label description = new Label("Description");
		descript = new TextArea();
		descript.setPromptText("Optional");

		projName.textProperty().bindBidirectional(project.nameProperty());
		date.valueProperty().bindBidirectional(project.dateProperty());
		descript.textProperty().bindBidirectional(project.descriptionProperty());

		Button saveButton = new Button("Save");
		saveButton.setOnAction(event -> projControl.save());

		Button cancelButton = new Button("Cancel");
		cancelButton.setOnAction(event -> projControl.cancel());

		//horizontal layout for save/cancel buttons
		HBox hbox = new HBox(10);
		hbox.setAlignment(Pos.CENTER);

		nameBox.getChildren().addAll(projName,projNameError);
		hbox.getChildren().addAll(cancelButton,saveButton);
		root.getChildren().addAll(title, nameBox, dateSelect, date, description, descript, hbox);
	}

	private void errorFields() {
		// TODO Auto-generated method stub
		projNameError = new Label("Required");
		projNameError.setVisible(false);
		projNameError.getStyleClass().add("error-label");
	}

	public boolean validateForm() {
		boolean isValid = true;

		if (projName.getText().trim().isEmpty()) {
			projNameError.setVisible(true);
			projName.getStyleClass().add("error-field");
			isValid = false;
		} else {
			projNameError.setVisible(false);
			projName.getStyleClass().remove("error-field");
		}

		return isValid;
	}
	@Override
	public VBox getRoot() {
		return root;
	}

	public Project getProject() {
		return project;
	}

	public void clearForm() {
		project.setName("");
		project.setDate(LocalDate.now());
		project.setDescription("");
	}



}
