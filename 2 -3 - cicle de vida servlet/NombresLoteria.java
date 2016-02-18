package edu.fje.daw2;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Servlet que presenta el nombres de la 6/49 utilitza inicialització de servlet i el mètode getLastModified. Només quan
 * es torna a desplegar l'aplicació es tornen a calcular els nombres. Fa servir la anotació d'inicialització de manera
 * alternativa
 * <servlet>
    <servlet-name>appInfoServlet</servlet-name>
    <init-param>
        <param-name>email</param-name>
        <param-value>sergi.grau@fje.edu</param-value>
    </init-param>
  </servlet>
  * 
 * @author sergi grau
 * @version 3.10.2013
 */

@WebServlet(name="nombresLoteria", urlPatterns ="/nombresLoteria", 
        initParams = {@WebInitParam(name="email", value="sergi.grau@fje.edu"),
        @WebInitParam(name="email2", value="info@fje.edu")})

public class NombresLoteria extends HttpServlet {

    private long dataModificacio;
    private final int[] nombres = new int[6];

    /**
     * El mètode init es cridat la primera vegada que es carrega el servlet, abans que cap petició HTTP es processada.
     *
     * @throws javax.servlet.ServletException
     */
    @Override
    public void init() throws ServletException {
        // arrdonomin els milisegons per obtenir segons
        dataModificacio = System.currentTimeMillis() / 1000 * 1000;
        for (int i = 0; i < nombres.length; i++) {
            nombres[i] = randomNum();
        }
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletConfig config= getServletConfig();
        response.setContentType("text/html");
        PrintWriter sortida = response.getWriter();
        String titol = "6/49";
        String docType = "<!DOCTYPE html>\n";
        sortida.println(docType + "<html>\n" + "<head><title>" + titol
                + "</title></head>\n" + "<body>\n"
                + "<h1>" + titol + "</h1>\n"
                + "<ul>");
        for (int i = 0; i < nombres.length; i++) {
            sortida.println(" <li>" + nombres[i] + "</li>");
        }
        sortida.println("contacte:"+config.getInitParameter("email"));
        sortida.println("</ul></body></html>");
    }

    @Override
    public long getLastModified(HttpServletRequest request) {
        return (dataModificacio);
    }

    /**
     * Mètode que genera un nombre aleatori entre 1 i 49
     */
    private int randomNum() {
        return ((int) (Math.random() * 49) + 1);
    }
}
