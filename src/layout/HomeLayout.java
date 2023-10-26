package layout;


import controller.ProjectController;
import controller.TicketController;
import controller.CommentController;
import database.ProjectDatabase;
import database.TicketDatabase;
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
import view.TicketViewTable;

public class HomeLayout implements LayoutInterface {
    private BorderPane root;
    private Stage homeStage;
    private Scene homeScene;
    private StackPane centerContainer;

    
    public HomeLayout(Stage homeStage) {
    	root = new BorderPane();
    	this.homeStage = homeStage;
    	homeScene = new Scene(root,1000,700);
    	GenerateForm();
    	configureTable();
    }

	private void configureTable() {
		// TODO Auto-generated method stub
        //SQLController controller = new SQLController();
        ProjectDatabase projdb = new ProjectDatabase();
        TicketDatabase tickdb = new TicketDatabase();
        projdb.createProjectsTable();
        tickdb.createTicketsTable();
	}

	public void GenerateForm() {
		// TODO Auto-generated method stub
        homeScene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        Label homeLabel = new Label("Personal Bug-Tracking System");
        homeLabel.setId("home_title"); //for css 

        centerContainer = new StackPane();
        centerContainer.getChildren().add(homeLabel);
        centerContainer.getStyleClass().add("background");
        root.setCenter(centerContainer);

        Button homeBtn = new Button("Home");
        Button newProject = new Button("Project");
        Button newTicket = new Button("Ticket");
        Button displayProjects = new Button("Project List");
        Button displayTickets = new Button("Ticket List");
        Button newComment = new Button("Comment");
        
        HBox menu = new HBox(50); 
        menu.getChildren().addAll(homeBtn,newProject, newTicket, newComment);
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
        
        homeBtn.setOnAction(event ->{
        	showHomePage();
        });
        
        //action performed when newProject button is clicked
        newProject.setOnAction(event -> {
            //ProjectController projectControl = new ProjectController(homeStage, homeScene);
            ProjectController projectControl = new ProjectController(homeStage,this);
            projectControl.displayProjectForm();
        });
        newTicket.setOnAction(event ->{
        	//TicketController ticketControl = new TicketController(homeStage, homeScene);
        	TicketController ticketControl = new TicketController(homeStage, this);
        	ticketControl.displayTicketForm();
        });
        newComment.setOnAction(event ->{
        	CommentController commentControl = new CommentController(homeStage, this);
        	commentControl.displayCommentForm();
        });
        //action opens new window 
        displayProjects.setOnAction(event ->{
        	ProjectViewTable projTable = new ProjectViewTable();
        	root.setCenter(projTable.getTableView());
        	homeStage.setTitle("Display Projects");
        });
        
        displayTickets.setOnAction(event ->{
        	TicketViewTable ticketTable = new TicketViewTable();
        	root.setCenter(ticketTable.getTableView());
        	homeStage.setTitle("Display Tickets");
        });


	}
	
	
	public BorderPane getRoot() {
		return root;
	}
	
	public Scene getScene() {
		return homeScene;
	}
	public void showHomePage() {
	    root.setCenter(centerContainer);
	    homeStage.setTitle("Home Page");
	}
	

    
}
