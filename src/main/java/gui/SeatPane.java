package gui;

import controller.Controller;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.Booking;
import model.Customer;
import model.Seat;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Optional;

public class SeatPane extends GridPane
{
    private final ListView<Seat> lvwSeats = new ListView<>();
    private final TextField txfDate = new TextField();
    private final Alert alert = new Alert(Alert.AlertType.ERROR);
    private final Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
    private final Alert information = new Alert(Alert.AlertType.INFORMATION);
    private final ShowPane showPane;
    private final CustomerPane customerPane;

    public SeatPane(ShowPane showPane, CustomerPane customerPane)
    {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        this.showPane = showPane;
        this.customerPane = customerPane;

        Label lblShows = new Label("Seats");
        this.add(lblShows,0,0);

        this.add(lvwSeats,0,1,2,1);
        lvwSeats.setPrefSize(300,200);
        lvwSeats.getItems().setAll(Controller.getSeats());
        lvwSeats.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        Label lblDate = new Label("Date:");
        this.add(lblDate,0,2);
        this.add(txfDate,1,2);
        txfDate.setPromptText("YYYY-MM-DD");

        Button btnCreateBooking = new Button("Create Booking");
        this.add(btnCreateBooking,1,3);
        btnCreateBooking.setOnAction(event -> createBookingAction());

        confirmation.setTitle("Create show");
        confirmation.setHeaderText("Are you sure?");

        information.setTitle("Booking confirmation");

    }

    private void createBookingAction()
    {
        LocalDate date;
        ObservableList<Seat> selectedSeats = lvwSeats.getSelectionModel().getSelectedItems();
        ArrayList<Seat> seats = new ArrayList<>(selectedSeats);

        // Checking if startDate is before todays date and if the format is valid
        try {
            date = LocalDate.parse(txfDate.getText());
            if (date.isBefore(this.showPane.getSelectedShow().getStartDate())) {
                alert.setContentText("Booking date cannot be before shows start date.");
                alert.show();
                return;
            } else if (date.isAfter(this.showPane.getSelectedShow().getEndDate()))
            {
                alert.setContentText("Booking date cannot be after shows end date.");
                alert.show();
                return;
            }
        } catch (DateTimeParseException e) {
            alert.setContentText("Invalid start date format.");
            alert.show();
            return;
        }


        if (Controller.createBookingWithSeats(this.showPane.getSelectedShow(), this.customerPane.getSelectedCustomer(), date, seats) != null)
        {

            // Showing the confirmation alert
            Optional<ButtonType> result = confirmation.showAndWait();

            // If user confirms a new show is created
            if (result.isPresent() && (result.get() == ButtonType.OK))
            {
                Controller.createBookingWithSeats(this.showPane.getSelectedShow(), this.customerPane.getSelectedCustomer(), date, seats);

                information.setHeaderText("" + this.showPane.getSelectedShow());
                information.setContentText(
                        "Customer: " + this.customerPane.getSelectedCustomer() + "\n"
                        + "Seats: \n" + seats
                );
                information.show();

            }
        }
        else
        {
            alert.setContentText("Seats are taken");
            alert.show();
        }
    }
}
