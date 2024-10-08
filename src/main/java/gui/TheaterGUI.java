package gui;

import controller.Controller;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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

    private Stage stage;
    private Stage dialog = new Stage();

    @Override
    public void start(Stage stage) throws Exception {


        stage.setTitle("Theater Booking System");
        GridPane pane = new GridPane();
        this.initContent(pane);

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
        statisticsPane.setAlignment(Pos.CENTER);

        // Add sub-panes
        mainPain.add(showPane, 0, 0);
        mainPain.add(customerPane, 1, 0);
        mainPain.add(seatPane, 2, 0);
        mainPain.add(statisticsPane, 0, 1, 3, 1);

        // Upadate panes
        updatePaneControls();

    }

    // opdaterer alle skærme efter en ændring
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
                new BorderWidths(0, 1, 1, 0)          // top, right, bottom, left
        );

        Border border = new Border(borderStroke);
        return border;
    }

    // open "the hard way" window
    public void btnHardWayAction() {
        Show show = showPane.getSelectedShow();
        Customer customer = customerPane.getSelectedCustomer();
        LocalDate date = seatPane.getDpcSeatDate().getValue();
        if (show != null && customer != null && date != null) {
            if (date.isAfter(show.getStartDate().minusDays(1)) && date.isBefore(show.getEndDate().plusDays(1))) {
                SeatWindow.paintReservedSeats();
                dialog.showAndWait();
            } else {
                seatPane.getAlertDate().show();
            }
        } else seatPane.getAlertHard().show();
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

}