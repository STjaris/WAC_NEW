package nl.hu.v1wac.fisrtapp.servlets;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(urlPatterns = "/DynamicServlet.do")
public class DynamicServlet extends HttpServlet { 

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		double getalHeel = 0;
		String getal1 = req.getParameter("getal1");
		String getal2 = req.getParameter("getal2");
		String knop = req.getParameter("knop");
		System.out.println(knop);
		double eenGetal = Integer.parseInt(getal1);
		double tweeGetal = Integer.parseInt(getal2);
		
		if(knop.equals("+")) {
			getalHeel = eenGetal + tweeGetal;
		}else if(knop.equals("-")){
			getalHeel = eenGetal - tweeGetal;
		}else if(knop.equals("/")) {
			getalHeel = eenGetal / tweeGetal;
		}else {
			getalHeel = eenGetal * tweeGetal;
		}
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");

		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println(" <title>Dynamic Example</title>");
		out.println(" <body>");
		out.println(" <h2>Dynamic webapplication example</h2>");
		out.println(" <h2>" + getalHeel +  "!</h2>");
		out.println(" </body>");
		out.println("</html>");

	}
}