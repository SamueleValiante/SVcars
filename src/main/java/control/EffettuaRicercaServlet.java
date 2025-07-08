package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{	
		boolean filtro = false;
		
		// otteniamo tutti gli annunci
		AnnuncioDAO daoAnnuncio = new AnnuncioDAO();		
		try {
			List<AnnuncioBean> annunci = daoAnnuncio.doRetrieveAll("titolo");
			
			List<AnnuncioBean> annunciFiltrati = new ArrayList<>();

			for (AnnuncioBean annuncio : annunci) {
			    boolean valido = true;

			    if (request.getParameter("barraRicerca") != null && !request.getParameter("barraRicerca").isEmpty())
			        if (!annuncio.getTitolo().toLowerCase().contains(request.getParameter("barraRicerca").toLowerCase()))
			            valido = false;
			    
			    if (request.getParameter("barraRicercaFiltro") != null && !request.getParameter("barraRicercaFiltro").isEmpty())
			        if (!annuncio.getTitolo().toLowerCase().contains(request.getParameter("barraRicercaFiltro").toLowerCase()))
			            valido = false;

			    if (request.getParameter("marcaAuto") != null && !request.getParameter("marcaAuto").isEmpty())
			        if (!annuncio.getMarca().equalsIgnoreCase(request.getParameter("marcaAuto")))
			            valido = false;

			    if (request.getParameter("modelloAuto") != null && !request.getParameter("modelloAuto").isEmpty())
			        if (!annuncio.getModello().equalsIgnoreCase(request.getParameter("modelloAuto")))
			            valido = false;

			    if (request.getParameter("prezzoMin") != null && !request.getParameter("prezzoMin").isEmpty())
			        if (annuncio.getPrezzo() < Double.parseDouble(request.getParameter("prezzoMin")))
			            valido = false;

			    if (request.getParameter("prezzoMax") != null && !request.getParameter("prezzoMax").isEmpty())
			        if (annuncio.getPrezzo() > Double.parseDouble(request.getParameter("prezzoMax")))
			            valido = false;

			    if (request.getParameter("KmMin") != null && !request.getParameter("KmMin").isEmpty())
			        if (annuncio.getKm() < Integer.parseInt(request.getParameter("KmMin")))
			            valido = false;

			    if (request.getParameter("KmMax") != null && !request.getParameter("KmMax").isEmpty())
			        if (annuncio.getKm() > Integer.parseInt(request.getParameter("KmMax")))
			            valido = false;

			    if (request.getParameter("annoMin") != null && !request.getParameter("annoMin").isEmpty())
			        if (annuncio.getAnno() < Integer.parseInt(request.getParameter("annoMin")))
			            valido = false;

			    if (request.getParameter("annoMax") != null && !request.getParameter("annoMax").isEmpty())
			        if (annuncio.getAnno() > Integer.parseInt(request.getParameter("annoMax")))
			            valido = false;

			    if (request.getParameter("coloreAuto") != null && !request.getParameter("coloreAuto").isEmpty())
			        if (!annuncio.getColore().equalsIgnoreCase(request.getParameter("coloreAuto")))
			            valido = false;

			    if (request.getParameter("tipologiaAuto") != null && !request.getParameter("tipologiaAuto").isEmpty())
			        if (!annuncio.getTipologia().equalsIgnoreCase(request.getParameter("tipologiaAuto")))
			            valido = false;

			    if (valido) {
			        annunciFiltrati.add(annuncio);
			    }
			}
			
			if(filtro)
			{
				request.setAttribute("annunciCercati", annunciFiltrati);
				request.getRequestDispatcher("jsp/ricerca.jsp").forward(request, response);
			}
				
			
			response.setContentType("application/json");
			JSONArray jsonArray = new JSONArray();

			for (AnnuncioBean annuncio : annunciFiltrati) {
			    JSONObject obj = new JSONObject();
			    obj.put("titolo", annuncio.getTitolo());
			    obj.put("targa", annuncio.getTarga());
			    obj.put("marca", annuncio.getMarca());
			    obj.put("modello", annuncio.getModello());
			    obj.put("prezzo", annuncio.getPrezzo());
			    obj.put("km", annuncio.getKm());
			    obj.put("anno", annuncio.getAnno());
			    obj.put("colore", annuncio.getColore());
			    obj.put("citta", annuncio.getCitta());
			    obj.put("tipologia", annuncio.getTipologia());
			    jsonArray.put(obj);
			}

			response.getWriter().print(jsonArray.toString());
				
		} 
		catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
		}
        
	}

}
