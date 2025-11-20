package com.example.mainapp;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


abstract class Task {
    private String title;
    private String category;
    private String status;

    public Task(String title, String category) {
        this.title = title;
        this.category = category;
        this.status = "Pending";
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public abstract String displayTask();
}


class PersonalTask extends Task {
    public PersonalTask(String title) { super(title, "Personal"); }
    @Override
    public String displayTask() { return "[PERSONAL] " + getTitle() + " - Status: " + getStatus(); }
}

class WorkTask extends Task {
    public WorkTask(String title) { super(title, "Work"); }
    @Override
    public String displayTask() { return "[WORK] " + getTitle() + " - Status: " + getStatus(); }
}

class UrgentTask extends Task {
    public UrgentTask(String title) { super(title, "Urgent"); }
    @Override
    public String displayTask() { return "[URGENT] " + getTitle() + " - Status: " + getStatus(); }
}


class TaskManager {
    private List<Task> tasks = new ArrayList<>();

    public void addTask(Task task) { tasks.add(task); }
    public List<Task> getTasks() { return tasks; }
    public void deleteTask(Task task) { tasks.remove(task); }

    public void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("tasks.txt"))) {
            for (Task t : tasks) {
                writer.write(t.getTitle() + "," + t.getCategory() + "," + t.getStatus());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFromFile() {
        File file = new File("tasks.txt");
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 3) continue;
                String title = parts[0];
                String category = parts[1];
                String status = parts[2];

                Task t;
                switch (category) {
                    case "Personal": t = new PersonalTask(title); break;
                    case "Work": t = new WorkTask(title); break;
                    default: t = new UrgentTask(title); break;
                }
                t.setStatus(status);
                tasks.add(t);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// -------------------- MAIN APP --------------------
public class MainApp extends Application {

    private TaskManager taskManager = new TaskManager();
    private ListView<String> taskListView = new ListView<>();

    @Override
    public void start(Stage primaryStage) {
        taskManager.loadFromFile();
        refreshList();

        TextField titleField = new TextField();
        titleField.setPromptText("Enter task title");

        ComboBox<String> categoryBox = new ComboBox<>();
        categoryBox.getItems().addAll("Personal", "Work", "Urgent");
        categoryBox.setValue("Personal");

        Button addButton = new Button("Add Task");
        addButton.setOnAction(e -> addTask(titleField.getText().trim(), categoryBox.getValue()));

        Button completeButton = new Button("Mark Complete");
        completeButton.setOnAction(e -> updateStatus());

        Button deleteButton = new Button("Delete Task");
        deleteButton.setOnAction(e -> deleteTask());

        VBox controls = new VBox(10, titleField, categoryBox, addButton, completeButton, deleteButton);
        controls.setPadding(new Insets(10));

        HBox layout = new HBox(10, controls, taskListView);
        layout.setPadding(new Insets(10));

        Scene scene = new Scene(layout, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("To-Do List App");
        primaryStage.show();
    }

    private void addTask(String title, String category) {
        if (title.isEmpty()) return;

        Task t;
        switch (category) {
            case "Personal": t = new PersonalTask(title); break;
            case "Work": t = new WorkTask(title); break;
            default: t = new UrgentTask(title); break;
        }

        taskManager.addTask(t);
        taskManager.saveToFile();
        refreshList();
    }

    private void updateStatus() {
        int index = taskListView.getSelectionModel().getSelectedIndex();
        if (index == -1) return;

        Task t = taskManager.getTasks().get(index);
        t.setStatus("Complete");

        taskManager.saveToFile();
        refreshList();
    }

    private void deleteTask() {
        int index = taskListView.getSelectionModel().getSelectedIndex();
        if (index == -1) return;

        Task t = taskManager.getTasks().get(index);
        taskManager.deleteTask(t);

        taskManager.saveToFile();
        refreshList();
    }

    private void refreshList() {
        taskListView.getItems().clear();
        for (Task t : taskManager.getTasks()) {
            taskListView.getItems().add(t.displayTask());
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}

