package control;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.GeneraCodici;
import model.UtenteGuestBean;
import model.UtenteGuestDAO;
import model.UtenteIscrittoBean;

// filtro che ottiene l'utente dalla sessione per poi determinare di quale si tratta
public class TipoUtenteFilter implements Filter 
{
	@Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
		
		
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(false);
        String tipoUtente = "guest";
        String tipoUtenteCookie = null;

        // ottengo il cookie tipoutente se cè
        Cookie[] cookies = ((HttpServletRequest) request).getCookies();
        
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
        else
        {
        	
        	// crea nuovo utente guest
        	codiceGuest = new GeneraCodici().generaCodiceUtente();
        	String codiceCarrello = new GeneraCodici().generaCodiceCarrello(codiceGuest);
        	UtenteGuestBean utente = new UtenteGuestBean(codiceGuest, codiceCarrello);
        	
        	// salvo l'utente nel db
        	try {
				new UtenteGuestDAO().doSave(utente);
			} catch (SQLException e) {
				e.printStackTrace();
			}
        	
        	// imposto il cookie per codice_utente_guest
        	Cookie c = new Cookie("codice_utente_guest", codiceGuest);
        	c.setMaxAge(60 * 60 * 24 * 365);  // 1 anno
        	c.setPath("/");
        	((HttpServletResponse) response).addCookie(c);
        }
        
        if(codiceGuest == null)
        {
        	
        	// crea nuovo utente guest
        	codiceGuest = new GeneraCodici().generaCodiceUtente();
        	String codiceCarrello = new GeneraCodici().generaCodiceCarrello(codiceGuest);
        	UtenteGuestBean utente = new UtenteGuestBean(codiceGuest, codiceCarrello);
        	
        	// salvo l'utente nel db
        	try {
				new UtenteGuestDAO().doSave(utente);
			} catch (SQLException e) {
				e.printStackTrace();
			}
        	
        	// imposto il cookie per codice_utente_guest
        	Cookie c = new Cookie("codice_utente_guest", codiceGuest);
        	c.setMaxAge(60 * 60 * 24 * 365);  // 1 anno
        	c.setPath("/");
        	((HttpServletResponse) response).addCookie(c);
        }
        
        if (cookies != null) 
        {
	        for (Cookie cookie : cookies) {
	            if ("tipoUtente".equals(cookie.getName())) {
	                tipoUtenteCookie = cookie.getValue();
	                break;
	            }
	        }
        }
        
        if (session != null) {
            Object utente = session.getAttribute("utente");

            if (utente instanceof UtenteIscrittoBean utente2) {
                tipoUtente = utente2.getTipo_utente().toString(); // es. "Iscritto" o "Amministratore"
            }
            	
        }
        
        if(tipoUtenteCookie == null)
        {
        	
	        if (session != null) {
	        	
	            Object utente = session.getAttribute("utente");
	
	            if (utente instanceof UtenteIscrittoBean utente2) {
	                tipoUtente = utente2.getTipo_utente().toString(); // es. "Iscritto" o "Amministratore"
	            }
	            	
	        }
	        else {
	        	
	            if (cookies != null) 
	            {
	                for (Cookie cookie : cookies) {
	                    if ("codice_utente_guest".equals(cookie.getName())) {
	                        codiceGuest = cookie.getValue();
	                        break;
	                    }
	                }
	            }
	            else
	            {
	            	// crea nuovo utente guest
	            	codiceGuest = new GeneraCodici().generaCodiceUtente();
	            	String codiceCarrello = new GeneraCodici().generaCodiceCarrello(codiceGuest);
	            	UtenteGuestBean utente = new UtenteGuestBean(codiceGuest, codiceCarrello);
	            	
	            	// salvo l'utente nel db
	            	try {
						new UtenteGuestDAO().doSave(utente);
					} catch (SQLException e) {
						e.printStackTrace();
					}
	            	
	            	// imposto il cookie per codice_utente_guest
	            	Cookie c = new Cookie("codice_utente_guest", codiceGuest);
	            	c.setMaxAge(60 * 60 * 24 * 365);  // 1 anno
	            	c.setPath("/");
	            	((HttpServletResponse) response).addCookie(c);
	            }
	        }
	        
	        // imposto il cookie per tipoUtente
        	Cookie c = new Cookie("tipoUtente", tipoUtente);
        	c.setMaxAge(60 * 60 * 24 * 365);  // 1 anno
        	c.setPath("/");
        	((HttpServletResponse) response).addCookie(c);
	        
	        // Salvo nella request per essere letto nella JSP
	        req.setAttribute("tipoUtente", tipoUtente);

	        // Continua con la richiesta
	        chain.doFilter(request, response);
        }
        else {
        	
        	// Salvo nella request per essere letto nella JSP
            req.setAttribute("tipoUtente", tipoUtente);

            // Continua con la richiesta
            chain.doFilter(request, response);
        }
    }
}

