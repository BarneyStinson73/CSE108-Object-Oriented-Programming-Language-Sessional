package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Salaryrange {

    @FXML
    private TextField lowsalary;
    @FXML
    private TextField highsalary;
    private Main main;
    private List<Player> salarykhor;

    public void searchBySalaryRange(ActionEvent actionEvent) throws IOException {
        salarykhor=new ArrayList<>();
        double inis, fins;
        inis=Double.parseDouble(lowsalary.getText());

        fins=Double.parseDouble(highsalary.getText());

        salarykhor=main.salrangecheck(inis,fins);
        main.showPlayersinaTable(salarykhor);
    }
    void setMain(Main main){
        this.main=main;
    }

    public void previousFromSalaryRange(ActionEvent actionEvent) throws IOException {
        main.showSearchPlayer();
    }

}
