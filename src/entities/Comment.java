package entities;

import java.time.LocalDateTime;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Comment extends AbstractEntities {
    private int ticketId;
    private final ObjectProperty<LocalDateTime> timestamp;
    
	public Comment(String description,LocalDateTime timestamp) { 
		super(description);
		this.timestamp = new SimpleObjectProperty<>(timestamp);
	}
	
	public Comment(int id,int ticketId, String description,LocalDateTime timestamp) {
		super(id,null,null,description);
		this.ticketId =  ticketId;
		this.timestamp = new SimpleObjectProperty<>(timestamp);
	}
	

    public LocalDateTime getTimestamp() {
        return timestamp.get();
    }

    public ObjectProperty<LocalDateTime> timestampProperty() {
        return timestamp;
    }
    
    public int getTicketId() {
    	return ticketId;
    }
    public void setTicketId(int ticketId) {
    	this.ticketId = ticketId;
    }
}

