package gui;

import controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.Booking;
import model.Customer;
import model.Seat;
import model.Show;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

public class StatisticsPane extends GridPane {

    private final ListView<Booking> lvwBookings = new ListView<>();
    private final TextArea txaSeats = new TextArea();
    private final DatePicker dprDate = new DatePicker();
    private final TextField txfBookedSeatOnDate = new TextField();
    private final TextField txfSuccesDate = new TextField();
    private final Alert alert = new Alert(Alert.AlertType.ERROR);
    private final TheaterGUI theaterGUI;


    public StatisticsPane(TheaterGUI theaterGUI) {


        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        this.theaterGUI = theaterGUI;


        Label lblShows = new Label("Customer Bookings");
        this.add(lblShows, 0, 0);

        Label lblSeats = new Label("All Customer Seats and Total Price");
        this.add(lblSeats, 3, 0);

        this.add(txaSeats, 3, 1, 2, 1);
        txaSeats.setPrefSize(300, 200);
        txaSeats.setEditable(false);

        this.add(lvwBookings, 0, 1, 3, 1);
        lvwBookings.setPrefSize(300, 200);
        lvwBookings.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> bookedSeatOnDayForShowAction());


        Label lblBookedSeatOnDate = new Label("Number of booked seats");
        this.add(lblBookedSeatOnDate, 0, 3);
        this.add(dprDate, 0, 4);
        this.add(txfBookedSeatOnDate, 0, 5);
        txfBookedSeatOnDate.setEditable(false);

        Label lblSuccesDate = new Label("Succes date:");
        this.add(lblSuccesDate, 1, 3);
        this.add(txfSuccesDate, 1, 4);
        txfSuccesDate.setEditable(false);


        Button btnGetBookings = new Button("Get Bookings");
        this.add(btnGetBookings, 0, 2);
        btnGetBookings.setOnAction(event -> getBookingsAction());

        Button btnBookedSeatsOnDay = new Button("Calculate");
        this.add(btnBookedSeatsOnDay, 0, 6);
        btnBookedSeatsOnDay.setOnAction(event -> bookedSeatsOnDayAction());

        Button btnSuccesDate = new Button("Get date");
        this.add(btnSuccesDate, 1, 5);
        btnSuccesDate.setOnAction(event -> succesDateAction());

    }


    private void getBookingsAction() {
        Show show = theaterGUI.getShowPane().getSelectedShow();
        Customer customer = theaterGUI.getCustomerPane().getSelectedCustomer();

        if (customer == null) {
            showAlert(alert, "Please select a customer", "No customer selected");
        } else if (customer.getBookings().isEmpty()) {
            showAlert(alert, "Customer have no bookings", "No bookings found");
        } else {
            lvwBookings.getItems().setAll(customer.getBookings());
        }

    }


    private void bookedSeatsOnDayAction() {
        Show show = theaterGUI.getShowPane().getSelectedShow();
        LocalDate date = dprDate.getValue();

        if (show == null) {
            showAlert(alert, "Please select a show", "No show selected");
        } else if (date == null) {
            showAlert(alert, "Please select a date", "No date selected");
        } else if (date.isBefore(show.getStartDate())) {
            showAlert(this.alert, "Booking date cannot be before shows start date.", "Invalid date");
        } else if (date.isAfter(show.getEndDate())) {
            showAlert(this.alert, "Booking date cannot be after shows end date.", "Invalid date");
        } else {
            txfBookedSeatOnDate.setText("" + show.getCountOfBookingOnDate(date));
        }
    }

    private void succesDateAction() {
        Show show = theaterGUI.getShowPane().getSelectedShow();

        if (show == null) {
            showAlert(alert, "Please select a show", "No show selected");
        } else if (show.getBookings().isEmpty()) {
            showAlert(alert, "Show doesn't have any bookings", "No bookings");
        } else {
            txfSuccesDate.setText("" + show.getSuccesDate());
        }

    }


    private void bookedSeatOnDayForShowAction() {
        if (getSelectedBooking() != null) {
            Booking booking = getSelectedBooking();
            LocalDate date = booking.getDate();
            Show show = booking.getShow();
            Customer customer = booking.getCustomer();

            txaSeats.setText("");

            StringBuilder sb = new StringBuilder();
            for (Seat seat : customer.getBookedSeatsForShowOnDate(show, date)) {
                sb.append(String.format("%s\n", seat));
            }

            sb.append(String.format("Total price: %s", booking.totalPrice()));

            txaSeats.setText("" + sb);
        }
    }

    private Booking getSelectedBooking() {
        return lvwBookings.getSelectionModel().getSelectedItem();
    }

    private void showAlert(Alert alert, String contextText, String headerText) {
        alert.setHeaderText(headerText);
        alert.setContentText(contextText);
        alert.show();
    }

}
