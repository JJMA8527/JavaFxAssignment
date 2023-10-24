package entities;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Comment {
    private StringProperty description;
    private int id;
    private int ticketId;
    
	public Comment(String description) {
		this.description = new SimpleStringProperty(description); 
	}
	
	public Comment(int id,int ticketId, String description) {
		this.id = id;
		this.ticketId = ticketId;
		this.description = new SimpleStringProperty(description);
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
    public void setId(int commentId) {
        this.id = commentId;
    }
    
    public int getTicketId() {
    	return ticketId;
    }
    public void setTicketId(int ticketId) {
    	this.ticketId = ticketId;
    }
}

