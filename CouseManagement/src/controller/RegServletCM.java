package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoException;
import dao.StudenteDao;
import entity.Studente;

/**
 * Servlet implementation class RegServletCM
 */
@WebServlet("/RegServletCM")
public class RegServletCM extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String confermaPass = request.getParameter("confermaPass");
		Studente nuovoUtente=new Studente();
		nuovoUtente.setNome(nome);
		nuovoUtente.setCognome(cognome);
		nuovoUtente.setUsername(username);
		nuovoUtente.setPassword(password);

		// registrazione utente
		try {
			StudenteDao ud = new StudenteDao();
			ArrayList<Studente> u = ud.getBy(username); // cerco username nuovo ut nel db
			

			if (!(u.isEmpty()) ) {  // se 
				request.setAttribute("errorMsg3", "Username già presente!");
				getServletContext().getRequestDispatcher("/registrazioneCM.jsp").forward(request, response);
			} else {
				if (password.equals(confermaPass)) {
					if (password.equals("")|| nome.equals("") || cognome.equals("") || username.equals("")) {
						if(password.equals(""))
						{request.setAttribute("errorMsg4", " campo obbligatorio vuoto!");};
						if(nome.equals(""))
						{request.setAttribute("errorMsg5", " campo obbligatorio vuoto!");};
						if(cognome.equals(""))
						{request.setAttribute("errorMsg6", " campo obbligatorio vuoto!");};
						if(username.equals(""))
						{request.setAttribute("errorMsg7", " campo obbligatorio vuoto!");};

						getServletContext().getRequestDispatcher("/registrazioneCM.jsp").forward(request, response);
					} else {
						request.setAttribute("username", request.getParameter("username"));

						ud.add(nuovoUtente);

						request.setAttribute("nome", nome);
						request.setAttribute("cognome", cognome);
						getServletContext().getRequestDispatcher("/pagPersCM.jsp").forward(request, response);
					}
				} else {
					request.setAttribute("errorMsg8", "password errata!");
					getServletContext().getRequestDispatcher("/registrazioneCM.jsp").forward(request, response);

				}
			}
		}catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

