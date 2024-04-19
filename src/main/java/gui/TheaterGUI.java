package gui;

import controller.Controller;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class TheaterGUI extends Application {

    private ListView lvwShows = new ListView();
    private ListView lvwCustomers = new ListView();
    private TextField txfShowName = new TextField();
    private DatePicker dpStartDate = new DatePicker();
    private DatePicker dpEndDate = new DatePicker();
    private TextField txfCustomerName = new TextField();
    private TextField txfCustomerPhone = new TextField();
    private Button btnCreateShow = new Button("Create Show");
    private Button btnCreateCustomer = new Button("Create Customer");
    private Label lblSeat = new Label("Seats:");
    private ListView lvwSeats = new ListView();
    private DatePicker dpDateBooking = new DatePicker();
    private Button btnCreateBooking = new Button("Create Booking");
    private Button btnHardWay = new Button("The HARD Way");
    private TextField txfNumberOfSeat = new TextField();
    private TextField txfTotalPrice = new TextField();
    private Button btnConfirm = new Button("Confirm Booking");

    Alert alertHard;
    Alert alertNormal;
    Alert alertDate;
    Alert alertSeatNotFree;
    Alert confirmation;

    Stage dialog = new Stage();
    Stage stage;
    GridPane seatPane = new GridPane();
    Scene seatScene = new Scene(seatPane);

    ArrayList<CheckBox> chbSeatArray = new ArrayList<>();

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Theater Ticket System");
        GridPane pane = new GridPane();
        this.initContent(pane, seatPane);
        initSeatPaneContent(seatPane);

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
        stage.show();
    }

    private void initContent(GridPane pane, GridPane seatPane) {
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        Label lblShows = new Label("Shows:");
        Label lblCustomers = new Label("Customers:");
        pane.add(lblShows, 0, 0);
        pane.add(lblCustomers, 2, 0);

        pane.add(lvwShows, 0, 1, 2, 1);
        pane.add(lvwCustomers, 2, 1, 2, 1);
        lvwShows.setMaxHeight(250);
        lvwCustomers.setMaxHeight(250);

        Label lblShowName = new Label("   Name:");
        Label lblStartDate = new Label("   Start Date:");
        Label lblEndDate = new Label("   End Date");
        pane.add(lblShowName, 0, 3);
        pane.add(lblStartDate, 0, 4);
        pane.add(lblEndDate, 0, 5);

        pane.add(txfShowName, 1, 3);
        pane.add(dpStartDate, 1, 4);
        pane.add(dpEndDate, 1, 5);
        dpStartDate.setFocusTraversable(false);
        dpEndDate.setFocusTraversable(false);

        Label lblCustomerName = new Label("   Customer Name:");
        Label lblCustomerPhone = new Label("   Phone Number:");
        pane.add(lblCustomerName, 2, 3);
        pane.add(lblCustomerPhone, 2, 4);
        pane.add(txfCustomerName, 3, 3);
        pane.add(txfCustomerPhone, 3, 4);

        pane.add(btnCreateShow, 1, 6);
        pane.add(btnCreateCustomer, 3, 5);
        btnCreateShow.setOnAction(event -> btnCreateShowAction());
        btnCreateCustomer.setOnAction(event -> btnCreateCustomerAction());

        pane.add(lblSeat, 5, 0);
        pane.add(lvwSeats, 5, 1, 2, 1);
        lvwSeats.setMaxHeight(250);
        lvwSeats.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        Label lblDateBooking = new Label("   Date:");
        pane.add(lblDateBooking, 5, 3);
        pane.add(dpDateBooking, 6, 3);
        dpDateBooking.setFocusTraversable(false);
        pane.add(btnHardWay, 6, 4);
        pane.add(btnCreateBooking, 6, 5);

        btnCreateBooking.setOnAction(event -> btnConfirmBookingAction());
        btnHardWay.setOnAction(event -> btnHardWayAction());

        updateContent();
    }

    public void initSeatPaneContent(GridPane seatPane) {
        seatPane.setPadding(new Insets(20));
        seatPane.setHgap(10);
        seatPane.setVgap(10);
        seatPane.setGridLinesVisible(false);
        dialog.setScene(seatScene);
        dialog.initOwner(stage);
        dialog.setTitle("The Hard Way");
        dialog.initModality(Modality.APPLICATION_MODAL);
        HBox topBox = new HBox();
        Label lblHardWay = new Label("---  STAGE  ---      ");
        lblHardWay.setFont(new Font(20));
        topBox.getChildren().setAll(lblHardWay);
        topBox.setAlignment(Pos.CENTER);
        seatPane.add(topBox, 0, 0, 20, 1);

        // init seats
        int col = 1;
        int row = 1;
        for (int i = 0; i < Controller.getSeats().size(); i++) {
            if (i % 20 == 0) {
                row++;
                col = 0;
            }
            CheckBox chbSeat = new CheckBox();
            chbSeat.setFocusTraversable(false);
            chbSeat.setOnAction(event -> updateTotalPrice());
            seatPane.add(chbSeat, col, row);
            chbSeatArray.add(chbSeat);
            col++;
        }

        paintSeats();

        CheckBox chbBluePrice = new CheckBox("$400");
        chbBluePrice.setStyle("-fx-body-color: blue; -fx-inner-border: blue;");
        chbBluePrice.setMouseTransparent(true);
        chbBluePrice.setFocusTraversable(false);
        CheckBox chbGreenPrice = new CheckBox("$450");
        chbGreenPrice.setStyle("-fx-body-color: green; -fx-inner-border: green;");
        chbGreenPrice.setMouseTransparent(true);
        chbGreenPrice.setFocusTraversable(false);
        CheckBox chbYellowPrice = new CheckBox("$500");
        chbYellowPrice.setStyle("-fx-body-color: yellow; -fx-inner-border: yellow;");
        chbYellowPrice.setMouseTransparent(true);
        chbYellowPrice.setFocusTraversable(false);
        CheckBox chbExtraSpace = new CheckBox("Extra Space");
        chbExtraSpace.setStyle("-fx-body-color: lightblue; -fx-inner-border: blue;");
        chbExtraSpace.setMouseTransparent(true);
        chbExtraSpace.setFocusTraversable(false);
        CheckBox chbWheelchair = new CheckBox("Wheelchair");
        chbWheelchair.setStyle("-fx-body-color: lightgreen; -fx-inner-border: green;");
        chbWheelchair.setMouseTransparent(true);
        chbWheelchair.setFocusTraversable(false);

        HBox colourBox = new HBox();
        HBox disabilityBox = new HBox();
        colourBox.getChildren().setAll(chbBluePrice, chbGreenPrice, chbYellowPrice);
        disabilityBox.getChildren().setAll(chbExtraSpace, chbWheelchair);
        colourBox.setAlignment(Pos.CENTER);
        colourBox.setSpacing(20);
        disabilityBox.setSpacing(20);
        disabilityBox.setAlignment(Pos.CENTER);
        seatPane.add(colourBox, 0, 17, 20, 1);
        seatPane.add(disabilityBox, 0, 18, 20, 1);

        HBox totalPriceBox = new HBox();
        Label lblNumberOfSeat = new Label("Number of Seats:");
        Label lblTotalPrice = new Label("Total Price:");
        txfNumberOfSeat.setMaxWidth(50);
        txfNumberOfSeat.setEditable(false);
        txfNumberOfSeat.setFocusTraversable(false);
        txfNumberOfSeat.setMouseTransparent(true);
        txfTotalPrice.setMaxWidth(100);
        txfTotalPrice.setEditable(false);
        txfTotalPrice.setFocusTraversable(false);
        txfTotalPrice.setMouseTransparent(true);
        totalPriceBox.getChildren().setAll(lblNumberOfSeat, txfNumberOfSeat, lblTotalPrice, txfTotalPrice, btnConfirm);
        seatPane.add(totalPriceBox, 0, 20, 20, 1);
        totalPriceBox.setAlignment(Pos.CENTER);
        totalPriceBox.setSpacing(15);

        btnConfirm.setOnAction(event -> hardWayConfirmBooking());
    }

    private void paintSeats() {
        for (int i = 0; i < chbSeatArray.size(); i++) {
            CheckBox chb = chbSeatArray.get(i);
            Seat seat = Controller.getSeats().get(i);
            chb.setSelected(false);
            switch (seat.getPrice()) {
                case 500 -> chb.setStyle("-fx-body-color: yellow; -fx-inner-border: yellow;");
                case 450 -> chb.setStyle("-fx-body-color: green; -fx-inner-border: green;");
                case 400 -> chb.setStyle("-fx-body-color: blue; -fx-inner-border: blue;");
            }
            if (seat.getSeatType() == SeatType.EXTRASPACE) {
                chb.setStyle("-fx-body-color: lightblue; -fx-inner-border: blue;");
            }
            if (seat.getSeatType() == SeatType.WHEELCHAIR) {
                chb.setStyle("-fx-body-color: lightgreen; -fx-inner-border: green;");
            }
        }
    }

    public void updateContent() {
        lvwShows.getItems().setAll(Controller.getShows());
        lvwCustomers.getItems().setAll(Controller.getCustomers());
        lvwSeats.getItems().setAll(Controller.getSeats());
        txfShowName.clear();
        txfCustomerName.clear();
        txfCustomerPhone.clear();
        dpStartDate.setValue(null);
        dpEndDate.setValue(null);
        dpDateBooking.setValue(null);
    }

    public void updateTotalPrice() {
        int totalPrice = 0;
        int numberOfSeats = 0;
        for (int i = 0; i < chbSeatArray.size(); i++) {
            if (chbSeatArray.get(i).isSelected()) {
                totalPrice += Controller.getSeats().get(i).getPrice();
                numberOfSeats++;
            }
        }
        txfNumberOfSeat.setText(String.valueOf(numberOfSeats));
        txfTotalPrice.setText("$" + String.valueOf(totalPrice));
    }

    public void btnCreateShowAction() {
        String name = txfShowName.getText().trim();
        LocalDate startDate = dpStartDate.getValue();
        LocalDate endDate = dpEndDate.getValue();
        if (!name.isEmpty() && startDate != null && endDate != null) {
            if (startDate.isBefore(endDate.plusDays(1))) {
                Controller.createShow(name, startDate, endDate);
                updateContent();
            }
        }
    }

    public void btnCreateCustomerAction() {
        String name = txfCustomerName.getText().trim();
        String phoneNumber = txfCustomerPhone.getText().trim();
        if (!name.isEmpty() && !phoneNumber.isEmpty()) {
            Controller.createCustomer(name, phoneNumber);
            updateContent();
        }
    }

    public void btnHardWayAction() {
        Show show = (Show) lvwShows.getSelectionModel().getSelectedItem();
        Customer customer = (Customer) lvwCustomers.getSelectionModel().getSelectedItem();
        LocalDate date = dpDateBooking.getValue();
        if (show != null && customer != null && date != null) {
            if (date.isAfter(show.getStartDate().minusDays(1)) && date.isBefore(show.getEndDate().plusDays(1))) {
                paintReservedSeats();
                dialog.showAndWait();
            } else {
                alertDate.show();
            }
        } else alertHard.show();
    }

    public void btnConfirmBookingAction() {
        Show show = (Show) lvwShows.getSelectionModel().getSelectedItem();
        Customer customer = (Customer) lvwCustomers.getSelectionModel().getSelectedItem();
        LocalDate date = dpDateBooking.getValue();
        ArrayList<Seat> seats = new ArrayList<>();
        seats.addAll(lvwSeats.getSelectionModel().getSelectedItems());
        Seat seat = (Seat) lvwSeats.getSelectionModel().getSelectedItem();
        if (show != null && customer != null && seats.size() != 0 && date != null) {
            if (!date.isAfter(show.getStartDate().minusDays(1)) || !date.isBefore(show.getEndDate().plusDays(1))) {
                alertDate.show();
            } else if (show.isSeatFree(seat.getRow(), seat.getNumber(), date)) {
                Booking booking = Controller.createBookingWithSeats(show, customer, date, seats);
                showConfirmationWindow(booking);
            } else alertSeatNotFree.show();
        } else alertNormal.show();
    }

    public void hardWayConfirmBooking() {
        Show show = (Show) lvwShows.getSelectionModel().getSelectedItem();
        Customer customer = (Customer) lvwCustomers.getSelectionModel().getSelectedItem();
        LocalDate date = dpDateBooking.getValue();
        ArrayList<Seat> seats = new ArrayList<>();
        for (int i = 0; i < chbSeatArray.size(); i++) {
            if (chbSeatArray.get(i).isSelected()) {
                seats.add(Controller.getSeats().get(i));
            }
        }
        if (seats.size() > 0) {
            Booking booking = Controller.createBookingWithSeats(show, customer, date, seats);
            showConfirmationWindow(booking);
            dialog.hide();
        }
    }

    public void paintReservedSeats() {
        Show show = (Show) lvwShows.getSelectionModel().getSelectedItem();
        LocalDate date = dpDateBooking.getValue();
        paintSeats();
        for (int i = 0; i < chbSeatArray.size(); i++) {
            chbSeatArray.get(i).setDisable(false);
            Seat seat = Controller.getSeats().get(i);
            if (!show.isSeatFree(seat.getRow(), seat.getNumber(), date)) {
                chbSeatArray.get(i).setDisable(true);
                chbSeatArray.get(i).setSelected(false);
                chbSeatArray.get(i).setStyle("-fx-body-color: white; -fx-inner-border: white;");
            }
        }
    }

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
}
