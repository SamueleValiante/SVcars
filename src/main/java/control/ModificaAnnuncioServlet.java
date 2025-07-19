package control;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.AnnuncioBean;
import model.UtenteIscrittoBean;
import model.UtenteIscrittoDAO;

/**
 * Servlet implementation class ModificaAnnuncioServlet
 */
@WebServlet("/ModificaAnnuncioServlet")
public class ModificaAnnuncioServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
    	// creo un bean annuncio e ci salvo i campi ottenuti nella form
    	AnnuncioBean annuncio = new AnnuncioBean();
    	
    	annuncio.setTarga(request.getParameter("targa"));
    	annuncio.setTitolo(request.getParameter("titolo"));
    	annuncio.setVisibilita(true);
    	annuncio.setDescrizione(request.getParameter("descrizione"));
    	annuncio.setMarca(request.getParameter("marcaAuto"));
    	annuncio.setModello(request.getParameter("modelloAuto"));
    	annuncio.setPrezzo(Integer.parseInt(request.getParameter("prezzo")));
    	annuncio.setKm(Integer.parseInt(request.getParameter("km")));
    	annuncio.setAnno(Integer.parseInt(request.getParameter("anno")));
    	annuncio.setCarburante(request.getParameter("carburante"));
    	annuncio.setTipologia(request.getParameter("tipologiaAuto"));
    	annuncio.setColore(request.getParameter("coloreAuto"));
    	annuncio.setN_porte(Integer.parseInt(request.getParameter("n_porte"))); //
    	annuncio.setCilindrata(Integer.parseInt(request.getParameter("cilindrata")));
    	annuncio.setCitta(request.getParameter("citta"));
    	
    	// ottiene l'email di chi la crea
    	HttpSession session = request.getSession(false); // false = non crea una nuova sessione se non esiste

    	if (session != null) {
            Object utente = session.getAttribute("utente");

            if (utente instanceof UtenteIscrittoBean utente2) {
                annuncio.setEmail(utente2.getEmail()); 
            }
            	
    	}
    	
    	// salvo la modifica
    	try 
    	{
			new UtenteIscrittoDAO().modificaAnnuncio(annuncio);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	request.getRequestDispatcher("/VisualizzaAnnunciServlet").forward(request, response);
    	
	}

}
