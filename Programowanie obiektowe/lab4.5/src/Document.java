import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class Document {
    @XmlElement
    String title;
    @XmlElement
    Photo photo;
    @XmlElement(name = "section")
    List<Section> sections= new ArrayList<>();
    Document(){}
    Document(String a){
        this.title=a;
    }
    Photo addPhoto(String url){
        photo=new Photo(url);
        return photo;
    }
    Section addSection(String title){
        Section a=new Section(title);
        sections.add(a);
        return a;
    }
    void writeHTML(PrintStream out){
        out.printf("<html>\n" +
                "<head>\n" +
                "<title>Page Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1>%s</h1>\n",title);
        photo.writeHTML(out);
        for (int i=0;i<sections.size();i++){
            sections.get(i).writeHTML(out);
        }
        out.printf("</body>\n" +
                "</html>");
    }
    public void write(String fileName){
        try {
            JAXBContext jc = JAXBContext.newInstance(Document.class);
            Marshaller m = jc.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            FileWriter writer= new FileWriter(fileName);;
            m.marshal(this, writer);
        } catch (JAXBException ex) {
            ex.printStackTrace();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    public static Document read(String fileName){
        try {
            JAXBContext jc = JAXBContext.newInstance(Document.class);
            Unmarshaller m = jc.createUnmarshaller();
            FileReader reader = new FileReader(fileName);
            return (Document) m.unmarshal(reader);
        } catch (JAXBException ex) {
            ex.printStackTrace();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
