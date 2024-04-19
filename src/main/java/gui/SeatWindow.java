package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.*;
import model.Seat;
import model.SeatType;
import model.Show;

import java.time.LocalDate;
import java.util.ArrayList;


public class SeatWindow extends Stage {
    public static TextField txfNumberOfSeat = new TextField();
    public static TextField txfTotalPrice = new TextField();
    private static TheaterGUI theaterGUI;
    private static ArrayList<CheckBox> chbSeatArray;

    GridPane seatPane = new GridPane();
    Scene seatScene = new Scene(seatPane);

    public SeatWindow(TheaterGUI theaterGUI) {
        chbSeatArray = theaterGUI.getChbSeatArray();
        this.theaterGUI = theaterGUI;
        initSeatPaneContent();
    }

    public void initSeatPaneContent() {
        seatPane.setPadding(new Insets(20));
        seatPane.setHgap(10);
        seatPane.setVgap(10);
        seatPane.setGridLinesVisible(false);
        theaterGUI.getDialog().setScene(seatScene);
        theaterGUI.getDialog().initOwner(theaterGUI.getStage());
        theaterGUI.getDialog().setTitle("The Hard Way");
        theaterGUI.getDialog().initModality(Modality.APPLICATION_MODAL);
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
        totalPriceBox.getChildren().setAll(lblNumberOfSeat, txfNumberOfSeat, lblTotalPrice, txfTotalPrice, theaterGUI.getBtnConfirm());
        seatPane.add(totalPriceBox, 0, 20, 20, 1);
        totalPriceBox.setAlignment(Pos.CENTER);
        totalPriceBox.setSpacing(15);

        theaterGUI.getBtnConfirm().setOnAction(event -> theaterGUI.hardWayConfirmBooking());
    }


    public static void paintSeats() {
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

    public static void paintReservedSeats() {
        Show show = (Show) theaterGUI.getLvwShows().getSelectionModel().getSelectedItem();
        LocalDate date = theaterGUI.getDpDateBooking().getValue();
        paintSeats();
        for (int i = 0; i < chbSeatArray.size(); i++) {
            chbSeatArray.get(i).setDisable(false);
            Seat seat = Controller.getSeats().get(i);
            if (!show.isSeatAvailable(seat.getRow(), seat.getNumber(), date)) {
                chbSeatArray.get(i).setDisable(true);
                chbSeatArray.get(i).setSelected(false);
                chbSeatArray.get(i).setStyle("-fx-body-color: white; -fx-inner-border: white;");
            }
        }
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
}
