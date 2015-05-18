

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

  public void readDataBase() throws Exception {
    try {
      // This will load the MySQL driver, each DB has its own driver
      Class.forName("com.mysql.jdbc.Driver");
      // Setup the connection with the DB
      connect = DriverManager
          .getConnection("jdbc:mysql://localhost/data_cleaning?"
              + "user='root'");

      // Statements allow to issue SQL queries to the database

      // Result set get the result of the SQL query
     // resultSet = statement
       //   .executeQuery("select city from data_cleaning.address");
//      writeResultSet(resultSet);
//
//      // PreparedStatements can use variables and are more efficient
//      preparedStatement = connect
//          .prepareStatement("insert into  feedback.comments values (default, ?, ?, ?, ? , ?, ?)");
//      // "myuser, webpage, datum, summery, COMMENTS from feedback.comments");
//      // Parameters start with 1
//      preparedStatement.setString(1, "Test");
//      preparedStatement.setString(2, "TestEmail");
//      preparedStatement.setString(3, "TestWebpage");
//      preparedStatement.setDate(4, new java.sql.Date(2009, 12, 11));
//      preparedStatement.setString(5, "TestSummary");
//      preparedStatement.setString(6, "TestComment");
//      preparedStatement.executeUpdate();
//
//      preparedStatement = connect
//          .prepareStatement("SELECT myuser, webpage, datum, summery, COMMENTS from feedback.comments");
//      resultSet = preparedStatement.executeQuery();
//      writeResultSet(resultSet);
//
//      // Remove again the insert comment
//      preparedStatement = connect
//      .prepareStatement("delete from feedback.comments where myuser= ? ; ");
//      preparedStatement.setString(1, "Test");
//      preparedStatement.executeUpdate();
//      
//      resultSet = statement
//      .executeQuery("select * from feedback.comments");
//      writeMetaData(resultSet);
      
    } catch (Exception e) {
      throw e;
    }

  }
  
  public ArrayList<String> getValues(){
	  ArrayList<String> values=new ArrayList<String>();
	  try {
		statement = connect.createStatement();
		resultSet = statement.executeQuery("select city from data_cleaning.address");
		while(resultSet.next()){
			System.out.println(resultSet.getString("city"));
			values.add(resultSet.getString("city"));
			
		}
		return values;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  return null;
  }

  private void writeMetaData(ResultSet resultSet) throws SQLException {
    //   Now get some metadata from the database
    // Result set get the result of the SQL query
    
    System.out.println("The columns in the table are: ");
    
    System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
    for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
      System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
    }
  }

  private void writeResultSet(ResultSet resultSet) throws SQLException {
    // ResultSet is initially before the first data set
    while (resultSet.next()) {
      // It is possible to get the columns via name
      // also possible to get the columns via the column number
      // which starts at 1
      // e.g. resultSet.getSTring(2);
      String user = resultSet.getString("myuser");
      String website = resultSet.getString("webpage");
      String summery = resultSet.getString("summery");
      Date date = resultSet.getDate("datum");
      String comment = resultSet.getString("comments");
      System.out.println("User: " + user);
      System.out.println("Website: " + website);
      System.out.println("Summery: " + summery);
      System.out.println("Date: " + date);
      System.out.println("Comment: " + comment);
    }
  }

  // You need to close the resultSet
  private void close() {
    try {
      if (resultSet != null) {
        resultSet.close();
      }

      if (statement != null) {
        statement.close();
      }

      if (connect != null) {
        connect.close();
      }
    } catch (Exception e) {

    }
  }

public void applyCleaning(ArrayList<Cluster> clusters) {
	for(int i=0;i<clusters.size();i++)
		{
			Set<String> candidates = clusters.get(i).getFrequencies().keySet();
			for(String s:candidates){
				try {
					statement = connect.createStatement();
					String query="update data_cleaning.address SET "+"city"+"='"+clusters.get(i).getRep()+"' where "+"city"+"='"+s+"'";
					int result = statement.executeUpdate(query);
		
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
				
			
		
		}
	}
}

 