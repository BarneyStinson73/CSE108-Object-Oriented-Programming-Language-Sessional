package sample;

import util.NetworkUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public class Server {

    private ServerSocket serverSocket;
    public static HashMap<String, NetworkUtil> clientMap;
    public static HashMap<String, Boolean> isLogged;
    public static List<Player> marketPlayers;

    public Server() {
        clientMap = new HashMap<>();
        isLogged = new HashMap<>();
        marketPlayers = new Vector<>();
        try {
            Main.loadPlayers();
        }
        catch(Exception e) {
            System.out.println(e);
        }
        Main.loadClub();
        Main.loadCountry();
        try {
            serverSocket = new ServerSocket(33333);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                serve(clientSocket);
            }
        } catch (Exception e) {
            System.out.println("Server starts:" + e);
        }
    }

    public void serve(Socket clientSocket) throws IOException, ClassNotFoundException {
        NetworkUtil networkUtil = new NetworkUtil(clientSocket);
        String clientName = (String) networkUtil.read();
        boolean found = false;
        Club c = null;
        for(var i : Main.clubs) {
            if(i.getName().equals(clientName)) {
                found = true;
                c = i;
                break;
            }
        }
        if(!found) {
            networkUtil.write(0);
            networkUtil.closeConnection();
            clientSocket.close();
            return;
        }
        else if(isLogged.get(clientName)!=null) {
            networkUtil.write(-1);
            networkUtil.closeConnection();
            clientSocket.close();
            return;
        }
        networkUtil.write(1);
        networkUtil.write(c.members);
        networkUtil.write(marketPlayers);
        isLogged.put(clientName, true);
        clientMap.put(clientName, networkUtil);
        new ReadThreadServer(clientMap, networkUtil, isLogged, c, marketPlayers);
    }

    public static void main(String args[]) {
        new Server();
    }

    public static synchronized void buyWorks(Player player, NetworkUtil networkUtil, Club club) throws Exception{
        boolean found = false;
        Player myPlayer = null;
        for(var i : marketPlayers) {
            if(i.getName().equalsIgnoreCase(player.getName())) {
                found = true;
                myPlayer = i;
                break;
            }
        }
        if(!found) {
            networkUtil.write(-3);
            return;
        }
        else if(club.members.contains(myPlayer)) {
            networkUtil.write(-4);
            return;
        }
        networkUtil.write(1);
        Club former = null;
        for(var i : Main.clubs) {
            if(i.getName().equalsIgnoreCase(myPlayer.getClub())){
                former = i;
                break;
            }
        }
        NetworkUtil formerNet = clientMap.get(former.getName());
        former.members.remove(myPlayer);
        if(formerNet != null) {
            formerNet.write(2);
            formerNet.write(former.members);
        }
        myPlayer.setClub(club.getName());
        myPlayer.status = "Available";
        club.members.add(myPlayer);
        networkUtil.write(club.members);
        marketPlayers.remove(myPlayer);
        for(var i : clientMap.values()) {
            i.write(marketPlayers);
        }

    }
}