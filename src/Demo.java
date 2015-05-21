import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class Demo {
    public static void main(String[] args) {
    	String server;
    	String dbName;
    	String user;
    	String pass;
    	String table;
    	String attribute;
    	double disThreshold,occRel;
    	disThreshold=6.0;
    	Scanner in = new Scanner(System.in);
//        System.out.println("Enter value of disThreshold");
//        disThreshold= in.nextDouble();
         System.out.println("Enter server name");
         server = in.nextLine();
         System.out.println("Enter name of the database");
         dbName = in.nextLine();
         System.out.println("Enter username for database");
         user = in.nextLine();
         System.out.println("Enter password ");
         pass = in.nextLine();
         System.out.println("Enter table name");
         table = in.nextLine();
         System.out.println("Enter name of attribute to be cleaned");
         attribute = in.nextLine();
         System.out.println("Enter disThreshold Value");
         disThreshold = in.nextDouble();
        MySQLAccess dao = new MySQLAccess();
        try {
			dao.connnectDataBase(server,dbName,user,pass);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        
        
        ArrayList<String> input=dao.getValues(table,attribute);
        System.out.println("Number of distinct values before cleaning = "+input.size());
        
      
        if(input==null)
        	return;
        Cleaning cleaning=new Cleaning(input,disThreshold,1.0);
        cleaning.execute();
        
        dao.applyCleaning(cleaning.getClusters(),table,attribute);
        System.out.println(cleaning.getOutputString());
        System.out.println("Number of distinct values after clening = "+cleaning.getClusters().size());

        
    }
    
   
}