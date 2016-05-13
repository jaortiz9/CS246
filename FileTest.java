import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.BufferedReader;

class FileTest{

    public FileTest(){
    
    }

    void ReadFile(String filename){

	File theFile = new File(filename);
	if(!theFile.exists()){
	    System.out.println("Bad filename.");
	    System.exit(-1);
	}
	if(!theFile.canRead()){
	    System.out.println("Couldn't read file.");
            System.exit(-2);
	}
	
	try(BufferedReader b = new BufferedReader(new FileReader(filename))){
		String theLine;
		
		while((theLine = b.readLine()) != null){
		    
		    String[]part = theLine.split(" ");
		    int count = part.length;
		    System.out.println(count + ": " + theLine);

		}
	    }
		
	catch(Exception ex){
	    System.out.println("Exception Occured." + ex.getMessage());
            System.exit(-4);
	}
    }

    public static void main(String[] args){

        if(args.length == 0){
            System.out.println("Please specify a filename...");
            System. exit(-3);
        }

        String filename = args[0]; //checking argument line                       
        FileTest tester = new FileTest();
        tester.ReadFile(filename);
    }    
}
