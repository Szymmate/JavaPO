import javax.xml.bind.annotation.*;
import java.io.PrintStream;

@XmlSeeAlso({ParagraphWithList.class})
public class Paragraph {
    @XmlValue
    String content;
    Paragraph(){}
    Paragraph(String cont){
        content=cont;
    }
    void writeHTML(PrintStream out){
        out.printf("<p>%s</p>\n",content);
    }

}
