package control;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.GeneraCodici;
import model.Tipo_utente;
import model.UtenteGuestBean;
import model.UtenteGuestDAO;
import model.UtenteIscrittoBean;
import model.UtenteIscrittoDAO;


@WebServlet("/CreaNuovoUtenteServlet")
public class CreaNuovoUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// creiamo un nuovo utente
		UtenteIscrittoBean utente = new UtenteIscrittoBean();
		UtenteIscrittoDAO daoUtente = new UtenteIscrittoDAO();
		
		utente.setEmail(request.getParameter("email"));
		utente.setNome(request.getParameter("nome"));
		utente.setCognome(request.getParameter("cognome"));
		utente.setTipo_utente(Tipo_utente.Utente_normale.toString());
		utente.setPassword(request.getParameter("pwd"));
		utente.setCitta(request.getParameter("citta"));
		utente.setCap(Integer.parseInt(request.getParameter("cap")));
		utente.setVia(request.getParameter("via"));
		
		// genero codice carrello
		GeneraCodici generatore = new GeneraCodici();
		
		// codice utente guest
		String codiceGuest = null;
		
		// ottiene il cookie in cui è memorizzato l'utente guest
		Cookie[] cookies = request.getCookies();
        
        if (cookies != null) 
        {
	        for (Cookie cookie : cookies) {
	            if ("codice_utente_guest".equals(cookie.getName())) {
	                codiceGuest = cookie.getValue();
	                
	                // elimino il cookie
	                Cookie cookie2 = new Cookie("codice_utente_guest", "");
	                cookie2.setMaxAge(0);      // Imposta la scadenza immediata
	                cookie2.setPath("/"); 
	                response.addCookie(cookie2);
	                break;
	            }
	        }
        }
        
        // ottengo l'oggetto dell'utente guest dal db e il codice carrello
        UtenteGuestBean utenteG;
        String codicecarrello = "";
        
        try 
        {
			utenteG = new UtenteGuestDAO().doRetrieveByKey(codiceGuest);
			codicecarrello = utenteG.getCodice_carrello();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
        // imposto il codice carrello del nuovo utente
        utente.setCodice_carrello(codicecarrello);
        
		
		try 
		{
			// salvo l'utente nel db
			daoUtente.doSave(utente);
			
			// cancello l'utente guest dal db
			new UtenteGuestDAO().doDelete(codiceGuest);
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		// salva nella sessione l'utente
		HttpSession session = request.getSession();
	    session.setAttribute("utente", utente);

	    response.sendRedirect(request.getContextPath() + "/index.jsp");
		
		
		
	}

}
