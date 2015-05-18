import java.util.ArrayList;
import java.util.Arrays;

class Demo {
    public static void main(String[] args) {
    	
    	String dbName=args[0];
    	String user=args[1];
    	String pass=args[2];
    	String table=args[3];
    	String attribute=args[4];
        MySQLAccess dao = new MySQLAccess();
        try {
			dao.connnectDataBase(dbName,user,pass);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        ArrayList<String> input=dao.getValues(table,attribute);
        if(input==null)
        	return;
        Cleaning cleaning=new Cleaning(input,6.0,1.0);
        cleaning.execute();
        
        dao.applyCleaning(cleaning.getClusters(),table,attribute);
        System.out.println(cleaning.getOutputString());
        
    }
    
   
}