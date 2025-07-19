package control;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.AnnuncioDAO;

/**
 * Servlet implementation class EliminaAnnuncioServlet
 */
@WebServlet("/EliminaAnnuncioServlet")
public class EliminaAnnuncioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
		// ottengo la targa dell'annuncio
    	String targa = request.getParameter("targa");
    	
    	// elimino l'annuncio
    	try {
			new AnnuncioDAO().doDelete(targa);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	request.getRequestDispatcher("/VisualizzaAnnunciServlet").forward(request, response);
	}

}
