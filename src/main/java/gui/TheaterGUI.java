package gui;

import controller.Controller;
import javafx.application.Application;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Customer;
import model.Show;

import java.time.LocalDate;

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
    private final ListView<Show> lvwShow = new ListView<>();
    private final ListView<Customer> lvwCustomer = new ListView<>();
    private final Button btnAddShow = new Button("Add Show");
    private final Button btnAddCustomer = new Button("Add Customer");
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

        Label lblShows = new Label("Shows:");
        Label lblName = new Label("Name:");
        Label lblStartDate = new Label("Start Date:");
        Label lblEndDate = new Label("End Date:");

        showPane.add(lblShows, 0, 0);
        showPane.add(lblName, 0, 2);
        showPane.add(lblStartDate, 0, 3);
        showPane.add(lblEndDate,0,4);

        showPane.add(lvwShow,0,1);
        lvwShow.setEditable(false);
        lvwShow.getItems().addAll(Controller.getShows());

        showPane.add(txfName,1,2);
        showPane.add(txfStartDate,1,3);
        showPane.add(txfEndDate,1,4);

        showPane.add(btnAddShow,1,5);
        btnAddShow.setOnAction(Event -> addShowAction());


        //-------------Customer Pane ------------------

        GridPane customerPane = new GridPane();
        pane.add(customerPane, 1, 0);
        customerPane.setGridLinesVisible(false);
        customerPane.setPadding(new Insets(10));
        customerPane.setHgap(10);
        customerPane.setVgap(10);

        Label lblCustomers = new Label("Customers:");
        Label lblCustomerName = new Label("Customer Name:");
        Label lblCustomerPhone = new Label("Customer Phone-number:");

        customerPane.add(lblCustomers,0,0);
        customerPane.add(lblCustomerName,0,2);
        customerPane.add(lblCustomerPhone,0,3);

        customerPane.add(lvwCustomer,0,1);
        lvwCustomer.setEditable(false);
        lvwCustomer.getItems().addAll(Controller.getCustomers());

        customerPane.add(txfCustomerName,1,2);
        customerPane.add(txfCustomerPhoneNumber,1,3);

        customerPane.add(btnAddCustomer,1,4);
        btnAddCustomer.setOnAction(Event -> addCustomerAction());

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
}
