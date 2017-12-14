import java.util.ArrayList;
import java.util.List;

public class AdminUnit {
    AdminUnit parent;
    String name;
    int adminLevel;
    double area;
    double population;
    double density;
    BoundingBox bbox;
    List<AdminUnit> children=new ArrayList<>();
    public String toString(){
        StringBuilder a=new StringBuilder();
        a.append(name).append(" ");
        if(parent!=null){
            a.append("w jednostce ").append(parent.name);
        }else {
            a.append("jednostka podstawowa");
        }
        a.append(" poziom administracyjny: ").append(adminLevel).append(" powierzchnia: ").append(area).append(" populacja: ").append(population).append(" gęstość zaludnienia: ").append(density);
        return a.toString();
    }
}
