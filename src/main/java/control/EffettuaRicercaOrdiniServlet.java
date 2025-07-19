package control;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.OrdineBean;
import model.OrdineDAO;

/**
 * Servlet implementation class EffettuaRicercaOrdiniServlet
 */
@WebServlet("/EffettuaRicercaOrdiniServlet")
public class EffettuaRicercaOrdiniServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
		
		// otteniamo tutti gli annunci
		OrdineDAO dao = new OrdineDAO();		
		try {
			List<OrdineBean> ordini = dao.doRetrieveAll("codice_ordine");
			
			List<OrdineBean> ordiniFiltrati = new ArrayList<>();

			for (OrdineBean ordine : ordini) {
			    boolean valido = true;

			    if (request.getParameter("barraRicercaFiltro") != null && !request.getParameter("barraRicercaFiltro").isEmpty())
			        if (!ordine.getEmail_compratore().toLowerCase().contains(request.getParameter("barraRicercaFiltro").toLowerCase()))
			            valido = false;
			    
			    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			    if (request.getParameter("dataMin") != null && !request.getParameter("dataMin").isEmpty()) {
			        Date dataMin = sdf.parse(request.getParameter("dataMin"));
			        if (ordine.getDataAcquisto().before(dataMin)) {
			            valido = false;
			        }
			    }

			    if (request.getParameter("dataMax") != null && !request.getParameter("dataMax").isEmpty()) {
			        Date dataMax = sdf.parse(request.getParameter("dataMax"));
			        if (ordine.getDataAcquisto().after(dataMax)) {
			            valido = false;
			        }
			    }

			    if (valido) {
			        ordiniFiltrati.add(ordine);
			    }
			}
			
			
			request.setAttribute("ordiniCercati", ordiniFiltrati);
			request.getRequestDispatcher("jsp/ricercaOrdini.jsp").forward(request, response);
		} 
		catch (SQLException e) {
					e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
	}

}
