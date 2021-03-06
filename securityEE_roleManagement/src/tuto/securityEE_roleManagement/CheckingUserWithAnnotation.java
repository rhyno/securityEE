package tuto.securityEE_roleManagement;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.ServletSecurity.TransportGuarantee;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.HttpConstraint;

@WebServlet(name = "declarativelyCheckingUser", urlPatterns = { "/declarativelyCheckingUser" })
@ServletSecurity(@HttpConstraint(transportGuarantee = TransportGuarantee.NONE, 
	rolesAllowed = {"ADMIN" }))
public class CheckingUserWithAnnotation extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.processRequest(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.processRequest(req, resp);
	}

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {

			// on affiche une fenêtre de connexion pour l'utilisateur
			request.authenticate(response);

			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet Checking User with annotation</title>");
			out.println("</head>");
			out.println("<body>");

			out.println(
					"L'utilisateur est il dans le rôle ADMIN ? : " + (request.isUserInRole("ADMIN") ? "OUI" : "NON"));
			out.println("</br>");
			out.println("L'utilisateur est il dans le rôle USER ? : " + (request.isUserInRole("USER") ? "OUI" : "NON"));
			out.println("</br>");

			out.println("<h1> checking user with annotation : ok!!</h1>");
			out.println("</body>");
			out.println("</html>");
		} finally {
			out.close();
		}
	}

}
