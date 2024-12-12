package sample;
import java.util.ArrayList;
import java.util.List;

public class Country {
    private String name;
    List<Player> countrymen = new ArrayList<>();
    private int count;
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void show(){
        for(var m:countrymen){
            m.show();
        }
    }
    public void setCount(int i){
        count=i;
    }
    public int getCount(){
        return count;
    }
    public void show(String nclub){
        for (var m:countrymen){
            if(m.getClub().equalsIgnoreCase(nclub)){
                m.show();
            }
        }
    }
    public int docount(){
        for(var x:countrymen){
            count++;
        }
        return count;
    }
}
