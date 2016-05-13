import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.NodeList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.lang.Exception;
import java.io.File;

/**                                                           
 * The journal class
 * This class holds main and the run function. This file also 
 * creates an array list of entries and displays the data that 
 * we receive from a file.
 * @author Janine Ortiz
 * @collaborators James Itthipornvanich
 */

public class Journal {

   // private variables
   private Document doc;
   private String fileName;
   private List<Entry> journalEntries;

    // the main method
   public static void main(String[] args){
      if (args.length == 1) new Journal().run(args);
      else System.out.println("Error: no file name included");
   }
    
   // make filname and the list
   public Journal(){
      fileName       = new String();
      journalEntries = new ArrayList<Entry>();
   }

   // retrieves the xml file that we want to parse
   public void createDocument(String[] args){
      File file = new File(args[0]);
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      try {
         DocumentBuilder db = dbf.newDocumentBuilder();
         doc = db.parse(file);
      } catch (Exception e){
         System.out.println("Error reading xml file: " + file);
      }
      doc.getDocumentElement().normalize();
   }

   // tells the program what key words to look for
   public void createObjects() {
      Element root = doc.getDocumentElement();
      NodeList entries = root.getElementsByTagName("entry");

      for (int i = 0; i < entries.getLength(); ++i) {
         Element e = (Element) entries.item(i);
         
         Entry temp = new Entry(e.getAttribute("date"));
         journalEntries.add(temp);

         NodeList scriptures = e.getElementsByTagName("scripture");
         for (int j = 0; j < scriptures.getLength(); ++j) {
            Element scrpt = (Element) scriptures.item(j);
            Scripture tmpScrpt = new Scripture();
            tmpScrpt.setBook(scrpt.getAttribute("book"));
            tmpScrpt.setChapter(scrpt.getAttribute("chapter"));
            tmpScrpt.setStartVerse(scrpt.getAttribute("startVerse"));
            tmpScrpt.setEndVerse(scrpt.getAttribute("endVerse"));
            journalEntries.get(i).info.add(tmpScrpt);
         }

         NodeList topics = e.getElementsByTagName("topic");
         for(int j = 0; j < topics.getLength(); ++j) {
            Element tpc = (Element) topics.item(j);

            Topic tmpTopic = new Topic(tpc.getAttribute("name"));
            journalEntries.get(i).info.add(tmpTopic);
         }

         NodeList contentInfo = e.getElementsByTagName("content");
         if(contentInfo.getLength() != 0) {
            journalEntries.get(i).
               setData(contentInfo.item(0).
                       getTextContent().replaceAll("\t", ""));
         }
      }
   }

   // displays the data in the format required by the instructions
   public void display() {
   
      for (Entry e : journalEntries) {
         System.out.println("\n-----");
         System.out.println("Entry");
         System.out.println("Date: " + e.displayDate());
         System.out.println("Content:");
         System.out.println(e.displayInfo());
         System.out.println("Annotations:");
         e.displayAnnotation();
      }
   }

   // the run function that calls the other functions
   public void run(String[] args){
      fileName = args[0];
      createDocument(args);
      createObjects();
      display();
   }
}