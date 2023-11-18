package view;
/* Set up buttons for the action column in tableview. Serves as a gateway with user's interactions and 
 * execution of defined actions (edit, delete) passed through Consumer interface
 * 
 */
import java.util.function.Consumer;

import entities.AbstractEntities;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.layout.HBox;

public class ActionButton<T> extends TableCell<T,Void> {
	private final Button editBtn;
	private final Button deleteBtn;
	private Button commentsBtn;

    
	public ActionButton(Consumer<T>edit,Consumer<T>delete) {
		editBtn = new Button("Edit");
		deleteBtn = new Button("Delete");

		editBtn.setOnAction(event -> edit.accept(getCurrItem()));
		deleteBtn.setOnAction(event-> delete.accept(getCurrItem()));
		commentsBtn = null;
	}
	
	//for the ticket table
	public ActionButton(Consumer<T>edit,Consumer<T>delete,Consumer<T>comments) {
		this(edit,delete);
		
		commentsBtn = new Button("Comments");
		commentsBtn.setOnAction(event->comments.accept(getCurrItem()));
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

	
	private T getCurrItem() {
		return getTableView().getItems().get(getIndex());
	}

}
