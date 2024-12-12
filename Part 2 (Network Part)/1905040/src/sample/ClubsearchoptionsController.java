package sample;

import javafx.event.ActionEvent;

import java.io.IOException;

public class ClubsearchoptionsController {

    private Main main;

    public void maxSalaryClub(ActionEvent actionEvent) throws IOException {
            main.showClubBox();
    }

    public void maxAgeClub(ActionEvent actionEvent) throws IOException {
        main.showClubBox();
    }

    public void maxHeightClub(ActionEvent actionEvent) throws IOException {
        main.showClubBox();
    }

    public void totalSalaryClub(ActionEvent actionEvent) throws IOException {
        main.showClubBox();
    }

    public void returntomainmenu(ActionEvent actionEvent) throws IOException {
        main.goToMainMenu();
    }

    public void setMain(Main main) {
        this.main=main;
    }
}
