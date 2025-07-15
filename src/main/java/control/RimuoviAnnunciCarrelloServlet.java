package control;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.AnnuncioDAO;
import model.CarrelloBean;
import model.UtenteIscrittoDAO;

/**
 * Servlet implementation class RimuoviAnnunciCarrelloServlet
 */
@WebServlet("/RimuoviAnnunciCarrelloServlet")
public class RimuoviAnnunciCarrelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// memorizzo targa e carrello utente che effettua aggiunta
		String targa = request.getParameter("targa");
		String carrelloCod = request.getParameter("carrello");
		System.out.println(carrelloCod);
		CarrelloBean carrello = new CarrelloBean();
		
		// rimuovo l'annuncio dal carrello
		try {
			new UtenteIscrittoDAO().rimuoviAnnuncioCarrello(new AnnuncioDAO().doRetrieveByKey(targa), carrelloCod);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/VisualizzaAnnunciCarrelloServlet").forward(request, response);
	}

}
