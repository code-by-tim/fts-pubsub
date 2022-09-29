package FTSLocalSystem;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;

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
    static public String xmlToString(String filepath) throws ParserConfigurationException, IOException, SAXException, TransformerException {
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

    /**
     * The method validates the specified XML-File against the MTS-Datamodel standard.
     * If the XML-File does not comply with the XSD, the reason will be printed and false will be returned.
     * @param filepath path to an XML-File
     * @return true if the XML-File contains a valid XML structure according to the XSD. Otherwise returns false.
     */
    static public boolean validateXMLFile(String filepath) {
        URL schemaFile = ClassLoader.getSystemClassLoader().getResource("FTSDatamodel.xsd");
        Source xmlFile = new StreamSource(new File(filepath));
        SchemaFactory schemaFactory = SchemaFactory
                .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            Schema schema = schemaFactory.newSchema(schemaFile);
            Validator validator = schema.newValidator();
            validator.validate(xmlFile);
            return true;
        } catch (Exception e) {
            System.out.println("XMLHandler: Dokument entspricht nicht dem XSD-Standard. Grund: ");
            System.out.println(e.getMessage());
            return false;
        }

    }

    /**
     * This method will be used in future updates. It creates a runtime representation of the XML-Document
     * described by the method parameter
     * @param knowledgeUpdate String representation of a XML-Document
     * @return Document A runtime representation of the specified XML-Document
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    static public Document xmlFromString(String knowledgeUpdate) throws ParserConfigurationException, IOException, SAXException {
        knowledgeUpdate = knowledgeUpdate.replaceAll("[^\\x20-\\x7e\\x0A]", "");
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document updateDoc = builder.parse(new InputSource(new StringReader(knowledgeUpdate)));
        return updateDoc;
    }
}
