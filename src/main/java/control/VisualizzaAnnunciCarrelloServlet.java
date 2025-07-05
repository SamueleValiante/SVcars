package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.AnnuncioBean;
import model.CarrelloDAO;
import model.UtenteGuestBean;
import model.UtenteGuestDAO;
import model.UtenteIscrittoBean;

/**
 * Servlet implementation class VisualizzaAnnunciCarrelloServlet
 */
@WebServlet("/VisualizzaAnnunciCarrelloServlet")
public class VisualizzaAnnunciCarrelloServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String tipoUtente = null;
        HttpSession session = request.getSession(false);
        List<AnnuncioBean> annunciCarrello = new ArrayList<AnnuncioBean>();
        
        if(session.getAttribute("utente")==null)
        	tipoUtente = "guest";
        else
        	tipoUtente = "normale";
		
		// se è guest prendo il suo codice utente dal coockie
		if(tipoUtente.equals("guest"))
		{
			String codiceGuest = null;
			Cookie[] cookies = request.getCookies();
			
			// ottengo il cookie con il codice utente
			for (Cookie cookie : cookies) {
                if ("codice_utente_guest".equals(cookie.getName())) {
                    codiceGuest = cookie.getValue();
                    break;
                }
            }
			
			// ottengo l'utente guest e gli annunci del suo carrello
			try {
				UtenteGuestBean user = new UtenteGuestDAO().doRetrieveByKey(codiceGuest);
				annunciCarrello = new CarrelloDAO().getAnnunciCarrello(user.getCodice_carrello());
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
			
		// se è amministratore o normale ottengo la sua email dalla sessione
		else
		{
			// ottengo l'oggetto associato all'utente dalla sessione
			UtenteIscrittoBean utente = (UtenteIscrittoBean) session.getAttribute("utente");
			
			// ottengo tutti i suoi annunci nel carrello
			try {
				annunciCarrello = new CarrelloDAO().getAnnunciCarrello(utente.getCodice_carrello());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		request.setAttribute("annunciCarrello", annunciCarrello);
		request.getRequestDispatcher("jsp/VisualizzaAnnunciCarrello.jsp").forward(request, response);
	}

}
