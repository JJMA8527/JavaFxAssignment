package view;



import database.TicketDAO;
import entities.Ticket;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class TicketViewTable {
	private TableView<Ticket>table;
	private TicketDAO ticdb;
	private int projectId;
	private ObservableList<Ticket>tickets;
	private FilteredList<Ticket> filteredTickets;

	//default constructor. no project id needed
	public TicketViewTable() {
		table = new TableView<>();
		ticdb = new TicketDAO();
		initializeTable();
	}
	//constructor with project id. for specific project 
	public TicketViewTable(int projectId) {
		table = new TableView<>();
		ticdb = new TicketDAO();
		this.projectId = projectId;
		initializeTable();
	}

	public VBox getTableView() {
		TextField search = new TextField();
		search.setPromptText("Search by name");
		search.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredTickets.setPredicate(ticket -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();
				if (ticket.getName().toLowerCase().contains(lowerCaseFilter)) {
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

	public void initializeTable() {
		// TODO Auto-generated method stub
		TableColumn<Ticket, Integer> idCol = new TableColumn<>("ID");
		TableColumn<Ticket,String>nameCol = new TableColumn<>("Name");
		TableColumn<Ticket,String>dateCol = new TableColumn<>("Date");
		TableColumn<Ticket,String>descriptionCol = new TableColumn<>("Description");
		TableColumn<Ticket, Integer> projectIdCol = new TableColumn<>("Project ID");
		TableColumn<Ticket,String> typeCol = new TableColumn<>("Type");
		TableColumn<Ticket, Void> actionsCol = new TableColumn<>("Actions");
		nameCol.setPrefWidth(200);
		descriptionCol.setPrefWidth(288);
		projectIdCol.setPrefWidth(86);
		idCol.setCellValueFactory(
				new PropertyValueFactory<Ticket, Integer>("id") 
				);
		nameCol.setCellValueFactory(
				new PropertyValueFactory<Ticket,String>("Name")
				);
		dateCol.setCellValueFactory(
				new PropertyValueFactory<Ticket,String>("Date")
				);
		descriptionCol.setCellValueFactory(
				new PropertyValueFactory<Ticket,String>("Description")
				);
		projectIdCol.setCellValueFactory(
				new PropertyValueFactory<Ticket,Integer>("projectId")
				);
		typeCol.setCellValueFactory(
				new PropertyValueFactory<Ticket,String>("ticketType")
				);
		actionsCol.setCellFactory(param -> new ActionButton<>(
				ticket -> {
					ticdb.update(ticket);
					table.refresh();
				},
				ticket -> {
					ticdb.delete(ticket.getId());
					table.refresh();
				},
				ticket -> {
					//placeholder for comments
				}
				));

		table.getColumns().add(idCol);
		table.getColumns().add(nameCol);
		table.getColumns().add(dateCol);
		table.getColumns().add(descriptionCol);
		table.getColumns().add(projectIdCol);
		table.getColumns().add(typeCol);
		table.getColumns().add(actionsCol);
		tickets = FXCollections.observableArrayList(ticdb.getAll());
		filteredTickets = new FilteredList<>(tickets,p->true);
		table.setPrefSize(700, 700);

		table.setItems(filteredTickets);


	}


}
