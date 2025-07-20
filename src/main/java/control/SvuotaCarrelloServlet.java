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
import model.CarrelloDAO;
import model.UtenteGuestDAO;
import model.UtenteIscrittoBean;

/**
 * Servlet implementation class SvuotaCarrelloServlet
 */
@WebServlet("/SvuotaCarrelloServlet")
public class SvuotaCarrelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
    	HttpSession session2 = request.getSession();
	    UtenteIscrittoBean user = (UtenteIscrittoBean) session2.getAttribute("utente");
	    String codice_carrello = "";
	    
	    if(user != null)
	    {
		    // ottiano il carrello
		    codice_carrello = user.getCodice_carrello();
	    } else {
	    	// ottengo il cookie tipoutente se cè
	        Cookie[] cookies = request.getCookies();
	        
	        String codiceGuest = null;
	        
	        if (cookies != null) 
	        {
	        	
	            for (Cookie cookie : cookies) {
	                if ("codice_utente_guest".equals(cookie.getName())) {
	                    codiceGuest = cookie.getValue();
	                    break;
	                }
	            }
	        }
	        
	        try {
				codice_carrello = new UtenteGuestDAO().doRetrieveByKey(codiceGuest).getCodice_carrello();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    }
	    
	    // svuota il carrello
	    try {
			new CarrelloDAO().svuotaCarrello(codice_carrello);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
	    // restituiiamo il controllo
	    request.getRequestDispatcher("/VisualizzaAnnunciCarrelloServlet").forward(request, response);
	}

}
