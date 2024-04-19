package gui;

import controller.Controller;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Booking;
import model.Customer;
import model.Seat;
import model.Show;

import java.time.LocalDate;
import java.util.ArrayList;

public class TheaterGUI extends Application {

    @Override
    public void start(Stage stage) throws Exception {


        stage.setTitle("Theater System");
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }
    private final TextField txfName = new TextField();
    private final TextField txfStartDate = new TextField();
    private final TextField txfEndDate = new TextField();
    private final TextField txfCustomerName = new TextField();
    private final TextField txfCustomerPhoneNumber = new TextField();
    private final TextField txfSeatDate = new TextField();
    private final ListView<Show> lvwShow = new ListView<>();
    private final ListView<Customer> lvwCustomer = new ListView<>();
    private final ListView<Seat> lvwSeat = new ListView<>();
    private final Button btnAddShow = new Button("Add Show");
    private final Button btnAddCustomer = new Button("Add Customer");
    private final Button btnAddBooking = new Button("Add Booking");
    private Alert confirmation;
    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);


        //--------------Show Pane ------------------

        GridPane showPane = new GridPane();
        pane.add(showPane, 0, 0);
        showPane.setGridLinesVisible(false);
        showPane.setPadding(new Insets(10));
        showPane.setHgap(10);
        showPane.setVgap(10);

        GridPane showBotPane = new GridPane();
        showPane.add(showBotPane, 0, 2);
        showBotPane.setGridLinesVisible(false);
        showBotPane.setPadding(new Insets(10));
        showBotPane.setHgap(10);
        showBotPane.setVgap(10);

        Label lblShows = new Label("Shows:");
        Label lblName = new Label("Name:");
        Label lblStartDate = new Label("Start Date:");
        Label lblEndDate = new Label("End Date:");

        showPane.add(lblShows, 0, 0);
        showBotPane.add(lblName, 0, 0);
        showBotPane.add(lblStartDate, 0, 1);
        showBotPane.add(lblEndDate,0,2);

        showPane.add(lvwShow,0,1);
        lvwShow.setEditable(false);
        lvwShow.getItems().addAll(Controller.getShows());

        showBotPane.add(txfName,1,0);
        showBotPane.add(txfStartDate,1,1);
        showBotPane.add(txfEndDate,1,2);

        showBotPane.add(btnAddShow,1,3);
        btnAddShow.setOnAction(Event -> addShowAction());


        //-------------Customer Pane ------------------

        GridPane customerPane = new GridPane();
        pane.add(customerPane, 1, 0);
        customerPane.setGridLinesVisible(false);
        customerPane.setPadding(new Insets(10));
        customerPane.setHgap(10);
        customerPane.setVgap(10);

        GridPane cusBotPane = new GridPane();
        customerPane.add(cusBotPane,0,2);
        cusBotPane.setGridLinesVisible(false);
        cusBotPane.setPadding(new Insets(10));
        cusBotPane.setHgap(10);
        cusBotPane.setVgap(10);

        Label lblCustomers = new Label("Customers:");
        Label lblCustomerName = new Label("Customer Name:");
        Label lblCustomerPhone = new Label("Customer Phone-number:");

        customerPane.add(lblCustomers,0,0);
        cusBotPane.add(lblCustomerName,0,0);
        cusBotPane.add(lblCustomerPhone,0,1);

        customerPane.add(lvwCustomer,0,1);
        lvwCustomer.setEditable(false);
        lvwCustomer.getItems().addAll(Controller.getCustomers());

        cusBotPane.add(txfCustomerName,1,0);
        cusBotPane.add(txfCustomerPhoneNumber,1,1);

        cusBotPane.add(btnAddCustomer,1,2);
        btnAddCustomer.setOnAction(Event -> addCustomerAction());

        // ------------- Seats pane -----------------
        GridPane seatPane = new GridPane();
        pane.add(seatPane, 2, 0);
        seatPane.setGridLinesVisible(false);
        seatPane.setPadding(new Insets(10));
        seatPane.setHgap(10);
        seatPane.setVgap(10);

        GridPane seatbotPane = new GridPane();
        seatPane.add(seatbotPane, 0, 2);
        seatbotPane.setGridLinesVisible(false);
        seatbotPane.setPadding(new Insets(10));
        seatbotPane.setHgap(10);
        seatbotPane.setVgap(10);

        Label lblSeats = new Label("Seats:");
        Label lblDate = new Label("Date:");

        seatPane.add(lblSeats,0,0);
        seatbotPane.add(lblDate,0,0);

        seatPane.add(lvwSeat,0,1);
        lvwSeat.setEditable(false);
        lvwSeat.getItems().setAll(Controller.getSeats());
        lvwSeat.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        seatbotPane.add(txfSeatDate,1,0);

        seatbotPane.add(btnAddBooking,1,1);
        btnAddBooking.setOnAction(Event -> addBookingAction());


    }
    private void addShowAction(){
        String name = "";
        LocalDate startDate = LocalDate.of(1111,11,11);
        LocalDate endDate = LocalDate.of(9999,9,9);
        if (txfName != null){
            name = txfName.getText().trim();
            if(txfStartDate != null){
                startDate = LocalDate.parse(txfStartDate.getText().trim());
                if (txfEndDate != null){
                    endDate = LocalDate.parse(txfEndDate.getText().trim());
                    Controller.createShow(name, startDate, endDate);

                    lvwShow.getItems().setAll(Controller.getShows());
                    txfName.clear();
                    txfStartDate.clear();
                    txfEndDate.clear();
                }
                else{
                    //TODO
                    //Error handling maybe to be done with red labels
                }
            }
            else {
                //TODO
                //Error handling maybe to be done with red labels
            }
        }
        else{
            //TODO
            //Error handling maybe to be done with red labels
        }
    }
    private void addCustomerAction(){
        String name = "";
        String phoneNumber = "";
        if (txfCustomerName != null){
            name = txfCustomerName.getText().trim();
            if(txfCustomerPhoneNumber != null){
                phoneNumber = txfCustomerPhoneNumber.getText().trim();
                Controller.createCustomer(name,phoneNumber);

                lvwCustomer.getItems().setAll(Controller.getCustomers());
                txfCustomerName.clear();
                txfCustomerPhoneNumber.clear();
            }
            else {
                //TODO
                //Error handling maybe to be done with red labels
            }
        }
        else{
            //TODO
            //Error handling maybe to be done with red labels
        }

    }
    private void addBookingAction(){
        Show show = lvwShow.getSelectionModel().getSelectedItem();
        Customer customer = lvwCustomer.getSelectionModel().getSelectedItem();
        LocalDate date = LocalDate.parse(txfSeatDate.getText().trim());
        ArrayList<Seat> seats = new ArrayList<>();

        for (Seat s: lvwSeat.getSelectionModel().getSelectedItems()){
            seats.add(s);
        }
        Booking booking = Controller.createBooking(show,customer,date,seats);
        if (booking != null){
            //succes window
            System.out.println("KORREKT");
            confirmationWindow(booking);

        }
        else{
            System.out.println("FEJL");
            //TODO
            //FejlHåndtering
        }

    }
    public void confirmationWindow(Booking booking){
        confirmation = new Alert(Alert.AlertType.INFORMATION);
        confirmation.setTitle("Booking Confirmation");
        confirmation.setHeaderText("Booking confirmation for " + booking.getShow());
        String str = "Booking done for: " + booking.getCustomer() + "\n \n" + "Seats: \n";
        for (Seat s: booking.getSeats()){
            str += "Row: " + s.getRow() + " Seat Number: " + s.getNumber() + " Type: " + s.getSeatType() + "\n";
        }
        confirmation.setContentText(str);
        confirmation.showAndWait();
    }
}
