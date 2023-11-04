package controller;

import java.time.LocalDate;

import database.TicketDAO;
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

public class TicketController extends AbstractController {
	private TicketDAO ticdb;
	private TicketLayout ticketLayout;
	private Project project;
	

	public TicketController(Stage primaryStage, HomeLayout homeLayout) {
		// TODO Auto-generated constructor stub
		this.homeLayout = homeLayout;
		ticdb = new TicketDAO();
		ticketLayout = new TicketLayout(primaryStage,this);
	}
	
	@Override
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
        String selectedType = ticketLayout.getSelectTicketType().getValue();
        
        ticket.setProjectId(projectId);
        ticket.setTicketType(selectedType);

        int generatedId = ticdb.insert(ticket);
        ticket.setId(generatedId);

        // clears field after saving project
        ticket.setName("");
        ticket.setDate(LocalDate.now());
        ticket.setDescription("");
	}
	
	protected boolean validField() {
	    TextField ticketNameField = ticketLayout.getTicketName();
	    Label requiredField = ticketLayout.getTicketNameError();
	    return validField(ticketNameField, requiredField);
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

	
	public void displayTicketForm() {
	    homeLayout.getRoot().setCenter(ticketLayout.getRoot());
	}


}
