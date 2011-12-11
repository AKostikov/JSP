package org.javatrainer;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class newbase
 */
@WebServlet("/newbase")
public class newbase extends HttpServlet {
	private static final long serialVersionUID = 1L;
	java.sql.Connection conn; 
	java.sql.Statement stmt = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public newbase() {
        super();
        // TODO Auto-generated constructor stub
    }

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String result = "Database initialized!";
		System.out.println(request.toString());
		try{
		Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		System.setProperty("derby.system.home", System.getProperty("user.home") + "/RealtyDB");
	    conn = DriverManager.getConnection("jdbc:derby:RealtyDB;create=true");	
	    String sqlCreateBuildingsTable = "CREATE TABLE Buildings(BuildingID int primary key generated always as identity," +
	     		" Street varchar (50), Number int )";
	     String sqlCreateApartmentsTable = "CREATE TABLE Apartments(ApartmentID int primary key generated always as identity," +
	     		" Number int, BuildingID int references Buildings(BuildingID))";


	     
	     stmt = conn.createStatement();
	     
	     conn.setAutoCommit(false);
	     
	     try{
		     stmt.addBatch(sqlCreateBuildingsTable);
		     stmt.addBatch(sqlCreateApartmentsTable);

	     
	     stmt.executeBatch();
	     conn.commit();
	     }
	     catch(SQLException e){
	    	 do {
	    	        System.out.println("\n----- SQLException -----");
	    	        System.out.println("  SQLState:   " + e.getSQLState());
	    	        System.out.println("  Error Code: " + e.getErrorCode());
	    	        System.out.println("  Message:    " + e.getMessage());
	    	        e = e.getNextException();
	    	    } while (e != null);
	     }     
	    
	    conn.setAutoCommit(true);
		}
		catch(SQLException e){
	    	 do {
	    	        System.out.println("\n----- SQLException -----");
	    	        System.out.println("  SQLState:   " + e.getSQLState());
	    	        System.out.println("  Error Code: " + e.getErrorCode());
	    	        System.out.println("  Message:    " + e.getMessage());
	    	        e = e.getNextException();
	    	    } while (e != null);
	     } 
		catch(Exception e){
			System.out.println (e.getMessage ());
			result = "Failed to initialize database...";
		}
		finally{
        	try{
        		   conn.close();  
        	       } catch(Exception e){
        	           e.printStackTrace();
        	       } 
        }
		response.setContentType("text/html;charset=Windows-1251");
	    java.io.PrintWriter out = response.getWriter();
	    out.print("<HEAD><TITLE>");
	    out.print("Calculator");
	    out.print("</TITLE></HEAD><BODY>");
	    out.print("<h1> " + result);
	    out.print("</h1>");
	    out.print("<br>");
	    out.print("<a href=\"http://localhost:8080/myapp/addbuilding.jsp\">OK</a>");
	    out.print("</BODY>");
	    out.close();
	}

}
