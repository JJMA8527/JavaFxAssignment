package layout;

import java.time.LocalDate;
import java.util.ArrayList;

import controller.ProjectController;
import controller.TicketController;
import database.DatabaseConnector;
import database.ProjectDAO;
import entities.Project;
import entities.Ticket;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class TicketLayout implements LayoutInterface {
	private Stage ticketStage;
	private TicketController ticketControl;
	private TextField ticketName;
	private DatePicker date;
	private TextArea descript;
	private Label ticketNameError;
	private ComboBox<Project> selectProject;
	private ComboBox<String> selectType;

	private VBox root;
	private Ticket ticket;

	public TicketLayout(Stage ticketStage,TicketController ticketControl) {
		this.ticketStage = ticketStage;
		this.ticketControl = ticketControl;
		ticket = new Ticket("", LocalDate.now(), "", "Bug");
		errorFields();
		root = new VBox(20);
		GenerateForm();
	}

	@Override
	public void GenerateForm() {
		// TODO Auto-generated method stub
		VBox nameBox = new VBox(5); //spacing for the name field and error message

		root.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
		root.setAlignment(Pos.TOP_LEFT);
		ticketStage.setTitle("Ticket Form");

		Label projectLabel = new Label("Select Project:");
		ComboBox<Project> projectDropdown = ProjectDropdown();

		HBox projectBox = new HBox(10); 
		projectBox.getChildren().addAll(projectLabel, projectDropdown);

		Label title = new Label("Enter Ticket Name: ");
		ticketName = new TextField();

		ComboBox<String> typeDropdown = TypeDropdown();

		HBox ticketBox = new HBox(50); 

		ticketName.setPromptText("Ticket name");
		ticketName.setPrefWidth(500);

		Label dateSelect = new Label("Ticket's Starting Date");
		date = new DatePicker(LocalDate.now());

		Label description = new Label("Description");
		descript = new TextArea();
		descript.setPromptText("Optional");

		ticketName.textProperty().bindBidirectional(ticket.nameProperty());
		date.valueProperty().bindBidirectional(ticket.dateProperty());
		descript.textProperty().bindBidirectional(ticket.descriptionProperty());


		Button saveButton = new Button("Save");
		saveButton.setOnAction(event -> ticketControl.save());

		Button cancelButton = new Button("Cancel");
		cancelButton.setOnAction(event -> ticketControl.cancel()); 
		//horizontal layout for save/cancel buttons
		HBox hbox = new HBox(20);
		hbox.setAlignment(Pos.CENTER);

		ticketBox.getChildren().addAll(ticketName, typeDropdown);
		nameBox.getChildren().addAll(ticketBox,ticketNameError);
		hbox.getChildren().addAll(cancelButton,saveButton);
		root.getChildren().addAll(projectBox,title, nameBox, dateSelect, date, description, descript, hbox);
		root.getStyleClass().add("background");
	}
	private void errorFields() {
		// TODO Auto-generated method stub
		ticketNameError = new Label("Required");
		ticketNameError.setVisible(false);
		ticketNameError.getStyleClass().add("error-label");

	}
	public boolean validateForm() {
		boolean isValid = true;

		if (ticketName.getText().isEmpty()) {
			ticketNameError.setVisible(true);
			ticketName.getStyleClass().add("error-field");
			isValid = false;
		}
		else {
			ticketNameError.setVisible(false);
			ticketName.getStyleClass().removeAll("error-field");
		}

		return isValid;
	}
	//ticket type dropdown
	private ComboBox<String> TypeDropdown(){
		selectType = new ComboBox<>();
		selectType.getItems().addAll("Bug", "Enhancement");
		selectType.setValue("Bug");
		return selectType; 
	}
	//return the value in combobox for ticket type
	public ComboBox<String> getSelectTicketType(){
		return selectType;
	}

	//project selection dropdown
	private ComboBox<Project> ProjectDropdown() {
		selectProject = new ComboBox<>();
		selectProject.setPromptText("Select");
		ProjectDAO projdb = new ProjectDAO();
		ArrayList<Project>projects = projdb.getAll();
		selectProject.getItems().addAll(projects);
		selectProject.setConverter(new StringConverter<Project>() {
			@Override
			public String toString(Project project) {
				return project.getName();
			}
			@Override
			public Project fromString(String string) {
				return null;
			}
		});
		//will check for available projects. disables combobox if none
		if (!projects.isEmpty()) {
			selectProject.getSelectionModel().selectFirst();
		} else {
			selectProject.setDisable(true);
			selectProject.setPromptText("No projects available");
		}

		return selectProject;
	}

	//return project user selected
	public ComboBox<Project> getSelectProject() {
		return selectProject;
	}

	@Override
	public VBox getRoot() {
		return root;
	}

	public Ticket getTicket() {
		return ticket;
	}
	public void clearForm() {
		ticket.setName("");
		ticket.setDate(LocalDate.now());
		ticket.setDescription("");
	}

}
