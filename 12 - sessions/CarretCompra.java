package edu.fje.daw2;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import javax.servlet.annotation.WebServlet;

/**
 * Servlet que implementa un carret de compra molt senzill. Desa aquesta
 * informació en un ArrayList.
 * Com les sessions es desen en el costat servidor, (el client només utilitza les
 * sessions per a guardar un id)
 * @author Sergi Grau
 * @version 2.0 7/11/2013
 */
@WebServlet("/carretCompra")
public class CarretCompra extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
	
		if(peticio.getParameter("logout")!=null){
			HttpSession session = peticio.getSession();
			session.invalidate();
		}
	}
		/**
     * @param peticio
     * @param resposta
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
		 *      response)
		 */	
        @Override
	protected void doPost(HttpServletRequest peticio,
			HttpServletResponse resposta) throws ServletException, IOException {

		HttpSession session = peticio.getSession();
		ArrayList<Producte> productes = (ArrayList<Producte>) session.getAttribute("productes");
		if (productes == null) {
			productes = new ArrayList<Producte>();
			session.setAttribute("productes", productes);
		}
		String producte = peticio.getParameter("producte");
		int unitats = Integer.parseInt(peticio.getParameter("unitats"));
		resposta.setContentType("text/html");
		PrintWriter out = resposta.getWriter();
		String titol = "productes comprats";
		String docType = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 "
				+ "Transitional//EN\">\n";
		out.println(docType + "<html>\n" + "<head><title>" + titol
				+ "</title></head>\n" + "<body>\n" + titol );
		synchronized (productes) {
			if (producte != null) {
				productes.add(new Producte(producte, unitats));
			}
			if (productes.isEmpty()) {
				out.println("<i>cap producte</i>");
			} else {
				out.println("<ul>");
				for (int i = 0; i < productes.size(); i++) {
					out.println("<li>" + (Producte) productes.get(i)+"</li>");
				}
				out.println("</ul>");
			}
		}
		out.println("</body></html>");
	}

    

}