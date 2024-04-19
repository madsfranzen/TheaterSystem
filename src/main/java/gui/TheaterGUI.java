package gui;

import controller.Controller;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import model.Booking;
import model.Customer;
import model.Seat;
import model.Show;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TheaterGUI extends Application {

    private ShowPane showPane;
    private CustomerPane customerPane;
    private SeatPane seatPane;
    private StatisticsPane statisticsPane;

    private Alert alertHard;
    private Alert alertNormal;
    private Alert alertDate;
    private Alert alertSeatNotFree;
    private Alert confirmation;

    private Stage stage;
    private Stage dialog = new Stage();


    @Override
    public void start(Stage stage) throws Exception {


        stage.setTitle("Theater Booking System");
        GridPane pane = new GridPane();
        this.initContent(pane);

        alertHard = new Alert(Alert.AlertType.ERROR);
        alertHard.setTitle("Error");
        alertHard.setHeaderText("Please select Show, Customer and Date before picking seats.");

        alertNormal = new Alert(Alert.AlertType.ERROR);
        alertNormal.setTitle("Error");
        alertNormal.setHeaderText("Please select Show, Customer, Date and Seat.");

        alertDate = new Alert(Alert.AlertType.ERROR);
        alertDate.setTitle("Error");
        alertDate.setHeaderText("Date must be between start and end date of show.");

        alertSeatNotFree = new Alert(Alert.AlertType.ERROR);
        alertSeatNotFree.setTitle("Error");
        alertSeatNotFree.setHeaderText("This seat has already been reserved.");


        Scene scene = new Scene(pane);
        stage.setScene(scene);

        scene.setOnMouseClicked(event -> {
            if (seatPane.getLvwSeats().equals(event.getSource())) {
                showPane.getLvwShows().getParent().requestFocus();
            }
        });

        SeatWindow seatWindow = new SeatWindow(this);

        stage.show();
    }


    private void initContent(GridPane mainPain) {
        mainPain.setPadding(new Insets(10));
        mainPain.setHgap(0);
        mainPain.setVgap(10);
        mainPain.setGridLinesVisible(false);

        // Set sub-panes
        showPane = new ShowPane(this);
        customerPane = new CustomerPane(this);
        seatPane = new SeatPane(this);
        statisticsPane = new StatisticsPane(this);
        // Add sub-panes
        mainPain.add(showPane, 0, 0);
        mainPain.add(customerPane, 1, 0);
        mainPain.add(seatPane, 2, 0);
        mainPain.add(statisticsPane, 3, 0);

        // Upadate panes
        updatePaneControls();

    }

    public void updatePaneControls() {
        showPane.updateControls();
        customerPane.updateControls();
        seatPane.updateControls(true);
    }

    public void informationDialogue(String titel, String infoText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titel);
        alert.setHeaderText(infoText);
        alert.showAndWait();
    }

    public static Border getBorder() {
        BorderStroke borderStroke = new BorderStroke(
                Color.BLACK,
                BorderStrokeStyle.SOLID,
                null,                                    // rounded corners
                new BorderWidths(0, 1, 0, 0)            // top, right, bottom, left
        );

        Border border = new Border(borderStroke);
        return border;
    }


//    public void updateContent() {
//        lvwShows.getItems().setAll(Controller.getShows());
//        lvwCustomers.getItems().setAll(Controller.getCustomers());
//        lvwSeats.getItems().setAll(Controller.getSeats());
//        txfShowName.clear();
//        txfCustomerName.clear();
//        txfCustomerPhone.clear();
//        dpStartDate.setValue(null);
//        dpEndDate.setValue(null);
//        dpDateBooking.setValue(null);
//    }

//    public void btnCreateShowAction() {
//        String name = txfShowName.getText().trim();
//        LocalDate startDate = dpStartDate.getValue();
//        LocalDate endDate = dpEndDate.getValue();
//        if (!name.isEmpty() && startDate != null && endDate != null) {
//            if (startDate.isBefore(endDate.plusDays(1))) {
//                Controller.createShow(name, startDate, endDate);
//                updateContent();
//            }
//        }
//    }

//    public void btnCreateCustomerAction() {
//        String name = txfCustomerName.getText().trim();
//        String phoneNumber = txfCustomerPhone.getText().trim();
//        if (!name.isEmpty() && !phoneNumber.isEmpty()) {
//            Controller.createCustomer(name, phoneNumber);
//            updateContent();
//        }
//    }

    public void btnHardWayAction() {
        Show show = showPane.getSelectedShow();
        Customer customer = customerPane.getSelectedCustomer();
        LocalDate date = seatPane.getDpcSeatDate().getValue();
        if (show != null && customer != null && date != null) {
            if (date.isAfter(show.getStartDate().minusDays(1)) && date.isBefore(show.getEndDate().plusDays(1))) {
                SeatWindow.paintReservedSeats();
                dialog.showAndWait();
            } else {
                alertDate.show();
            }
        } else alertHard.show();
    }

//    public void btnConfirmBookingAction() {
//        Show show = showPane.getSelectedShow();
//        Customer customer = customerPane.getSelectedCustomer();
//        LocalDate date = dpDateBooking.getValue();
//        ArrayList<Seat> seats = new ArrayList<>();
//        seats.addAll(lvwSeats.getSelectionModel().getSelectedItems());
//        Seat seat = (Seat) lvwSeats.getSelectionModel().getSelectedItem();
//        if (show != null && customer != null && seats.size() != 0 && date != null) {
//            if (!date.isAfter(show.getStartDate().minusDays(1)) || !date.isBefore(show.getEndDate().plusDays(1))) {
//                alertDate.show();
//            } else if (show.isSeatAvailable(seat.getRow(), seat.getNumber(), date)) {
//                Booking booking = Controller.createBookingWithSeats(show, customer, date, seats);
//                showConfirmationWindow(booking);
//            } else alertSeatNotFree.show();
//        } else alertNormal.show();
//    }


    public void showConfirmationWindow(Booking booking) {
        confirmation = new Alert(Alert.AlertType.INFORMATION);
        confirmation.setTitle("Confirmation");
        confirmation.setHeaderText(" Booking for " + booking.getShowName() + " confirmed.");
        String seatsString = "";
        for (Seat seat : booking.getSeats()) {
            seatsString += "Row:  " + seat.getRow() + "   ";
            seatsString += "Nr.:  " + seat.getNumber() + "   ";
            seatsString += seat.getSeatType();
            seatsString += "\n";
        }
        confirmation.setContentText("Your tickets for " + booking.getDate()
                + " with seats: \n" + seatsString + "\n\n"
                + "have been reserved for you, \n" + booking.getCustomer());
        confirmation.showAndWait();
    }

    public Stage getStage() {
        return stage;
    }

    public Stage getDialog() {
        return dialog;
    }


    public CustomerPane getCustomerPane() {
        return customerPane;
    }

    public ShowPane getShowPane() {
        return showPane;
    }

    public SeatPane getSeatPane() {
        return seatPane;
    }

    public StringConverter datePickerFormat(DatePicker dpcStartDate) {
        StringConverter datePickerFormat = new StringConverter<LocalDate>() {
            String pattern = "yyyy-MM-dd";
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

            {
                dpcStartDate.setPromptText(pattern.toLowerCase());
            }

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        };
        return datePickerFormat;
    }

}