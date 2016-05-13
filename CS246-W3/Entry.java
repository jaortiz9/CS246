import java.util.List;
import java.util.ArrayList;

/**                                                                 
 * The entry class
 * This class handels the date information
 * This class also creates an array list of annotations
 */
public class Entry {

   private String data; 
   private String date;
   public List<Annotation> info;

   public Entry(String date){
      this.date = date;
      info      = new ArrayList<Annotation>();
   } 

   public void setData(String data) {
      this.data = data;
   }

   public String displayDate() {
      return date;
   }

   public String displayInfo() {
      return data; 
   }

   public void displayAnnotation() {
      for (Annotation a : info) {
         System.out.println(a.getDisplayText());
      }		
   }  
}