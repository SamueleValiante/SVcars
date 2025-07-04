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

/**
 * Servlet implementation class VisualizzaAnnunciServlet
 */
@WebServlet("/VisualizzaAnnunciServlet")
public class VisualizzaAnnunciServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// otteniamo tutti gli annunci
		AnnuncioDAO daoAnnuncio = new AnnuncioDAO();
		
		try 
		{
			List<AnnuncioBean> annunci = daoAnnuncio.doRetrieveAll("titolo");
			
			request.setAttribute("annunciDB", annunci);
			request.getRequestDispatcher("jsp/VisualizzaAnnunci.jsp").forward(request, response);
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
