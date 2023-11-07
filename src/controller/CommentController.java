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
		Comment comment = commentLayout.getComment();
		ticket = commentLayout.getTicket().getSelectionModel().getSelectedItem();
		
		if(!commentLayout.validateForm()) {
			return;
		}
        
        int ticketId = ticket.getId();
        comment.setTicketId(ticketId);
        
        int generatedId = commentdb.insert(comment);
        comment.setId(generatedId);

        commentLayout.clearForm();

	}

	
	public void displayCommentForm() {
	    homeLayout.getRoot().setCenter(commentLayout.getRoot());
	}
	
}
