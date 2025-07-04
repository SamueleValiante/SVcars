package control;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import model.UtenteIscrittoBean;

// filtro che ottiene l'utente dalla sessione per poi determinare di quale si tratta
@WebFilter("/*")
public class TipoUtenteFilter implements Filter 
{
	@Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException 
    {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(false);
        String tipoUtente = "guest";

        if (session != null) {
            Object utente = session.getAttribute("utente");

            if (utente instanceof UtenteIscrittoBean utente2) {
                tipoUtente = utente2.getTipo_utente().toString(); // es. "Iscritto" o "Amministratore"
            }
            	
        }

        // Salvo nella request per essere letto nella JSP
        req.setAttribute("tipoUtente", tipoUtente);

        // Continua con la richiesta
        chain.doFilter(request, response);
    }
}

