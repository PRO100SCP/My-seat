package service.xml;

import model.SchoolClass;
import model.Student;
import model.Subject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SchoolClassesXmlService {

    private Document document;

    public SchoolClassesXmlService(){
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            this.document= documentBuilder.parse("src/data/schoolClasses.xml");
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
    public void add(SchoolClass schoolClass){
        Node root=document.getDocumentElement();
        Element elementSchoolClass=document.createElement("schoolClass");
        elementSchoolClass.setAttribute("name", schoolClass.getName());
        Element elementStudents=document.createElement("students");
        elementSchoolClass.setAttribute("id", String.valueOf(schoolClass.getId()));
        elementSchoolClass.setAttribute("studAmount", String.valueOf(schoolClass.getStudAmount()));
        for(int i=0; i<schoolClass.getStudents().size(); i++) {
            Student student=schoolClass.getStudents().get(i);
            Element elementStudent = document.createElement("student");
            elementStudent.setAttribute("id", String.valueOf(student.getId()));
            Element elementFName = document.createElement("firstName");
            elementFName.setTextContent(student.getFirstName());
            Element elementLName = document.createElement("lastName");
            elementLName.setTextContent(student.getLastName());
            Element elementA = document.createElement("age");
            elementA.setTextContent(String.valueOf(student.getAge()));
            Element elementV = document.createElement("vision");
            elementV.setTextContent(String.valueOf(student.getEyesight()));
            Element elementS = document.createElement("special");
            elementS.setTextContent(student.getSpecial());
            Element elementSubs = document.createElement("subjects");
            elementStudent.appendChild(elementFName);
            elementStudent.appendChild(elementLName);
            elementStudent.appendChild(elementA);
            elementStudent.appendChild(elementV);
            for (int k=0; k<student.getSubjects().size(); k++){
               Subject subject=student.getSubjects().get(k);
                Element elementSub = document.createElement("subject");
                elementSub.setAttribute("id", String.valueOf(subject.getId()));
                Element elementSubN=document.createElement("name");
                elementSubN.setTextContent(subject.getName());
                Element elementSubSc=document.createElement("score");
                elementSubSc.setTextContent(String.valueOf(subject.getAverageMark()));

                elementSub.appendChild(elementSubN);
                elementSub.appendChild(elementSubSc);
                elementSubs.appendChild(elementSub);
            }

            elementStudent.appendChild(elementSubs);
            elementStudents.appendChild(elementStudent);
        }
        elementSchoolClass.appendChild(elementStudents);
        root.appendChild(elementSchoolClass);
        saveDocument(document);
    }
    public void deleteByName(String name){
        Node root = document.getDocumentElement();
        NodeList elementSchoolClasses = root.getChildNodes();
        for (int i=0; i<elementSchoolClasses.getLength(); i++) {
            Node elementSchoolClass = elementSchoolClasses.item(i);
            if (elementSchoolClass.getNodeType() != Node.TEXT_NODE) {
                if (elementSchoolClass.getNodeName().equals("schoolClass")) {
                    if (elementSchoolClass.getAttributes().getNamedItem("name").getTextContent().equals(name)) {
                        elementSchoolClass.getParentNode().removeChild(elementSchoolClass);
                        saveDocument(document);
                    }
                }
            }
        }
    }

    public SchoolClass getByName(String name){
        SchoolClass schoolClass = new SchoolClass();
        List<Student> students = new ArrayList<>();
        Node root = document.getDocumentElement();
        NodeList elementSchoolClasses = root.getChildNodes();
        for (int i=0; i<elementSchoolClasses.getLength(); i++) {
            Node elementSchoolClass = elementSchoolClasses.item(i);
            if (elementSchoolClass.getNodeType() != Node.TEXT_NODE) {
                if (elementSchoolClass.getNodeName().equals("schoolClass")) {
                    if (elementSchoolClass.getAttributes().getNamedItem("name").getTextContent().equals(name)) {
                        schoolClass.setId(Long.parseLong(elementSchoolClass.getAttributes().getNamedItem("id").getTextContent()));
                        schoolClass.setName(elementSchoolClass.getAttributes().getNamedItem("name").getTextContent());
                        schoolClass.setStudAmount(Integer.parseInt(elementSchoolClass.getAttributes().getNamedItem("studAmount").getTextContent()));
                        NodeList elementStudents = elementSchoolClass.getChildNodes();
                        for (int j = 0; j < elementStudents.getLength(); j++) {
                            NodeList elementStudent = elementStudents.item(j).getChildNodes();
                            for (int k = 0; k < elementStudent.getLength(); k++){
                                Student student = new Student();
                                Subject subject = new Subject();
                                student.setSubjects(new ArrayList<>());
                                if (elementStudent.item(k).getNodeType() != Node.TEXT_NODE){
                                    student.setId(Long.parseLong(elementStudent.item(k).getAttributes().getNamedItem("id").getTextContent()));
                                }
                                NodeList elementStudentInfo = elementStudent.item(k).getChildNodes();
                                for (int l = 0; l < elementStudentInfo.getLength(); l++){
                                    Node elementStudentInfoPart = elementStudentInfo.item(l);
                                    if (elementStudentInfoPart.getNodeType() != Node.TEXT_NODE){
                                        switch (elementStudentInfoPart.getNodeName()){
                                            case ("firstName"):
                                                student.setFirstName(elementStudentInfoPart.getTextContent());
                                                break;
                                            case("lastName"):
                                                student.setLastName(elementStudentInfoPart.getTextContent());
                                                break;
                                            case("age"):
                                                student.setAge(Integer.parseInt(elementStudentInfoPart.getTextContent()));
                                                break;
                                            case("vision"):
                                                student.setEyesight(Double.parseDouble(elementStudentInfoPart.getTextContent()));
                                                break;
                                            case("subjects"):
                                                NodeList elementStudentSubject = elementStudentInfoPart.getChildNodes();
                                                for (int o = 0; o < elementStudentSubject.getLength(); o++){
                                                    if (elementStudentSubject.item(o).getNodeType() != Node.TEXT_NODE){
                                                        subject.setId(Long.parseLong(elementStudentSubject.item(o).getAttributes().getNamedItem("id").getTextContent()));
                                                    }
                                                    NodeList elementStudentSubjectInfo = elementStudentSubject.item(o).getChildNodes();
                                                    for (int p = 0; p < elementStudentSubjectInfo.getLength(); p++){
                                                        Node elementStudentSubjectInfoPart = elementStudentSubjectInfo.item(p);
                                                        switch (elementStudentSubjectInfoPart.getNodeName()){
                                                            case ("name"):
                                                                subject.setName(elementStudentSubjectInfoPart.getTextContent());
                                                                break;
                                                            case("score"):
                                                                subject.setAverageMark(Double.parseDouble(elementStudentSubjectInfoPart.getTextContent()));
                                                                student.getSubjects().add(subject);
                                                                subject = new Subject();
                                                                break;
                                                        }
                                                    }
                                                }
                                                break;
                                        }
                                    }
                                }
                                if (student.getFirstName() != null) students.add(student);
                            }
                        }
                        schoolClass.setStudents(students);
                    }
                }
            }
        }
        return schoolClass;
    }

    private static void saveDocument(Document document) throws TransformerFactoryConfigurationError {
        try {
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(document);
            FileOutputStream fos = new FileOutputStream("src/data/schoolClasses.xml");
            StreamResult result = new StreamResult(fos);
            tr.setOutputProperty(OutputKeys.INDENT, "yes");
            tr.transform(source, result);

        }catch(TransformerException | IOException e) {
            e.printStackTrace();
        }
    }
}
