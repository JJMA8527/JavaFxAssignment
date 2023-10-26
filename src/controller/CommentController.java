package controller;

import entities.Comment;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import layout.CommentLayout;
import layout.HomeLayout;
import layout.TicketLayout;

public class CommentController {
	   private HomeLayout homeLayout;
	   private CommentLayout commentLayout;
	   private TicketLayout ticketLayout;
	   private TicketController ticketController;
	
	public CommentController(Stage primaryStage, HomeLayout homeLayout) {
	    this.homeLayout = homeLayout;
	    commentLayout = new CommentLayout(primaryStage, this);

	}
	public void cancel() {
		homeLayout.showHomePage();

	}
	
	public void save() {
        //error display when user didn't enter required field
		Comment comment = commentLayout.getComment();
		ComboBox selectTicket = commentLayout.getTicket();
		
		
        if (!validField()) {
            return;
        }
        
        comment.setDescription("");
        selectTicket.setValue(null);

	}

    private boolean validField() {
        boolean isFilled = true;
        TextArea commentDescriptionField = commentLayout.getCommentDescription();
        Label requiredField = commentLayout.getCommentDescriptionError();
        
        // Check if the ticketNameField is empty or null.
        if (commentDescriptionField.getText().trim().isEmpty()) {
            commentDescriptionField.getStyleClass().add("error-field");
            requiredField.setText("Required");  // Set the error message.
            requiredField.setVisible(true);
            isFilled = false;
        } else {
            commentDescriptionField.getStyleClass().remove("error-field");
            requiredField.setVisible(false);
        }

        return isFilled;
    }
	public void viewCommentsForTicket(int ticketId) {
		
	}
	public void displayCommentForm() {
	    commentLayout.GenerateForm();
	    homeLayout.getRoot().setCenter(commentLayout.getRoot());
	}
	
}
