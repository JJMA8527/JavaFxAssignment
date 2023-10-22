package entities;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.time.LocalDate;
import java.util.HashMap;

public class Project {

    private final StringProperty name;
    private final ObjectProperty<LocalDate> date;
    private final StringProperty description;
    private int id;
    //private HashMap <Integer,Ticket>tickets;
    

    // Constructor
    public Project(String name, LocalDate date, String description) {
        this.name = new SimpleStringProperty(name);
        this.date = new SimpleObjectProperty<>(date);
        this.description = new SimpleStringProperty(description);
        //this.tickets = new HashMap<>();
    }
    
    //Constructor with id
    public Project(int id,String name, LocalDate date, String description) {
    	this.id = id;
        this.name = new SimpleStringProperty(name);
        this.date = new SimpleObjectProperty<>(date);
        this.description = new SimpleStringProperty(description);
        //this.tickets = new HashMap<>();
    }

    // Name property
    public StringProperty nameProperty() {
        return name;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    // Date property
    public ObjectProperty<LocalDate> dateProperty() {
        return date;
    }

    public LocalDate getDate() {
        return date.get();
    }

    public void setDate(LocalDate date) {
        this.date.set(date);
    }

    // Description property
    public StringProperty descriptionProperty() {
        return description;
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }
    
    public int getId() {
        return id;
    }
    public void setId(int projectId) {
        this.id = projectId;
    }
    
 /*   public HashMap<Integer,Ticket>getTickets(){
    	return tickets;
    }*/
    
}
