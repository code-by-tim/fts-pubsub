package FTSLocalSystem;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * Class providing different methods to handle XML-Documents and XML representations during runtime.
 */
public class XMLHandler {

    /**
     * Method returns a String representation of the specified XML-Document.
     *
     * Inspired by https://www.baeldung.com/java-xerces-dom-parsing
     * @param filepath absolute path to the XML file
     * @return
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public String XMLToString(String filepath) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = builder.parse(new File(filepath));
        doc.getDocumentElement().normalize();
        return doc.getDocumentElement().getTextContent();
    }

}
