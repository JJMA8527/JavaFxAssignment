package controller;

import database.CommentDAO;
import entities.Comment;
import entities.Ticket;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import layout.CommentLayout;
import layout.HomeLayout;
import layout.TicketLayout;

public class CommentController extends AbstractController {
	   private CommentLayout commentLayout;
	   private Ticket ticket;
	   private CommentDAO commentdb;
	
	public CommentController(Stage primaryStage, HomeLayout homeLayout) {
	    this.homeLayout = homeLayout;
	    commentdb = new CommentDAO();
	    commentLayout = new CommentLayout(primaryStage, this);

	}

	@Override
	public void save() {
        //error display when user didn't enter required field
		Comment comment = commentLayout.getComment();
		//ComboBox<Ticket> selectTicket = commentLayout.getTicket();
		ticket = commentLayout.getTicket().getSelectionModel().getSelectedItem();
		
        if (!validField()) {
            return;
        }
        
        int ticketId = ticket.getId();
        comment.setTicketId(ticketId);
        
        int generatedId = commentdb.insert(comment);
        comment.setId(generatedId);

        comment.setDescription("");
        //selectTicket.setValue(null);

	}
		
	protected boolean validField() {
	    TextArea commentDescriptionField = commentLayout.getCommentDescription();
	    Label requiredField = commentLayout.getCommentDescriptionError();
	    return validField(commentDescriptionField, requiredField);
	}
	

	public void viewCommentsForTicket(int ticketId) {
		
	}
	public void displayCommentForm() {
	    homeLayout.getRoot().setCenter(commentLayout.getRoot());
	}
	
}
