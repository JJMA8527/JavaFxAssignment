package layout;

import controller.ProjectController;
import controller.SQLController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.ProjectViewTable;

public class HomeLayout implements LayoutInterface {
    private BorderPane root;
    private Stage homeStage;
    private Scene homeScene;
    
    public HomeLayout(Stage homeStage) {
    	root = new BorderPane();
    	this.homeStage = homeStage;
    	homeScene = new Scene(root,1000,700);
    	GenerateForm();
    	configureTable();
    }

	private void configureTable() {
		// TODO Auto-generated method stub
        SQLController controller = new SQLController();
        controller.createProjectsTable();
	}

	public void GenerateForm() {
		// TODO Auto-generated method stub
        homeScene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        Label homeLabel = new Label("Personal Bug-Tracking System");
        homeLabel.setId("home_title"); //for css 
        //BorderPane.setAlignment(homeLabel, Pos.CENTER);
        StackPane centerContainer = new StackPane();
        centerContainer.getChildren().add(homeLabel);
        centerContainer.getStyleClass().add("background");
        root.setCenter(centerContainer);
        //root.getStyleClass().add("background");
        //root.setCenter(homeLabel);
        //homeLabel.getStyleClass().add("background");
        
        Button newProject = new Button("Project");
        Button newTicket = new Button("Ticket");
        Button displayProjects = new Button("Project List");
        Button displayTickets = new Button("Ticket List");
        
        HBox menu = new HBox(50); 
        menu.getChildren().addAll(newProject, newTicket);
        menu.setAlignment(Pos.CENTER);
        menu.getStyleClass().add("header");
        menu.setPrefHeight(50);
        root.setTop(menu);
        
        VBox list = new VBox(50);
        list.getChildren().addAll(displayProjects,displayTickets);
        list.setAlignment(Pos.CENTER);
        list.getStyleClass().add("side_background");
        list.setPadding(new Insets(50, 50, 50, 50));
        root.setLeft(list);
        
        
        //action performed when newProject button is clicked
        newProject.setOnAction(event -> {
            ProjectController projectControl = new ProjectController(homeStage, homeScene);
            //projectControl.projectCreationForm();
            projectControl.displayProjectForm();
        });
        //action opens new window 
        displayProjects.setOnAction(event ->{
        	ProjectViewTable projTable = new ProjectViewTable();
        	projTable.displayProjects();
        });
	}
	
	
	public BorderPane getRoot() {
		return root;
	}
	
	public Scene getScene() {
		return homeScene;
	}
    
}
