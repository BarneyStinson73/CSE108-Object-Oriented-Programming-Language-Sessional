import java.util.ArrayList;
import java.util.List;

public class Club {
    private String name;
    List<Player> members = new ArrayList<>();
    private int cc;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public double max_sal(){
        double max=-2.0;
        for(var k:members){
            if(max<k.getSalary()){
                max=k.getSalary();
            }
        }
        return max;
    }
    public int max_age(){
        int a=-1;
        for(var k:members){
            if(a<k.getAge()){
                a=k.getAge();
            }
        }
        return a;
    }
    public double max_height(){
        double max=-2.0;
        for(var k:members){
            if(max<k.getHeight()){
                max=k.getHeight();
            }
        }
        return max;
    }
    public double yearsalary(){
        double total=0.0;
        for (var k:members){
            total=total+k.getSalary();
        }
        total=total*52.0;
        return total;
    }
    public int docount(){
        for(var x:members){
            cc++;
        }
        return cc;
    }
}
