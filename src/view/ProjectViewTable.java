/* Provides visual representation of the projects in a table form. Users can see recently updated
 * data along with previous ones. 
 * 
 */
package view;


import database.ProjectDAO;
import database.TicketDAO;
import entities.Project;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import layout.DialogSetup;


public class ProjectViewTable {
	private TableView<Project>table;
	private ProjectDAO projdb;
	private ObservableList<Project>projects;
	private FilteredList<Project> filteredProjects;


	public ProjectViewTable() {
		table = new TableView<>();
		projdb = new ProjectDAO();
		initializeTable();
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
				}
				return false;
			});
		});
		VBox vbox = new VBox(20);
		vbox.setPadding(new Insets(20, 50, 10, 50));
		vbox.getStyleClass().add("background");
		vbox.getChildren().addAll(search, table);

		return vbox;
	}

	// Creates a table with the appropriate columns
	private void initializeTable() {
		TableColumn<Project,String>nameCol = new TableColumn<>("Name");
		TableColumn<Project,String>dateCol = new TableColumn<>("Date");
		TableColumn<Project,String>descriptionCol = new TableColumn<>("Description");
		TableColumn<Project, Integer> idCol = new TableColumn<>("ID");
		TableColumn<Project, Void> actionsCol = new TableColumn<>("Actions");
		nameCol.setPrefWidth(200);
		descriptionCol.setPrefWidth(540);
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
		actionsCol.setCellFactory(param -> new ActionButton<>(
				project -> {
					String oldName = project.getName(); // needed to refer to project name col in ticket
					DialogSetup.editDialog(project, updatedProject -> {
						projdb.update(updatedProject);
						TicketDAO ticketdb = new TicketDAO();
						ticketdb.updateProjectNameForTickets(oldName, updatedProject.getName());
						table.refresh();
					});
				},
				project -> {
					boolean confirm = DialogSetup.deleteConfirm();
					if(confirm) {
						projdb.delete(project.getId());
						projects.remove(project); 
						table.refresh();
					}
				}

				));

		table.getColumns().add(idCol);
		table.getColumns().add(nameCol);
		table.getColumns().add(dateCol);
		table.getColumns().add(descriptionCol);
		table.getColumns().add(actionsCol);
		projects = FXCollections.observableArrayList(projdb.getAll());
		filteredProjects = new FilteredList<>(projects, p -> true);
		table.setItems(filteredProjects);
		table.setPrefSize(700, 700);

	}






}
