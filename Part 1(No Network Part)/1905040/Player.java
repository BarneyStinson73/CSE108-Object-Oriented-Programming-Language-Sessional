public class Player {
    private String name;
    private String country;
    private int age;
    private double height;
    private String club;
    private String position;
    private int number;
    private double salary;
    public void setName(String name){
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setCountry(String country){
        this.country=country;
    }

    public String getCountry() {
        return country;
    }

    public void setAge(int age){
        this.age=age;
    }

    public int getAge() {
        return age;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public String getClub() {
        return club;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }
    public void show(){
        System.out.println(name+" "+country+" "+age+" "+number+" "+club+" "+position+" "+height+" "+salary);
    }
}
