import javax.xml.bind.annotation.*;
import java.io.PrintStream;

public class Listitem {
    @XmlValue
    String content;
    Listitem(String name){
        content=name;
    }
    void writeHTML(PrintStream out){
        out.printf("<li> %s </li>\n",content);
    }
}
