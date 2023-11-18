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
import layout.DialogSetup;

public class TicketViewTable {
	private TableView<Ticket>table;
	private TicketDAO ticdb;
	private ObservableList<Ticket>tickets;
	private FilteredList<Ticket> filteredTickets;


	public TicketViewTable() {
		table = new TableView<>();
		ticdb = new TicketDAO();
		initializeTable();
	}

	public VBox getTableView() {
		TextField search = new TextField();
		search.setPromptText("Search by ticket name or project name");
		search.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredTickets.setPredicate(ticket -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();
				String projectName = ticket.getProjectName();
				if (ticket.getName().toLowerCase().contains(lowerCaseFilter)) {
					return true;
				}
				else if(projectName.toLowerCase().contains(lowerCaseFilter)) {
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

	public void initializeTable() {
		// TODO Auto-generated method stub
		TableColumn<Ticket, Integer> idCol = new TableColumn<>("ID");
		TableColumn<Ticket,String>nameCol = new TableColumn<>("Name");
		TableColumn<Ticket, String> projectNameCol = new TableColumn<>("Project Name");
		TableColumn<Ticket,String>dateCol = new TableColumn<>("Date");
		TableColumn<Ticket,String>descriptionCol = new TableColumn<>("Description");
		TableColumn<Ticket,String> typeCol = new TableColumn<>("Type");
		TableColumn<Ticket, Void> actionsCol = new TableColumn<>("Actions");
		nameCol.setPrefWidth(200);
		descriptionCol.setPrefWidth(288);

		idCol.setCellValueFactory(
				new PropertyValueFactory<Ticket, Integer>("id") 
				);
		nameCol.setCellValueFactory(
				new PropertyValueFactory<Ticket,String>("Name")
				);
		projectNameCol.setCellValueFactory(
				new PropertyValueFactory<Ticket,String>("projectName")
				);
		dateCol.setCellValueFactory(
				new PropertyValueFactory<Ticket,String>("Date")
				);
		descriptionCol.setCellValueFactory(
				new PropertyValueFactory<Ticket,String>("Description")
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
					boolean confirm = DialogSetup.deleteConfirm();
					if(confirm) {
						ticdb.delete(ticket.getId());
						tickets.remove(ticket);
						table.refresh();
					}
				},
				ticket -> {
					CommentViewTable commentTable = new CommentViewTable(ticket.getId());
					commentTable.display();
				}
				));

		table.getColumns().add(idCol);
		table.getColumns().add(nameCol);
		table.getColumns().add(dateCol);
		table.getColumns().add(descriptionCol);
		table.getColumns().add(projectNameCol);
		table.getColumns().add(typeCol);
		table.getColumns().add(actionsCol);
		tickets = FXCollections.observableArrayList(ticdb.getAll());
		filteredTickets = new FilteredList<>(tickets,p->true);
		table.setPrefSize(700, 700);

		table.setItems(filteredTickets);
		table.refresh();

	}


}
