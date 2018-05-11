package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDaoCM;
import entity.UserCM;

/**
 * Servlet implementation class LoginServletCM
 */
@WebServlet("/LoginServletCM")
public class LoginServletCM extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();

		// verifica esistenza utente

		UserDaoCM ud = new UserDaoCM();
		UserCM u = ud.selectByUsername(username);

		if (u != null && u.getUsername().equals(username)) {
			if (u.getPassword().equals(password)) {
				request.getSession().setAttribute("nome", u.getNome());
				request.getSession().setAttribute("cognome", u.getCognome());
				request.getSession().setAttribute("username", u.getUsername());
				request.setAttribute("email", u.getEmail());
				request.getSession().setAttribute("password", u.getPassword());
				request.setAttribute("tipoUtente", u.getTipo_utente());

				getServletContext().getRequestDispatcher("/pagPersCM.jsp").forward(request, response);

				// PagPersCM.jsp

			} else {

				request.setAttribute("erroreMsg1", " password errata!");
				getServletContext().getRequestDispatcher("/loginCM.jsp").forward(request, response);
			}

		} else {

			request.setAttribute("erroreMsg2", "Utente inesistente! Registrati");
			getServletContext().getRequestDispatcher("/registrazioneCM.jsp").forward(request, response);

		}
	}

}
