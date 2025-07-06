package control;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.UtenteIscrittoBean;
import model.UtenteIscrittoDAO;

/**
 * Servlet implementation class EffettuaLoginServlet
 */
@WebServlet("/EffettuaLoginServlet")
public class EffettuaLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// memorizzo l'email
		String email = request.getParameter("email");
		String password = request.getParameter("pwd");
		
		// se esiste l'utente
		try 
		{
			if(new UtenteIscrittoDAO().utenteEsiste(email))
			{
				if(new UtenteIscrittoDAO().verificaCredenziali(email, password))
				{	
					// recupera utente bean
					UtenteIscrittoBean utente = new UtenteIscrittoDAO().doRetrieveByKey(email);
					
					// crea sessione
					HttpSession session = request.getSession();
					session.setAttribute("utente", utente); 
					
					response.sendRedirect(request.getContextPath() + "/index.jsp");
				}
					
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
