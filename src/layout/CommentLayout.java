package layout;

import java.time.LocalDateTime;
import java.util.ArrayList;

import controller.CommentController;
import database.TicketDAO;
import entities.Comment;
import entities.Ticket;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class CommentLayout implements LayoutInterface {
	private Stage commentStage;
	private CommentController commentControl;
	private TextArea descript;

	private VBox root;
	private Comment comment;
	private Label commentDescriptionError;
	private ComboBox<Ticket> selectTicket;


	public CommentLayout(Stage commentStage, CommentController commentControl) {
		// TODO Auto-generated constructor stub
		this.commentStage = commentStage;
		this.commentControl = commentControl;
		root = new VBox(20);
		comment = new Comment("",LocalDateTime.now());
		errorFields();
		GenerateForm();

	}

	@Override
	public void GenerateForm() {
		// TODO Auto-generated method stub
		root.getChildren().clear();
		commentStage.setTitle("Comment Form");

		HBox ticketBox = new HBox(20);
		Label selectTicket = new Label("Select Ticket:");
		ComboBox<Ticket> ticketDropdown = ticketDropdown();	

		VBox descriptionBox = new VBox(5);
		Label description = new Label("Description");
		descript = new TextArea();
		descript.setPromptText("Enter information");
		descript.textProperty().bindBidirectional(comment.descriptionProperty());

		HBox timeBox = new HBox(10);
		Label timestamp = new Label("Date-Time");
		TextField timestampField = new TextField();
		
		timestampField.textProperty().bind(Bindings.createStringBinding(() -> 
		comment.getTimestamp().toString(), comment.timestampProperty())
				);
		timestampField.setEditable(false);
		//horizontal layout for save/cancel buttons
		HBox hbox = new HBox(10);
		hbox.setAlignment(Pos.CENTER);
		Button saveButton = new Button("Save");
		saveButton.setOnAction(event -> commentControl.save());

		Button cancelButton = new Button("Cancel");
		cancelButton.setOnAction(event -> commentControl.cancel());

		descriptionBox.getChildren().addAll(descript,commentDescriptionError);
		ticketBox.getChildren().addAll( selectTicket, ticketDropdown);
		timeBox.getChildren().addAll(timestamp,timestampField); 
		hbox.getChildren().addAll(cancelButton,saveButton);
		root.getChildren().addAll(ticketBox,description,descriptionBox,timeBox,hbox);
		root.getStyleClass().add("background");
	}
	@Override
	public VBox getRoot() {
		// TODO Auto-generated method stub
		return root;
	}

	public Comment getComment() {
		// TODO Auto-generated method stub
		return comment;
	}

	//ticket selection dropdown
	private ComboBox<Ticket> ticketDropdown() {
		selectTicket = new ComboBox<>();
		selectTicket.setPromptText("Select");
		TicketDAO ticketdb = new TicketDAO();
		ArrayList<Ticket>tickets = ticketdb.getAll();
		selectTicket.getItems().addAll(tickets);
		selectTicket.setConverter(new StringConverter<Ticket>() {
			@Override
			public String toString(Ticket ticket) {
				return ticket.getName();
			}
			@Override
			public Ticket fromString(String string) {
				return null;
			}
		});
		if(!tickets.isEmpty()) {
			selectTicket.getSelectionModel().selectFirst();
		}
		else {
			selectTicket.setDisable(true);
			selectTicket.setPromptText("No tickets available");
		}

		return selectTicket;
	}

	//return ticket user selected
	public ComboBox<Ticket> getTicket() {
		return selectTicket;
	}
	private void errorFields() {
		// TODO Auto-generated method stub
		commentDescriptionError = new Label("Required");
		commentDescriptionError.setVisible(false);
		commentDescriptionError.getStyleClass().add("error-label");

	}
	public boolean validateForm() {
		boolean isValid = true;

		if (descript.getText().isEmpty()) {
			commentDescriptionError.setVisible(true);
			descript.getStyleClass().add("error-field");
			isValid = false;
		}
		else {
			commentDescriptionError.setVisible(false);
			descript.getStyleClass().removeAll("error-field");
		}

		return isValid;
	}

	public void clearForm() {
		descript.clear();
	}


}
