package control;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class EffettuaLogoutServlet
 */
@WebServlet("/EffettuaLogoutServlet")
public class EffettuaLogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// Invalida la sessione
      HttpSession session = request.getSession(false);
      if (session != null) {
          session.invalidate();
      }

      // Elimina il cookie tipoUtente
      Cookie[] cookies = request.getCookies();
      if (cookies != null) {
          for (Cookie cookie : cookies) {
              if ("tipoUtente".equals(cookie.getName())) {
                  cookie.setMaxAge(0);
                  cookie.setPath("/");
                  response.addCookie(cookie);
              }
          }
      }

      // Reindirizza alla home
      response.sendRedirect(request.getContextPath() + "/VisualizzaAnnunciServlet");
	}

}
