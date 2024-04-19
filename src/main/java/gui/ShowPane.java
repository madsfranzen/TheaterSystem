package gui;

import controller.Controller;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.StringConverter;
import model.Show;
import storage.Storage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ShowPane extends GridPane {

    private final ListView<Show> lvwShows = new ListView<>();
    private final TextField txfShowName = new TextField();
    private final DatePicker dpcStartDate = new DatePicker();
    private final DatePicker dpcEndDate = new DatePicker();
    private final TheaterGUI theaterGUI;
    private Show selectedShow;

    public ShowPane(TheaterGUI theaterGUI){
        this.theaterGUI = theaterGUI;
        this.setPadding(new Insets(10));
        this.setHgap(10);
        this.setVgap(10);
        this.setGridLinesVisible(false);
        this.setBorder(TheaterGUI.getBorder());

        // Add labels
        int _lblWidth = 100;

        Label lblShow = new Label("Shows");
        lblShow.setPrefWidth(_lblWidth);
        this.add(lblShow,0,0);

        Label lblShowName = new Label("Name:");
        lblShowName.setPrefWidth(_lblWidth);
        this.add(lblShowName,0,2);

        Label lblShowStartDate = new Label("Start Date:");
        lblShowStartDate.setPrefWidth(_lblWidth);
        this.add(lblShowStartDate,0,3);

        Label lblShowEndDate = new Label("End Date:");
        lblShowEndDate.setPrefWidth(_lblWidth);
        this.add(lblShowEndDate,0,4);

        // Add list views
        int _lvwWidht = 350;
        int _lvwHeight = 250;

        lvwShows.setPrefWidth(_lvwWidht);
        lvwShows.setPrefHeight(_lvwHeight);
        lvwShows.setEditable(false);
        this.add(lvwShows,0,1,2,1);
        ChangeListener<Show> listener = (ov, o, n) -> {
            selectedShow = lvwShows.getSelectionModel().getSelectedItem();
            theaterGUI.getSeatPane().updateControls(true);
        };
        lvwShows.getSelectionModel().selectedItemProperty().addListener(listener);

        // Add text fields
        int _txfWidth = 150;

        txfShowName.setPrefWidth(_txfWidth);
        txfShowName.setPromptText("Titel Of The Show");
        this.add(txfShowName,1,2);

        // Add date pickers
        dpcStartDate.setPrefWidth(_txfWidth);
        dpcStartDate.setPromptText("yyyy-MM-dd");
        dpcStartDate.setConverter(theaterGUI.datePickerFormat(dpcStartDate));

        this.add(dpcStartDate,1,3);

        dpcEndDate.setPrefWidth(_txfWidth);
        dpcEndDate.setPromptText("yyyy-MM-dd");
        dpcEndDate.setConverter(theaterGUI.datePickerFormat(dpcEndDate));
        this.add(dpcEndDate,1,4);

        // Add buttons
        Button btnCreateShow = new Button("Create Show");
        this.add(btnCreateShow,1,5);

        // Add button action
        btnCreateShow.setOnAction(actionEvent -> actionCreateShow());

    }

    public void updateControls(){
        // Update list views
        lvwShows.getItems().setAll(Storage.getShows());

        // Clear text fields
        txfShowName.clear();
        dpcStartDate.getEditor().clear();
        dpcEndDate.getEditor().clear();
    }

    private void actionCreateShow(){
        String showName = txfShowName.getText().trim();
        LocalDate startDate = dpcStartDate.getValue();
        LocalDate endDate = dpcEndDate.getValue();
        boolean validCreate = true;

        if (startDate == null || endDate == null){
            validCreate = false;
        }

        if (validCreate && !startDate.isBefore(endDate)){
            validCreate = false;
        }

        if (showName.equalsIgnoreCase("")){
            validCreate = false;
        }

        if (validCreate){
            Controller.createShow(showName,startDate,endDate);
            theaterGUI.updatePaneControls();
        }else {
            theaterGUI.informationDialogue("Create Show - Error In Customer Information", "Please ensure that dates and name information are valid.");
        }
    }

    public Show getSelectedShow() {
        return selectedShow;
    }
}
