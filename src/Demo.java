import java.util.ArrayList;
import java.util.Arrays;

class Demo {
    public static void main(String[] args) {
    	
        
        ArrayList<String> input=getInput();
        Cleaning cleaning=new Cleaning(input,1.0,1.0);
        cleaning.execute();
        System.out.println(cleaning.getOutput());
        
    }
    
    
    
  private static ArrayList<String> getInput(){
	  
	  ArrayList<String> result=new ArrayList<String>(Arrays.asList("Hello","Hello","helo"));
	  return result;
	  
  }
}