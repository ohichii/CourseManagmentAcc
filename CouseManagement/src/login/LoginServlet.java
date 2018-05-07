package login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import entity.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String confermaPass = request.getParameter("confermaPass");
			
			//registrazione utente
			
			UserDao ud = new UserDao();
			User u = ud.selectByUsername(username);
			
			if (u!=null && u.getUsername().equals(username)) {
				request.setAttribute("errorMsg3", "Username già presente!");
				getServletContext().getRequestDispatcher("/jsp/registrazione.jsp")
				.forward(request, response);
			} else { 
				if (password.equals(confermaPass)) {
					if (password.equals("")) {
						request.setAttribute("errorMsg4", " campo vuoto!");
						getServletContext().getRequestDispatcher("/jsp/registrazione.jsp")
						.forward(request, response);
					} else {
						request.setAttribute("username", request.getParameter("username"));
						ud.insertUtente(username, password);
						getServletContext().getRequestDispatcher("/jsp/benvenuto.jsp")
						.forward(request, response);	
					} 
				} else {request.setAttribute("errorMsg5", "password errata!");
				getServletContext().getRequestDispatcher("/jsp/registrazione.jsp")
				.forward(request, response);
					
				}
			}
		}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		//verifica esistenza utente
		
		UserDao ud = new UserDao();
		User u = ud.selectByUsername(username);	

		if(u!=null && u.getUsername().equals(username)) {
			if(u.getPassword().equals(password)) {
				request.setAttribute("username", request.getParameter("username"));
				getServletContext().getRequestDispatcher("/jsp/benvenuto.jsp")
				.forward(request, response);

				//benvenuto.jsp

			} else {

				request.setAttribute("erroreMsg1", " password errata!");
				getServletContext().getRequestDispatcher("/jsp/home.jsp")
				.forward(request, response);
			}

		} else {

			request.setAttribute("erroreMsg2", "Utente inesistente! Registrati");
			getServletContext().getRequestDispatcher("/jsp/registrazione.jsp")
			.forward(request, response);

			//registrazione.jsp

		}
	}


}


