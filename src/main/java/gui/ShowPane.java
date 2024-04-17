package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.Show;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class ShowPane extends GridPane
{

    private final ListView<Show> lvwShows = new ListView<>();
    private final TextField txfName = new TextField();
    private final TextField txfStartDate = new TextField();
    private final TextField txfEndDate = new TextField();
    private final Alert alert = new Alert(Alert.AlertType.ERROR);
    private final Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);

    public ShowPane()
    {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        Label lblShows = new Label("Shows");
        this.add(lblShows,0,0);

        this.add(lvwShows,0,1,2,1);
        lvwShows.setPrefSize(300,200);
        lvwShows.getItems().setAll(Controller.getShows());

        // Name (lbl and textfield)
        Label lblName = new Label("Name:");
        this.add(lblName,0,2);
        this.add(txfName,1,2);

        // Start date (lbl and textfield)
        Label lblStartDate = new Label("Start date:");
        this.add(lblStartDate,0,3);
        this.add(txfStartDate,1,3);
        txfStartDate.setPromptText("YYYY-MM-DD");

        // End date (lbl and textfield)
        Label lblEndDate = new Label("End date:");
        this.add(lblEndDate,0,4);
        this.add(txfEndDate,1,4);
        txfEndDate.setPromptText("YYYY-MM-DD");

        Button btnCreateShow = new Button("Create Show");
        this.add(btnCreateShow,1,5);
        btnCreateShow.setOnAction(event -> createShowAction());

        confirmation.setTitle("Create show");
        confirmation.setHeaderText("Are you sure?");

    }

    public Show getSelectedShow()
    {
        return lvwShows.getSelectionModel().getSelectedItem();
    }

    /*
    * Creates a show given the name, startDate and endDate
    * */
    private void createShowAction() {
        String name = txfName.getText().trim();
        LocalDate startDate;
        LocalDate endDate;

        // Checking if name is empty
        if (name.isEmpty()) {
            alert.setContentText("Please enter a name for the show.");
            alert.show();
            return;
        }

        // Checking if startDate is before todays date and if the format is valid
        try {
            startDate = LocalDate.parse(txfStartDate.getText());
            if (startDate.isBefore(LocalDate.now())) {
                alert.setContentText("Start date cannot be before today's date.");
                alert.show();
                return;
            }
        } catch (DateTimeParseException e) {
            alert.setContentText("Invalid start date format.");
            alert.show();
            return;
        }

        // Checking if endDate is before startDate and if the format is valid
        try {
            endDate = LocalDate.parse(txfEndDate.getText());
            if (endDate.isBefore(startDate)) {
                alert.setContentText("End date cannot be before start date.");
                alert.show();
                return;
            }
        } catch (DateTimeParseException e) {
            alert.setContentText("Invalid end date format.");
            alert.show();
            return;
        }

        // Showing the confirmation alert
        Optional<ButtonType> result = confirmation.showAndWait();

        // If user confirms a new show is created
        if (result.isPresent() && (result.get() == ButtonType.OK))
        {
            Controller.createShow(name, startDate, endDate);
            updateControls();
        }
    }

    /*
    * Updates and selects new show in listview and resets name, startDate, endDate.
    * */
    private void updateControls()
    {
        lvwShows.getItems().setAll(Controller.getShows());
        lvwShows.getSelectionModel().select(Controller.getShows().size() - 1);
        txfName.clear();
        txfStartDate.clear();
        txfEndDate.clear();
    }
}
