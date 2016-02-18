package edu.fje.daw2;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet que demostra el funcionament del filtratge de caràcters d'entitat HTML
 * Utilitza una classe d'utilitat. Podeu provar-ho passam al param1 el valor <i>n</i>m
 * @author sergi grau
 * @version 3.11.2011
 */
public class FiltreEntitatsHTML extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest peticio, HttpServletResponse resposta) throws ServletException, IOException {
        resposta.setContentType("text/html");
        PrintWriter sortida = resposta.getWriter();

        String docType = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\""
                + "\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n";

        sortida.println(docType + "<html>\n"
                + "<head><title>paràmetres</title></head>\n" + "<body>\n"
                + "<h1>" + peticio.getParameter("param1") + "<br/>"
                + UtilitatServlets.filtre(peticio.getParameter("param1")) + "</h1>\n"
                + "</body></html>");

    }
}

/**
 * Classe que substitueix determinats caràcters HTML 
 * pels seus equivalents entitats de caracter.
 * @author sergi grau
 * @version 3.11.2011
 *
 */
class UtilitatServlets {

    public static String filtre(String cadena) {
        if (!teCaractersEspecials(cadena)) {
            return (cadena);
        }
        /*utilitzem StringBuilder per evitar el fet que els objs String no es poden modificar
        quan es fa es creem copies dels mateixos*/
        StringBuilder filtrada = new StringBuilder(cadena.length());
        char c;
        for (int i = 0; i < cadena.length(); i++) {
            c = cadena.charAt(i);
            switch (c) {
                case '<':
                    filtrada.append("&lt;");
                    break;
                case '>':
                    filtrada.append("&gt;");
                    break;
                case '"':
                    filtrada.append("&quot;");
                    break;
                case '&':
                    filtrada.append("&amp;");
                    break;
                default:
                    filtrada.append(c);
            }
        }
        return (filtrada.toString());
    }

    private static boolean teCaractersEspecials(String cadena) {
        boolean trobat = false;
        if ((cadena != null) && (cadena.length() > 0)) {
            char c;
            for (int i = 0; i < cadena.length()&&!trobat; i++) {
                c = cadena.charAt(i);
                switch (c) {
                    case '<':
                        trobat = true;
                        break;
                    case '>':
                        trobat = true;
                        break;
                    case '"':
                        trobat = true;
                        break;
                    case '&':
                        trobat = true;
                        break;
                }
            }
        }
        return (trobat);
    }
}