package org.javatrainer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloServlet
 */
@WebServlet("/calc")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse res) throws ServletException, IOException {
		String p1Name = "p1";
		String p2Name = "p2";
		String p1 = request.getParameter(p1Name);
		String p2 = request.getParameter(p2Name);
		String result = "parameters not defined";
		if(p1 != null && p2 != null){
			try{
				int p1Val = Integer.parseInt(p1);
				int p2Val = Integer.parseInt(p2);
				result = Integer.toString(p1Val + p2Val);
			}
			catch (NumberFormatException e){
				result = "wrong format";
			}
		}
		res.setContentType("text/html;charset=Windows-1251");
	    java.io.PrintWriter out = res.getWriter();
	    out.print("<HEAD><TITLE>");
	    out.print("Calculator");
	    out.print("</TITLE></HEAD><BODY>");
	    out.print("<h1>Result: " + result);
	    out.print("</h1></BODY>");
	    out.close();
	}

}
