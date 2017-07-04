/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CrossWalk.Menu;

import java.io.File;
import java.io.IOException;
import CrossWalk.Const;
import CrossWalk.Utilities.ExceptionWriter;
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

    private static String SettingPath = Const.MAIN_SETTING_FILE;

    private static int rtlLineCount;
    private static int ltrLineCount;
    private static int crosswalkMiddlePosition;
    private static int autoCreateCarRate;
    private static int lineImageNumber;
    private static int middleLineImageNumber;
    private static int crossWalkImageNumber;
    private static int sheepImageNumber;
    private static int changedLinesDirections;
    private static float carsSpeedFromUser;
    private static String carsNumbers;

    /// Getter Methods
    public static int getAutoCreateCarRate() {
        return autoCreateCarRate;
    }

    public static int getRtlLineCount() {
        return rtlLineCount;
    }

    public static int getLtrLineCount() {
        return ltrLineCount;
    }

    public static int getCrosswalkMiddlePosition() {
        return crosswalkMiddlePosition;
    }

    public static int getLineImageNumber() {
        if(lineImageNumber == 0 )
            return 1;
        return lineImageNumber;
    }
    
     public static int getMiddleLineImageNumber() {
         if(middleLineImageNumber == 0 )
            return 1;
        return middleLineImageNumber;
    }
     
    public static int getCrossWalkImageNumber() {
        if(crossWalkImageNumber == 0 )
            return 1;
        return crossWalkImageNumber;
    }
     
    public static int getSheepImageNumber() {
        if(sheepImageNumber == 0 )
            return 1;
        return sheepImageNumber;
    }

    public static int getChangedLinesDirections() {
        return changedLinesDirections;
    }

    public static String getCarsNumbers() {
        if(carsNumbers == null )
            return "1234";
        return carsNumbers;
    }

    public static float getCarsSpeed() {
        return carsSpeedFromUser;
    }
    
    

    /// Setter Methods
    public static void setSettingPath(String path) {
        SettingPath = path;
    }
//

    public static void SetDefaultSettingPath() {
        SettingPath = Const.MAIN_SETTING_FILE;
    }

    public static boolean setRtlLineCount(Object value) {
        int valueAsInt = Integer.parseInt(value.toString().trim());
        if (valueAsInt >= MenuConst.MIN_TOP_LINE_COUNT && valueAsInt <= MenuConst.MAX_TOP_LINE_COUNT) {
            rtlLineCount = valueAsInt;
            return true;
        }
        return false;
    }

    public static boolean setLtrLineCount(Object value) {
        int valueAsInt = Integer.parseInt(value.toString().trim());
        if (valueAsInt >= MenuConst.MIN_BOTTOM_LINE_COUNT && valueAsInt <= MenuConst.MAX_BOTTOM_LINE_COUNT) {
            ltrLineCount = valueAsInt;
            return true;
        }
        return false;
    }

    public static boolean setCrosswalkMiddlePosition(Object value) {
        int valueAsInt = Integer.parseInt(value.toString().trim());
        if (valueAsInt >= MenuConst.MIN_CROSSWALK_POS && valueAsInt <= MenuConst.MAX_CROSSWALK_POS) {
            crosswalkMiddlePosition = valueAsInt;
            return true;
        }
        return false;
    }

    public static boolean setAutoCreateCarRate(Object value) {
        int valueAsInt = Integer.parseInt(value.toString().trim());
        if (valueAsInt >= MenuConst.MIN_CREATE_CAR_RATE && valueAsInt <= MenuConst.MAX_CREATE_CAR_RATE) {
            autoCreateCarRate = valueAsInt;
            return true;
        }
        return false;
    }

    public static boolean setChangedLinesDirections(Object value) {
        int valueAsInt = Integer.parseInt(value.toString().trim());
        if (valueAsInt >= MenuConst.MIN_LINE_DIRECTION && valueAsInt <= MenuConst.MAX_LINE_DIRECTION) {
            changedLinesDirections = valueAsInt;
            return true;
        }
        return false;
    }

    public static boolean setCarsNumbers(Object value) {
        String valueAsString = value.toString().trim();
        carsNumbers = valueAsString;
        return true;
    }

    public static boolean setLineImageNumber(Object value) {
        int valueAsInt = Integer.parseInt(value.toString().trim());
        lineImageNumber = valueAsInt;
        return true;
    }
    
    public static boolean setMiddleLineImageNumber(Object value) {
        int valueAsInt = Integer.parseInt(value.toString().trim());
        middleLineImageNumber = valueAsInt;
        return true;
    }
    
    public static boolean setCrossWalkImageNumber(Object value) {
        int valueAsInt = Integer.parseInt(value.toString().trim());
        crossWalkImageNumber = valueAsInt;
        return true;
    }

    public static boolean setSheepImageNumber(Object value) {
        int valueAsInt = Integer.parseInt(value.toString().trim());
        sheepImageNumber = valueAsInt;
        return true;
    }

    public static boolean setCarsSpeed(Object value) {
        Float valueAsFloat = Float.parseFloat(value.toString().trim());
        if (valueAsFloat >= MenuConst.MIN_CARS_SPEED && valueAsFloat <= MenuConst.MAX_CARS_SPEED) {
            carsSpeedFromUser = valueAsFloat;
            return true;
        }
        return false;
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
                    if (SettingConst.RTL_LINE_COUNT.equals(eElement.getNodeName())) {
                        setRtlLineCount(eElement.getTextContent());
                    } else if (SettingConst.LTR_LINE_COUNT.equals(eElement.getNodeName())) {
                        setLtrLineCount(eElement.getTextContent());
                    } else if (SettingConst.CROSSWALK_MIDDLE_POSITION.equals(eElement.getNodeName())) {
                        setCrosswalkMiddlePosition(eElement.getTextContent());
                    } else if (SettingConst.AUTO_CREATE_CAR_RATE.equals(eElement.getNodeName())) {
                        setAutoCreateCarRate(eElement.getTextContent());
                    } else if (SettingConst.SHEEP_IMG_NUMBER.equals(eElement.getNodeName())) {
                        setSheepImageNumber(eElement.getTextContent());
                    } else if (SettingConst.LINE_DIRECTION.equals(eElement.getNodeName())) {
                        setChangedLinesDirections(eElement.getTextContent());
                    } else if (SettingConst.CARS_NUMBER.equals(eElement.getNodeName())) {
                        setCarsNumbers(eElement.getTextContent());
                    } else if (SettingConst.LINE_IMG_NUMBER.equals(eElement.getNodeName())) {
                        setLineImageNumber(eElement.getTextContent());
                    } else if (SettingConst.CARS_SPEED_RATE.equals(eElement.getNodeName())) {
                        setCarsSpeed(eElement.getTextContent());
                    } else if (SettingConst.MIDDLE_LINE_IMG_NUMBER.equals(eElement.getNodeName())) {
                        setMiddleLineImageNumber(eElement.getTextContent());
                    } else if (SettingConst.CROSSWALK_IMG_NUMBER.equals(eElement.getNodeName())) {
                        setCrossWalkImageNumber(eElement.getTextContent());
                    }
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            new ExceptionWriter().write(ex);
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

            Element firstname = doc.createElement(SettingConst.RTL_LINE_COUNT);
            firstname.appendChild(doc.createTextNode(String.format("%d", getRtlLineCount())));
            staff.appendChild(firstname);

            firstname = doc.createElement(SettingConst.LTR_LINE_COUNT);
            firstname.appendChild(doc.createTextNode(String.format("%d", getLtrLineCount())));
            staff.appendChild(firstname);

            firstname = doc.createElement(SettingConst.AUTO_CREATE_CAR_RATE);
            firstname.appendChild(doc.createTextNode(String.format("%d", getAutoCreateCarRate())));
            staff.appendChild(firstname);

            firstname = doc.createElement(SettingConst.CROSSWALK_MIDDLE_POSITION);
            firstname.appendChild(doc.createTextNode(String.format("%d", getCrosswalkMiddlePosition())));
            staff.appendChild(firstname);

            firstname = doc.createElement(SettingConst.LINE_IMG_NUMBER);
            firstname.appendChild(doc.createTextNode(String.format("%d", getLineImageNumber())));
            staff.appendChild(firstname);

            firstname = doc.createElement(SettingConst.SHEEP_IMG_NUMBER);
            firstname.appendChild(doc.createTextNode(String.format("%d", getSheepImageNumber())));
            staff.appendChild(firstname);

            firstname = doc.createElement(SettingConst.LINE_DIRECTION);
            firstname.appendChild(doc.createTextNode(String.format("%d", getChangedLinesDirections())));
            staff.appendChild(firstname);

            firstname = doc.createElement(SettingConst.CARS_NUMBER);
            firstname.appendChild(doc.createTextNode(String.format("%s", getCarsNumbers())));
            staff.appendChild(firstname);

            firstname = doc.createElement(SettingConst.CARS_SPEED_RATE);
            firstname.appendChild(doc.createTextNode(String.format("%f", getCarsSpeed())));
            staff.appendChild(firstname);
            
            firstname = doc.createElement(SettingConst.MIDDLE_LINE_IMG_NUMBER);
            firstname.appendChild(doc.createTextNode(String.format("%d", getMiddleLineImageNumber())));
            staff.appendChild(firstname);
            
            firstname = doc.createElement(SettingConst.CROSSWALK_IMG_NUMBER);
            firstname.appendChild(doc.createTextNode(String.format("%d", getCrossWalkImageNumber())));
            staff.appendChild(firstname);

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(Const.ROOT_PATH + path));

            transformer.transform(source, result);

        } catch (ParserConfigurationException | TransformerException ex) {
            new ExceptionWriter().write(ex);
        }

    }
}
