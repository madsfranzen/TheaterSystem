package gui;

import controller.Controller;
import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.Booking;
import model.Customer;
import model.Seat;
import model.Show;
import storage.Storage;
import java.util.Iterator;

public class StatisticsPane extends GridPane {

    private final TheaterGUI theaterGUI;
    private final TextArea txaStatInfo = new TextArea();

    public StatisticsPane(TheaterGUI theaterGUI){
        this.theaterGUI = theaterGUI;
        this.setPadding(new Insets(10));
        this.setHgap(10);
        this.setVgap(10);
        this.setGridLinesVisible(false);
        this.setBorder(TheaterGUI.getBorder());

        // Add labels
        int _lblWidth = 150;

        Label lblStats = new Label("Booking Information");
        lblStats.setPrefWidth(_lblWidth);
        this.add(lblStats,0,0);

        // Add textArea
        int _txaWidht = 350;
        int _txaHeight = 250;

        txaStatInfo.setPrefWidth(_txaWidht);
        txaStatInfo.setPrefHeight(_txaHeight);
        txaStatInfo.setEditable(false);
        this.add(txaStatInfo,0,1);
    }

}
