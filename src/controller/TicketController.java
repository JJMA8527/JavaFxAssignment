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
import layout.CommentLayout;
import layout.HomeLayout;
import layout.TicketLayout;

public class TicketController {
	private TicketDatabase ticdb;
	private TicketLayout ticketLayout;
	private Project project;
	private Ticket ticket;
    private HomeLayout homeLayout;
	

	public TicketController(Stage primaryStage, HomeLayout homeLayout) {
		// TODO Auto-generated constructor stub
		this.homeLayout = homeLayout;
		ticdb = new TicketDatabase();
		ticketLayout = new TicketLayout(primaryStage,this);
	}
	public void save() {
		// TODO Auto-generated method stub
	    Ticket ticket = ticketLayout.getTicket();
	    project = ticketLayout.getSelectProject().getSelectionModel().getSelectedItem();

        if (!selectField(ticketLayout.getSelectProject())) {
            return;
        }

        //error display when user didn't enter required field
        if (!validField()) {
            return;
        }
        int projectId = project.getId();
        String ticketName = ticket.getName();
        LocalDate ticketDate = ticket.getDate();
        String ticketDescription = ticket.getDescription();
        String selectedType = ticketLayout.getSelectTicketType().getValue();


        Ticket newTicket = new Ticket(ticketName, ticketDate, ticketDescription, selectedType);
        newTicket.setProjectId(projectId);
        int generatedId = ticdb.insertTicket(newTicket);
        newTicket.setId(generatedId);

        // clears field after saving project
        ticket.setName("");
        ticket.setDate(LocalDate.now());
        ticket.setDescription("");
	}

    private boolean validField() {
        boolean isFilled = true;
        TextField ticketNameField = ticketLayout.getTicketName();
        Label requiredField = ticketLayout.getTicketNameError();
        
        // Check if the ticketNameField is empty or null.
        if (ticketNameField.getText().trim().isEmpty()) {
            ticketNameField.getStyleClass().add("error-field");
            requiredField.setText("Required");  // Set the error message.
            requiredField.setVisible(true);
            isFilled = false;
        } else {
            ticketNameField.getStyleClass().remove("error-field");
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
		
		homeLayout.showHomePage();
	}
	
	public void displayTicketForm() {
	    homeLayout.getRoot().setCenter(ticketLayout.getRoot());
	}


}
