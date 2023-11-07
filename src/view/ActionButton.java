package view;
/* Set up buttons for the action column in tableview. Serves as a gateway with user's interactions and 
 * execution of defined actions (edit, delete) passed through Consumer interface
 * 
 */
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

		editBtn.setOnAction(event -> handleEdit());
		deleteBtn.setOnAction(event-> handleDelete());
		commentsBtn = null;
	}
	
	//for the ticket table
	public ActionButton(Consumer<T>edit,Consumer<T>delete,Consumer<T>comments) {
		this(edit,delete);
		this.comments = comments;
		
		commentsBtn = new Button("Comments");
		commentsBtn.setOnAction(event->handleComments());
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

	private void handleDelete() {
		// TODO Auto-generated method stub

	}

	private void handleEdit() {

	}
	private void handleComments() {
		// TODO Auto-generated method stub
	    T currentItem = getCurrItem();
        comments.accept(currentItem);
	}
	
	private T getCurrItem() {
		return getTableView().getItems().get(getIndex());
	}

}
