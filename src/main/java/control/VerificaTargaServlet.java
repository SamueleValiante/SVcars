package control;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.AnnuncioDAO;


public class VerificaTargaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String targa = request.getParameter("targa");
        
        boolean valide = false;
		
        try {
			valide = new AnnuncioDAO().verificaTargaEsiste(targa);
		} catch (SQLException e) {
			e.printStackTrace();
		}

        response.setContentType("application/json");
        response.getWriter().write("{\"valide\": " + valide + "}");
	}
}
