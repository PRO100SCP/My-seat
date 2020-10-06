package service.xml;

import model.ClassRoom;
import model.ClassRoom.Desk;
import model.SchoolClass;
import model.Student;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import org.w3c.dom.Document;
import service.SchoolClassService;
import service.StudentService;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClassRoomsXmlService {

    private Document document;

    public ClassRoomsXmlService(){
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            this.document= documentBuilder.parse("src/data/classRooms.xml");
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    public void add (ClassRoom classRoom) {
        Node root=document.getDocumentElement();
        Element elementCR=document.createElement("classRoom");
        elementCR.setAttribute("id", String.valueOf(classRoom.getId()));
        Element elementClasNum=document.createElement("ClassNumber");
        elementClasNum.setTextContent(classRoom.getName());
        Element elementSchoolClassName = document.createElement("SchoolClassName");
        if (classRoom.getSchoolClass() == null){
            elementSchoolClassName.setTextContent("null");
        }
        else {
            elementSchoolClassName.setTextContent(String.valueOf(classRoom.getSchoolClass().getName()));
        }
        Element elementDesNum=document.createElement("DeskNumbers");
        elementDesNum.setTextContent(String.valueOf(classRoom.getDeskAmount()));
        elementCR.appendChild(elementClasNum);
        elementCR.appendChild(elementSchoolClassName);
        elementCR.appendChild(elementDesNum);
        for(int i=0; i<classRoom.getDesks().size(); i++){
            Desk desk=classRoom.getDesks().get(i);
            Element elementDess=document.createElement("Desks");
            elementDess.setAttribute("id", String.valueOf(desk.getId()));
            Element elementLS=document.createElement("LeftSeat");
            try{
                elementLS.setTextContent(String.valueOf(desk.getLeftSeat().getId()));
            }catch (NullPointerException e){
                elementLS.setTextContent("none");
            }
            Element elementRS=document.createElement("RightSeat");
            try{
                elementRS.setTextContent(String.valueOf(desk.getRightSeat().getId()));
            }catch (NullPointerException e) {
                elementRS.setTextContent("none");
            }
            elementDess.appendChild(elementLS);
            elementDess.appendChild(elementRS);
            elementCR.appendChild(elementDess);
        }
        root.appendChild(elementCR);
        saveDocument();
    }
    public void deleteByName(String id){
        Node root=document.getDocumentElement();
        NodeList elementClassRooms=root.getChildNodes();
        for (int i=0; i<elementClassRooms.getLength(); i++){
            Node elementClassRoom=elementClassRooms.item(i);
            if(elementClassRoom.getNodeType()!=Node.TEXT_NODE){
                if(elementClassRoom.getAttributes().getNamedItem("id").getTextContent().equals(id)){
                    elementClassRoom.getParentNode().removeChild(elementClassRoom);
                    saveDocument();
                }
            }
        }
    }
    public ClassRoom getById(String id) {
        ClassRoom classRoom = new ClassRoom();
        List<Desk> deskList=new ArrayList<>();
        Node root = document.getDocumentElement();
        Student student=new Student();
        NodeList elementClasRooms=root.getChildNodes();
        for (int i=0; i<elementClasRooms.getLength(); i++){
            Node elementClasRoom=elementClasRooms.item(i);
            if (elementClasRoom.getNodeType()!=Node.TEXT_NODE){
                if (elementClasRoom.getNodeName().equals("classRoom")){
                    if (elementClasRoom.getAttributes().getNamedItem("id").getTextContent().equals(id)){
                        classRoom.setId(Long.parseLong(elementClasRoom.getAttributes().getNamedItem("id").getTextContent()));
                        NodeList elementClassRoomDetails = elementClasRoom.getChildNodes();
                        for (int j=0; j<elementClassRoomDetails.getLength(); j++){
                            Node elementClassRoomDetail=elementClassRoomDetails.item(j);
                            if (elementClassRoomDetail.getNodeType()!=Node.TEXT_NODE){
                                if (elementClassRoomDetail.getNodeName().equals("ClassNumber")){
                                    classRoom.setName(elementClassRoomDetail.getTextContent());
                                }
                                if (elementClassRoomDetail.getNodeName().equals("SchoolClassName")){
                                    if (elementClassRoomDetail.getTextContent().equals("null")){
                                        classRoom.setSchoolClass(null);
                                    }
                                    else{
                                        SchoolClassesXmlService schoolClassesXmlServ = new SchoolClassesXmlService();
                                        classRoom.setSchoolClass(schoolClassesXmlServ.getByName(elementClassRoomDetail.getTextContent())
                                        );
                                    }
                                }
                                if (elementClassRoomDetail.getNodeName().equals("DeskNumbers")){
                                    classRoom.setDeskAmount(Integer.parseInt(elementClassRoomDetail.getTextContent()));
                                }
                                if (elementClassRoomDetail.getNodeName().equals("Desks")){
                                    ClassRoom.Desk desk = classRoom.new Desk();
                                    desk.setId(Long.parseLong(elementClassRoomDetail.getAttributes().getNamedItem("id").getTextContent()));
                                    NodeList elementDeskDetails = elementClassRoomDetail.getChildNodes();
                                    for (int k=0; k<elementDeskDetails.getLength(); k++){
                                        Node elementDeskDetail = elementDeskDetails.item(k);
                                        if (elementDeskDetail.getNodeType()!=Node.TEXT_NODE){
                                            if (elementDeskDetail.getNodeName().equals("LeftSeat")){
                                                if (elementDeskDetail.getTextContent().equals("none")){
                                                    desk.setLeftSeat(null);
                                                }
                                                else {
                                                    SchoolClassService schoolClassS=new SchoolClassService(classRoom.getSchoolClass());
                                                    desk.setLeftSeat(schoolClassS.getFromStudentList(Long.parseLong(elementDeskDetail.getTextContent())));
                                                }
                                            }
                                            if (elementDeskDetail.getNodeName().equals("RightSeat")){
                                                if (elementDeskDetail.getTextContent().equals("none")){
                                                    desk.setRightSeat(null);
                                                }
                                                else {
                                                    SchoolClassService schoolClassS=new SchoolClassService(classRoom.getSchoolClass());
                                                    desk.setRightSeat(schoolClassS.getFromStudentList(Long.parseLong(elementDeskDetail.getTextContent())));
                                                }
                                            }
                                        }
                                    }
                                    deskList.add(desk);
                                }
                            }
                        }
                    }
                }
            }
        }
        classRoom.setDesks(deskList);
        return classRoom;
    }

    public void saveDocument(){
        try {
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(document);
            FileOutputStream fos = new FileOutputStream("src/data/classRooms.xml");
            StreamResult result = new StreamResult(fos);
            tr.setOutputProperty(OutputKeys.INDENT, "yes");
            tr.transform(source, result);

        }
        catch (TransformerException | IOException e) {
            e.printStackTrace();
        }
    }
}
