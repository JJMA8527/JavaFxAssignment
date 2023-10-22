package controller;

import java.time.LocalDate;

import database.TicketDatabase;
import entities.Project;
import entities.Ticket;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import layout.TicketLayout;

public class TicketController {
	private Stage ticketStage; //ticket window
	private SQLController sqlControl;
	private TicketDatabase ticdb;
	private Scene prevScene; //stores the previous window
	private TicketLayout ticketLayout;
	private Project project;
	
	public TicketController(Stage primaryStage, Scene homeScene) {
		prevScene = homeScene;
		//sqlControl = new SQLController();
		ticdb = new TicketDatabase();
		ticketStage = primaryStage;
		ticketLayout = new TicketLayout(ticketStage,this);
	}
	public void save() {
		// TODO Auto-generated method stub
	    project = ticketLayout.getSelectProject().getSelectionModel().getSelectedItem();

	    if (!selectField(ticketLayout.getSelectProject())) {
	        return;
	    }

	    int projectId = project.getId();
	    TextField nameField = ticketLayout.getTicketName();
	    DatePicker dateField = ticketLayout.getDatePicker();
	    TextArea descriptionArea = ticketLayout.getDescriptionArea();
	    String selectedType = ticketLayout.getSelectTicketType().getValue();
	    
	    if(!validField(nameField)) {
	    	return;
	    }
	    
        Ticket ticket = new Ticket(nameField.getText(), dateField.getValue(), descriptionArea.getText(),selectedType);
        ticket.setProjectId(projectId);
	    //int generatedId = sqlControl.insertTicket(ticket);
        int generatedId = ticdb.insertTicket(ticket);
	    ticket.setId(generatedId);
        
        //clears field after saving project
        nameField.clear();
        dateField.setValue(LocalDate.now());
        descriptionArea.clear();
	}

	private boolean validField(TextField nameField) {
		// TODO Auto-generated method stub
		boolean isFilled = true;
	    Label requiredField = ticketLayout.getTicketNameError();
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
	private boolean selectField(ComboBox<Project> project) {
		boolean isFilled = true;
		Label requiredSelect = ticketLayout.getProjectErrorLabel();

	    if (project.getSelectionModel().isEmpty()) {
	        requiredSelect.setVisible(true);
	        isFilled = false;
	    } else {
	        requiredSelect.setVisible(false);
	    }
		return isFilled;
	}
	public void cancel() {
		// TODO Auto-generated method stub
        ticketStage.setScene(prevScene); 
	    ticketStage.setTitle("Home Page");
	}
	
	public void displayTicketForm() {
		ticketStage.setScene(ticketLayout.getScene());
	}
	

}
