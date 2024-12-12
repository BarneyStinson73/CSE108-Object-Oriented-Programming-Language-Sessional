package sample;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import util.NetworkUtil;

import javax.xml.crypto.Data;
import java.io.Serializable;
import java.util.List;

public class Login {
    public TextField userName;
    private Main main;

    public void setMain(Main main) {
        this.main = main;
    }

    public void reset() {
        userName.setText("");
    }

    public void login() {
        String serverAddress = "127.0.0.1";
        int serverPort = 33333;
        int response;
        try {
            NetworkUtil networkUtil = new NetworkUtil(serverAddress, serverPort);
            networkUtil.write(userName.getText());
            response = (Integer) networkUtil.read();
            if(response == 0) {
                giveAlert("Wrong user name or password");
            }
            else if(response == -1) {
                giveAlert("Club already logged in");
            }
           else {
                List<Player> players = (List<Player>) networkUtil.read();
                List<Player> marketPlayers = (List<Player>) networkUtil.read();
                Main.players = players;
                Main.playersMarket= marketPlayers;
                Main.nUtil = networkUtil;
                Main.loadPlayers(players);
                Main.loadCountry();
                Main.loadClub();
                Main.club = new Club();
                Main.club.setName(userName.getText());
                Main.club.members = players;
                new ReadThreadClient(networkUtil, main);
                main.showOptionChoicePage();
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }

    }

    public void giveAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Login Error");
        alert.setContentText(msg);
        alert.showAndWait();
        reset();
    }

}
