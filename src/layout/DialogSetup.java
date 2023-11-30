package layout;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.function.Consumer;

import entities.Comment;
import entities.Project;
import entities.Ticket;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/* for the edit and delete options in table
 * Use Dialog for edit and Alert for delete
 */
public class DialogSetup {
	private static Label name;
	private static Label date;
	private static Label description;

	private static TextField nameField;
	private static DatePicker dateField;
	private static TextArea descriptionField;

	public static void editDialog(Project project,Consumer<Project>edited) {		
		Dialog<Project> dialog = new Dialog<>();
		dialog.setTitle("Edit Project");

		VBox vbox = new VBox(10);
		nameField = new TextField(project.getName());
		dateField = new DatePicker(project.getDate());
		descriptionField = new TextArea(project.getDescription());

		name = new Label("Project Name");
		date = new Label("Date");
		description = new Label("Description");

		vbox.getChildren().addAll(name,nameField,date,dateField,description,descriptionField);

		dialog.getDialogPane().setContent(vbox);
		dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

		dialog.setResultConverter(dialogBtn -> {
			if (dialogBtn == ButtonType.OK) {
				project.setName(nameField.getText());
				project.setDate(dateField.getValue());
				project.setDescription(descriptionField.getText());
				return project;
			}
			return null;
		});

		Optional<Project> result = dialog.showAndWait();
		result.ifPresent(updatedProject -> {
			edited.accept(updatedProject);
		});

	}

	public static void editDialog(Ticket ticket,Consumer<Ticket>edited) {
		Dialog<Ticket>dialog = new Dialog<>();
		dialog.setTitle("Edit Ticket");

		ComboBox<String>selectType = new ComboBox<>();
		selectType.getItems().addAll("Bug", "Enhancement");
		selectType.setValue(ticket.getTicketType());

		VBox vbox = new VBox(10);
		nameField = new TextField(ticket.getName());
		dateField = new DatePicker(ticket.getDate());
		descriptionField = new TextArea(ticket.getDescription());

		name = new Label("Ticket Name");
		date = new Label("Date");
		description = new Label("Description");

		vbox.getChildren().addAll(name, nameField, date, dateField, selectType, description, descriptionField);

		dialog.getDialogPane().setContent(vbox);
		dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

		dialog.setResultConverter(dialogBtn -> {
			if(dialogBtn==ButtonType.OK) {
				ticket.setName(nameField.getText());
				ticket.setDate(dateField.getValue());
				ticket.setDescription(descriptionField.getText());
				ticket.setTicketType(selectType.getValue());

				return ticket;
			}
			return null;
		});
		Optional<Ticket>result = dialog.showAndWait();
		result.ifPresent(updatedTicket-> {
			edited.accept(updatedTicket);
		});
	}
	public static void editDialog(Comment comment,Consumer<Comment>edited) {
		Dialog<Comment>dialog = new Dialog<>();
		dialog.setTitle("Edit Comment");
		
		VBox vbox = new VBox(10);
		descriptionField = new TextArea(comment.getDescription());
		TextField timestampField = new TextField(LocalDateTime.now().toString());
		
		timestampField.setEditable(false);
		description = new Label("Description");
		Label timestamp = new Label("Updated Timestamp");
		
		vbox.getChildren().addAll(description,descriptionField,timestamp,timestampField);
		
		dialog.getDialogPane().setContent(vbox);
		dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
		
		dialog.setResultConverter(dialogBtn ->{
			if(dialogBtn==ButtonType.OK) {
				comment.setDescription(descriptionField.getText());
				comment.setTimestamp(LocalDateTime.now());
				return comment;
			}
			return null;
		});
		Optional<Comment>result = dialog.showAndWait();
		result.ifPresent(updatedComment-> {
			edited.accept(updatedComment);
		});		
			
	}

	public static boolean deleteConfirm() {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Confirm deletion");

		alert.setContentText("Click OK for deletion");

		Optional<ButtonType> result = alert.showAndWait();
		return result.isPresent() && result.get() == ButtonType.OK;
	}
}
