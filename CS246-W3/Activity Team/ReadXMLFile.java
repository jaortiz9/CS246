import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

class ReadXMLFile {

   public static void main(String argv[]) {

    try {

	SAXParserFactory factory = SAXParserFactory.newInstance();
	SAXParser saxParser = factory.newSAXParser();

	DefaultHandler handler = new DefaultHandler() {

	public void startElement(String uri, String localName,String qName, 
                Attributes attributes) throws SAXException {

		//System.out.println("Start Element :" + qName);

		if (qName.equalsIgnoreCase("CONFERENCE")) {
			System.out.println("Conference : " + attributes.getValue("month")+ "/" + attributes.getValue("year"));
		}

		if (qName.equalsIgnoreCase("SESSION")) {
			System.out.println("\t" + attributes.getValue("name") );
		}

		if (qName.equalsIgnoreCase("SPEAKER")) {
			System.out.println("\t\tSpeaker : " + attributes.getValue("name"));
		}

	}

	public void endElement(String uri, String localName,
		String qName) throws SAXException {

	//	System.out.println("End Element :" + qName);

	}

	public void characters(char ch[], int start, int length) throws SAXException {
        // used for content within the attributes

	}

     };

       saxParser.parse("saxparse.xml", handler);
 
     } catch (Exception e) {
       e.printStackTrace();
     }
  
   }

}