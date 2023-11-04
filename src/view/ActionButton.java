package view;

import java.util.function.Consumer;

import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.layout.HBox;

public class ActionButton<T> extends TableCell<T,Void> {
	private final Button editBtn;
	private final Button deleteBtn;
	private Button commentsBtn;

	private final Consumer<T>edit;
	private final Consumer<T>delete;
    private Consumer<T> comments;
    
	public ActionButton(Consumer<T>edit,Consumer<T>delete) {
		editBtn = new Button("Edit");
		deleteBtn = new Button("Delete");

		this.edit = edit;
		this.delete = delete;

		editBtn.setOnAction(event -> edit());
		deleteBtn.setOnAction(event-> delete());
		commentsBtn = null;
	}
	
	public ActionButton(Consumer<T>edit,Consumer<T>delete,Consumer<T>comments) {
		this(edit,delete);
		this.comments = comments;
		
		commentsBtn = new Button("Comments");
		commentsBtn.setOnAction(event->displayComments());
	}

	@Override
	protected void updateItem(Void item, boolean empty) {
		super.updateItem(item, empty);
		if (empty) {
			setGraphic(null);
		} else {
			HBox hbox = new HBox(5);
			hbox.getChildren().addAll(editBtn,deleteBtn);
            if (commentsBtn != null) {
                hbox.getChildren().add(commentsBtn);
            }
			setGraphic(hbox);
		}
	}

	private void delete() {
		// TODO Auto-generated method stub

	}

	private void edit() {

	}
	private void displayComments() {
		// TODO Auto-generated method stub
	}

}
