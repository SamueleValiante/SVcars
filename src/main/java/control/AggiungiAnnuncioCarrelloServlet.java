package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.AnnuncioBean;
import model.AnnuncioDAO;
import model.CarrelloBean;
import model.CarrelloDAO;
import model.UtenteIscrittoDAO;

/**
 * Servlet implementation class AggiungiAnnuncioCarrelloServlet
 */
@WebServlet("/AggiungiAnnuncioCarrelloServlet")
public class AggiungiAnnuncioCarrelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// memorizzo targa e carrello utente che effettua aggiunta
		String targa = request.getParameter("targa");
		String carrelloCod = request.getParameter("carrello");
		System.out.println(carrelloCod);
		CarrelloBean carrello = new CarrelloBean();
		
		// verifico che non sia gia stato aggiunto al carrello
		try {
			carrello = new CarrelloDAO().doRetrieveByKey(carrelloCod);
			
			List<AnnuncioBean> annunci = new CarrelloDAO().getAnnunciCarrello(carrelloCod);
			
			if(!annunci.isEmpty())
			{
				Boolean trovata = false;
				for(AnnuncioBean annuncio : annunci)
				{
					if(annuncio.getTarga().equals(targa))
					{
						trovata = true;
					}
				}
				
				if(!trovata)
					new UtenteIscrittoDAO().aggiungiAnnuncioCarrello(new AnnuncioDAO().doRetrieveByKey(targa), carrelloCod);
			} 
			
			else 
			{
				new UtenteIscrittoDAO().aggiungiAnnuncioCarrello(new AnnuncioDAO().doRetrieveByKey(targa), carrelloCod);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/VisualizzaAnnunciCarrelloServlet").forward(request, response);
	}

}
