package control;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.UtenteIscrittoDAO;

/**
 * Servlet implementation class VerificaEmailServlet
 */
public class VerificaEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		String email = request.getParameter("email");
	    boolean esiste = false;
			
	    try {
	    	esiste = new UtenteIscrittoDAO().utenteEsiste(email);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	    response.setContentType("application/json");
	    response.getWriter().write("{\"esiste\": " + esiste + "}");
	}

}
