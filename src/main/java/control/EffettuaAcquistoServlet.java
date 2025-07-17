package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.AnnuncioBean;
import model.AnnuncioDAO;

/**
 * Servlet implementation class EffettuaAcquistoServlet
 */
@WebServlet("/EffettuaAcquistoServlet")
public class EffettuaAcquistoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// ottengo la targa e il totale
		String targa = request.getParameter("targa");
		
		// ottengo la lista degli annunci
		List<AnnuncioBean> annunci = (ArrayList<AnnuncioBean>)request.getSession().getAttribute("annunciCarrello");
		List<String> targhe = new ArrayList<String>();
		
		double totale = 0;
		
		try {
			totale = new AnnuncioDAO().doRetrieveByKey(targa).getPrezzo();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(targa == null && annunci != null)
		{
			totale = 0;
			
			for(AnnuncioBean annuncio: annunci)
			{
				targhe.add(annuncio.getTarga());
				totale += annuncio.getPrezzo();
			}
			
			request.setAttribute("targhe", targhe);
			request.setAttribute("totale", totale);
			request.getRequestDispatcher("jsp/EffettuaOrdine.jsp").forward(request, response);
			
			return;
		}
		
		// imposto la request per la jsp per impostare gli annunci e il totale
		request.setAttribute("targa", targa);
		request.setAttribute("totale", totale);
		request.getRequestDispatcher("jsp/EffettuaOrdine.jsp").forward(request, response);
	}

}
