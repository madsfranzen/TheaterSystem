package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.Customer;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class CustomerPane extends GridPane
{

    private final ListView<Customer> lvwCustomers = new ListView<>();
    private final TextField txfName = new TextField();
    private final TextField txfMobile = new TextField();
    private final Alert alert = new Alert(Alert.AlertType.ERROR);
    private final Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);

    public CustomerPane()
    {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        Label lblCustomers = new Label("Customers");
        this.add(lblCustomers,0,0);

        this.add(lvwCustomers,0,1,2,1);
        lvwCustomers.setPrefSize(300,200);
        lvwCustomers.getItems().setAll(Controller.getCustomers());

        // Name (lbl and textfield)
        Label lblName = new Label("Customer name:");
        this.add(lblName,0,2);
        this.add(txfName,1,2);

        // Start date (lbl and textfield)
        Label lblMobile = new Label("Customer mobile:");
        this.add(lblMobile,0,3);
        this.add(txfMobile,1,3);


        Button btnCreateShow = new Button("Create Customer");
        this.add(btnCreateShow,1,4);
        btnCreateShow.setOnAction(event -> createCustomerAction());

        confirmation.setTitle("Create Customer");
        confirmation.setHeaderText("Are you sure?");

    }

    // Method to get the selected customer
    public Customer getSelectedCustomer() {
        return lvwCustomers.getSelectionModel().getSelectedItem();
    }

    /*
     * Creates a show given the name, startDate and endDate
     * */
    private void createCustomerAction() {
        String name = txfName.getText().trim();
        String mobile = txfMobile.getText().trim();

        // Checking if name is empty
        if (name.isEmpty()) {
            alert.setContentText("Please enter a name for the customer.");
            alert.show();
            return;
        }

        // Checking if name is empty
        if (mobile.length() < 8) {
            alert.setContentText("Please enter an 8 digit phone number for the customer.");
            alert.show();
            return;
        }

        // Showing the confirmation alert
        Optional<ButtonType> result = confirmation.showAndWait();

        // If user confirms a new show is created
        if (result.isPresent() && (result.get() == ButtonType.OK))
        {
            Controller.createCustomer(name, mobile);
            updateControls();
        }
    }

    /*
     * Updates and selects new show in listview and resets name, startDate, endDate.
     * */
    private void updateControls()
    {
        lvwCustomers.getItems().setAll(Controller.getCustomers());
        lvwCustomers.getSelectionModel().select(Controller.getCustomers().size() - 1);
        txfName.clear();
        txfMobile.clear();
    }

}
