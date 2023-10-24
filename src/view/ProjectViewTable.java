/* Provides visual representation of the projects in a table form. Users can see recently updated
 * data along with previous ones. 
 * 
 */
package view;


import controller.SQLController;
import database.ProjectDatabase;
import entities.Project;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ProjectViewTable {
	private TableView<Project>table;
	private SQLController sq;
	private ProjectDatabase projdb;
	private ObservableList<Project>projects;
	private FilteredList<Project> filteredProjects;
	
	public ProjectViewTable() {
		table = new TableView<>();
		//sq = new SQLController();
		projdb = new ProjectDatabase();
		createTable();
	}
	
	public VBox getTableView() {
		TextField search = new TextField();
		search.setPromptText("Search by name");
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredProjects.setPredicate(project -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (project.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (project.getDescription().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
        VBox vbox = new VBox(20); // 10 is the spacing between the children
        vbox.setPadding(new Insets(20, 50, 10, 50));
        vbox.getStyleClass().add("background");
        vbox.getChildren().addAll(search, table);
        
        return vbox;
	}
	
	// Creates a table with the appropriate columns
	private void createTable() {
		TableColumn<Project,String>nameCol = new TableColumn<>("Name");
		TableColumn<Project,String>dateCol = new TableColumn<>("Date");
		TableColumn<Project,String>descriptionCol = new TableColumn<>("Description");
	    TableColumn<Project, Integer> idCol = new TableColumn<>("ID");
	    nameCol.setPrefWidth(200);
	    descriptionCol.setPrefWidth(375);
	    idCol.setCellValueFactory(
	            new PropertyValueFactory<Project, Integer>("id") 
	    );
		nameCol.setCellValueFactory(
				new PropertyValueFactory<Project,String>("Name")
				);
		dateCol.setCellValueFactory(
				new PropertyValueFactory<Project,String>("Date")
				);
		descriptionCol.setCellValueFactory(
				new PropertyValueFactory<Project,String>("Description")
				);
		table.getColumns().add(idCol);
        table.getColumns().add(nameCol);
        table.getColumns().add(dateCol);
        table.getColumns().add(descriptionCol);
        //projects = FXCollections.observableArrayList(sq.getProjects());
        projects = FXCollections.observableArrayList(projdb.getProjects());
        filteredProjects = new FilteredList<>(projects, p -> true);
        table.setItems(filteredProjects);
        table.setPrefSize(700, 700);
        //table.setItems(projects);
        table.refresh();
    }


		
	
	
}
