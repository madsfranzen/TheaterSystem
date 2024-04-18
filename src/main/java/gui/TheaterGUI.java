package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Customer;
import model.Show;

public class TheaterGUI extends Application {

    @Override
    public void start(Stage stage) throws Exception {


        stage.setTitle("Theater Booking System");
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    private final ListView<Show> lvwShows = new ListView<>();
    private final ListView<Customer> lvwCustomers = new ListView<>();
    private final TextField txfShowName = new TextField();
    private final TextField txfStartDate = new TextField();
    private final TextField txfEndDate = new TextField();
    private final TextField txfCustomerName = new TextField();
    private final TextField txfPhoneNumber = new TextField();
    private ShowPane showPane;
    private CustomerPane customerPane;
    private SeatPane seatPane;

    private void initContent(GridPane mainPain) {
        mainPain.setPadding(new Insets(10));
        mainPain.setHgap(0);
        mainPain.setVgap(10);
        mainPain.setGridLinesVisible(false);

        // Set sub-panes
        showPane = new ShowPane(this);
        customerPane = new CustomerPane(this);
        seatPane = new SeatPane(this);
        // Add sub-panes
        mainPain.add(showPane,0,0);
        mainPain.add(customerPane,1,0);
        mainPain.add(seatPane,2,0);

        // Upadate panes
        updatePaneControls();

    }

    public void updatePaneControls(){
        showPane.updateControls();
        customerPane.updateControls();
        seatPane.updateControls(true);
    }

    public void informationDialogue(String titel, String infoText){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titel);
        alert.setHeaderText(infoText);
        alert.showAndWait();
    }

    public static Border getBorder(){
        BorderStroke borderStroke = new BorderStroke(
                Color.BLACK,
                BorderStrokeStyle.SOLID,
                null,                                    // rounded corners
                new BorderWidths(0,1,0,0)            // top, right, bottom, left
        );

        Border border = new Border(borderStroke);
        return border;
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
