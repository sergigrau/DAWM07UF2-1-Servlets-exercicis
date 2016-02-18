package edu.fje.daw2;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet que genera una sortida en llenguatge html 
 * utilitzant un descriptor de desplegament
 * @author sergi grau
 * @version 3.10.2013
 */
@WebServlet("/dd2")
public class SenseDescriptorDesplegament extends HttpServlet {
	@Override
        protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html"); // canviar content-type per text/plain
		PrintWriter sortida = response.getWriter();
		String docType = "<!DOCTYPE html>\n";
		sortida.println(docType + "<html>\n"
				+ "<head><title>hola món</title></head>\n"
				+ "<body bgcolor=\"#FDF5E6\">\n" + "<h1>hola món!</h1>\n"
				+ "</body></html>");
	}
       
}
