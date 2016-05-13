import java.lang.Exception;
import java.lang.Character;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileInputStream;

import java.util.List;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Properties;

/**                                                                        
 * 
 * @author Janine Ortiz
 * @collaborators James Itthipornvanich
 */

public class Finder {

  /**                                                                        
   * The finder class private variables
   */
   private String fileName;
   private List <String> data;
   private List <Topic> topics;
   private List <File> fileNames;

 /**                                                                        
  * The finder class public variables
  */
  public Finder() {
      fileName   = new String();
      fileNames  = new ArrayList <File>();
      topics     = new ArrayList <Topic>();
      data       = new ArrayList <String>();
   } 
   
  /**                                                                        
   * The one and only main method
   */
   public static void main(String[] args) {
      new Finder().run(args);
   }
   
  /**                                                                        
   * The run function manages the arguments for the program
   * @param String[] args
   */
   public void run(String[] args) {
      if (args.length == 2) {
         getFile(args[0]);
         getFolder(args[1]);
      }
      
      else if (args.length == 1) {
         try {
            FileReader file = new FileReader(args[0]);
            BufferedReader input = new BufferedReader(file);
            getFile(input.readLine());
            getFolder(input.readLine());
            input.close();
         }

         catch (Exception e) {
            System.out.println();
            System.out.println("Error: file '" + args[0] + "' is invalid\n");
         }
      }
      
      parse();
      display();
   }
   
  /**                                                                        
   * The get folder function retrieves the folder which contains all the chapters
   * @param String directory
   */
   public void getFolder(String directory) {
      try {
         File fileDirectory = new File(directory);
         File [] directoryListing = fileDirectory.listFiles();
         Arrays.sort(directoryListing);
         for (File file : directoryListing) {
            fileNames.add(file);
         }
      }
      catch(Exception e) {
         System.out.println();
         System.out.println("Error: directory '" +
                            directory + "' is invalid\n");
      }
   }

  /**                                                                        
   * Parse is what goes through each file and splits or spereates acordingly
   */
   public void parse()
   {
      String text = new String();
      String line = new String();    
      boolean start;
      
      for (File file : fileNames){
         try (Scanner scan = new Scanner(file)) {
               text  = scan.useDelimiter("\\Z").next().toLowerCase();
	       start = true;
               line  = file.getName() + ": ";
               for (Topic topic : topics) {
                  for (String keyword : topic.getTopicKeyWords())
                     if(text.contains(keyword)) {
                        line += (start) ? topic.getTopic() : ", " +
                           topic.getTopic();
                        
                        start = false;
                        break;
                     }
               }
               data.add(line);
            } catch (Exception e){}
      }
   }
   
  /**                                                                        
   * This function gets the topics file ready for parsing
   * @param String topicFile
   */
   public void getFile(String topicFile)
   {
      String line;
      try (BufferedReader read =
           new BufferedReader(new FileReader(topicFile))){
            while((line = read.readLine()) != null) {
               topics.add(new Topic(line));
            }
         } catch (Exception e) {}
   }

  /**                                                                        
   * The display function simply displays the data
   */
   public void display() {
      for(String line : data) {
         System.out.println(line);
      }
   }  
}