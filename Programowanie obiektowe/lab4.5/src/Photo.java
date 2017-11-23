import javax.xml.bind.annotation.XmlAttribute;
import java.io.PrintStream;

public class Photo {
    Photo(String url){
        this.url =url;
    }
    @XmlAttribute
    String url;
    void writeHTML(PrintStream out){
        out.printf("<img src=\"%s\" alt=\"Smiley face\" height=\"42\" width=\"42\"/>\n",url);
    }
}
