package edu.fje.daw2;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet que actua com a controller i que envia diferents codis d'estat segons
 * les sol·licituds que s'efectuin. Implementa un frontend que cerca informació
 * en diferents cercadors. Envia una resposta amb codi 302 (via sendRedirect) si
 * coneix el motor de cerca, altrament envia un 404 (via sendError).
 * 
 * Per exemple google té el següent format http://www.google.com/search?q=
 * @author sergi grau
 * @version 8.11.2013
 */
@WebServlet(name = "codistatus", urlPatterns = {"/codi"})
public class CodiStatus extends HttpServlet {

    /**
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        String cadenaACercar = request.getParameter("cadenaACercar");
        if ((cadenaACercar == null) || (cadenaACercar.length() == 0)) {
            informarError(response, "manca especificar la cadena");
            return;
        }
        /*
         * URLEncoder canvia espais a"+" i codi no alfanumerics a "%XY", on
         * XY es le codi hex del ASCII corresponent
         */
        cadenaACercar = URLEncoder.encode(cadenaACercar, "UTF-8");

        String cercador = request.getParameter("cercador");
        if ((cercador == null) || (cercador.length() == 0)) {
            informarError(response, "manca especificar cercador");
            return;
        }
        String URLcerca = cercador + cadenaACercar;
        if (URLcerca != null) {
            response.sendRedirect(URLcerca);
        } else {
            informarError(response, "cercador no reconegut");
        }

    }

    /**
     * Mètode que presenta un error 404
     * @param response
     * @param message
     * @throws IOException
     */
    private void informarError(HttpServletResponse response, String message)
            throws IOException {
        //error 404, el recurs no està disponible
        response.sendError(HttpServletResponse.SC_NOT_FOUND, message);
    }
}
