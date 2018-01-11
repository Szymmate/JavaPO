import java.io.IOException;
import java.io.PrintStream;
import java.util.*;
import java.util.function.Predicate;

class comparename implements Comparator<AdminUnit>{
    @Override
    public int compare(AdminUnit o1, AdminUnit o2) {
        return o1.name.compareTo(o2.name);
    }
}

public class AdminUnitList {
    List<AdminUnit> units=new ArrayList<>();
    public void read(String filename) throws IOException {
        CSVReader a=new CSVReader(filename);//"
        int s=0,ic=-1;
        Map <Integer ,Integer> id=new HashMap<Integer, Integer>();
        Map <Integer ,Integer> idparent=new HashMap<Integer, Integer>();
        do {
            ic++;
            AdminUnit c = new AdminUnit();
            c.parent=null;
            if (!a.isMissing("admin_level")) {
                c.adminLevel = a.getInt("admin_level");
            }
            else {
                c.adminLevel = -1;
            }
            if (!a.isMissing("area")) {
                c.area = a.getDouble("area");
            }
            else {
                c.area = -1;
            }
            if (!a.isMissing("density")) {
                c.density = a.getDouble("density");
            }
            else {
                c.density = -1;
            }
            if (!a.isMissing("population")) {
                c.population = a.getDouble("population");
            }
            else {
                c.population = -1;
            }
            if (!a.isMissing("name")) {
                c.name = a.get("name");
            }
            else {
                c.name = "";
            }
            if (!a.isMissing("parent")) {
                idparent.put(ic, a.getInt("parent"));
            }
            else{
                idparent.put(ic, 0);
            }
            if(!a.isMissing("id")) {
                id.put(a.getInt("id"), ic);
            }
            else {
                id.put(0, ic);
            }
            double xmin = Double.NaN;
            double ymin = Double.NaN;
            double xmax = Double.NaN;
            double ymax = Double.NaN;
            if(!a.isMissing(7)) {
                xmin = a.getDouble(7);
            }
            if(!a.isMissing(8)) {
                ymin = a.getDouble(8);
            }
            if(!a.isMissing(7)) {
                xmax = a.getDouble(7);
            }
            if(!a.isMissing(8)) {
                ymax = a.getDouble(8);
            }
            for(int i=9;i<=13;i=i+2) {
                if (!a.isMissing(i)){
                    if (xmin > a.getDouble(i)) {
                        xmin = a.getDouble(i);
                    }
                }
                if (!a.isMissing(i+1)) {
                    if (ymin > a.getDouble(1 + i)) {
                        ymin = a.getDouble(i + 1);
                    }
                }
                if (!a.isMissing(i)) {
                    if (xmax < a.getDouble(i)) {
                        xmax = a.getDouble(i);
                    }
                }
                if (!a.isMissing(i+1)) {
                    if (ymax < a.getDouble(i + 1)) {
                        ymax = a.getDouble(i + 1);
                    }
                }
            }
            c.bbox=new BoundingBox(xmax,ymax,xmin,ymin);
            units.add(c);
        }while (a.next()&&a!=null);
        for(int i=0;i<id.size();i++){
            if(idparent.get(i)!=0){
                try{
                    units.get(i).parent=units.get(id.get(idparent.get(i)));
                }
                catch (NullPointerException nullpointer){
                    System.out.println(units.get(i).name+"brak rodzica");
                }
            }
        }
        for(int i=0;i<units.size();i++) {
            if(units.get(i).parent!=null){
                try{
                    units.get(i).parent.children.add(units.get(i));
                }
                catch (NullPointerException nullpointer){
                    System.out.println(units.get(i).name);
                }
            }
        }
    }
    void list(PrintStream out){
        for (AdminUnit x:units) {
            System.out.printf(x.toString(),out);
            System.out.printf("\n",out);
        }
    }
    void list(PrintStream out,int offset, int limit ){
        for (int i=offset;i<limit&&i<units.size();i++){
            System.out.printf(units.get(i).toString(),out);
            System.out.printf("\n",out);
        }
    }
    AdminUnitList selectByName(String pattern, boolean regex){
        AdminUnitList ret = new AdminUnitList();
        if(regex){
            for (int i=0;i<units.size();i++){
                if(units.get(i).name.matches(pattern)){
                    ret.units.add(units.get(i));
                }
            }
        }
        else {
            for (int i=0;i<units.size();i++){
                if(units.get(i).name.contains(pattern)){
                    ret.units.add(units.get(i));
                }
            }
        }
        return ret;
    }
    void fixMissingValues(){
        for (int i=0;i<units.size();i++){
            if(units.get(i).density==-1){
                AdminUnit tmp=units.get(i).parent;
                while(tmp!=null&&tmp.density==-1){
                    tmp=tmp.parent;
                }
                units.get(i).density=tmp.density;
            }
            if(units.get(i).population==-1&&units.get(i).area!=-1){
                units.get(i).population=units.get(i).density*units.get(i).area;
            }
        }
    }
    AdminUnitList getNeighbors(AdminUnit unit, double maxdistance){
        AdminUnitList a=new AdminUnitList();
        for (AdminUnit x:units) {
            if(unit.name!=x.name&&unit.adminLevel==x.adminLevel&&(unit.bbox.intersects(x.bbox))){
                a.units.add(x);
            }
            if(unit.adminLevel==8){
                if(unit.name!=x.name&&unit.adminLevel==x.adminLevel&&(unit.bbox.intersects(x.bbox)||unit.bbox.distanceTo(x.bbox)<=maxdistance)){
                    a.units.add(x);
                }
            }

        }
        return a;
    }
    AdminUnitList sortInplaceByName(){
        units.sort(new comparename());
        return this;
    }
    AdminUnitList sortInplaceByArea(){

        units.sort(new Comparator<AdminUnit>(){
            @Override
            public int compare(AdminUnit o1, AdminUnit o2) {
                return new Double(o1.area).compareTo(new Double(o2.area));
            }
        });
        return this;
    }
    AdminUnitList sortInplaceByPopulation(){
        units.sort((AdminUnit o1, AdminUnit o2)->new Double(o1.population).compareTo(new Double(o2.population)));
        return this;
    }
    AdminUnitList sortInplace(Comparator<AdminUnit> cmp){
        units.sort(cmp);
        return this;
    }
    AdminUnitList sort(Comparator<AdminUnit> cmp){
        AdminUnitList a=new AdminUnitList();
        for (AdminUnit ten:this.units
             ) {
            a.units.add(ten);
        }
        sortInplace(cmp);
        return a;
    }
    AdminUnitList filter(Predicate<AdminUnit> pred) {
        AdminUnitList a=new AdminUnitList();
        for (AdminUnit ten:this.units
                ) {
            if(pred.test(ten))
            a.units.add(ten);
        }
        return a;
    }
    AdminUnitList filter(Predicate<AdminUnit> pred, int limit){
        AdminUnitList a=new AdminUnitList();
        for (int i=0;i<this.units.size()&&a.units.size()<limit;i++) {
            if(pred.test(this.units.get(i)))
                a.units.add(this.units.get(i));
        }
        return a;
    }
    AdminUnitList filter(Predicate<AdminUnit> pred, int offset, int limit){
        AdminUnitList a=new AdminUnitList();
        for (int i=0;i<this.units.size()&&a.units.size()<limit+offset;i++) {
            if(pred.test(this.units.get(i)))
                a.units.add(this.units.get(i));
        }
        AdminUnitList b=new AdminUnitList();
        for (int i=offset;i<a.units.size();i++) {
                b.units.add(a.units.get(i));
        }
        return b;
    }
}

