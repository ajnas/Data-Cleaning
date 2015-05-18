

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

public class MySQLAccess {
  private Connection connect = null;
  private Statement statement = null;
  private PreparedStatement preparedStatement = null;
  private ResultSet resultSet = null;
  private String dbName,user,password;

  public void connnectDataBase(String dbName,String user,String password) throws Exception {
    try {
      // This will load the MySQL driver, each DB has its own driver
      Class.forName("com.mysql.jdbc.Driver");
      this.dbName=dbName;
	  this.user=user;
	  this.password=password;
      // Setup the connection with the DB
      connect = DriverManager
          .getConnection("jdbc:mysql://localhost/"+dbName+"?"
              + "user='"+user+"'");
              
      
    } catch (Exception e) {
      throw e;
    }

  }

  
  public ArrayList<String> getValues(String table,String attribute){
	  ArrayList<String> values=new ArrayList<String>();
	  try {
		statement = connect.createStatement();
		resultSet = statement.executeQuery("select "+attribute+" from "+table);
		while(resultSet.next()){
			System.out.println(resultSet.getString(attribute));
			values.add(resultSet.getString(attribute));
			
		}
		return values;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  return null;
  }





public void applyCleaning(ArrayList<Cluster> clusters,String table,String attribute) {
	for(int i=0;i<clusters.size();i++)
		{
			Set<String> candidates = clusters.get(i).getFrequencies().keySet();
			for(String s:candidates){
				try {
					statement = connect.createStatement();
					String query="update "+table+" SET "+attribute+"='"+clusters.get(i).getRep()+"' where "+attribute+"='"+s+"'";
					int result = statement.executeUpdate(query);
		
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
				
			
		
		}
	}
}

 