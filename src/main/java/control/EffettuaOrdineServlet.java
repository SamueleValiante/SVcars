package control;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.AnnuncioBean;
import model.GeneraCodici;
import model.OrdineBean;
import model.OrdineDAO;
import model.UtenteIscrittoBean;

/**
 * Servlet implementation class EffettuaOrdineServlet
 */
@WebServlet("/EffettuaOrdineServlet")
public class EffettuaOrdineServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// creo Bean ordine
		OrdineBean ordine = new OrdineBean();
		
		ordine.setCodice_ordine(new GeneraCodici().generaCodiceOrdine((UtenteIscrittoBean) request.getSession().getAttribute("utente")));
		ordine.setCodiceFattura(new GeneraCodici().generaCodiceFattura(ordine.getCodice_ordine()));
		ordine.setCosto_prodotti(Double.parseDouble(request.getParameter("totale"))-100);
		ordine.setCosto_spedizione(100);
		ordine.setDataAcquisto(Date.valueOf(LocalDate.now()));
		ordine.setEmail_compratore(((UtenteIscrittoBean)request.getSession().getAttribute("utente")).getEmail());
		ordine.setIndirizzo_destinazione(request.getParameter("indirizzo"));
		ordine.setIndirizzo_origine("Viale del bosco, 5, Milano");
		ordine.setProdotti(((List<AnnuncioBean>)request.getSession().getAttribute("annunciCarrello")));
		ordine.setTempo_spedizione("7 giorni");
		ordine.setTotale(Double.parseDouble(request.getParameter("totale")));
		
		// eseguo doSave dell ordine
		try {
			new OrdineDAO().doSave(ordine);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("ordine", ordine);
		request.getRequestDispatcher("jsp/MostraOrdini.jsp").forward(request, response);
	}

}
