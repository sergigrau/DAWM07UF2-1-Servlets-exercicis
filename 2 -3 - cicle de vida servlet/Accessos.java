package edu.fje.daw2;

import java.io.*;
import java.util.Date;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Servlet que mostra el funcionament dels atributs compartits en els Servlets,
 * i el mètode init per a la incialització
 * 
 * @author sergi grau
 * @version 03.10.2013
 */
@WebServlet("/accessos")
public class Accessos extends HttpServlet {

    private int accessos;
    private long dataModificacio;

    /**
     * El mètode init es cridat la primera vegada que es carrega el servlet,
     * abans que cap petició HTTP sigui processada.
     */
    @Override
    public void init() throws ServletException {
        // arrdonomin els milisegons per obtenir segons
        dataModificacio = System.currentTimeMillis() / 1000 * 1000;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter sortida = response.getWriter();
        String docType = "<!DOCTYPE html>\n";
        sortida.println(docType + "<html>\n" + "<head><title>JavaEE</title></head>\n" 
                + "<body>\n"
                 + "<h1>" + accessos++ + "</h1>\n"
                + "<h1>darrera modificació " + new Date(dataModificacio) + "</h1>\n"
                + "<h1>darrer accés " + new Date() + "</h1>\n"
                + "</body></html>");
    }

    /**
     * Retorna l'hora de l'objecte HttpServletRequest ser modificada per última vegada, 
     * en mil · lisegons des de la mitjanit 1 gener 1970 GMT. 
     * Si no es coneix el temps, aquest mètode retorna un nombre negatiu (el valor predeterminat).
     * Servlets que admeten sol · licituds HTTP GET i pot determinar ràpidament l'última data 
     * de modificació de reemplaçar aquest mètode. Això fa que les memòries cau del navegador i el proxy 
     * de treballar més eficaçment, reduint la càrrega en els recursos del servidor i la xarxa.
     * Es pot utilitzar conjuntament amb el camp If-Modified-Since de la linia de petició.
     * @param request peticiñó HTTP
     * @return retorna la data de modificació
     */
    @Override
    public long getLastModified(HttpServletRequest request) {
        return (dataModificacio);
    }

}