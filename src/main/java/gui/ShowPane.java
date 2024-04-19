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
    private final DatePicker dprStartDate = new DatePicker();
    private final DatePicker dprEndDate = new DatePicker();
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

        Label lblName = new Label("Name:");
        this.add(lblName,0,2);
        this.add(txfName,1,2);

        Label lblStartDate = new Label("Start date:");
        this.add(lblStartDate,0,3);
        this.add(dprStartDate,1,3);
        dprStartDate.setPromptText("DD/MM/YYYY");

        Label lblEndDate = new Label("End date:");
        this.add(lblEndDate,0,4);
        this.add(dprEndDate,1,4);
        dprEndDate.setPromptText("DD/MM/YYYY");

        Button btnCreateShow = new Button("Create Show");
        this.add(btnCreateShow,1,5);
        btnCreateShow.setOnAction(event -> createShowAction());

        confirmation.setTitle("Create show");
        confirmation.setHeaderText("Are you sure?");

    }

    /**
     * Returns the selected show
     */
    public Show getSelectedShow()
    {
        return lvwShows.getSelectionModel().getSelectedItem();
    }

    /**
    * Creates a show given the name, startDate and endDate
    */
    private void createShowAction() {
        String name = txfName.getText().trim();
        LocalDate startDate = dprStartDate.getValue();
        LocalDate endDate = dprEndDate.getValue();

        // Checking if name is empty
        if (name.isEmpty()) {
            alert.setContentText("Please enter a name for the show.");
            alert.show();
        } else if (startDate == null)
        {
            alert.setContentText("Please select a start date");
            alert.show();
        }
        else if (endDate == null)
        {
            alert.setContentText("Please select a end date");
            alert.show();
        } else if (startDate.isBefore(LocalDate.now())) {
            alert.setContentText("Start date cannot be before today's date.");
            alert.show();
        } else if (endDate.isBefore(startDate)) {
                alert.setContentText("End date cannot be before start date.");
                alert.show();
        } else
        {
            Optional<ButtonType> result = confirmation.showAndWait();

            if (result.isPresent() && (result.get() == ButtonType.OK))
            {
                Controller.createShow(name, startDate, endDate);
                updateControls();
            }
        }
    }

    /**
    * Updates and selects new show in listview and resets name, startDate, endDate.
    */
    private void updateControls()
    {
        lvwShows.getItems().setAll(Controller.getShows());
        lvwShows.getSelectionModel().select(Controller.getShows().size() - 1);
        txfName.clear();
        dprStartDate.getEditor().clear();
        dprEndDate.getEditor().clear();
        dprStartDate.setValue(null);
        dprEndDate.setValue(null);
    }
}
