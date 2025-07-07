package control;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import model.AnnuncioBean;
import model.AnnuncioDAO;
import model.UtenteIscrittoBean;

/**
 * Servlet implementation class CreaAnnuncioServlet
 */
@WebServlet("/CreaAnnuncioServlet")
@MultipartConfig
public class CreaAnnuncioServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    String FILE_NAME;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
    	creaAnnuncio(request, response);
    }
       
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
    	creaAnnuncio(request, response);
    }
    
    
    protected void creaAnnuncio(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
    	String UPLOAD_DIR = getServletContext().getRealPath("/images/");
    	FILE_NAME = request.getParameter("targa") + ".jpg";
    	
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
    	annuncio.setN_porte(Integer.parseInt(request.getParameter("n_porte"))); //
    	annuncio.setCilindrata(Integer.parseInt(request.getParameter("cilindrata")));
    	annuncio.setCitta(request.getParameter("citta"));
    	
    	// ottiene l'email di chi la crea
    	HttpSession session = request.getSession(false); // false = non crea una nuova sessione se non esiste

    	if (session != null) {
            Object utente = session.getAttribute("utente");

            if (utente instanceof UtenteIscrittoBean utente2) {
                annuncio.setEmail(utente2.getEmail()); 
            }
            	
    	}
    	
    	// Prende la parte del file dal form
        Part filePart = request.getPart("immagine");
        
        if (filePart != null && filePart.getSize() > 0) {

            // Percorso completo dove salvare il file
            String filePath = UPLOAD_DIR + FILE_NAME;
            
            System.out.println(filePath);

            // Legge i byte dal file e li scrive su disco
            try (InputStream fileContent = filePart.getInputStream();
                 FileOutputStream fos = new FileOutputStream(filePath)) {
                 
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = fileContent.read(buffer)) != -1) {
                    fos.write(buffer, 0, bytesRead);
                }
            }
        }
    	
    	// salvo l'annuncio nel db
    	try {
			new AnnuncioDAO().doSave(annuncio);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	request.getRequestDispatcher("/VisualizzaAnnunciServlet").forward(request, response);
	}
}
