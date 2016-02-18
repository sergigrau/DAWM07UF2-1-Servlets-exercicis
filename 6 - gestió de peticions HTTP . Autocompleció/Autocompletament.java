package edu.fje.daw2;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Classe que realitza l'autocompletament d'un JavaBean. Utilitza el component
 * beanUtils i Logging de http://commons.apache.org/ Cal copiar aquests components
 * (commons-beanutils-1.8.0.jar i commons-logging-1.1.1.jar) al
 * directori lib de Tomcat o dins d'un directori lib dins de WEB-INF
 *  i afegir-ho al classpath. Normalment al fitxer
 * .profile
 * 
 * @author sergi grau
 * @version 31.11.2013
 */
@WebServlet("/apache")
public class Autocompletament extends HttpServlet {

    /**
     * @param peticio
     * @param resposta
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(HttpServletRequest peticio,
            HttpServletResponse resposta) throws ServletException, IOException {
        Persona persona = new Persona();

        UtilitatsJavaBeans.omplirBean(persona, peticio);
        resposta.setContentType("text/html");
        PrintWriter sortida = resposta.getWriter();
        String docType = "<!DOCTYPE html>\n";
        sortida.println(docType + "<html>\n"
                + "<head><title>paràmetres</title></head>\n<body>\n<ul>"
                + "<li>nom: " + persona.getNom() + "</li>\n"
                + "<li>num fills: " + persona.getNumFills() + "</li>\n"
                + "<li>casat: " + persona.isCasat() + "</li>\n" + "</ul></body></html>");
    }
}
