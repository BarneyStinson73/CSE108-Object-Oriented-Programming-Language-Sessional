package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static sample.Main.search1;

public class ClubcountryController {
    @FXML
    private TextField clubplayer;
    @FXML
    private TextField coutryplayer;
    private Main main;

    public void searchByClubandCountry(ActionEvent actionEvent) throws IOException {
        Country cn=null;
        cn=search1(coutryplayer.getText(),null);
        if(cn==null){
            main.showAlert("No Country Like this,try again");
        }
        else{
            List<Player> coutmen = new ArrayList<>();
            for(var i:cn.countrymen){

                    coutmen.add(i);

            }
            main.showPlayersinaTable(coutmen);
        }
    }
    public void setMain(Main main) {
        this.main=main;
    }

    public void previousFromClubCountry(ActionEvent actionEvent) throws IOException {
        main.showSearchPlayer();
    }

    public void returntomainmenu(ActionEvent actionEvent) throws IOException {
        main.goToMainMenu();
    }
}
