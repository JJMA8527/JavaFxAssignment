package controller;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import layout.HomeLayout;

public abstract class AbstractController {
    protected HomeLayout homeLayout;
 
    public abstract void save();
    
    public void cancel() {
        homeLayout.showHomePage();
    }
    
    protected boolean validField(TextField field, Label errorLabel) {
        if (field.getText().trim().isEmpty()) {
            field.getStyleClass().add("error-field");
            errorLabel.setVisible(true);
            return false;
        } else {
            field.getStyleClass().remove("error-field");
            errorLabel.setVisible(false);
            return true;
        }
    }
    
    protected boolean validField(TextArea area, Label errorLabel) {
        if (area.getText().trim().isEmpty()) {
            area.getStyleClass().add("error-field");
            errorLabel.setVisible(true);
            return false;
        } else {
            area.getStyleClass().remove("error-field");
            errorLabel.setVisible(false);
            return true;
        }
    }
}
