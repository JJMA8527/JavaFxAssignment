package application;
	
import controller.ProjectController;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import model.ProjectViewTable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;



public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene homeScene = new Scene(root,1000,700);

			primaryStage.setTitle("Home Page");
	        Label homeLabel = new Label("Welcome to the Home Page");
	        BorderPane.setAlignment(homeLabel, Pos.CENTER);
	        Button newProject = new Button("Create a new project");
	        Button displayProjects = new Button("Display Projects");
	        root.setTop(homeLabel);
	        root.setCenter(newProject);
	        root.setCenter(displayProjects);
	        
	        //action performed when newProject button is clicked
	        newProject.setOnAction(event -> {
	            ProjectController projectControl = new ProjectController(primaryStage, homeScene);
	            projectControl.projectCreationForm();
	        });
	        //action opens new window 
	        displayProjects.setOnAction(event ->{
	        	ProjectViewTable projTable = new ProjectViewTable();
	        	projTable.displayProjects();
	        });

			primaryStage.setScene(homeScene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
