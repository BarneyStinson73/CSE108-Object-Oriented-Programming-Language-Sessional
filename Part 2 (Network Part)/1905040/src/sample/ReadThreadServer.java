package sample;

import sample.Main;
import sample.Player;
import sample.Club;
import sample.Country;
import util.NetworkUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ReadThreadServer implements Runnable {
    private Thread thr;
    private NetworkUtil networkUtil;
    public HashMap<String, NetworkUtil> clientMap;
    public HashMap<String, Boolean> isLogged;
    public Club club;
    public List<Player> players;

    public ReadThreadServer(HashMap<String, NetworkUtil> map, NetworkUtil networkUtil, HashMap<String, Boolean> isLogged, Club club, List<Player> players) {
        this.isLogged = isLogged;
        this.club = club;
        this.players = players;
        this.clientMap = map;
        this.networkUtil = networkUtil;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            while (true) {
                Object o = networkUtil.read();
                if((Integer) o == -1) {
                    networkUtil.closeConnection();
                    isLogged.remove(club.getName());
                    clientMap.remove(club.getName());
                    return;
                }
                else if((Integer) o == 0) {
                    Player player = (Player) networkUtil.read();
                    Player myPlayer = null;
                    for(var i : Main.players) {
                        if(i.getName().equalsIgnoreCase(player.getName())) {
                            myPlayer = i;
                        }
                    }
                    if(players.contains(myPlayer)) {
                        networkUtil.write(-2);
                    }
                    else {
                        players.add(myPlayer);
                        for(var i : clientMap.values()) {
                            i.write(players);
                        }
                    }
                }
                else if((Integer) o == 1) {
                    Player player = (Player) networkUtil.read();
                    Server.buyWorks(player, networkUtil, club);
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
