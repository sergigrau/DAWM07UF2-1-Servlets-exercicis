package edu.fje.daw2;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet que calcula operacions utilitzant la lectura de paràmetres multivaluats
 * 
 * @author sergi grau
 * @version 13.10.2013
 */
@WebServlet("/operands")
public class Operands extends HttpServlet {

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
        resposta.setContentType("text/html");
        PrintWriter sortida = resposta.getWriter();

        String docType = "<!DOCTYPE html>\n";

        sortida.println(docType + "<html>\n<head><title>operacions</title></head>\n" + "<body>\n");
        
        boolean formatIncorrecte=false;
        
        if(peticio.getParameter("operacio")==null || peticio.getParameter("operacio").trim().equals("")){
            formatIncorrecte=true;
        }
        
         if(peticio.getParameter("operand")==null || 
                 peticio.getParameter("operand").trim().equals("") || 
                 peticio.getParameterValues("operand").length!=2){
            formatIncorrecte=true;
        }
         
        if(formatIncorrecte){
           sortida.println("format incorrecte"); 
        }
        else{
             String[] valorsParametres =  peticio.getParameterValues("operand");
             sortida.println(valorsParametres[0]+peticio.getParameter("operacio")+valorsParametres[1]);
             sortida.println(calcularResultat(peticio.getParameter("operacio"), 
                     Integer.parseInt(valorsParametres[0]), Integer.parseInt(valorsParametres[1])));

        }
        
        sortida.println("</body></html>");
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doPost(HttpServletRequest peticio,
            HttpServletResponse resposta) throws ServletException, IOException {
        doGet(peticio, resposta);
    }

    private int calcularResultat(String operacio, int operand1, int operand2){
        switch (operacio){
            case "suma":
                return operand1+operand2;
                
            case "resta":
                return operand1-operand2;
        
        }
        return 0;
    }
}
