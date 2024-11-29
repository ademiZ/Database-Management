package org.example;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private final ObservableList<Employee> employees = FXCollections.observableArrayList();

    @Override
    public void start(Stage stage) {
        // TableView to display employees
        TableView<Employee> tableView = new TableView<>();
        TableColumn<Employee, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getName()));

        TableColumn<Employee, String> typeColumn = new TableColumn<>("Type");
        typeColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getClass().getSimpleName()));

        TableColumn<Employee, String> salaryColumn = new TableColumn<>("Salary");
        salaryColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(
                String.format("%.2f", data.getValue().calculateSalary())
        ));

        tableView.getColumns().addAll(nameColumn, typeColumn, salaryColumn);
        tableView.setItems(employees);

        // Input fields
        TextField nameField = new TextField();
        nameField.setPromptText("Name");

        ComboBox<String> typeBox = new ComboBox<>();
        typeBox.getItems().addAll("FullTime", "PartTime", "Contractor");
        typeBox.setPromptText("Employee Type");

        TextField salaryField = new TextField();
        salaryField.setPromptText("Annual Salary or Hourly Rate");

        TextField hoursField = new TextField();
        hoursField.setPromptText("Hours Worked or Max Hours");

        Button addButton = new Button("Add Employee");
        addButton.setOnAction(e -> {
            String name = nameField.getText();
            String type = typeBox.getValue();
            double salary = Double.parseDouble(salaryField.getText());
            double hours = hoursField.getText().isEmpty() ? 0 : Double.parseDouble(hoursField.getText());

            Employee employee;
            switch (type) {
                case "FullTime" -> employee = new FullTimeEmployee(name, salary);
                case "PartTime" -> employee = new PartTimeEmployee(name, salary, hours);
                case "Contractor" -> employee = new Contractor(name, salary, hours);
                default -> throw new IllegalStateException("Unexpected value: " + type);
            }
            employees.add(employee);

            // Clear fields
            nameField.clear();
            salaryField.clear();
            hoursField.clear();
            typeBox.setValue(null);
        });

        Button removeButton = new Button("Remove Selected");
        removeButton.setOnAction(e -> {
            Employee selectedEmployee = tableView.getSelectionModel().getSelectedItem();
            if (selectedEmployee != null) {
                employees.remove(selectedEmployee);
            }
        });

        // Layout
        GridPane inputGrid = new GridPane();
        inputGrid.setHgap(10);
        inputGrid.setVgap(10);
        inputGrid.add(new Label("Name:"), 0, 0);
        inputGrid.add(nameField, 1, 0);
        inputGrid.add(new Label("Type:"), 0, 1);
        inputGrid.add(typeBox, 1, 1);
        inputGrid.add(new Label("Salary:"), 0, 2);
        inputGrid.add(salaryField, 1, 2);
        inputGrid.add(new Label("Hours:"), 0, 3);
        inputGrid.add(hoursField, 1, 3);

        VBox root = new VBox(10, tableView, inputGrid, addButton, removeButton);

        // Scene and Stage
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Employee Management");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
