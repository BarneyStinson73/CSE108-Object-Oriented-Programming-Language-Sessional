package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class playerBoxController {
    public TextField playernametext;
    private Main main;

    public void setMain(Main main) {
        this.main = main;
    }

    public void searchByPlayerName(ActionEvent actionEvent) throws IOException {
        List<Player> p=new ArrayList<>();
        String s=playernametext.getText();
        p.add(main.search1(s));
        main.showPlayer(p);
    }

    public void previousFromSearchByP(ActionEvent actionEvent) throws IOException {
        main.showSearchPlayer();
    }

    public void returntomainmenu(ActionEvent actionEvent) throws IOException {
        main.goToMainMenu();
    }
}
