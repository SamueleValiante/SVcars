package control;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.GeneraCodici;
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
		utente.setTipo_utente(request.getParameter("tipo_utente"));
		utente.setPassword(request.getParameter("password"));
		utente.setCitta(request.getParameter("citta"));
		utente.setCap(Integer.parseInt(request.getParameter("cap")));
		utente.setVia(request.getParameter("via"));
		
		// genero codice carrello
		GeneraCodici generatore = new GeneraCodici();
		String codice_carrello = generatore.generaCodiceCarrello(utente);
		
		utente.setCodice_carrello(codice_carrello);
		
		// crea cookie 
		Cookie cookie = new Cookie("utenteRegistrato", utente.getEmail());
		cookie.setMaxAge(3600); // 1 ora
		response.addCookie(cookie);
		
		
	}

}
