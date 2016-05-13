import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

/**                                                                        
 * 
 * @author Janine Ortiz
 * @collaborators  James Itthipornvanich  
 */
public class Topic {
    
 /**                                                                        
   * 
   * @param String string
   */
   public Topic(String string) {
      String [] detach = string.split(":");
      detach[1] = detach[1].toLowerCase();
      topic = detach[0];
      keywords =
         new ArrayList<String>(Arrays.asList(detach[1].split(",")));
   }
    
 /**                                                            
   * 
   */
   public String getTopic() {return topic;}
    
 /**                                                                        
   * 
   */
   public List <String> getTopicKeyWords() {return keywords;}
    
 /**                                                                     
   * 
   */         
   private List <String> keywords; 
   private String topic;    
}