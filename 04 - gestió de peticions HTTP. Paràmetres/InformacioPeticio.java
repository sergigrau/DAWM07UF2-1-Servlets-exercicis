package edu.fje.daw2;

import java.io.*;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Classe que mostra diversa informació de la petició HTTP
 * @author sergi grau
 * @version 8.11.2011
 */
@WebServlet(name = "InformacioPeticio", urlPatterns = {"/info"})
public class InformacioPeticio extends HttpServlet {

    private static final long serialVersionUID = 1L;

    
    @Override
    protected void doGet(HttpServletRequest peticio,
            HttpServletResponse resposta) throws ServletException, IOException {

        resposta.setContentType("text/html");
        PrintWriter sortida = resposta.getWriter();
        String docType = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\""
                + "\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n";
        sortida.println(docType
                + "<html>\n"
                + "<head></head>\n"
                + "<body>\n"
                + "<b>mtode de petici: </b>"
                + peticio.getMethod() + "<br />\n"
                + "<b>URI petici: </b>"
                + peticio.getRequestURI() + "<br />\n"
                + "<b>Protocol: </b>"
                + peticio.getProtocol() + "<br />\n"
                + "<table border=1>\n"
                + "<tr>\n"
                + "<th>nom camp</th><th>valor</th>");
        Enumeration<?> nomCapsaleres = peticio.getHeaderNames();
        while (nomCapsaleres.hasMoreElements()) {
            String nomCapsalera = (String) nomCapsaleres.nextElement();
            sortida.println("<tr><td>" + nomCapsalera);
            sortida.println("<td>" + peticio.getHeader(nomCapsalera));
        }
        sortida.println("</table>\n</body></html>");
    }

   
    @Override
    protected void doPost(HttpServletRequest peticio,
            HttpServletResponse resposta) throws ServletException, IOException {
        doGet(peticio, resposta);
    }
}
