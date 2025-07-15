package control;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.AnnuncioBean;
import model.AnnuncioDAO;

/**
 * Servlet implementation class VisualizzaAnnuncioCarrelloServlet
 */
@WebServlet("/VisualizzaAnnuncioCarrelloServlet")
public class VisualizzaAnnuncioCarrelloServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// mi salvo la targa
		String targa = request.getParameter("targa");
		AnnuncioBean annuncio = new AnnuncioBean();
		
		// mi salvo tutti i dettagli 
		try {
			annuncio = new AnnuncioDAO().doRetrieveByKey(targa);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(annuncio != null)
		{
			request.setAttribute("annuncio", annuncio);
			request.getRequestDispatcher("jsp/MostraAnnuncioCarrello.jsp").forward(request, response);
		}
		
		else
		{
			// forward a error page: 404
		}
	}

}
