package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
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
			
			Iterator<AnnuncioBean> iterator = annunci.iterator();
			while (iterator.hasNext()) {
			    AnnuncioBean annuncio = iterator.next();

			    if (request.getParameter("barraRicerca") != null && !request.getParameter("barraRicerca").equals(""))
			        if (!annuncio.getTitolo().toLowerCase().contains(request.getParameter("barraRicerca").toLowerCase()))
			            iterator.remove();

			    if (request.getParameter("marcaAuto") != null && !request.getParameter("marcaAuto").equals(""))
			        if (!annuncio.getMarca().toLowerCase().equals(request.getParameter("marcaAuto").toLowerCase()))
			            iterator.remove();

			    if (request.getParameter("modelloAuto") != null && !request.getParameter("modelloAuto").equals(""))
			        if (!(annuncio.getModello().toLowerCase().equals(request.getParameter("modelloAuto").toLowerCase())))
			            iterator.remove();

			    if (request.getParameter("prezzoMin") != null && !request.getParameter("prezzoMin").equals(""))
			        if (annuncio.getPrezzo() < Double.parseDouble(request.getParameter("prezzoMin")))
			            iterator.remove();

			    if (request.getParameter("prezzoMax") != null && !request.getParameter("prezzoMax").equals(""))
			        if (annuncio.getPrezzo() > Double.parseDouble(request.getParameter("prezzoMax")))
			            iterator.remove();

			    if (request.getParameter("KmMin") != null && !request.getParameter("KmMin").equals(""))
			        if (annuncio.getKm() < Integer.parseInt(request.getParameter("KmMin")))
			            iterator.remove();

			    if (request.getParameter("KmMax") != null && !request.getParameter("KmMax").equals(""))
			        if (annuncio.getKm() > Integer.parseInt(request.getParameter("KmMax")))
			            iterator.remove();

			    if (request.getParameter("annoMin") != null && !request.getParameter("annoMin").equals(""))
			        if (annuncio.getAnno() < Integer.parseInt(request.getParameter("annoMin")))
			            iterator.remove();

			    if (request.getParameter("annoMax") != null && !request.getParameter("annoMax").equals(""))
			        if (annuncio.getAnno() > Integer.parseInt(request.getParameter("annoMax")))
			            iterator.remove();

			    if (request.getParameter("coloreAuto") != null && !request.getParameter("coloreAuto").equals(""))
			        if (!annuncio.getColore().toLowerCase().equals(request.getParameter("coloreAuto").toLowerCase()))
			            iterator.remove();

			    if (request.getParameter("tipologiaAuto") != null && !request.getParameter("tipologiaAuto").equals(""))
			        if (!annuncio.getTipologia().toLowerCase().equals(request.getParameter("tipologiaAuto").toLowerCase()))
			            iterator.remove();
			}

			
			request.setAttribute("annunciCercati", annunci);
			request.getRequestDispatcher("jsp/ricerca.jsp").forward(request, response);
				
		} 
		catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
		}
        
	}

}
