package entities;

import java.time.LocalDate;


import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Ticket extends AbstractEntities {
    private StringProperty ticketType;
    private StringProperty projectName;

    // Constructor with id
    public Ticket(int id,String name,String projectName, LocalDate date, String description,String ticketType) {
    	super(id,name,date,description);
    	this.ticketType = new SimpleStringProperty(ticketType);
    	this.projectName = new SimpleStringProperty(projectName);
    }

    //Constructor
    public Ticket(String name, LocalDate date, String description,String ticketType) {
		// TODO Auto-generated constructor stub
    	super(name,date,description);
    	this.ticketType = new SimpleStringProperty(ticketType);
        this.projectName = new SimpleStringProperty("");
	}

    public String getProjectName() {
        return projectName.get();
    }

    public void setProjectName(String projectName) {
        this.projectName.set(projectName);
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
