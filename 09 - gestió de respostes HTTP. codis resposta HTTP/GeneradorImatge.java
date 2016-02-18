package edu.fje.daw2;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import javax.imageio.*;

/**
 * Servlet que genera una imatge JPEG, amb un text passat com a paràmetre i que genera una ombra
 *
 * @author Sergi Grau
 * @version 2.0 31.10.2013
 */
public class GeneradorImatge extends HttpServlet {

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

        if (peticio.getParameter("llistaFonts") != null) {
            llistarFontsServidor(resposta);
        } else {
            String text = peticio.getParameter("text");
            if ((text == null) || (text.length() == 0)) {
                text = "manca text a dibuixar";
            }
            String fontNom = peticio.getParameter("fontNom");
            if ((fontNom == null) || (fontNom.length() == 0)) {
                fontNom = "Serif";
            }

            int fontMida;
            try {
                fontMida = Integer.parseInt(peticio.getParameter("fontMida"));
            } catch (NumberFormatException nfe) {
                fontMida = 90;
            }
            resposta.setContentType("image/jpeg");
            ImatgeText.escriureJPEG(ImatgeText.crearImatge(text, fontNom,
                    fontMida), resposta.getOutputStream());
        }
    }

    /**
     * M�tode que retorna una p�gina html amb la llista de fonts disponibles al servidor
     *
     * @param resposta
     * @throws IOException
     */
    private void llistarFontsServidor(HttpServletResponse resposta)
            throws IOException {

        PrintWriter sortida = resposta.getWriter();
        String docType = "<!DOCTYPE html>\n";
        String titol = "Fonts disponibles al servidor";
        sortida.println(docType + "<html>\n" + "<head><title>" + titol
                + "</title></head>\n" + "<body>\n" + titol + "<ul>");
        String[] llistatFonts = ImatgeText.llistarFonts();
        for (int i = 0; i < llistatFonts.length; i++) {
            sortida.println("  <li>" + llistatFonts[i] + "</li>");
        }
        sortida.println("</yl>\n" + "</body></html>");
    }
}

/**
 * Classe que dibuixa un text i una ombra
 *
 * @author sergi grau
 * @version 1.0 31.10.2013
 */
class ImatgeText {

    public static BufferedImage crearImatge(String text, String fontNom,
            int fontMida) {

        Font font = new Font(fontNom, Font.PLAIN, fontMida);
        FontMetrics metrics = getFontMetrics(font);
        int ampladaText = metrics.stringWidth(text);
        int liniaBaseX = ampladaText / 10;
        int amplada = ampladaText + 2 * (liniaBaseX + fontMida);
        int alcada = fontMida * 7 / 2;
        int liniaBaseY = alcada * 8 / 10;

        BufferedImage imatge = new BufferedImage(amplada, alcada,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = (Graphics2D) imatge.getGraphics();
        g2d.setBackground(Color.white);
        g2d.clearRect(0, 0, amplada, alcada);
        g2d.setFont(font);
        g2d.translate(liniaBaseX, liniaBaseY);
        g2d.setPaint(Color.lightGray);
        //transformació 2D. desem la matriu de transformació (coordenades, escales, rotacions, ...)
        AffineTransform origTransform = g2d.getTransform();
        //dibuixem l'ombra
        g2d.shear(-0.95, 0); //inclinació
        g2d.scale(1, 3);//escala
        g2d.drawString(text, 0, 0);
        //restaurem la matriu de transformació original
        g2d.setTransform(origTransform);
        g2d.setPaint(Color.black);
        g2d.drawString(text, 0, 0);
        return (imatge);
    }

    /**
     * Mètode que fa un stream de sortida amb la imatge generada
     *
     * @param imatge
     * @param sortida
     */
    public static void escriureJPEG(BufferedImage imatge, OutputStream sortida) {
        try {
            ImageIO.write(imatge, "jpg", sortida);
        } catch (IOException ioe) {
            System.err.println("Error en stream del JPEG: " + ioe);
        }
    }

    /**
     * Mètode que escriu una imatge en format JPEG en un fitxer
     *
     * @param imatge Imatge
     * @param fitxer Fixter que es vol generar
     */
    public static void escriureJPEG(BufferedImage imatge, File fitxer) {
        try {
            ImageIO.write(imatge, "jpg", fitxer);
        } catch (IOException ioe) {
            System.err.println("Error escrivint JPEG: " + ioe);
        }
    }

    /**
     * Mètode que retorna una llista amb les fonts disponibles en el servidor
     *
     * @return llista de fonts
     */
    public static String[] llistarFonts() {
        GraphicsEnvironment entorn = GraphicsEnvironment
                .getLocalGraphicsEnvironment();
        return (entorn.getAvailableFontFamilyNames());
    }

    /**
     * L'objecte FontMetrics que permet con�ixer les mides possibles de les fonts. 
     * Per a calcular-ho necessitem una imatge temporal.
     */
    private static FontMetrics getFontMetrics(Font font) {
        BufferedImage imatgeTemporal = new BufferedImage(1, 1,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = (Graphics2D) imatgeTemporal.getGraphics();
        return (g2d.getFontMetrics(font));
    }
}
