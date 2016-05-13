/**                                                                        
 * The scritpure class
 * This class returns the list of scriptures from the file
 * in an orderly fashion with no random : or -
 * This class implements annotation
 */
public class Scripture implements Annotation  {
   
   private String book;
   private String chapter;
   private String endVerse;
   private String startVerse;

   // getters
   public String getBook() {
      return book;
   } 

   public String getChapter() {
      return chapter; 
   }

   public String getEndVerse() {
      return endVerse; 
   }

   public String getStartVerse() {
      return startVerse;
   }

   // mutators
   public void setBook(String book) {
      this.book = book;
   } 

   public void setChapter(String chapter) {
      this.chapter = chapter;
   }

   public void setEndVerse(String endVerse) {
      this.endVerse = endVerse;
   }
   
   public void setStartVerse(String startVerse) {
      this.startVerse = startVerse;
   }

   // overide
   public String getDisplayText() {
      String text;
      text = "-" + book + " " + chapter +
         (startVerse == "" ? "" : ":" + startVerse);
      text += endVerse != "" ? "-" + endVerse : ""; 
      return text;
   }
}