package sample;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.util.Scanner;

import static sample.Main.countries;

public class SearchpsubController {
    private Main main;

    public void bynamesearch(ActionEvent actionEvent) throws IOException {
        main.showPlayerNameBox();
    }

    public void clubcountrysearch(ActionEvent actionEvent) throws IOException {
        main.showPlayerClubCountryBox();
    }

    public void positionsearch(ActionEvent actionEvent) throws IOException {
        main.showPositionBox();
    }

    public void salaryrangesearch(ActionEvent actionEvent) throws IOException {
        main.showSalaryRangeBox();
    }

    public void countryplayercount(ActionEvent actionEvent) {
        main.showPlayerCount();
    }

    void setMain(Main main) {
        this.main = main;
    }

    public void previousFromSearchPlayer(ActionEvent actionEvent) throws IOException {
            main.showOptionChoicePage();
    }
}
