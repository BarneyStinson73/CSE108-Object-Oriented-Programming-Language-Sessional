package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClubInputBox {
    @FXML
    private TextField clubinput;
    private Main main;

    public void setMain(Main main) {
        this.main=main;
    }

    public void searchForMaxSalary(ActionEvent actionEvent) throws IOException {
          String s=Main.players.get(0).getClub();
          Club c=null;
          c=main.findmaxclub(s);


              double max=c.max_sal();
              List<Player>cplay=new ArrayList<>();
              for(var r:c.members){
                  if(max==r.getSalary()){
                      cplay.add(r);
                  }
              }
              main.showPlayersinaTable(cplay);

    }

    public void maxAge(ActionEvent actionEvent) throws IOException {
        String s=Main.players.get(0).getClub();
        Club c=null;
        c=main.findmaxclub(s);


            double max=c.max_age();
            List<Player>cplay=new ArrayList<>();
            for(var r:c.members){
                if(max==r.getAge()){
                    cplay.add(r);
                }
            }
            main.showPlayersinaTable(cplay);

    }

    public void maxHeight(ActionEvent actionEvent) throws IOException {
        String s=Main.players.get(0).getClub();
        Club c=null;
        c=main.findmaxclub(s);

            double max=c.max_height();
            List<Player>cplay=new ArrayList<>();
            for(var r:c.members){
                if(max==r.getHeight()){
                    cplay.add(r);
                }
            }
            main.showPlayersinaTable(cplay);

    }

    public void totalSalary(ActionEvent actionEvent) {
        String s=Main.players.get(0).getClub();
        Club c=null;
        c=main.findmaxclub(s);

            Alert a=new Alert(Alert.AlertType.INFORMATION);
            a.setContentText(String.format("Total Yearly Salary of %s club is %f", c.getName(),c.yearsalary()));
            a.show();

    }

    public void returntomainmenu(ActionEvent actionEvent) throws IOException {
        main.goToMainMenu();
    }

    public void previousPage(ActionEvent actionEvent) throws IOException {
        main.showOptionChoicePage();
    }
}
