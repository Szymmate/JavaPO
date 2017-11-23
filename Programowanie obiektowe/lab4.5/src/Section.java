import javax.xml.bind.annotation.*;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Section {
    @XmlAttribute
    String title;
    @XmlElements(value= {
            @XmlElement(name = "paragraph", type = Paragraph.class),
            @XmlElement(name = "paragraph-with-list", type = ParagraphWithList.class)
    })
    List<Paragraph> paragraphs=new ArrayList<>();
    Section(String tit){
        title=tit;
    }
    Section addParagraph(String content){
        Paragraph a=new Paragraph(content);
        paragraphs.add(a);
        return this;
    }
    Section addParagraph(ParagraphWithList a){
        paragraphs.add(a);
        return this;
    }
    void writeHTML(PrintStream out){
        out.printf("<h1>%s</h1>\n",title);
        for(int i=0;i<paragraphs.size();i++){
            paragraphs.get(i).writeHTML(out);
        }
    }
}
