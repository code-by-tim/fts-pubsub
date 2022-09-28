package FTSLocalSystem;

import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class provides functionality to publish MTS knowledge to a server
 */
public class Publisher {

    XMLHandler xmlHandler = new XMLHandler();

    //Pattern matches a filepath ending with "... .xml".
    Pattern xmlFilePattern = Pattern.compile("\\w*\\.xml");
    // Pattern used to remove leading and trailing ".
    Pattern quotationPattern = Pattern.compile("\"(.*?)\"");

    /**
     * The poublish routine constantly checks for user input, asking if users want to publish something.
     * If they do it asks for the filepath to the corresponding XML-File, validates that path and publishes it.
     * @param broker Reference to RemoteObject on which to publish
     */
    public void publishRoutine(IBroker broker) {
        while(true) {
            System.out.println("Wollen Sie ein FTS-Update veröffentlichen? y/n");
            Scanner scanner = new Scanner(System.in);
            String intention = scanner.nextLine();
            if (intention.equals("y") || intention.equals("Y")) {
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
            } else if(intention.equals("n") || intention.equals("N")){
                System.out.println("Okay!");
            } else {
                System.out.println("Tut mir leid, das habe ich nicht verstanden!");
            }
        }
    }

    /**
     *
     * @param broker
     * @param filepath
     */
    private void publishFTSKnowledge(IBroker broker, String filepath) {
        try {
            String knowledge = xmlHandler.XMLToString(filepath);
            broker.publish(knowledge);
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
