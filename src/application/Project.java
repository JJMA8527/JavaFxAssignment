package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.time.LocalDate;

public class Project {

    private final StringProperty name;
    private final ObjectProperty<LocalDate> date;
    private final StringProperty description;

    // Constructor
    public Project(String name, LocalDate date, String description) {
        this.name = new SimpleStringProperty(name);
        this.date = new SimpleObjectProperty<>(date);
        this.description = new SimpleStringProperty(description);
    }

    // Name property
    public StringProperty nameProperty() {
        return name;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    // Date property
    public ObjectProperty<LocalDate> dateProperty() {
        return date;
    }

    public LocalDate getDate() {
        return date.get();
    }

    public void setDate(LocalDate date) {
        this.date.set(date);
    }

    // Description property
    public StringProperty descriptionProperty() {
        return description;
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }
}
