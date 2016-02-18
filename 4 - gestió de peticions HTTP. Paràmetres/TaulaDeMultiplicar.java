package edu.fje.daw2;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Servlet que processa una petició GET o una POST per a generar la taula de multiplicar d'un valor passat com a
 * paràmetre
 *
 * @author sergi grau
 * @version 03.10.2013
 */
@WebServlet("/taulaMultiplicar")
public class TaulaDeMultiplicar extends HttpServlet {

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter sortida = response.getWriter();

        String docType = "<!DOCTYPE html>\n";
        sortida.println(docType + "<html>\n"
                + "<head><title>taula de multiplicar</title></head>\n" + "<body>\n<ul>"
                + generarTaula(request.getParameter("nombre"))
                + "</ul></body></html>");

    }

    /**
     * Mètode que genera la taula de multiplicar d'un nombre
     *
     * @param parametre passat
     * @return una cadena amb una llista
     */
    private String generarTaula(String parametre) {
        StringBuilder cadena = new StringBuilder();

        if ((parametre == null) || (parametre.trim().equals(""))) {
            return "manca especificar nombre";
        }

            for (int i = 0; i <= 10; i++) {
                cadena.append("<li>");
                cadena.append(i * Integer.parseInt(parametre));
                cadena.append("</li>");

            }
            return cadena.toString();
        
    }
}
