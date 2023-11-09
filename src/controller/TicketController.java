package controller;


import database.TicketDAO;
import entities.Project;
import entities.Ticket;

import javafx.stage.Stage;
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

		if(!ticketLayout.validateForm()) {
			return;
		}

 
	    String projectName = project.getName();
		String selectedType = ticketLayout.getSelectTicketType().getValue();

	    ticket.setProjectName(projectName); 
		ticket.setTicketType(selectedType);

		int generatedId = ticdb.insert(ticket);
		ticket.setId(generatedId);

		ticketLayout.clearForm();
	}


	public void displayTicketForm() {
		homeLayout.getRoot().setCenter(ticketLayout.getRoot());
	}


}
