package org.javatrainer;
import java.sql.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ViewBuildings
 */
@WebServlet("/ViewBuildings")
public class ViewBuildings extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Connection conn=null;
    Statement stmt=null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewBuildings() {
        super();
        try{

	        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
	        conn = DriverManager.getConnection("jdbc:derby:RealtyDB");
	        String sqlCreateBuildingsTable = "CREATE TABLE Buildings(BuildingID int primary key generated always as identity," +
	        		" Street varchar (50), Number int )";
	        String sqlCreateApartmentsTable = "CREATE TABLE Apartments(ApartmentID int primary key generated always as identity," +
	        		" Number int, BuildingID int references Buildings(BuildingID))";
	        String sqlInsertBuildings = "INSERT INTO Buildings (Street, Number ) VALUES ('Бауманская', 11), ('Стромынка', 22)";
	        String sqlInsertApartments = "INSERT INTO Apartments (Number, BuildingID) VALUES (141, 1), (142, 1), (95, 2), (96, 2)";
	        
	        stmt = conn.createStatement();
	        
	        //conn.setAutoCommit(false);
	            
	   	    //stmt.addBatch(sqlCreateBuildingsTable);
	   	    //stmt.addBatch(sqlCreateApartmentsTable);
	   	    //stmt.addBatch(sqlInsertBuildings);
	   	    //stmt.addBatch(sqlInsertApartments);
	        
	        //stmt.executeBatch();
	       // conn.commit();
	      
	       
	       conn.setAutoCommit(true);
        }
        catch (SQLException e){
        	System.out.println(e.getMessage()); 
            e.printStackTrace(); 
        }
        catch (ClassNotFoundException e){
        	System.out.println(e.getMessage()); 
            e.printStackTrace(); 
        }
        finally{
        	try{
        		conn.setAutoCommit(true);
        		   stmt.close(); 
        		   conn.close();  
        	       } catch(Exception e){
        	           e.printStackTrace();
        	       } 
        }
        
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse res) throws ServletException, IOException {
		String sqlSimpleSelect = "Select * from Buildings";
		String result = "<table width=\"30%\" cellspacing=\"0\" border=\"1\"><tr><th>Street</th><th>Number</th></tr>";
		try{
			 conn = DriverManager.getConnection("jdbc:derby:RealtyDB;create=true");
			 stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sqlSimpleSelect);  
		    
		    while (rs.next()){
		    	 result += "<tr>";
		    	 result += "<td>" + rs.getString("Street") + "</td>";
		    	 result += "<td>" + rs.getInt("Number") + "</td>";
		    	 result += "</tr>";	
		    }
		    result += "</table>";
		}
		catch (SQLException e){
			result = "SQL error!";
		}
		finally{
        	try{
        		   stmt.close(); 
        		   conn.close();  
        	       } catch(Exception e){
        	           e.printStackTrace();
        	       } 
        }
		res.setContentType("text/html;charset=Windows-1251");
	    java.io.PrintWriter out = res.getWriter();
	    out.print("<HEAD><TITLE>");
	    out.print("Buildings");
	    out.print("</TITLE></HEAD><BODY>");
	    out.print("<br>");
	    out.print(result);
	    out.print("<br>");
	    out.print("<a href=\"http://localhost:8080/myapp/addbuilding.jsp\">Back</a>");
	    out.print("</BODY>");
	    out.close();
	}

}
