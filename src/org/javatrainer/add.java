package org.javatrainer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
 * Servlet implementation class add
 */
@WebServlet("/add")
public class add extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection conn=null;
    PreparedStatement stmt=null;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public add() {
        super();
        try{

	        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
	        conn = DriverManager.getConnection("jdbc:derby:RealtyDB");
	        
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String result = "Запись успешно добавлена!";
		System.out.println(request.toString());
		String sqlInsertBuildings = "INSERT INTO Buildings (Street, Number ) VALUES (?, ?)";
		try{
		Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
	    conn = DriverManager.getConnection("jdbc:derby:RealtyDB");	
		PreparedStatement prepstmt = conn.prepareStatement(sqlInsertBuildings);
		prepstmt.setString(1, request.getParameter("street"));
		int num = Integer.parseInt(request.getParameter("number"));
		prepstmt.setInt(2, num);
		prepstmt.execute();
		}
		catch(Exception e){
			System.out.println (e.getMessage ());
			result = "Не удалось добавить запись...";
		}
		finally{
        	try{
        		   stmt.close(); 
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
