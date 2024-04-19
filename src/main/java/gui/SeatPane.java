package gui;

import controller.Controller;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.Booking;
import model.Customer;
import model.Seat;
import model.Show;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Optional;

public class SeatPane extends GridPane
{
    private final ListView<Seat> lvwSeats = new ListView<>();
    private final DatePicker dprDate = new DatePicker();
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
        this.add(dprDate,1,2);
        dprDate.setPromptText("DD/MM/YYYY");

        Button btnCreateBooking = new Button("Create Booking");
        this.add(btnCreateBooking,1,3);
        btnCreateBooking.setOnAction(event -> createBookingAction());

        confirmation.setTitle("Create show");
        confirmation.setHeaderText("Are you sure?");

        information.setTitle("Booking confirmation");

    }

    /**
     * Creates a booking given the show, customer, seats and date
     */
    private void createBookingAction()
    {
        Show show = this.showPane.getSelectedShow();
        Customer customer = this.customerPane.getSelectedCustomer();
        ObservableList<Seat> selectedSeats = lvwSeats.getSelectionModel().getSelectedItems();
        ArrayList<Seat> seats = new ArrayList<>(selectedSeats);
        LocalDate date = dprDate.getValue();

        if (show == null )
        {
            showAlert(this.alert, "Please select a show", "No show selected");
        } else if (customer == null)
        {
            showAlert(this.alert, "Please select a customer", "No customer selected");
        } else if (date == null)
        {
            showAlert(this.alert, "Please select a date", "No date selected");
        }
        else if (seats.isEmpty())
        {
            showAlert(this.alert, "Please select seat(s)", "No seat(s) selected");
        }
        else if (date.isBefore(this.showPane.getSelectedShow().getStartDate())) {
            showAlert(this.alert, "Booking date cannot be before shows start date.", "Invalid date");
        }
        else if (date.isAfter(this.showPane.getSelectedShow().getEndDate()))
        {
            showAlert(this.alert, "Booking date cannot be after shows end date.", "Invalid date");
        } else if (Controller.createBookingWithSeats(this.showPane.getSelectedShow(), this.customerPane.getSelectedCustomer(), date, seats) != null)
        {
            Optional<ButtonType> result = confirmation.showAndWait();

            if (result.isPresent() && (result.get() == ButtonType.OK))
            {
                Controller.createBookingWithSeats(this.showPane.getSelectedShow(), this.customerPane.getSelectedCustomer(), date, seats);

                StringBuilder sb = new StringBuilder();
                sb.append(String.format("Show: " + show + "\n") );
                sb.append(String.format("Customer: " + customer + "\n"));
                sb.append(String.format("Date: " + date + "\n\n"));
                sb.append(String.format("Seats below have been booked: \n"));

                for (Seat seat : seats)
                {
                    sb.append(String.format("%s\n", seat));
                }

                showAlert(information, sb.toString(), "Booking confirmation");
                updateControls();
            }
        }
        else
        {
            showAlert(this.alert, "Atleast one of the seats are taken.", "Seats taken");
        }
    }

    /**
     * Resets date
     */
    private void updateControls()
    {
        dprDate.getEditor().clear();
        dprDate.setValue(null);
    }

    private void showAlert(Alert alert, String contextText, String headerText)
    {
        alert.setHeaderText(headerText);
        alert.setContentText(contextText);
        alert.show();
    }
}
