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
     * The poublish routine first asks for the filepath to the corresponding XML-File, then validates the entered path and publishes the XML-File.
     * @param broker Reference to RemoteObject on which to publish
     */
    public void publishRoutine(IBroker broker, Scanner scanner) {
        //Get the filepath to the XML-file
        System.out.println("Bitte geben Sie nun den Pfad zur XML-Datei an:");
        String filepath = scanner.nextLine();
        filepath = this.removeQuotationMarks(filepath); //When copying the filepath in Windows there might be quotation marks
        // Validate Filepath
        if (this.isValidPath(filepath)) {
            this.publishFTSKnowledge(broker, filepath);
        } else {
            // Catch wrong inputs
            System.out.println(filepath);
            System.out.println("is not a valid filepath!");
        }
    }

    /**
     *
     * @param broker
     * @param filepath
     */
    private void publishFTSKnowledge(IBroker broker, String filepath) {
        try {
            System.out.println("Publisher: Publishing Document...");
            String knowledge = XMLHandler.XMLToString(filepath);
            broker.publish(knowledge);
            System.out.println("Publisher: Document published!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String removeQuotationMarks(String filepath) {
        Matcher matcher = quotationPattern.matcher(filepath);
        if (matcher.find())
        {
           return matcher.group(1);
        } else {
            return filepath;
        }
    }

    private boolean isValidPath(String path) {
        // Check if the path is valid
        try {
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
