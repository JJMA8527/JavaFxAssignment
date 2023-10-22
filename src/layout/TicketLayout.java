package layout;

import java.time.LocalDate;
import java.util.ArrayList;

import controller.ProjectController;
import controller.SQLController;
import controller.TicketController;
import database.ProjectDatabase;
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
	private Label selectProjectError;
	
	private Scene ticketScene;
	private VBox root;
	
	
    public TicketLayout(Stage ticketStage,TicketController ticketControl) {
        this.ticketStage = ticketStage;
        this.ticketControl = ticketControl;
        root = new VBox(20);
        ticketScene = new Scene(root, 1000, 700);
        GenerateForm();
    }


	@Override
	public Scene getScene() {
		// TODO Auto-generated method stub
		return ticketScene;
	}


	@Override
	public void GenerateForm() {
		// TODO Auto-generated method stub
        VBox nameBox = new VBox(5); //spacing for the name field and error message

        ticketScene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        root.setAlignment(Pos.TOP_LEFT);
        ticketStage.setTitle("Ticket Form");
        
        Label projectLabel = new Label("Select Project:");
        ComboBox<Project> projectDropdown = ProjectDropdown();
        
        selectProjectError = new Label("Please choose a project");
        selectProjectError.getStyleClass().add("error-label");
        selectProjectError.setVisible(false);
        
        HBox projectBox = new HBox(10); 
        projectBox.getChildren().addAll(projectLabel, projectDropdown,selectProjectError);
        
        Label title = new Label("Enter Ticket Name: ");
        ticketName = new TextField();
        
        ComboBox<String> typeDropdown = TypeDropdown();
        //selectType.getItems().addAll("Bug", "Enhancement");
        //selectType.setValue("Bug");
        
        HBox ticketBox = new HBox(50); 
        
        //Set the field as required. If user hasn't filled in field, error message becomes visible
        ticketNameError = new Label("Required");
        ticketNameError.setVisible(false);
        ticketNameError.getStyleClass().add("error-label");

        ticketName.setPromptText("Ticket name");
        ticketName.setPrefWidth(500);

        Label dateSelect = new Label("Ticket's Starting Date");
        date = new DatePicker(LocalDate.now());

        Label description = new Label("Description");
        descript = new TextArea();
        descript.setPromptText("Optional");
        

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
		
	}
	
	private ComboBox<String> TypeDropdown(){
		selectType = new ComboBox<>();
        selectType.getItems().addAll("Bug", "Enhancement");
        selectType.setValue("Bug");
        return selectType; 
	}
	public ComboBox<String> getSelectTicketType(){
		return selectType;
	}
	
	private ComboBox<Project> ProjectDropdown() {
		selectProject = new ComboBox<>();
		selectProject.setPromptText("Select");
		//SQLController sqlControl = new SQLController();
		ProjectDatabase projdb = new ProjectDatabase();
		//ArrayList<Project>projects = sqlControl.getProjects();
		ArrayList<Project>projects = projdb.getProjects();
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

	    return selectProject;
	}
	
	public ComboBox<Project> getSelectProject() {
	    return selectProject;
	}
	
	public TextField getTicketName() {
	    return ticketName;
	}

	public Label getTicketNameError() {
	    return ticketNameError;
	}
    public Label getProjectErrorLabel() {
        return selectProjectError;
    }

	public DatePicker getDatePicker() {
	    return date;
	}

	public TextArea getDescriptionArea() {
	    return descript;
	}
}