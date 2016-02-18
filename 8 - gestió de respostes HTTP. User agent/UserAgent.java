package edu.fje.daw2;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet que mostra una informació� diferent en funció� de la camp User-agent de la capçalera de la petició� HTTP.
 *
 * @author Sergi Grau
 * @version 2.0 31.10.2013
 */
@WebServlet("/frontend")
public class UserAgent extends HttpServlet {

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
        resposta.setContentType("text/html");
        PrintWriter sortida = resposta.getWriter();
        String missatge;
        String navegador = peticio.getHeader("User-Agent");
        if ((navegador != null) && (navegador.indexOf("MSIE") != -1)) {
            missatge = "Internet Explorer";
        } else if ((navegador != null) && navegador.indexOf("WebKit") != -1) {
            missatge = "Webkit (Safari i Chrome)";
        } else {
            missatge = "Firefox";
        }
        String docType = "<!DOCTYPE html>\n";

        sortida
                .println(docType + "<html>\n"
                        + "<head><title></title></head>\n" + "<body>\n"
                        + "<h1>" + missatge + "</h1>\n" + "</body></html>");
    }
}
