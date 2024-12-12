package sample;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import util.NetworkUtil;

import java.io.IOException;

public class Main extends Application {
    private Stage stage;
    public static NetworkUtil nUtil;
    public static Club club;
    public static volatile int flag;
    public static MyPlayers myp;
    public static Market mark;

    public Stage getStage() {
        return stage;
    }

    private static final String INPUT_FILE_NAME = "players.txt";
    //private static final String OUTPUT_FILE_NAME = "players.txt";
    static List<Player> players = new ArrayList<>();
    public static List<Player> playersMarket;
    static List<Club> clubs = new ArrayList<>();
    static List<Country> countries = new ArrayList<>();
    public static Player search1(String pname){
        Player p=null;
        for(var x:players){
            if(x.getName().equalsIgnoreCase(pname)){
                p=x;
                return p;
            }
        }
        return p;
    }
    public static Country search1(String pclub,String pcountry){
        // Player p=null;
        Country nc=null;
        for(var x:countries){

            if(x.getName().equalsIgnoreCase(pclub)){
                nc=x;
                return nc;
            }
        }
        return nc;
    }

    public static List<Player> position_search(String ppos) {
        List<Player> playerspos = new ArrayList<>();
        for(var p:players){
            if(p.getPosition().equalsIgnoreCase(ppos)){
                playerspos.add(p);
            }
        }
        return playerspos;
    }
    public static List<Player> salrangecheck(double i,double f){
        List<Player> playsal=new ArrayList<>();
        for(var m:players){
            if(m.getSalary()>=i && m.getSalary()<=f){
                playsal.add(m);
            }
        }
        return playsal;

    }
    public static Club findmaxclub(String cname){
        Club temp=null;
        for(var q:clubs){
            if (q.getName().equalsIgnoreCase(cname)){

                temp=q;
                return temp;
            }
        }
        return null;
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        /* // for simple controls uncomment this block
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml")); // for simple controls
        primaryStage.setTitle("JavaFX Controls");
        primaryStage.setScene(new Scene(root, 500, 300));
        primaryStage.show(); */

        /* // for list view uncomment this block
        Parent root = FXMLLoader.load(getClass().getResource("listview.fxml")); // for simple controls
        primaryStage.setTitle("List View");
        primaryStage.setScene(new Scene(root, 500, 300));
        primaryStage.show(); */

        /*// for table view
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("tableview.fxml"));
        Parent root = loader.load();
        TableViewController controller = loader.getController();
        controller.load();
        primaryStage.setTitle("Table View");
        primaryStage.setScene(new Scene(root, 500, 300));
        primaryStage.show();*/
        stage = primaryStage;
        showLogin();
    }

    public void showProjectStarter() throws IOException {
        flag=0;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("projectTwo.fxml"));
        Parent root = loader.load();
        ProjectTwoController controller = loader.getController();
        controller.setMain(this);
        stage.setTitle("Project");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }
    public static void loadPlayers() throws Exception{
        BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        while (true) {
            String line = br.readLine();
            if (line == null) break;
            String[] tokens = line.split(",");
            Player p = new Player();
            p.setAge(Integer.parseInt(tokens[2]));
            p.setName(tokens[0]);
            p.setCountry(tokens[1]);
            p.setHeight(Double.parseDouble(tokens[3]));
            p.setClub(tokens[4]);
            p.setPosition(tokens[5]);
            p.setNumber(Integer.parseInt(tokens[6]));
            p.setSalary(Double.parseDouble(tokens[7]));
            players.add(p);
        }
        br.close();
    }
    public static void loadClub(){

        for(var i:players){
            boolean w=true;
            for(var j:clubs){
                if(i.getClub().equalsIgnoreCase(j.getName())){
                    w=false;
                    j.members.add(i);
                    break;
                }
            }
            if(w){
                Club nc=new Club();
                nc.setName(i.getClub());
                nc.members.add(i);
                clubs.add(nc);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        loadClub();
        loadCountry();

        launch(args);
        try {
            nUtil.write(-1);
        }
        catch (Exception e) {
        }
    }

    public static void loadCountry() {
        for(var i:players){
            boolean x=true;
            for(var j:countries){
                if(i.getCountry().equalsIgnoreCase(j.getName())){
                    x=false;
                    j.countrymen.add(i);
                    break;
                }
            }
            if(x){
                Country nct=new Country();
                nct.setName(i.getCountry());
                nct.countrymen.add(i);
                countries.add(nct);
            }
        }
    }

    public void showOptionChoicePage() throws IOException {
        flag=0;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("optionchoice1.fxml"));
        Parent root = loader.load();
        Optionchoice1Controller controller = loader.getController();
        controller.setMain(this);
        controller.loadlabel(club.getName());
        stage.setTitle("Option Choice!!");
        stage.setScene(new Scene(root, 525, 350));
        stage.show();
    }
    public void showSearchPlayer() throws IOException {
        flag=0;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("searchpsub.fxml"));
        Parent root = loader.load();
        SearchpsubController controller = loader.getController();
        controller.setMain(this);
        stage.setTitle("Choose Player Specific Option!!");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public void showPlayerNameBox() throws IOException {
        flag=0;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("playerbox.fxml"));
        Parent root = loader.load();
        playerBoxController controller = loader.getController();
        controller.setMain(this);
        stage.setTitle("Choose Player Specific Option!!");
        stage.setScene(new Scene(root, 600, 333));
        stage.show();
    }
    public void showPlayer(List<Player> list) throws IOException {
        flag=0;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("listview.fxml"));
        Parent root = loader.load();
        showPlayerController controller= loader.getController();
        controller.setPlayerslist(list);
        controller.setMain(this);
        stage.setTitle("Player(s)");
        stage.setScene(new Scene(root, 700, 500));
        stage.show();
    }

    public void showPlayerClubCountryBox() throws IOException {
        flag=0;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("clubcountry.fxml"));
        Parent root = loader.load();
        ClubcountryController controller = loader.getController();
        controller.setMain(this);
        stage.setTitle("Input Player's Club & Country!!");
        stage.setScene(new Scene(root, 620, 400));
        stage.show();
    }
    public void showAlert(String s){
        flag=0;
        Alert m=new Alert(Alert.AlertType.ERROR);
        m.setContentText(s);
        m.show();
    }
    public void showPlayersinaTable(List<Player> countryplayers) throws IOException {
        flag=0;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("tableview.fxml"));
        Parent root = loader.load();
        TableController controller = loader.getController();
        controller.setlist(countryplayers);
        controller.setMain(this);
        controller.load();
        stage.setTitle("Expected Players in a table");
        stage.setScene(new Scene(root, 750, 480));
        stage.show();
    }

    public void showPositionBox() throws IOException {
        flag=0;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("positionbox.fxml"));
        Parent root = loader.load();
        Positionbox controller = loader.getController();
        controller.setMain(this);
        stage.setTitle("Give the position name");
        stage.setScene(new Scene(root, 600, 350));
        stage.show();
    }
    public void showSalaryRangeBox() throws IOException {
        flag=0;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("salaryrange.fxml"));
        Parent root = loader.load();
        Salaryrange controller = loader.getController();
        controller.setMain(this);
        stage.setTitle("Input the limits below:");
        stage.setScene(new Scene(root, 600, 353));
        stage.show();
    }
    public void showPlayerCount() {
        flag=0;
        Alert a=new Alert(Alert.AlertType.INFORMATION);
        String s="";
        for(var i:countries){
                i.setCount(i.docount());
            s+=i.getName()+","+"Player Count: "+i.getCount()+"\n";
        }
        a.setContentText(s);
        a.setTitle("Country-wise Player Count");
        a.setHeaderText("Player Count per Country is Following:");
        a.show();
    }
    public void goToMainMenu() throws IOException {
        showProjectStarter();
    }

    public void showClubBox() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("clubinputbox.fxml"));
        Parent root = loader.load();
        ClubInputBox controller = loader.getController();
        controller.setMain(this);
        stage.setTitle("See Your Club Finances");
        stage.setScene(new Scene(root, 600, 370));
        stage.show();
    }
    public void showLogin() throws IOException {
        flag = 0;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Login1.fxml"));
        Parent root = loader.load();
        Login control = loader.getController();
        control.setMain(this);
        stage.setTitle("Player DataBase");
        stage.setScene(new Scene(root, 700, 500));
        stage.setResizable(false);
        stage.show();
    }
    public static void loadPlayers(List<Player> playerList) {
        players = playerList;
    }
    public void showMine() throws Exception {
        flag = 1;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("MyPlayers.fxml"));
        Parent root = loader.load();
        MyPlayers control = loader.getController();
        myp = control;
        control.setMain(this);
        control.setPlayers(players);
        stage.setTitle("Player DataBase");
        stage.setScene(new Scene(root, 700, 500));
        stage.setResizable(false);
        stage.show();
    }
    public void showMarket() throws Exception {
        flag = 2;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Market.fxml"));
        Parent root = loader.load();
        Market control = loader.getController();
        control.setMain(this);
        control.setPlayers(playersMarket);
        mark = control;
        stage.setTitle("Player Market");
        stage.setScene(new Scene(root, 700, 500));
        stage.setResizable(false);
        stage.show();
    }
}
