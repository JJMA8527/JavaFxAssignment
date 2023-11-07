package entities;



import java.time.LocalDate;

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
