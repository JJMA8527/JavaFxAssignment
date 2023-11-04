package entities;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.time.LocalDate;
import java.util.HashMap;

public class Project extends AbstractEntities {

    // Constructor
    public Project(String name, LocalDate date, String description) {
    	super(name,date,description);
    }
    
    //Constructor with id
    public Project(int id,String name, LocalDate date, String description) {
    	super(id,name,date,description);
    }

 
    

    
}
