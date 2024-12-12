package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;

import java.io.IOException;

public class Optionchoice1Controller {

    public Label label;
    private Main main;

    public void loadlabel(String s) {
        label.setText(s);
    }

    public void searchPlayers(ActionEvent actionEvent) throws IOException {
        main.showSearchPlayer();
    }

    public void searchClubs(ActionEvent actionEvent) throws IOException {
        main.showClubBox();
    }

    public void addPlayer(ActionEvent actionEvent) {
    }

    public void returntomainmenu(ActionEvent actionEvent) {
        try{
            main.showProjectStarter();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    void setMain(Main main) {
        this.main = main;
    }

    public void market(ActionEvent actionEvent) throws Exception {
        main.showMarket();
    }

    public void myPlayers(ActionEvent actionEvent) throws Exception {
        main.showMine();
    }
}
