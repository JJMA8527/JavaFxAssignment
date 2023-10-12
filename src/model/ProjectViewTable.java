package model;
import controller.SQLController;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class ProjectViewTable {
	private TableView<Project>table;
	private SQLController sq;
	
	public ProjectViewTable() {
		table = new TableView<>();
		sq = new SQLController();
		
	}
	
	public void displayProjects() {
		Stage display = new Stage();
		display.setTitle("Project List");
		display.setScene(null);
	}
	
	private void createTable() {
		TableColumn<Project,String>nameCol = new TableColumn<>("Project Name");
		TableColumn<Project,String>dateCol = new TableColumn<>("Date");
		TableColumn<Project,String>descriptionCol = new TableColumn<>("Description");
		
		
		
	}
	
	
}
