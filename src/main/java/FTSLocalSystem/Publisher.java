package FTSLocalSystem;

import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class provides functionality to publish MTS knowledge to a server
 */
public class Publisher {

    //Pattern matches a filepath ending with "... .xml".
    Pattern xmlFilePattern = Pattern.compile("\\w*\\.xml");
    // Pattern used to remove leading and trailing ".
    Pattern quotationPattern = Pattern.compile("\"(.*?)\"");

    /**
     * The poublish routine first asks for the filepath to the corresponding XML-File,
     * then validates the entered path and publishes the XML-File.
     *
     * @param broker Reference to RemoteObject on which to publish
     */
    public void publishRoutine(IBroker broker, Scanner scanner) {
        //Get the filepath to the XML-file
        System.out.println("Bitte geben Sie nun den Pfad zur XML-Datei an:");
        String filepath = scanner.nextLine();
        filepath = this.removeQuotationMarks(filepath); //When copying the filepath in Windows there might be quotation marks
        // Validate Filepath
        if (this.isValidPath(filepath)) {
            System.out.println("Publisher: Veröffentliche Dokument...");
            try {
                // Validate XML File against XSD
                if (!XMLHandler.validateXMLFile(filepath)) throw new Exception();

                // Transform XML-Document to string and publish it
                String knowledge = XMLHandler.xmlToString(filepath);
                broker.publish(knowledge);
                System.out.println("Publisher: Dokument veröffentlicht!");
            } catch (Exception e) {
                System.out.println("Publisher: Dokument konnte nicht veröffentlicht werden.");
            }
        } else {
            // Catch wrong inputs
            System.out.println(filepath);
            System.out.println("ist kein gültiger Dateipfad zu einer XML-Datei!");
        }
    }

    /**
     * Method removes quotation marks from the beginning and end of a string.
     * Helpful when copying the filepath directly out of the windows explorer.
     * @param filepath path to any file
     * @return filepath without surrounding quotation marks
     */
    private String removeQuotationMarks(String filepath) {
        Matcher matcher = quotationPattern.matcher(filepath);
        if (matcher.find())
        {
           return matcher.group(1);
        } else {
            return filepath;
        }
    }

    /**
     * The method checks if the path is a valid path in general and then if it points to a XML-File
     *
     * @param path to a file
     * @return true if it is a valid path pointing to an XML-File. Otherwise false.
     */
    private boolean isValidPath(String path) {
        try {
            // Check if the path is valid
            Paths.get(path);
            // Check if the filepath references an XML file
            Matcher matcher = xmlFilePattern.matcher(path);
            boolean isXML = matcher.find();
            if(!isXML) return false;
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
