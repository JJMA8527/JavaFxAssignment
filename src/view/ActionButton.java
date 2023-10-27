package view;

import java.util.function.Consumer;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;


public class ActionButton<T> extends TableCell<T,Void> {
    private final ComboBox<String> actionComboBox;
    private final Consumer<T> edit;
    private final Consumer<T> view;
    private final Consumer<T> delete;
    
    public ActionButton(Consumer<T> view, Consumer<T> edit, Consumer<T> delete) {
        this.edit = edit;
        this.view = view;
        this.delete = delete;

        actionComboBox = new ComboBox<>(FXCollections.observableArrayList("Select", "View", "Edit", "Delete"));
        actionComboBox.getSelectionModel().selectFirst();
        
        setupAction();

    }
    
    @Override
    protected void updateItem(Void item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setGraphic(null);
        } else {
            setGraphic(actionComboBox);
        }
    }
    
    private void setupAction() {
        actionComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                Platform.runLater(() -> {
                    T currentItem = getTableView().getItems().get(getIndex());
                    switch (newValue) {
                        case "View":
                            view.accept(currentItem);
                            break;
                        case "Edit":
                            edit.accept(currentItem);
                            break;
                        case "Delete":
                            delete.accept(currentItem);
                            break;
                    }
                    actionComboBox.getSelectionModel().selectFirst(); // Reset the ComboBox to the default selection
                });
            }
        });
    }
    
}
