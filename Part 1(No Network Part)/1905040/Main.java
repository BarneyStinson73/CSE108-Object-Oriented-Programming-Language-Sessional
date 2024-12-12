import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String INPUT_FILE_NAME = "players.txt";
    private static final String OUTPUT_FILE_NAME = "players.txt";
    static List<Player> players = new ArrayList<>();
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
    //public static Club maxageclub(String cname){
      //  Club temp=null;
      //  for(var q:clubs){
         //   if (q.getName().equalsIgnoreCase(cname)){

             //   temp=q;
                //return temp;
          //  }
      //  }
      //  return null;
   // }
    public static void main(String[] args) throws Exception{

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

        String name=players.get(0).getClub();
        Club m=new Club();
        m.setName(name);
        clubs.add(m);
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

        String cname=players.get(0).getCountry();
        Country k=new Country();
        k.setName(cname);
        countries.add(k);
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
        while(true){
            System.out.println("Main Menu:");
            System.out.println("(1) Search Players");
            System.out.println("(2) Search Clubs");
            System.out.println("(3) Add Player");
            System.out.println("(4) Exit System");
            Scanner sc=new Scanner(System.in);
            String n=sc.nextLine();
            int sn=Integer.parseInt(n);
            if(sn>4 || sn<=0){
                System.out.println("Invalid Input.Please try again.");
                continue;
            }
            else{
                if(sn==1){
                    while(true){
                        System.out.println("(1) By Player Name");
                        System.out.println("(2) By Club and Country");
                        System.out.println("(3) By Position");
                        System.out.println("(4) By Salary Range");
                        System.out.println("(5) Country-wise player count");
                        System.out.println("(6) Back to Main Menu");
                        n=sc.nextLine();
                        int ssn;
                        ssn=Integer.parseInt(n);
                        if(ssn>6 || ssn<=0){
                            System.out.println("Invalid Input.Please try again.");
                            continue;
                        }
                        else{
                            if(ssn==1){
                                n=sc.nextLine();
                                Player pn=new Player();
                                pn=search1(n);
                                if(pn==null) System.out.println("No such player with this name");
                                else{
                                    pn.show();
                                }
                            }
                            else if(ssn==2){
                              String sclub=sc.nextLine();
                              String scon=sc.nextLine();
                              Country cn=new Country();
                              cn=search1(sclub,scon);
                                if(cn==null) System.out.println("No such player with this country and club");
                                else{
                                    if(scon.equalsIgnoreCase("ANY")){
                                       cn.show();
                                    }
                                    else{
                                        cn.show(scon);
                                    }
                                }
                            }
                            else if(ssn==3){
                                String sposition=sc.nextLine();
                                List<Player> poslist = new ArrayList<>();
                                poslist=position_search(sposition);
                                if(poslist==null) System.out.println("No such player with this position");
                                else{
                                    for(var xx:poslist){
                                        xx.show();
                                    }
                                }
                            }
                            else if(ssn==4){
                                String ssali= sc.nextLine();
                                String ssalf=sc.nextLine();
                                double inis=Double.parseDouble(ssali);
                                double fins=Double.parseDouble(ssalf);
                                List<Player>salarykhor=new ArrayList<>();
                                salarykhor=salrangecheck(inis,fins);
                                if(salarykhor==null) System.out.println("No such player with this weekly salary range");
                                else{
                                    for(var xx:salarykhor){
                                        xx.show();
                                    }
                                }
                            }
                            else if(ssn==5){
                                for(var pc:countries){
                                    System.out.println(pc.getName()+"  "+pc.docount());
                                }
                            }
                            else{
                                break;
                            }
                        }
                    }
                }
                else if(sn==2){
                    while (true){
                        System.out.println("(1)Player(s) with the maximum salary of a club");
                        System.out.println("(2) Player(s) with the maximum age of a club");
                        System.out.println("(3) Player(s) with the maximum height of a club");
                        System.out.println("(4) Total yearly salary of a club");
                        System.out.println("(5) Back to Main Menu");
                        n=sc.nextLine();
                        sn=Integer.parseInt(n);
                        if(sn>5 || sn<=0){
                            System.out.println("Invalid Input.Please try again.");
                            continue;
                        }
                        else{
                            if(sn==1){
                                String clubname=sc.nextLine();
                                Club mm=findmaxclub(clubname);
                                if(mm==null) System.out.println("No such club with this name");
                                else{
                                    double max=mm.max_sal();
                                    for(var r:mm.members){
                                        if(max==r.getSalary()){
                                            r.show();
                                        }
                                    }
                                }
                            }
                            else if(sn==2){
                                String clubname=sc.nextLine();
                                Club mm=findmaxclub(clubname);
                                if(mm==null) System.out.println("No such club with this name");
                                else{
                                    int ag=mm.max_age();
                                    for(var r:mm.members){
                                        if(ag==r.getAge()){
                                            r.show();
                                        }
                                    }
                                }
                            }
                            else if(sn==3){
                                String clubname=sc.nextLine();
                                Club mm=findmaxclub(clubname);
                                if(mm==null) System.out.println("No such club with this name");
                                else{
                                    double max=mm.max_height();
                                    for(var r:mm.members){
                                        if(max==r.getHeight()){
                                            r.show();
                                        }
                                    }
                                }
                            }
                            else if(sn==4){
                                String clubname=sc.nextLine();
                                Club mm=findmaxclub(clubname);
                                if(mm==null) System.out.println("No such club with this name");
                                else{
                                    System.out.println("Total Yearly Salary of this club is "+mm.yearsalary());
                                }
                            }
                            else
                                break;
                        }
                    }
                }
                else if(sn==3){
                    boolean isCountry = false;
                    boolean isClub = false;
                    System.out.println("Enter player details:");
                    Player playerin=new Player();
                    System.out.println("Name:");
                    playerin.setName(sc.nextLine());
                    System.out.println("Club:");
                    playerin.setClub(sc.nextLine());
                    System.out.println("Country:");
                    playerin.setCountry(sc.nextLine());
                    Country con = search1(playerin.getCountry(), null);
                    if(con == null) {
                        con = new Country();
                        con.setName(playerin.getCountry());
                        isCountry = true;
                    }
                    System.out.println("Position:");
                    playerin.setPosition(sc.nextLine());
                    System.out.println("Age:");
                    playerin.setAge(Integer.parseInt(sc.nextLine()));
                    System.out.println("Number:");
                    playerin.setNumber(Integer.parseInt(sc.nextLine()));
                    System.out.println("Height:");
                    playerin.setHeight(Double.parseDouble(sc.nextLine()));
                    System.out.println("Weekly Salary:");
                    playerin.setSalary(Double.parseDouble(sc.nextLine()));
                    Club check=findmaxclub(playerin.getClub());
                    if(check == null) {
                        check = new Club();
                        check.setName(playerin.getClub());
                        isClub = true;
                    }
                    if(check.docount()>=7){
                        System.out.println("Error,reached maximum club member");
                    }
                    else{
                        boolean d=false;
                        for(var w:check.members){
                            if(w.getNumber()==playerin.getNumber()){
                                d=true;
                                System.out.println("Error,can't have two player with same number");
                                break;
                            }
                        }
                        if(playerin.getPosition().equalsIgnoreCase("Goalkeeper") || playerin.getPosition().equalsIgnoreCase("Defender")||playerin.getPosition().equalsIgnoreCase("Midfielder")||playerin.getPosition().equalsIgnoreCase("Forward")){

                        }
                        else{
	                        System.out.println("Error,can't have player with this position,input again please");
                            continue;
                        }
                        if (d==false){
                            check.members.add(playerin);
                            con.countrymen.add(playerin);
                            players.add(playerin);
                            if(isClub) {
                                clubs.add(check);
                            }
                            if(isCountry) {
                                countries.add(con);
                            }
                        }
                    }
                }
                else {
                    BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME));
                    for (var s : players) {
                        bw.write(s.getName() + "," + s.getCountry() + "," + s.getAge() + "," + s.getHeight()+","+s.getClub()+","+s.getPosition()+","+s.getNumber()+","+s.getSalary());
                        bw.write("\n");
                    }
                    bw.close();
                    break;
                }
            }

        }

    }
}
