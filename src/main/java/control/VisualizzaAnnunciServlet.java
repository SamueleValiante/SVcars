package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.AnnuncioBean;
import model.AnnuncioDAO;

public class VisualizzaAnnunciServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		mostraAnnunci(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		mostraAnnunci(request, response);
	}
	
	
	protected void mostraAnnunci(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// otteniamo tutti gli annunci
		AnnuncioDAO daoAnnuncio = new AnnuncioDAO();
		
		try 
		{
			List<AnnuncioBean> annunci = daoAnnuncio.doRetrieveAll("titolo");
			
			request.setAttribute("annunciDB", annunci);
			request.getRequestDispatcher("index.jsp").forward(request, response);
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
