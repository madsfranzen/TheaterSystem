package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Customer;
import model.Seat;
import model.Show;

public class TheaterGUI extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("Teater Bestiliinger");
        GridPane container = new GridPane();
        this.initContent(container);

        Scene scene = new Scene(container);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    // ------------------------------------------------------------------------- //

    private ShowPane showPane;
    private CustomerPane customerPane;
    private SeatPane seatPane;
    private StatisticsPane statisticsPane;

    public ShowPane getShowPane()
    {
        return showPane;
    }

    public CustomerPane getCustomerPane()
    {
        return customerPane;
    }

    public SeatPane getSeatPane()
    {
        return seatPane;
    }

    public StatisticsPane getStatisticsPane()
    {
        return statisticsPane;
    }

    private void initContent(GridPane pane) {

        pane.setGridLinesVisible(false);

        GridPane paneShow = new ShowPane();
        GridPane paneCustomer = new CustomerPane();
        GridPane paneSeat = new SeatPane((ShowPane) paneShow, (CustomerPane) paneCustomer);


        statisticsPane = new StatisticsPane(this);


        pane.add(paneShow,0,0);
        pane.add(paneCustomer,1,0);
        pane.add(paneSeat,2,0);
        pane.add(statisticsPane,0,1, 3,1);

        statisticsPane.setAlignment(Pos.CENTER);
    }

}
