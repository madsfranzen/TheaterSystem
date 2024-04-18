package gui;

import controller.Controller;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import model.Customer;
import model.Show;
import storage.Storage;

public class CustomerPane extends GridPane {

    private final ListView<Customer> lvwCustomers = new ListView<>();
    private final TextField txfCustomerName = new TextField();
    private final TextField txfPhoneNumber = new TextField();
    private final TheaterGUI theaterGUI;
    private Customer selectedCustomer;

    public CustomerPane(TheaterGUI theaterGUI){
        this.theaterGUI = theaterGUI;
        this.setPadding(new Insets(10));
        this.setHgap(10);
        this.setVgap(10);
        this.setGridLinesVisible(false);
        this.setBorder(TheaterGUI.getBorder());

        // Add labels
        int _lblWidth = 100;

        Label lblCustomer = new Label("Customers");
        lblCustomer.setPrefWidth(_lblWidth);
        this.add(lblCustomer,0,0);

        Label lblCustomerName = new Label("Customer Name:");
        lblCustomerName.setPrefWidth(_lblWidth);
        this.add(lblCustomerName,0,2);

        Label lblCustomerPhoneNumber = new Label("Customer Mobile:");
        lblCustomerPhoneNumber.setPrefWidth(_lblWidth);
        this.add(lblCustomerPhoneNumber,0,3);

        // Add list views
        int _lvwWidht = 350;
        int _lvwHeight = 250;

        lvwCustomers.setPrefWidth(_lvwWidht);
        lvwCustomers.setPrefHeight(_lvwHeight);
        lvwCustomers.setEditable(false);
        this.add(lvwCustomers,0,1,2,1);
        ChangeListener<Customer> listener = (ov, o, n) -> {
            selectedCustomer = lvwCustomers.getSelectionModel().getSelectedItem();
            theaterGUI.getSeatPane().updateControls(true);
        };
        lvwCustomers.getSelectionModel().selectedItemProperty().addListener(listener);

        // Add text fields
        int _txfWidth = 100;

        txfCustomerName.setPrefWidth(_txfWidth);
        txfCustomerName.setPromptText("Full Name");
        this.add(txfCustomerName,1,2);

        txfPhoneNumber.setPrefWidth(_txfWidth);
        txfPhoneNumber.setPromptText("+45 12345678");
        this.add(txfPhoneNumber,1,3);

        // Add buttons
        Button btnCreateCustomer = new Button("Create Customer");
        this.add(btnCreateCustomer,1,4);

        // Add button action
        btnCreateCustomer.setOnAction(actionEvent -> actionCreateCustomer());

    }

    public void updateControls(){
        // Update list views
        lvwCustomers.getItems().setAll(Storage.getCustomer());

        // Clear text fields
        txfCustomerName.clear();
        txfPhoneNumber.clear();
    }

    private void actionCreateCustomer(){
        String customerName = txfCustomerName.getText().trim();
        String customerPhoneNumber = txfPhoneNumber.getText().trim();

        Controller.createCustomer(customerName,customerPhoneNumber);
        theaterGUI.updatePaneControls();
    }

    public Customer getSelectedCustomer() {
        return selectedCustomer;
    }
}
