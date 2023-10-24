package layout;

import java.time.LocalDateTime;

import controller.CommentController;
import entities.Comment;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CommentLayout implements LayoutInterface {
	private Stage commentStage;
	private CommentController commentControl;
	private TextArea descript;

	private VBox root;
	private Scene commentScene;
	private Comment comment;
	private Label commentDescriptionError;
	
	public CommentLayout(Stage commentStage, CommentController commentControl) {
		// TODO Auto-generated constructor stub
		this.commentStage = commentStage;
		this.commentControl = commentControl;
	    root = new VBox(20);
	    comment = new Comment("");
	    //GenerateForm();
	    
	}

	@Override
	public Scene getScene() {
		// TODO Auto-generated method stub
		return commentScene;
	}

	@Override
	public void GenerateForm() {
		// TODO Auto-generated method stub
	    root.getChildren().clear();
		commentStage.setTitle("Comment Form");
		
		HBox titleBox = new HBox(20);
		Label title = new Label("Add Comment");
		titleBox.setAlignment(Pos.CENTER);

        VBox descriptionBox = new VBox(5);
        Label description = new Label("Description");
        descript = new TextArea();
        descript.setPromptText("Enter information");
        //Set the field as required. If user hasn't filled in field, error message becomes visible
        commentDescriptionError = new Label("Required");
        commentDescriptionError.setVisible(false);
        commentDescriptionError.getStyleClass().add("error-label");
        
        descript.textProperty().bindBidirectional(comment.descriptionProperty());
        
        HBox timeBox = new HBox(10);
        Label timestamp = new Label("Date-Time");
        TextField timestampField = new TextField();
        timestampField.setText(LocalDateTime.now().toString());
        timestampField.setEditable(false);
        
	    //horizontal layout for save/cancel buttons
	    HBox hbox = new HBox(10);
	    hbox.setAlignment(Pos.CENTER);
        Button saveButton = new Button("Save");
        saveButton.setOnAction(event -> commentControl.save());

        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(event -> commentControl.cancel());
        
        descriptionBox.getChildren().addAll(descript,commentDescriptionError);
        titleBox.getChildren().addAll(title);
        timeBox.getChildren().addAll(timestamp,timestampField); 
	    hbox.getChildren().addAll(cancelButton,saveButton);
        root.getChildren().addAll(titleBox,description,descriptionBox,timeBox,hbox);

	}

	public VBox getRoot() {
		// TODO Auto-generated method stub
		return root;
	}



	public TextArea getCommentDescription() {
		// TODO Auto-generated method stub
		return descript;
	}

	public Label getCommentDescriptionError() {
		// TODO Auto-generated method stub
		return commentDescriptionError;
	}

	public Comment getComment() {
		// TODO Auto-generated method stub
		return comment;
	}
	
	

}
