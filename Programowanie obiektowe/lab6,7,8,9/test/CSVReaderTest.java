import org.testng.annotations.Test;

import static org.junit.Assert.*;

public class CSVReaderTest {
    @Test
    public void no_headers() throws Exception {
        CSVReader a=new CSVReader("no-header.csv",";",false);
        while(a.next()){
            for(int i=0;i<a.getColumnLabels().size();i++){
                System.out.printf(a.get(i)+"  ");
            }
            System.out.println("");
        }
    }
    @Test
    public void with_headers() throws Exception {

        CSVReader a=new CSVReader("with-header.csv",";",false);
        while(a.next()){
            for(int i=0;i<a.getColumnLabels().size();i++){
                System.out.printf(a.get(i)+"  ");
            }
            System.out.println("\n");
        }
    }
    @Test
    public void elec() throws Exception {


        CSVReader a=new CSVReader("elec.csv",",",false);
        while(a.next()){
            int date,day;
            double nswprice,nswdemand,vicprice,vicdemand,transfer,period;
            String nameclass;
            date=a.getInt("date");
            day=a.getInt("day");
            period=a.getDouble("period");
            nswprice=a.getDouble("nswprice");
            nswdemand=a.getDouble("nswdemand");
            vicprice=a.getDouble("vicprice");
            vicdemand=a.getDouble("vicdemand");
            transfer=a.getDouble("transfer");
            nameclass=a.get("class");
            System.out.printf("%d %d %f %f %f %f %f %f %s\n",date,day,period,nswprice,nswdemand,vicprice,vicdemand,transfer,nameclass);
        }
    }
    @Test
    public void accelerator() throws Exception {
        CSVReader a=new CSVReader("accelerator.csv",";",true);
        while(a.next()){
            for(int i=0;i<a.getColumnLabels().size();i++){
                System.out.printf(a.get(i)+"  ");
            }
            System.out.println("\n");
        }
    }
    @Test
    public void missing_walues() throws Exception {
        CSVReader a=new CSVReader("missing-values.csv",";",true);
        while(a.next()){
            for(int i=0;i<a.getColumnLabels().size();i++){
                    System.out.printf(a.get(i) + "  ");
            }
            System.out.println("\n");
        }
    }
    @Test
    public void titanic_part() throws Exception {
        CSVReader a=new CSVReader("titanic-part.csv",",",true);
        while(a.next()){
            for(int i=0;i<a.getColumnLabels().size();i++){
                System.out.printf(a.get(i) + "  ");
            }
            System.out.println("\n");
        }
    }
}