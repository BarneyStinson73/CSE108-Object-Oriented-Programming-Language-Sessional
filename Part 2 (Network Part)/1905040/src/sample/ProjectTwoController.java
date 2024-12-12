package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class ProjectTwoController {
    private Main main;
    @FXML
    private Button confirmcheck;
    @FXML
    private Button worklater;

    public void workstart(ActionEvent actionEvent) {
        try {
            main.showOptionChoicePage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void returntomainmenu(ActionEvent actionEvent) throws IOException {
        main.goToMainMenu();
    }
    void setMain(Main main) {
        this.main = main;
    }
}
