package entities;

import java.time.LocalDate;
import java.util.HashMap;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Ticket {
    private int id;
    private StringProperty name;
    private ObjectProperty<LocalDate> date;
    private StringProperty description;
    //private HashMap<Integer,Comment>comments;
    private int projectId;
    private String ticketType;

    // Constructor with id
    public Ticket(int id,int projectId, String name, LocalDate date, String description,String ticketType) {
        this.id = id;
        this.projectId = projectId;
        this.name = new SimpleStringProperty(name);
        this.date = new SimpleObjectProperty<>(date);
        this.description = new SimpleStringProperty(description);
        this.ticketType = ticketType;
    }

    //Constructor
    public Ticket(String name, LocalDate date, String description,String ticketType) {
		// TODO Auto-generated constructor stub
        this.name = new SimpleStringProperty(name);
        this.date = new SimpleObjectProperty<>(date);
        this.description = new SimpleStringProperty(description);
        this.ticketType = ticketType;
	}


	// Name methods
    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }

    // Date methods
    public LocalDate getDate() {
        return date.get();
    }

    public void setDate(LocalDate date) {
        this.date.set(date);
    }

    public ObjectProperty<LocalDate> dateProperty() {
        return date;
    }

    // Description methods
    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public StringProperty descriptionProperty() {
        return description;
    }
    public int getId() {
        return id;
    }
    public void setId(int ticketId) {
        this.id = ticketId;
    }
    
    public int getProjectId() {
    	return projectId;
    }
    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
    
    public String getTicketType() {
    	return ticketType;
    }
    public void setTicketType(String ticketType) {
    	this.ticketType = ticketType;
    }
    
    
}
