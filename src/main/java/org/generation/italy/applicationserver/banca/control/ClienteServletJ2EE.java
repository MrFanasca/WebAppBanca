package org.generation.italy.applicationserver.banca.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.generation.italy.applicationserver.banca.model.BancaModelException;
import org.generation.italy.applicationserver.banca.model.TestJdbcBanca;
import org.generation.italy.applicationserver.banca.model.entity.Movimento;

@WebServlet(urlPatterns = {"/versamento", "/form-versamento"})       //java annotation WebServlet: indicazione per il container (GlassFish) con le action della URI inviata dal client che la servlet intende gestire
public class ClienteServletJ2EE extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void init() {                                                        //metodo che viene richiamato dal container al momento della installazione della webapp in esso con mappatura della servlet (l'altro è 'destroy' (al momento della rimozione della servlet dal container), non gestito in questa servlet).
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) //metodo chiamato dal container (GlassFish), a seguito di ricezione da parte sua del messaggio HTTP-Request, con metodo POST inviato dal client (browser)
            throws ServletException, IOException {
        executeAction(request, response);                                               //re-inoltra al metodo doGet la gestione della action | request e response sono istanze di tipo HttpServletRequest ed HttpServletResponse, create dal container per fornire a e ricevere dalla servlet i dettagli circa i messaggi di HTTP-Request ed HTTP-Response ricevuti da ed inviati al client.
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) //metodo chiamato dal container (GlassFish), a seguito di ricezione da parte sua del messaggio HTTP-Request, con metodo GET inviato dal client (browser)
            throws ServletException, IOException {
        executeAction(request, response);                                               //re-inoltra al metodo doGet la gestione della action | request e response sono istanze di tipo HttpServletRequest ed HttpServletResponse, create dal container per fornire a e ricevere dalla servlet i dettagli circa i messaggi di HTTP-Request ed HTTP-Response ricevuti da ed inviati al client.
   	
    }
    
    protected void executeAction(HttpServletRequest request, HttpServletResponse response) //metodo chiamato dal container (GlassFish), a seguito di ricezione da parte sua del messaggio HTTP-Request, con metodo GET inviato dal client (browser)
            throws ServletException, IOException {
    	
        String actionName = request.getServletPath();                               //parte action della URI: gestione della azione applicativa, la parte della URL dopo il nome della webapp...

        switch (actionName.toLowerCase().trim()) {
        
        	case "/versamento":
        		actionVersamento(request, response);
        		break;
        		
        	case "/form-versamento":
        		actionFormVersamento(request, response);
        		break;
        }
    }
    
    private void actionFormVersamento (HttpServletRequest request, HttpServletResponse response) 
    																throws ServletException, IOException {
    	
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/form-versamento.jsp");
    	
    	dispatcher.forward(request, response);
    }
	
    private void actionVersamento (HttpServletRequest request, HttpServletResponse response)
    																throws ServletException, IOException {
    	
    	String messageToShow = UserMessages.msgEsitoVersamentoOk;
    	
    	String ibanString = request.getParameter("iban") !=null ? request.getParameter("iban") : "";
    	String importoString = request.getParameter("importo") != null ? request.getParameter("importo") : "";
    	
    	//controlli sintattici e semantici su parametri da web
    	if ( 
    		(!UtilitiesControlliSintatticiSemantici.checkFormatIban(ibanString))
    		
    		||
    		
    		(!UtilitiesControlliSintatticiSemantici.checkSementicImporto(importoString)) 
    		
    		) {
    			
    			messageToShow = UserMessages.msgErroreParametriVersamento;    		
    	} else {
    		
    		Float importoFloat = new Float(importoString);
    		
    		Movimento movimento = new Movimento(ibanString, importoFloat, "V");
    		
    		try {
				
    			TestJdbcBanca testJdbcBanca = new TestJdbcBanca();
    			testJdbcBanca.getMovimentoDao().addMovimento(movimento);
    			
    			messageToShow = UserMessages.msgEsitoVersamentoOk;
			} catch (BancaModelException e) {

				messageToShow = UserMessages.msgErroreOperazioneVersamento;
				e.printStackTrace();
			}
    	}
    	
    	request.setAttribute("message-to-show", messageToShow);
    	
    	RequestDispatcher dispatcher = request.getRequestDispatcher("message.jsp");
    	dispatcher.forward(request, response);
    }    
    
}
