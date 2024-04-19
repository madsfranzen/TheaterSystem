package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
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

    private void initContent(GridPane pane) {

        pane.setGridLinesVisible(true);

        GridPane paneShow = new ShowPane();
        GridPane paneCustomer = new CustomerPane();
        GridPane paneSeat = new SeatPane((ShowPane) paneShow, (CustomerPane) paneCustomer);
        GridPane paneStatistics = new StatisticsPane((ShowPane) paneShow, (CustomerPane) paneCustomer);

        pane.add(paneShow,0,0);
        pane.add(paneCustomer,1,0);
        pane.add(paneSeat,2,0);
        pane.add(paneStatistics,3,0);
    }

}
