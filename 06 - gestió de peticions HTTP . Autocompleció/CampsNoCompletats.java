package edu.fje.daw2;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet que implementa una solució per a mostrar els camps que un usuari no ha completat en un formulari
 *
 * @author Sergi Grau
 * @version 2.0 31.10.2013
 */
@WebServlet("/campsNoCompletats")
public class CampsNoCompletats extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @param peticio
     * @param resposta
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest peticio,
            HttpServletResponse resposta) throws ServletException, IOException {

        Persona persona = new Persona();
        UtilitatsJavaBeans.omplirBean(persona, peticio);
        if (persona.isComplert()) {
            // mostrem el resultat
            mostrarDades(peticio, resposta, persona);
        } else {
            // mostrem de nou el formulari perque manquen dades
            mostrarFormulari(peticio, resposta, persona);
        }
    }

    /**
     * Mètode que mostra un resum amb les dades subministrades per l'usuari
     *
     * @param peticio
     * @param resposta
     * @param persona
     * @throws ServletException
     * @throws IOException
     */
    private void mostrarDades(HttpServletRequest peticio,
            HttpServletResponse resposta, Persona persona)
            throws ServletException, IOException {
        processarDades(persona);
        resposta.setContentType("text/html");
        PrintWriter sortida = resposta.getWriter();
        String docType = "<!DOCTYPE html>\n";
        sortida.println(docType + "<html>\n"
                + "<head><title>paràmetres</title></head>\n<body>\n<ul>"
                + "<li>nom: " + persona.getNom() + "</li>\n"
                + "<li>num fills: " + persona.getNumFills() + "</li>\n"
                + "<li>casat: " + persona.isCasat() + "</li>\n"
                + "</ul></body></html>");
    }

    /**
     * Mètode que mostra el formulari on entrar les dades d'una pesona
     *
     * @param peticio
     * @param resposta
     * @param persona
     * @throws ServletException
     * @throws IOException
     */
    private void mostrarFormulari(HttpServletRequest peticio,
            HttpServletResponse resposta, Persona persona)
            throws ServletException, IOException {
        boolean isParcialmentComplert = persona.isParcialmentComplert();
        resposta.setContentType("text/html");
        PrintWriter sortida = resposta.getWriter();
        String docType = "<!DOCTYPE html>\n";
        sortida
                .println(docType
                        + "<html>\n"
                        + "<head><title>formulari</title></head>\n<body>\n"
                        + avis(isParcialmentComplert)
                        + "<form>"
                        + inputElement("nom", "nom", persona.getNom(),
                                isParcialmentComplert)
                        + inputElement("nombre de fills", "numFills", persona
                                .getNumFills(), isParcialmentComplert)
                        + checkbox("està casat?", "casat", persona.isCasat())
                        + "<input type=\"submit\" value=\"enviar\" /></form></body></html>");
    }

    /**
     * Mètode que processaria les dades rebudes des del formulari
     *
     * @param persona
     */
    private void processarDades(Persona persona) {

    }

    /**
     * Mètode que avisa si un formulari està� complert
     *
     * @param isIncomplert
     * @return
     */
    private String avis(boolean isIncomplert) {
        if (isIncomplert) {
            return ("dades incomplertes\n");
        } else {
            return ("");
        }
    }

    /**
     * Mètode que presenta un quadre de text que contindrà� cadenes. 
     * Si està incomplert presenta un missatge indicant-lo
     *
     * @param missatge
     * @param nom
     * @param valor
     * @param isIncomplert
     * @return
     */
    private String inputElement(String missatge, String nom, String valor,
            boolean isIncomplert) {
        String etiqueta = "";
        if (isIncomplert && ((valor == null) || valor.equals(""))) {
            etiqueta = "<b>camp requerit!</b> ";
        }
        return (etiqueta + missatge + ": " + "<input type=\"text\" name=\"" + nom
                + "\"" + " value=\"" + valor + "\" /><br />\n");
    }

    /**
     * Mètode que presenta un quadre text per emmagatzemar enters. 
     * Si està incomplert presenta un missatge indicant-lo.
     *
     * @param missatge
     * @param nom
     * @param valor
     * @param isComplert
     * @return
     */
    private String inputElement(String missatge, String nom, int valor,
            boolean isComplert) {
        String num;
        if (valor == 0) {
            num = "";
        } else {
            num = String.valueOf(valor);
        }
        return (inputElement(missatge, nom, num, isComplert));
    }

    /**
     * Mètode que presenta una casella de verificació. 
     * Si està incomplert presenta un missatge indicant-lo.
     *
     * @param missatge
     * @param nom
     * @param isChecked
     * @return
     */
    private String checkbox(String missatge, String nom, boolean isChecked) {
        String etiqueta = missatge + ": " + "<input type=\"checkbox\" name=\""
                + nom + "\"";
        if (isChecked) {
            etiqueta = etiqueta + " checked";
        }
        etiqueta = etiqueta + " /><br/>\n";
        return (etiqueta);
    }

}
