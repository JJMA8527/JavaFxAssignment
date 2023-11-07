package entities;

import java.time.LocalDate;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class AbstractEntities {
    protected StringProperty name;
    protected ObjectProperty<LocalDate> date;
    protected final StringProperty description;
    protected int id;
    
    public AbstractEntities(String name, LocalDate date, String description) {
        this.name = new SimpleStringProperty(name);
        this.date = new SimpleObjectProperty<>(date);
        this.description = new SimpleStringProperty(description);
        
    }

	//constructor with id
    public AbstractEntities(int id, String name, LocalDate date, String description) {
    	this(name,date,description);
        this.id = id;
        /*this.name = new SimpleStringProperty(name);
        this.date = new SimpleObjectProperty<>(date);
        this.description = new SimpleStringProperty(description);*/
    }
    
    //constructor with no name, date (for comment)
    public AbstractEntities(String description) {
		this.description = new SimpleStringProperty(description);
    }


	public StringProperty nameProperty() {
        return name;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public ObjectProperty<LocalDate> dateProperty() {
        return date;
    }

    public LocalDate getDate() {
        return date.get();
    }

    public void setDate(LocalDate date) {
        this.date.set(date);
    }

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

    public void setId(int id) {
        this.id = id;
    }
}
