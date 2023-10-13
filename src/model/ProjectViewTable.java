/* Provides visual representation of the projects in a table form. Users can see recently updated
 * data along with previous ones. 
 * 
 */
package model;
import application.Project;
import controller.SQLController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ProjectViewTable {
	private TableView<Project>table;
	private SQLController sq;
	
	public ProjectViewTable() {
		table = new TableView<>();
		sq = new SQLController();
		createTable();
	}
	
	public void displayProjects() {
		Stage display = new Stage();
		display.setTitle("Project List");
		Scene tableScene = new Scene(table,500,500);
		
		display.setScene(tableScene);
		display.show();
		
	}
	
	// Creates a table with the appropriate columns
	private void createTable() {
		TableColumn<Project,String>nameCol = new TableColumn<>("Name");
		TableColumn<Project,String>dateCol = new TableColumn<>("Date");
		TableColumn<Project,String>descriptionCol = new TableColumn<>("Description");
		
		nameCol.setCellValueFactory(
				new PropertyValueFactory<Project,String>("Name")
				);
		dateCol.setCellValueFactory(
				new PropertyValueFactory<Project,String>("Date")
				);
		descriptionCol.setCellValueFactory(
				new PropertyValueFactory<Project,String>("Description")
				);
		
        table.getColumns().add(nameCol);
        table.getColumns().add(dateCol);
        table.getColumns().add(descriptionCol);
        ObservableList<Project> projects = FXCollections.observableArrayList(sq.getProjects());
        table.setItems(projects);
    }
		
	
	
}
