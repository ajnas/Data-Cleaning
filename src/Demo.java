import java.util.ArrayList;
import java.util.Arrays;

class Demo {
    public static void main(String[] args) {
    	
        MySQLAccess dao = new MySQLAccess();
        try {
			dao.readDataBase();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        ArrayList<String> input=dao.getValues();
        if(input==null)
        	return;
        Cleaning cleaning=new Cleaning(input,6.0,1.0);
        cleaning.execute();
        
        dao.applyCleaning(cleaning.getClusters());
        System.out.println(cleaning.getOutputString());
        
    }
    
    
    
  private static ArrayList<String> getInput(){
	  
	  ArrayList<String> result=new ArrayList<String>(Arrays.asList("Hello","Hello","Hello","Helo","Hello","Hallo","Hallo","Hallo","Halo","Hello","Halo","Hellow","hello","hello","hello","hallo","hallo","halo","hello","halo","halo","hellow","hellow","hallo")
);
	  return result;
	  
  }
}