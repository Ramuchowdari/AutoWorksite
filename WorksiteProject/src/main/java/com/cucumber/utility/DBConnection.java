package com.cucumber.utility;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class DBConnection {
	public Connection conn = null;
	int sqlserverport=1433;
	String sqlserver="ZAOMDSQLVLS301";
	private String dbName = "Magnify_HR";
	/* 
	 * 
	 *         
	 *
	 *connectionString="Data Source=ZAOMDSQLVLS301;Initial Catalog=Magnify_HR;Persist Security Info=True;Integrated Security=True;" 
      connectionString="Data Source=ZAOMDSQLVLS301;Initial Catalog=Magnify_TR;Persist Security Info=True;Integrated Security=True;" 
      connectionString="Data Source=ZAOMDSQLVLS301;Initial Catalog=Magnify_ImpExp;Persist Security Info=True;Integrated Security=True;" 

QA:
      connectionString="Data Source=ZAOMQSQLV301\SQLQA301;Initial Catalog=Magnify_HR;Persist Security Info=True;Integrated Security=True;" 
      connectionString="Data Source=ZAOMQSQLV301\SQLQA301;Initial Catalog=Magnify_TR;Persist Security Info=True;Integrated Security=True;" 
      connectionString="Data Source=ZAOMQSQLV301\SQLQA301;Initial Catalog=Magnify_ImpExp;Persist Security Info=True;Integrated Security=True”

*/	
	
//	private String dbURL ="jdbc:sqlserver://"+sqlserver+":"+sqlserverport+";DatabaseName="+dbName+";PersistSecurityInfo=true;user=OMCORE\\X449898;password=my0mn3wworrking@cc123;integratedSecurity=true";
	private String dbURL ="jdbc:sqlserver://"+sqlserver+":"+sqlserverport+";DatabaseName="+dbName+";integratedSecurity=true;PersistSecurityInfo=true";
//	private String dbURL="Data Source=ZAOMDSQLVLS301;Initial Catalog=Magnify_HR;Persist Security Info=True;Integrated Security=True";
	public DBConnection(){
		
	}
		   
	  public DBConnection(String dbName)
	  { 
		this.dbName = dbName; 
		
		dbURL ="jdbc:sqlserver://"+sqlserver+":"+sqlserverport+";DatabaseName="+dbName+";integratedSecurity=true;PersistSecurityInfo=true";
		
		try { 
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			System.out.println(dbURL);
			this.conn = DriverManager.getConnection(dbURL);//here put the new simple url.
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		      try {
		          if (conn != null) {
		              conn.close();
		          }
		        } catch (SQLException ex) {
		            System.out.println(ex.getMessage());
		        }
		}
		
		
	}
	public int updateSql(String sql) throws SQLException {
		Statement sta = conn.createStatement();
		return sta.executeUpdate(sql);
	}
	
	public int updateSqlWithTransaction(String sql) throws SQLException {
		Statement sta = conn.createStatement();
		
		PreparedStatement wsUpdate = conn.prepareStatement(sql);
		
		conn.setAutoCommit(false);
		
		
		return sta.executeUpdate(sql);
	}
 
}
