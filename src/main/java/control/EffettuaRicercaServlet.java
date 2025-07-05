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
 * Servlet implementation class EffettuaRicercaServlet
 */
@WebServlet("/EffettuaRicercaServlet")
public class EffettuaRicercaServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// otteniamo tutti gli annunci
		AnnuncioDAO daoAnnuncio = new AnnuncioDAO();		
		try {
			List<AnnuncioBean> annunci = daoAnnuncio.doRetrieveAll("titolo");
			
			for(AnnuncioBean annuncio: annunci)
			{
				if(request.getParameter("barraRicerca") == null || !request.getParameter("barraRicerca").equals(""))
					if(!annuncio.getTitolo().toLowerCase().equals(request.getParameter("barraRicerca").toLowerCase()))
						annunci.remove(annuncio);
				
				if(request.getParameter("marcaAuto") == null || !request.getParameter("marcaAuto").equals(""))
					if(!annuncio.getMarca().toLowerCase().equals(request.getParameter("marcaAuto").toLowerCase()))
						annunci.remove(annuncio);
				
				if(request.getParameter("marcaAuto") == null || !request.getParameter("marcaAuto").equals(""))
					if(!annuncio.getModello().toLowerCase().equals(request.getParameter("modelloAuto").toLowerCase()))
						annunci.remove(annuncio);
				
				if(request.getParameter("prezzoMin") == null || !request.getParameter("prezzoMin").equals(""))
					if(annuncio.getPrezzo() < Double.parseDouble(request.getParameter("prezzoMin")))
						annunci.remove(annuncio);
				
				if(request.getParameter("prezzoMax") == null || !request.getParameter("prezzoMax").equals(""))
					if(annuncio.getPrezzo() > Double.parseDouble(request.getParameter("prezzoMax")))
						annunci.remove(annuncio);
				
				if(request.getParameter("KmMin") == null || !request.getParameter("KmMin").equals(""))
					if(annuncio.getKm() < Integer.parseInt(request.getParameter("KmMin")))
						annunci.remove(annuncio);
				
				if(request.getParameter("KmMax") == null || !request.getParameter("KmMax").equals(""))
					if(annuncio.getKm() > Integer.parseInt(request.getParameter("KmMax")))
						annunci.remove(annuncio);
				
				if(request.getParameter("annoMin") == null || !request.getParameter("annoMin").equals(""))
					if(annuncio.getAnno() < Integer.parseInt(request.getParameter("annoMin")))
						annunci.remove(annuncio);
				
				if(request.getParameter("annoMax") == null || !request.getParameter("annoMax").equals(""))
					if(annuncio.getAnno() > Integer.parseInt(request.getParameter("annoMax")))
						annunci.remove(annuncio);
				
				if(request.getParameter("coloreAuto") == null || !request.getParameter("coloreAuto").equals(""))
					if(!annuncio.getTitolo().toLowerCase().equals(request.getParameter("coloreAuto").toLowerCase()))
						annunci.remove(annuncio);
				
				if(request.getParameter("tipologiaAuto") == null || !request.getParameter("tipologiaAuto").equals(""))
					if(!annuncio.getTitolo().toLowerCase().equals(request.getParameter("tipologiaAuto").toLowerCase()))
						annunci.remove(annuncio);
			}
			
			request.setAttribute("annunciCercati", annunci);
			if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
				request.getRequestDispatcher("jsp/ricerca.jsp").forward(request, response);
			} else {
			    request.getRequestDispatcher("jsp/ricerca.jsp").forward(request, response);
			}
				
		} 
		catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
		}
        
	}

}
