package test;

import model.SchoolClass;
import service.xml.SchoolClassesXmlService;

public class XmlTest {
    public static void main(String[] args) {
        SchoolClassesXmlService xml = new SchoolClassesXmlService();
        SchoolClass schoolClass = xml.getByName("7A");
        System.out.println();
    }
}
