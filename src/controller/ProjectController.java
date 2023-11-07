/* The primary controller for managing Project objects within the application. Establishes a form

 * where user can submit information on a project.
 */
package controller;



import database.ProjectDAO;
import entities.Project;

import javafx.stage.Stage;
import layout.HomeLayout;
import layout.ProjectLayout;


public class ProjectController extends AbstractController {
	private ProjectDAO projdb;
	private ProjectLayout projLayout;
	

	public ProjectController(Stage primaryStage,HomeLayout homeLayout) {
		// TODO Auto-generated constructor stub
		this.homeLayout = homeLayout;
		projdb = new ProjectDAO();
		projLayout = new ProjectLayout(primaryStage, this);
	}

	
    /* If the field is empty, the error message becomes visible. The borders of the box will become
     * red, prompting the user to fill in the field. Otherwise, it removes the message once user fills in
     * field and saves the project. If user fails to enter the name, the save function does not add the project
     * to the database unless user fixes their mistake. 
     * Otherwise, the system saves the project info into the database and clears the fields so the user can enter
     * next project if needed.
     */
	
	@Override
	public void save() {
		Project project = projLayout.getProject();

        if (!projLayout.validateForm()) {
            return;
        }
        
        int generatedId = projdb.insert(project);
        project.setId(generatedId);
	    
	    
	    projLayout.clearForm();
        
	}

	
	public void displayProjectForm() {
        homeLayout.getRoot().setCenter(projLayout.getRoot());

	}
	
	
}
