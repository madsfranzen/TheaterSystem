package gui;

import controller.Controller;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Customer;
import model.Show;
import storage.Storage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TheaterGUI extends Application {

    @Override
    public void start(Stage stage) throws Exception {


        stage.setTitle("Academy Xpress");
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

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        // Add labels
        int _lblWidth = 100;

        Label lblShow = new Label("Shows");
        lblShow.setPrefWidth(_lblWidth);
        pane.add(lblShow,0,0);

        Label lblShowName = new Label("Name:");
        lblShowName.setPrefWidth(_lblWidth);
        pane.add(lblShowName,0,2);

        Label lblShowStartDate = new Label("Start Date:");
        lblShowStartDate.setPrefWidth(_lblWidth);
        pane.add(lblShowStartDate,0,3);

        Label lblShowEndDate = new Label("End Date:");
        lblShowEndDate.setPrefWidth(_lblWidth);
        pane.add(lblShowEndDate,0,4);

        Label lblCustomer = new Label("Customers");
        lblCustomer.setPrefWidth(_lblWidth);
        pane.add(lblCustomer,2,0);

        Label lblCustomerName = new Label("Customer Name:");
        lblCustomerName.setPrefWidth(_lblWidth);
        pane.add(lblCustomerName,2,2);

        Label lblCustomerPhoneNumber = new Label("Customer Mobile:");
        lblCustomerPhoneNumber.setPrefWidth(_lblWidth);
        pane.add(lblCustomerPhoneNumber,2,3);

        // Add list views
        int _lvwWidht = 250;
        int _lvwHeight = 250;

        lvwShows.setPrefWidth(_lvwWidht);
        lvwShows.setPrefHeight(_lvwHeight);
        lvwShows.setEditable(false);
        pane.add(lvwShows,0,1,2,1);

        lvwCustomers.setPrefWidth(_lvwWidht);
        lvwCustomers.setPrefHeight(_lvwHeight);
        lvwCustomers.setEditable(false);
        pane.add(lvwCustomers,2,1,2,1);

        // Add text fields
        int _txfWidth = 100;

        txfShowName.setPrefWidth(_txfWidth);
        pane.add(txfShowName,1,2);

        txfStartDate.setPrefWidth(_txfWidth);
        txfStartDate.setPromptText("YYYY-MM-DD");
        pane.add(txfStartDate,1,3);

        txfEndDate.setPrefWidth(_txfWidth);
        txfEndDate.setPromptText("YYYY-MM-DD");
        pane.add(txfEndDate,1,4);

        txfCustomerName.setPrefWidth(_txfWidth);
        pane.add(txfCustomerName,3,2);

        txfPhoneNumber.setPrefWidth(_txfWidth);
        pane.add(txfPhoneNumber,3,3);

        // Add buttons
        Button btnCreateShow = new Button("Create Show");
        pane.add(btnCreateShow,1,5);

        Button btnCreateCustomer = new Button("Create Customer");
        pane.add(btnCreateCustomer,3,4);

        // Button actions
        btnCreateShow.setOnAction(actionEvent -> actionCreateShow());
        btnCreateCustomer.setOnAction(actionEvent -> actionCreateCustomer());

        // Update window
        updateControls();
    }

    private void updateControls(){
        // Update list views
        lvwCustomers.getItems().setAll(Storage.getCustomer());
        lvwShows.getItems().setAll(Storage.getShows());

        // Clear text fields
        txfShowName.clear();
        txfStartDate.clear();
        txfEndDate.clear();
        txfCustomerName.clear();
        txfPhoneNumber.clear();
    }

    private void actionCreateShow(){
        String showName = txfShowName.getText().trim();
        String startDate = txfStartDate.getText().trim();
        String endDate = txfEndDate.getText().trim();
        boolean validDates = true;

        // Validate date info
        try {
            LocalDate startLocalDate = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate endLocalDate = LocalDate.parse(endDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }catch (DateTimeParseException e){
            infomationDialog("Date information","Please make sure that date fields are formatted correctly.");
            validDates = false;
        }

        if (validDates){
            Controller.createShow(showName,LocalDate.parse(startDate),LocalDate.parse(endDate));
            updateControls();
        }
    }

    private void actionCreateCustomer(){
        String customerName = txfCustomerName.getText().trim();
        String customerPhoneNumber = txfPhoneNumber.getText().trim();

        Controller.createCustomer(customerName,customerPhoneNumber);
        updateControls();
    }

    private void infomationDialog(String titel, String infoText){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titel);
        alert.setHeaderText(infoText);
        alert.showAndWait();
    }
}
