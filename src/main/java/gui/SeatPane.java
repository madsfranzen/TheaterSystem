package gui;

import controller.Controller;
import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.Booking;
import model.Customer;
import model.Seat;
import model.Show;
import storage.Storage;

import java.util.Iterator;


import java.time.LocalDate;
import java.util.ArrayList;

public class SeatPane extends GridPane {

    private final TheaterGUI theaterGUI;
    private final ListView<Seat> lvwSeats = new ListView<>();
    private final DatePicker dpcSeatDate = new DatePicker();
    private final ArrayList<Seat> selectedSeats = new ArrayList<>();

    public SeatPane(TheaterGUI theaterGUI) {
        this.theaterGUI = theaterGUI;
        this.setPadding(new Insets(10));
        this.setHgap(10);
        this.setVgap(10);
        this.setGridLinesVisible(false);
        this.setBorder(TheaterGUI.getBorder());

        // Add labels
        int _lblWidth = 50;

        Label lblSeats = new Label("Seats");
        lblSeats.setPrefWidth(_lblWidth);
        this.add(lblSeats, 0, 0);

        Label lblDate = new Label("Date");
        lblDate.setPrefWidth(_lblWidth);
        this.add(lblDate, 0, 2);

        // Add list views and collect selected item(s)
        int _lvwWidht = 350;
        int _lvwHeight = 250;

        lvwSeats.setPrefWidth(_lvwWidht);
        lvwSeats.setPrefHeight(_lvwHeight);
        lvwSeats.setEditable(false);
        lvwSeats.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        this.add(lvwSeats, 0, 1, 2, 1);
        lvwSeats.getSelectionModel().getSelectedItems().addListener((ListChangeListener<Seat>) change -> {
            selectedSeats.clear();
            selectedSeats.addAll(change.getList());
        });

        // Add date pickers
        dpcSeatDate.setPrefWidth(150);
        dpcSeatDate.setPromptText("yyyy-MM-dd");
        dpcSeatDate.setConverter(theaterGUI.datePickerFormat(dpcSeatDate));
        dpcSeatDate.valueProperty().addListener((ov, o, n) -> {
            updateControls(false);
        });
        this.add(dpcSeatDate, 1, 2);

        // Add buttons
        Button btnCreateBookting = new Button("Create Booking");
        this.add(btnCreateBookting, 1, 4);
        Button btnHardWay = new Button("Hard Way");
        this.add(btnHardWay, 1, 3);

        // Add action
        btnCreateBookting.setOnAction(actionEvent -> actionCreateBooking());
        btnHardWay.setOnAction(event -> theaterGUI.btnHardWayAction());
    }

    public void updateControls(boolean clearDatePicker) {
        LocalDate selectedDate = dpcSeatDate.getValue();
        Show selectedShow = theaterGUI.getShowPane().getSelectedShow();

        // Chceck if selections have been done
        if (selectedShow != null && selectedDate != null) {
            // Check if show is active on selected date
            if (selectedShow.isShowActiveOnDate(selectedDate)) {

                // Update list views
                lvwSeats.getItems().setAll(Storage.getSeats());

                // Disable elements in list view
                if (selectedShow != null && selectedDate != null) {
                    ArrayList<Booking> bookings = new ArrayList<>(selectedShow.getBookings());

                    Iterator<Seat> iterator = lvwSeats.getItems().iterator();
                    while (iterator.hasNext()) {
                        Seat s = iterator.next();
                        boolean seatFree = true;
                        for (Booking b : bookings) {
                            if (selectedDate.equals(b.getDate())) {
                                for (Seat bs : b.getSeats()) {
                                    if (bs.equals(s)) {
                                        seatFree = false;
                                    }
                                }
                            }
                        }
                        if (!seatFree) {
                            iterator.remove(); // Sikkert fjernelse af element fra listen
                        }
                    }
                }
            } else {
                lvwSeats.getItems().clear();
            }
        } else {
            lvwSeats.getItems().clear();
        }


        // Clear date field
        if (clearDatePicker) {
            dpcSeatDate.getEditor().clear();
            dpcSeatDate.setValue(null);
        }
    }

    private void actionCreateBooking() {
        boolean validBooking = true;
        LocalDate selectedDate = dpcSeatDate.getValue();
        if (dpcSeatDate.getValue() == null) {
            validBooking = false;
            theaterGUI.informationDialogue("Booking - Date", "Please select a date.");
        }

        Show selectedShow = theaterGUI.getShowPane().getSelectedShow();
        if (selectedShow == null) {
            validBooking = false;
            theaterGUI.informationDialogue("Booking - Date", "Please select a show.");
        }

        Customer selectedCustomer = theaterGUI.getCustomerPane().getSelectedCustomer();
        if (selectedCustomer == null) {
            validBooking = false;
            theaterGUI.informationDialogue("Booking - Date", "Please select a customer.");
        }

        if (selectedSeats.size() == 0) {
            validBooking = false;
            theaterGUI.informationDialogue("Booking - Select booking", "Please ensure that there is selected at least one seat.");
        }
        // Check if show is active on the selected booking date
        if (validBooking && !selectedShow.isShowActiveOnDate(selectedDate)) {
            validBooking = false;
            theaterGUI.informationDialogue("Booking - Booking Date", "Please ensure that the booking date match the dates set for the selected show.");
        }

        // Check if seates are free
        if (validBooking) {
            boolean seatsFree = true;
            for (Seat s : selectedSeats) {
                if (!selectedShow.isSeatAvailable(s.getRow(), s.getNumber(), selectedDate)) {
                    seatsFree = false;
                }
            }

            if (!seatsFree) {
                validBooking = false;
                theaterGUI.informationDialogue("Booking - Seats", "One or more of the selected seats is not free.");
            }
        }


        // Create booking
        if (validBooking) {
            Controller.createBookingWithSeats(selectedShow, selectedCustomer, selectedDate, selectedSeats);

            StringBuilder sb = new StringBuilder();
            sb.append(String.format("Customer: '%s' has now booked seat(s) for the show '%s'.\n\n", selectedCustomer.getName(), selectedShow.getName()));
            sb.append(String.format("Seats below has been booked:\n"));
            for (Seat s : selectedSeats) {
                sb.append(String.format("%s\n", s.toString()));
            }

            theaterGUI.informationDialogue("Booking Confirmation", sb.toString());

            updateControls(true);
        }
    }

    public DatePicker getDpcSeatDate() {
        return dpcSeatDate;
    }

    public ListView<Seat> getLvwSeats() {
        return lvwSeats;
    }

    public ArrayList<Seat> getSelectedSeats() {
        return selectedSeats;
    }

}
