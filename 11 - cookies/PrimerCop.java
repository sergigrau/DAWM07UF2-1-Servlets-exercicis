package edu.fje.daw2;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Servlet que treballa amb cookies. Mostra un missatge de benvinguda si és el
 * primer cop, en cas contrari mostra una sèrie d'opcions
 * @author Sergi Grau
 * @version 2.0 7/11/2013
 */
@WebServlet("/cookie")
public class PrimerCop extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest peticio,
			HttpServletResponse resposta) throws ServletException, IOException {
		boolean nou = true;

		Cookie[] cookies = peticio.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie c = cookies[i];
				if ((c.getName().equals("repeteix")) &&
				// aquesta verificació es pot ometre
						(c.getValue().equals("si"))) {
					nou = false;
					break;
				}
			}
		}
		String missatge;
		if (nou) {
			Cookie cookieRepeteix = new Cookie("repeteix", "si");
			cookieRepeteix.setMaxAge(60 * 60 * 24 * 365); // 1 any
			resposta.addCookie(cookieRepeteix);
			missatge = "benvingut al site de JEE";
		} else {
			missatge = "opcions disponibles";
		}
		resposta.setContentType("text/html");
		PrintWriter sortida = resposta.getWriter();
		String docType = "<!DOCTYPE html>\n";
		sortida.println(docType + "<html>\n" + "<head><title>" + missatge
				+ "</title></head>\n" + "<body>\n" + missatge + "</body></html>");
	}
}
