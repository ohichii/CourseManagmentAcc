package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DaoException;
import dao.StudenteDao;
import entity.Studente;

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

		StudenteDao ud;
		try {
			ud = new StudenteDao();
		
		ArrayList<Studente> u = ud.getBy(username);
		Studente var = u.get(0);

		if (var != null && var.getUsername().equals(username)) {
			if (var.getPassword().equals(password)) {
				request.getSession().setAttribute("nome", var.getNome());
				request.getSession().setAttribute("cognome", var.getCognome());
				request.getSession().setAttribute("username", var.getUsername());
				request.setAttribute("email", var.getEmail());
				request.getSession().setAttribute("password", var.getPassword());
				request.setAttribute("tipoUtente", var.getTipo_utente());

				getServletContext().getRequestDispatcher("/pagPersCM.jsp").forward(request, response);

				// PagPersCM.jsp

			} else {

				request.setAttribute("erroreMsg1", " password errata!");
				getServletContext().getRequestDispatcher("/loginCM.jsp").forward(request, response);
			}

		} else {

			request.setAttribute("erroreMsg2", "Utente inesistente! Registrati");
			getServletContext().getRequestDispatcher("/registrazioneCM.jsp").forward(request, response);

		}} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
