package service.xml;

import model.ClassRoom;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.io.IOException;

public class ClassRoomsXmlService {

    private Document document;

    public ClassRoomsXmlService(){
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            this.document= documentBuilder.parse("src/data/classRoom.xml");
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    public void add (ClassRoom classRoom) {
        Node root=document.getDocumentElement();
        Element elementCR=document.createElement("classRoom");
        elementCR.setAttribute("id", String.valueOf(classRoom.getId()));
        elementCR.setAttribute("name", classRoom.getName());
        elementCR.setAttribute("deskAmount", String.valueOf(classRoom.getDeskAmount()));
        Element elementD=document.createElement("desks");
        for(int i=0; i<classRoom.getDesks().size(); i++){
            ClassRoom.Desk desk=classRoom.getDesks().get(i);
        }
    }
    public void saveDocument() {
        try {
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(document);
            FileOutputStream fos = new FileOutputStream("src/data/schoolClasses.xml");
            StreamResult result = new StreamResult(fos);
            tr.setOutputProperty(OutputKeys.INDENT, "yes");
            tr.transform(source, result);

        } catch (TransformerException | IOException e) {
            e.printStackTrace();
        }
    }
}
