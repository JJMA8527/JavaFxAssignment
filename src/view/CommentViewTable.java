package view;

import database.CommentDAO;
import entities.Comment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CommentViewTable {
	private TableView<Comment>table;
	private CommentDAO commentdb;
	private int ticketId;
	private ObservableList<Comment>comments;
	
	public CommentViewTable() {
		table = new TableView<>();
		commentdb = new CommentDAO();
		initializeTable();
		
	}
	//constructor with ticket id. for specific ticket
	public CommentViewTable(int ticketId) {
		table = new TableView<>();
		commentdb = new CommentDAO();
		this.ticketId = ticketId;
		initializeTable();
	}
	
	private void initializeTable() {
		// TODO Auto-generated method stub
		TableColumn<Comment,Integer>idCol = new TableColumn<>("ID");
		TableColumn<Comment,String>descriptionCol = new TableColumn<>("Description");
		TableColumn<Comment,String>dateCol = new TableColumn<>("Timestamp");
		TableColumn<Comment,Void>actionsCol = new TableColumn<>("Actions");
		
	    idCol.setCellValueFactory(
	            new PropertyValueFactory<Comment, Integer>("id") 
	    );
	    descriptionCol.setCellValueFactory(
	            new PropertyValueFactory<Comment, String>("Description") 
	    );
	    dateCol.setCellValueFactory(
	            new PropertyValueFactory<Comment, String>("Timestamp") 
	    );
		actionsCol.setCellFactory(param -> new ActionButton<>(
				comment -> {
					commentdb.update(comment);
					table.refresh();
				},
				comment -> {
					commentdb.delete(comment.getId());
					table.refresh();
				}
				
		));
	    descriptionCol.setPrefWidth(530);
		table.getColumns().add(idCol);
        table.getColumns().add(descriptionCol);
        table.getColumns().add(dateCol);
        table.getColumns().add(actionsCol);
        comments = FXCollections.observableArrayList(commentdb.getAll(ticketId));
        table.setItems(comments);
	}
	
    public void display() {
        VBox vbox = new VBox();
        vbox.getChildren().add(table);

        Stage stage = new Stage();
        Scene scene = new Scene(vbox,900,500);
        stage.setScene(scene);
        stage.setTitle("Comment History");
        stage.show();
    }
}
