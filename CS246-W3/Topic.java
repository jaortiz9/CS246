/**                                                                        
 * The topic class
 * This class returns the list of topics from the file
 * This class implements annotation
 */
public class Topic implements Annotation {

   private String topics;

   Topic(String topics) {
      this.topics = topics;
   }
   
   public String getDisplayText() {
      String data;
      data = "-" + topics;
      return data;
   }
}