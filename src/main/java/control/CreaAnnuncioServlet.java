package control;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.AnnuncioBean;

/**
 * Servlet implementation class CreaAnnuncioServlet
 */
@WebServlet("/CreaAnnuncioServlet")
public class CreaAnnuncioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
		// creo un bean annuncio e ci salvo i campi ottenuti nella form
    	AnnuncioBean annuncio = new AnnuncioBean();
    	
    	annuncio.setTarga(request.getParameter("targa"));
    	annuncio.setTitolo(request.getParameter("titolo"));
    	annuncio.setVisibilita(true);
    	annuncio.setDescrizione(request.getParameter("descrizione"));
    	annuncio.setMarca(request.getParameter("marcaAuto"));
    	annuncio.setModello(request.getParameter("modelloAuto"));
    	annuncio.setPrezzo(Integer.parseInt(request.getParameter("prezzo")));
    	annuncio.setKm(Integer.parseInt(request.getParameter("km")));
    	annuncio.setAnno(Integer.parseInt(request.getParameter("anno")));
    	annuncio.setCarburante(request.getParameter("carburante"));
    	annuncio.setTipologia(request.getParameter("tipologiaAuto"));
    	annuncio.setColore(request.getParameter("coloreAuto"));
    	annuncio.setMarca(request.getParameter("marcaAuto"));
    	annuncio.setCilindrata(Integer.parseInt(request.getParameter("cilindrata")));
    	annuncio.setCitta(request.getParameter("citta"));
    	//annuncio.setEmail();
	}
}
