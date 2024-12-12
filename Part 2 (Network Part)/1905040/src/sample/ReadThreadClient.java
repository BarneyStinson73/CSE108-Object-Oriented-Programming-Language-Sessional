package sample;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import util.NetworkUtil;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.List;
class DS1 implements Runnable {

    public void run() {

        Main.mark.setPlayers2(Main.playersMarket);

    }
}
class DS2 implements Runnable {

    public void run() {

        Main.myp.setPlayers2(Main.players);

    }
}
public class ReadThreadClient implements Runnable  {
    Thread th;
    private NetworkUtil networkUtil;
    public Main main;

    public ReadThreadClient(NetworkUtil networkUtil, Main main) {
        this.networkUtil = networkUtil;
        this.main= main;
        th = new Thread(this);
        th.start();
    }
    public void run() {
        try {
            while (true) {
                Object o = networkUtil.read();
                if(o instanceof List) {
                    Main.playersMarket = (List<Player>) o;
                    if(Main.flag == 2) {
                        Platform.runLater(new DS1());
                    }
                }
                else {
                    int msg = (Integer) o;
                    if(msg == -3 | msg==2) {
                        Platform.runLater(() -> {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Failed");
                            alert.setContentText("Not available");
                            alert.showAndWait();
                        });
                    }
                    if(msg == 1 || msg == 2) {
                        Main.players = (List<Player>) networkUtil.read();
                        for(var i : Main.players) {
                            i.setClub(Main.club.getName());
                            i.status = "Not For Sale Now";
                        }
                        Main.loadPlayers(Main.players);
                        Main.loadCountry();
                        Main.loadClub();
                        Main.club.members = Main.players;
                        if(Main.flag == 1) {
                            Platform.runLater(new DS2());
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                networkUtil.closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}


