package edu.fje.daw2;


import java.io.*;
import java.util.logging.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

/**
 * Servlet que demostra el funcionament d'un upload amb l'API Servlet 3.0
 *
 * @author sergi grau
 * @version 1.0 12.10.2013
 */
@WebServlet(name = "upload", urlPatterns = {"/upload"})
@MultipartConfig
public class ServletUpload extends HttpServlet {

    private final static Logger LOGGER = Logger.getLogger(ServletUpload.class.getCanonicalName());

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        final String rutaDestinacio = request.getParameter("destinacio");
        final Part fitxer = request.getPart("fitxer");
        final String nomFitxer = obtenirNomFitxer(fitxer);

        OutputStream out = null;
        InputStream filecontent = null;
        final PrintWriter writer = response.getWriter();

        try {
            out = new FileOutputStream(new File(rutaDestinacio + File.separator + nomFitxer));
            filecontent = fitxer.getInputStream();

            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = filecontent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            writer.println("Nou fitxer" + nomFitxer + " creat en" + rutaDestinacio);
            LOGGER.log(Level.INFO, "Fitxer{0} ha estat pujat a {1}",
                    new Object[]{nomFitxer, rutaDestinacio});
        } catch (FileNotFoundException fne) {
            writer.println("cal especificar ruta amb permisos, o seleccionar un fitxer");
            writer.println("<br/> ERROR: " + fne.getMessage());

            LOGGER.log(Level.SEVERE, "Problemes amb l'upload. Error: {0}",
                    new Object[]{fne.getMessage()});
        } finally {
            if (out != null) {
                out.close();
            }
            if (filecontent != null) {
                filecontent.close();
            }
            if (writer != null) {
                writer.close();
            }
        }
    }

    private String obtenirNomFitxer(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        LOGGER.log(Level.INFO, "Part Header = {0}", partHeader);
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}
