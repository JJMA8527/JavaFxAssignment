package entities;

import java.time.LocalDate;


import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Ticket extends AbstractEntities {
    private int projectId;
    private StringProperty ticketType;

    // Constructor with id
    public Ticket(int id,int projectId, String name, LocalDate date, String description,String ticketType) {
    	super(id,name,date,description);
    	this.projectId = projectId;
    	this.ticketType = new SimpleStringProperty(ticketType);
    }

    //Constructor
    public Ticket(String name, LocalDate date, String description,String ticketType) {
		// TODO Auto-generated constructor stub
    	super(name,date,description);
    	this.ticketType = new SimpleStringProperty(ticketType);
	}

    
    public int getProjectId() {
    	return projectId;
    }
    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
    
    public String getTicketType() {
        return ticketType.get();
    }
    public void setTicketType(String ticketType) {
        this.ticketType.set(ticketType);
    }
    
    public StringProperty ticketTypeProperty() {
        return ticketType;
    }
}
