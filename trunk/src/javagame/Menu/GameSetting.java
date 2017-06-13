/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javagame.Menu;

import java.io.File;
import java.io.IOException;
import javagame.Const;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class GameSetting {

    private static int RtlLineCount;
    private static int LtrLineCount;
    private static int CrosswalkMiddlePosition;
    private static int AutoCreateCarRate;
    private static int LineImageNumber;
    private static int SheepImageNumber;
    private static int ChangedLinesDirections;
    private static String CarsNumbers;
    private static String SettingPath = Const.MAIN_SETTING_FILE;

    /// Getter Methods
    public static int getAutoCreateCarRate() {
        return AutoCreateCarRate;
    }

    public static int getRtlLineCount() {
        return RtlLineCount;
    }

    public static int getLtrLineCount() {
        return LtrLineCount;
    }

    public static int getCrosswalkMiddlePosition() {
        return CrosswalkMiddlePosition;
    }
    
    public static int getLineImageNumber() {
        return LineImageNumber;
    }
    
    public static int getSheepImageNumber() {
        return SheepImageNumber;
    }
    
    public static int getChangedLinesDirections() {
        return ChangedLinesDirections;
    }
    
    public static String getCarsNumbers() {
        return CarsNumbers;
    }
    
    /// Setter Methods
    public static void setSettingPath(String path) {
        SettingPath = path;
    }

    public static void setDefaultSettingPath() {
        SettingPath = Const.MAIN_SETTING_FILE;
    }

    public static boolean setRtlLineCount(Object value) {
        int valueAsInt = Integer.parseInt(value.toString().trim());
        if (valueAsInt >= MenuConst.MIN_TOP_LINE_COUNT && valueAsInt <= MenuConst.MAX_TOP_LINE_COUNT) {
            RtlLineCount = valueAsInt;
            return true;
        }
        return false;
    }

    public static boolean setLtrLineCount(Object value) {
        int valueAsInt = Integer.parseInt(value.toString().trim());
        if (valueAsInt >= MenuConst.MIN_BOTTOM_LINE_COUNT && valueAsInt <= MenuConst.MAX_BOTTOM_LINE_COUNT) {
            LtrLineCount = valueAsInt;
            return true;
        }
        return false;
    }

    public static boolean setCrosswalkMiddlePosition(Object value) {
        int valueAsInt = Integer.parseInt(value.toString().trim());
        if (valueAsInt >= MenuConst.MIN_CROSSWALK_POS && valueAsInt <= MenuConst.MAX_CROSSWALK_POS)
        {
            CrosswalkMiddlePosition = valueAsInt;
            return true;
        }
        return false;
    }

    public static boolean setAutoCreateCarRate(Object value) {
        int valueAsInt = Integer.parseInt(value.toString().trim());
        if (valueAsInt >= MenuConst.MIN_CREATE_CAR_RATE && valueAsInt <= MenuConst.MAX_CREATE_CAR_RATE) {
            AutoCreateCarRate = valueAsInt;
            return true;
        }
        return false;  
    }
    public static boolean setChangedLinesDirections(Object value) {
        int valueAsInt = Integer.parseInt(value.toString().trim());
        if (valueAsInt >= MenuConst.MIN_LINE_DIRECTION && valueAsInt <= MenuConst.MAX_LINE_DIRECTION) {
            ChangedLinesDirections = valueAsInt;
            return true;
        }
        return false;  
    }
    
    public static boolean setCarsNumbers(Object value) {
        String valueAsString = value.toString().trim();
        CarsNumbers = valueAsString;
        return true;
    }
    
    
    public static boolean setLineImageNumber(Object value) {
        int valueAsInt = Integer.parseInt(value.toString().trim());
        LineImageNumber = valueAsInt;
        return true;
    }
    
    public static boolean setSheepImageNumber(Object value) {
        int valueAsInt = Integer.parseInt(value.toString().trim());
        SheepImageNumber = valueAsInt;
        return true;
    }

    public static void SaveChanges() {
        writeSetting(SettingPath);
    }

    public static void UpdateSettings() {
        readSetting(SettingPath);
    }

    public static void readSetting(String pathAddress) {
        try {
            String path = Const.ROOT_PATH + pathAddress;
            File inputFile = new File(path);

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(inputFile);

            Node Settings = doc.getFirstChild();
            Node GameSettings = doc.getElementsByTagName("GameSettings").item(0);

            // update GameSettings attribute
            //NamedNodeMap attr = GameSettings.getAttributes();
            //Node nodeAttr = attr.getNamedItem("company");
            //nodeAttr.setTextContent("Lamborigini");
            // loop the GameSettings child node
            NodeList list = GameSettings.getChildNodes();

            for (int temp = 0; temp < list.getLength(); temp++) {
                Node node = list.item(temp);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    if (SettingConst.RtlLineCount.equals(eElement.getNodeName())) {
                        setRtlLineCount(eElement.getTextContent());
                    }  else if (SettingConst.LtrLineCount.equals(eElement.getNodeName())) {
                        setLtrLineCount(eElement.getTextContent());
                    }  else if (SettingConst.CrosswalkMiddlePosition.equals(eElement.getNodeName())) {
                        setCrosswalkMiddlePosition(eElement.getTextContent());
                    }  else if (SettingConst.AutoCreateCarRate.equals(eElement.getNodeName())) {
                        setAutoCreateCarRate(eElement.getTextContent());
                    }  else if (SettingConst.SheepImageNumber.equals(eElement.getNodeName())) {
                        setSheepImageNumber(eElement.getTextContent());
                    }  else if (SettingConst.LineDirection.equals(eElement.getNodeName())) {
                        setChangedLinesDirections(eElement.getTextContent());
                    }  else if (SettingConst.CarsNumbers.equals(eElement.getNodeName())) {
                        setCarsNumbers(eElement.getTextContent());
                    }  else if (SettingConst.LineImageNumber.equals(eElement.getNodeName())) {
                        setLineImageNumber(eElement.getTextContent());
                    }
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            System.err.println("Setting SaveChange() " + ex);
        }
    }

    public static void writeSetting(String path) {
        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Settings");
            doc.appendChild(rootElement);

            // staff elements
            Element staff = doc.createElement("GameSettings");
            rootElement.appendChild(staff);

            Element firstname = doc.createElement(SettingConst.RtlLineCount);
            firstname.appendChild(doc.createTextNode(String.format("%d", getRtlLineCount())));
            staff.appendChild(firstname);
            
            firstname = doc.createElement(SettingConst.LtrLineCount);
            firstname.appendChild(doc.createTextNode(String.format("%d", getLtrLineCount())));
            staff.appendChild(firstname);
            
            firstname = doc.createElement(SettingConst.AutoCreateCarRate);
            firstname.appendChild(doc.createTextNode(String.format("%d", getAutoCreateCarRate())));
            staff.appendChild(firstname);
            
            firstname = doc.createElement(SettingConst.CrosswalkMiddlePosition);
            firstname.appendChild(doc.createTextNode(String.format("%d", getCrosswalkMiddlePosition())));
            staff.appendChild(firstname);
            
            firstname = doc.createElement(SettingConst.LineImageNumber);
            firstname.appendChild(doc.createTextNode(String.format("%d", getLineImageNumber())));
            staff.appendChild(firstname);
            
            firstname = doc.createElement(SettingConst.SheepImageNumber);
            firstname.appendChild(doc.createTextNode(String.format("%d", getSheepImageNumber())));
            staff.appendChild(firstname);

            firstname = doc.createElement(SettingConst.LineDirection);
            firstname.appendChild(doc.createTextNode(String.format("%d", getChangedLinesDirections())));
            staff.appendChild(firstname);
            
            firstname = doc.createElement(SettingConst.CarsNumbers);
            firstname.appendChild(doc.createTextNode(String.format("%s", getCarsNumbers())));
            staff.appendChild(firstname);

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(Const.ROOT_PATH + path));

            transformer.transform(source, result);

        }catch (ParserConfigurationException | TransformerException ex) {
            System.err.println("Setting writeSetting() " + ex);
        }

    }

//    public static void writeSetting(String pathAddress) {
//        try {
//            String path = Const.PATH + pathAddress;
//
//            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
//
//            Node Settings = doc.getFirstChild();
//            Node GameSettings = doc.getElementsByTagName("GameSettings").item(0);
//
//            // update GameSettings attribute
//            //NamedNodeMap attr = GameSettings.getAttributes();
//            //Node nodeAttr = attr.getNamedItem("company");
//            //nodeAttr.setTextContent("Lamborigini");
//            // loop the GameSettings child node
//            NodeList list = GameSettings.getChildNodes();
//
//            for (int temp = 0; temp < list.getLength(); temp++) {
//                Node node = list.item(temp);
//
//                if (node.getNodeType() == Node.ELEMENT_NODE) {
//                    Element eElement = (Element) node;
//                    if (SettingConst.RtlLineCount.equals(eElement.getNodeName())) {
//                        eElement.setTextContent(String.format("%d", RtlLineCount));
//                    } else if (SettingConst.LtrLineCount.equals(eElement.getNodeName())) {
//                        eElement.setTextContent(String.format("%d", LtrLineCount));
//                    } else if (SettingConst.CrosswalkMiddlePosition.equals(eElement.getNodeName())) {
//                        eElement.setTextContent(String.format("%d", CrosswalkMiddlePosition));
//                    } else if (SettingConst.AutoCreateCarRate.equals(eElement.getNodeName())) {
//                        eElement.setTextContent(String.format("%d", AutoCreateCarRate));
//                    }
//                }
//            }
//
//            TransformerFactory transformerFactory = TransformerFactory.newInstance();
//            Transformer transformer = transformerFactory.newTransformer();
//            DOMSource source = new DOMSource(doc);
//
//            File file = new File(path);
//            StreamResult consoleResult = new StreamResult(file);
//
//            transformer.transform(source, consoleResult);
//
//        } catch (ParserConfigurationException | SAXException | IOException | TransformerException ex) {
//            System.err.println("Setting writeSetting() " + ex);
//        }
//    }

}

// try {
//            File inputFile = new File(Const.PATH + Const.SETTING_FILE);
//
//            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
//            Document doc = docBuilder.parse(inputFile);
//            Node cars = doc.getFirstChild();
//            Node supercar = doc.getElementsByTagName("supercars").item(0);
//            // update supercar attribute
//            NamedNodeMap attr = supercar.getAttributes();
//            Node nodeAttr = attr.getNamedItem("company");
//            nodeAttr.setTextContent("Lamborigini");
//
//            // loop the supercar child node
//            NodeList list = supercar.getChildNodes();
//            for (int temp = 0; temp < list.getLength(); temp++) {
//                Node node = list.item(temp);
//                if (node.getNodeType() == Node.ELEMENT_NODE) {
//                    Element eElement = (Element) node;
//                    if ("carname".equals(eElement.getNodeName())) {
//                        if ("Ferrari 101".equals(eElement.getTextContent())) {
//                            eElement.setTextContent("Lamborigini 001");
//                        }
//                        if ("Ferrari 202".equals(eElement.getTextContent())) {
//                            eElement.setTextContent("Lamborigini 002");
//                        }
//                    }
//                }
//            }
//            NodeList childNodes = cars.getChildNodes();
//            for (int count = 0; count < childNodes.getLength(); count++) {
//                Node node = childNodes.item(count);
//                if ("luxurycars".equals(node.getNodeName())) {
//                    cars.removeChild(node);
//                }
//            }
//            // write the content on console
//            TransformerFactory transformerFactory
//                    = TransformerFactory.newInstance();
//            Transformer transformer = transformerFactory.newTransformer();
//            DOMSource source = new DOMSource(doc);
//            System.out.println("-----------Modified File-----------");
//            StreamResult consoleResult = new StreamResult(System.out);
//            transformer.transform(source, consoleResult);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//<?xml version="1.0" encoding="UTF-8" standalone="no"?>
//<cars>
//   <supercars company="Ferrari">
//      <carname type="formula one">Ferrari 101</carname>
//      <carname type="sports">Ferrari 202</carname>
//   </supercars>
//   <luxurycars company="Benteley">
//      <carname>Benteley 1</carname>
//      <carname>Benteley 2</carname>
//      <carname>Benteley 3</carname>
//   </luxurycars>
//</cars>
//
//-----------Modified File-----------
//<?xml version="1.0" encoding="UTF-8" standalone="no"?>
//<cars>
//<supercars company="Lamborigini">
//<carname type="formula one">Lamborigini 001</carname>
//<carname type="sports">Lamborigini 002</carname>
//</supercars></cars> 
