package control;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.UtenteIscrittoDAO;

public class VerificaCredenzialiServlet extends HttpServlet 
{
    private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        boolean valide = false;
		
        try {
			valide = new UtenteIscrittoDAO().verificaCredenziali(email, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}

        response.setContentType("application/json");
        response.getWriter().write("{\"valide\": " + valide + "}");
    }
}
