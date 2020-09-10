package test;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.io.IOException;

public class DOM {
    public static void main(String[] args) {
        try {
            DocumentBuilder documentBuilder=DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document=documentBuilder.parse("src/test/Games.xml");
            addNewGame(document);
            printList(document);
        } catch (IOException | SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static void printList(Document document){
        Node root = document.getDocumentElement();
        NodeList games = root.getChildNodes();
        for (int i=0; i<games.getLength(); i++) {
            Node game = games.item(i);
            if (game.getNodeType() != Node.TEXT_NODE) {
                NodeList gameinfo = game.getChildNodes();
                for (int j = 0; j < gameinfo.getLength(); j++) {
                    Node detail = gameinfo.item(j);
                    if (detail.getNodeType() != Node.TEXT_NODE) {
                        if (detail.getNodeName().equals("Name")){
                            System.out.println("Name: " + detail.getTextContent());
                        }
                        if (detail.getNodeName().equals("Score")){
                            System.out.println("Score: " + detail.getTextContent());
                        }
                        if (detail.getNodeName().equals("Cost")) {
                            if (detail.getAttributes().getNamedItem("value").getTextContent().equals("USD")) {
                                System.out.println("Cost: "+detail.getTextContent() + " DOLLARS");
                            } else if (detail.getAttributes().getNamedItem("value").getTextContent().equals("RUB")) {
                                System.out.println("Cost: "+detail.getTextContent() + " RUB");
                            } else {
                                System.out.println("Cost: "+detail.getTextContent() + "UNKNOWN CURRENCY");
                            }
                        }
                    }
                }
            }
        }
    }

    private static void addNewGame(Document document){
      Node root=document.getDocumentElement();
        Element game=document.createElement("Game");
        Element name=document.createElement("Name");
        Element score=document.createElement("Score");
        Element cost=document.createElement("Cost");
        name.setTextContent("SCP:CB");
        score.setTextContent("10");
        cost.setTextContent("0");
        cost.setAttribute("value", "USD");

        game.appendChild(name);
        game.appendChild(score);
        game.appendChild(cost);

        root.appendChild(game);

        writeDocument(document);
    }

    private static void deleteElementByName(Document document, String name){
        Node root = document.getDocumentElement();
        NodeList games = root.getChildNodes();
        for (int i = 0; i<games.getLength(); i++){
            Node game = games.item(i);
            if (game.getNodeType() != Node.TEXT_NODE){
                NodeList details = game.getChildNodes();
                for (int j = 0; j <details.getLength(); j++){
                    Node detail = details.item(j);
                    if (detail.getNodeName().equals("Name") && detail.getTextContent().equals(name)){
                        game.getParentNode().removeChild(game);
                        writeDocument(document);
                    }
                }
            }
        }
    }

    private static void writeDocument(Document document) throws TransformerFactoryConfigurationError {
        try {
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(document);
            FileOutputStream fos = new FileOutputStream("src/test/Games.xml");
            StreamResult result = new StreamResult(fos);
            tr.transform(source, result);

        }catch(TransformerException | IOException e) {
            e.printStackTrace();
        }
    }
}
