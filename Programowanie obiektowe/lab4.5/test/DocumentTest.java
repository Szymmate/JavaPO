import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

public class DocumentTest {
    @org.junit.Test
    public void Document() throws Exception {
        Document cv = new Document("Jana Kowalski - CV");
        cv.addPhoto("...");
        cv.addSection("Wykształcenie")
                .addParagraph("2000-2005 Przedszkole im. Królewny Snieżki w ...")
                .addParagraph("2006-2012 SP7 im Ronalda Regana w ...")
                .addParagraph("...");
        cv.addSection("Umiejętności")
                .addParagraph(
                        new ParagraphWithList().setContent("Umiejętności")
                                .addListItem("C")
                                .addListItem("C++")
                                .addListItem("Java")
                );
        cv.writeHTML(System.out);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        cv.writeHTML(ps);
        String result = null;
        try {
            result = os.toString("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        assertEquals("<html>\n" +
                "<head>\n" +
                "<title>Page Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1>Jana Kowalski - CV</h1>\n" +
                "<img src=\"...\" alt=\"Smiley face\" height=\"42\" width=\"42\"/>\n" +
                "<h1>Wykształcenie</h1>\n" +
                "<p>2000-2005 Przedszkole im. Królewny Snieżki w ...</p>\n" +
                "<p>2006-2012 SP7 im Ronalda Regana w ...</p>\n" +
                "<p>...</p>\n" +
                "<h1>Umiejętności</h1>\n" +
                "<p>Umiejętności</p>\n" +
                "<ul>\n" +
                "<li> C </li>\n" +
                "<li> C++ </li>\n" +
                "<li> Java </li>\n" +
                "</ul>\n" +
                "</body>\n" +
                "</html>",result);
        cv.write("cv.xml");
    }


}