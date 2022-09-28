package FTSLocalSystem;

import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Publisher {

    XMLHandler xmlHandler = new XMLHandler();

    //Pattern matches a filepath ending with "... .xml".
    Pattern xmlFilePattern = Pattern.compile("\\w*\\.xml");
    // Pattern used to remove leading and trailing ".
    Pattern quotationPattern = Pattern.compile("\"(.*?)\"");

    public void publishRoutine(IBroker broker) {
        while(true) {
            System.out.println("Wollen Sie ein FTS-Update veröffentlichen? y/n");
            Scanner scanner = new Scanner(System.in);
            String intention = scanner.nextLine();
            if (intention.equals("y") || intention.equals("Y")) {
                System.out.println("Bitte geben Sie nun den Pfad zur XML-Datei an:");
                String filepath = scanner.nextLine();
                filepath = this.removeQuotationMarks(filepath); //When copying the filepath in Windows there might be quotation marks
                // Validate Filepath
                if (this.isValidPath(filepath)) {
                    this.publishFTSKnowledge(broker, filepath);
                } else {
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
