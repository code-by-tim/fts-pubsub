package FTSLocalSystem;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

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
    static public String XMLToString(String filepath) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = builder.parse(new File(filepath));
        doc.getDocumentElement().normalize();

        StringWriter writer = new StringWriter();

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.transform(new DOMSource(doc), new StreamResult(writer));
        String xmlAsString = writer.getBuffer().toString();
        return xmlAsString;
    }

    /* Following method is needed in future versions
    static public Document XMLFromString(String knowledgeUpdate) throws ParserConfigurationException, IOException, SAXException {
        knowledgeUpdate = knowledgeUpdate.replaceAll("[^\\x20-\\x7e\\x0A]", "");
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document updateDoc = builder.parse(new InputSource(new StringReader(knowledgeUpdate)));
        return updateDoc;
    }*/
}
