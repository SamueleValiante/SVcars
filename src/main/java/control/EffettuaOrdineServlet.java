package control;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.AnnuncioBean;
import model.AnnuncioDAO;
import model.GeneraCodici;
import model.OrdineBean;
import model.UtenteIscrittoBean;
import model.UtenteIscrittoDAO;

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
		ordine.setCodiceFattura(new GeneraCodici().generaCodiceFattura());
		String totaleStr = request.getParameter("totale");
		
		if (totaleStr != null && !totaleStr.trim().isEmpty()) 
		{
		    double totale = Double.parseDouble(totaleStr.trim());
		    ordine.setCosto_prodotti(totale-100);
		    ordine.setTotale(totale);
		} else {
			ordine.setCosto_prodotti(0);
			ordine.setTotale(100);
		}

		ordine.setCosto_spedizione(100);
		ordine.setDataAcquisto(Date.valueOf(LocalDate.now()));
		ordine.setEmail_compratore(((UtenteIscrittoBean)request.getSession().getAttribute("utente")).getEmail());
		ordine.setIndirizzo_destinazione(request.getParameter("indirizzo"));
		ordine.setIndirizzo_origine("Viale del bosco, 5, Milano");
		
		ordine.setTempo_spedizione("7 giorni");
		
		
		System.out.println(ordine.getTotale());
		
		// eseguo doSave dell ordine
		try {
			if(request.getSession().getAttribute("tipoOrdine").equals("singolo"))
			{
				List<AnnuncioBean> list = new ArrayList<AnnuncioBean>();
				list.add(new AnnuncioDAO().doRetrieveByKey((String)request.getSession().getAttribute("targa")));
				ordine.setProdotti(list);
				new UtenteIscrittoDAO().effettuaOrdineSingolo(ordine, 
																new AnnuncioDAO().doRetrieveByKey((String)request.getSession().getAttribute("targa")), 
																(UtenteIscrittoBean)request.getSession().getAttribute("utente"));
			} else {
				ordine.setProdotti(((List<AnnuncioBean>)request.getSession().getAttribute("annunciCarrello")));
				new UtenteIscrittoDAO().effettuaOrdineCarrello(ordine);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("ordine", ordine);
		request.getRequestDispatcher("jsp/MostraOrdini.jsp").forward(request, response);
	}

}
