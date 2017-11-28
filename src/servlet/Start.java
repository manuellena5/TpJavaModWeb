package servlet;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Persona;
import negocio.PersonaLogic;
import negocio.ReservasLogic;
import util.AppDataException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Level;

/**
 * Servlet implementation class Start
 */
@WebServlet({"/Start", "/start" })
public class Start extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger logger;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Start() {
       
    	logger = LogManager.getLogger(getClass());
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String errores = "";
		Persona per= null;
		ReservasLogic reservaslogic = new ReservasLogic();
		
		
		try {
			if(request.getSession().getAttribute("user") != null){
				
				per = ((Persona)request.getSession().getAttribute("user"));
				
				request.getRequestDispatcher("WEB-INF/principal.jsp").forward(request, response);
			}
			else{
			String user=request.getParameter("user");
			String pass=request.getParameter("pass");
			
			per =new Persona();
			per.setUsuario(user);
			per.setPassword(pass);
			
			PersonaLogic perlogic= new PersonaLogic();
			
			
			Persona pers = perlogic.login(per);
				

			if (pers==null)
			{	
					errores+="Usuario y/o contraseña incorrectos.<br>Pruebe nuevamente";
					request.setAttribute("errores", errores);
					request.getRequestDispatcher("login.jsp").forward(request, response);
				//request.getRequestDispatcher("WEB-INF/loginincorrecto.jsp").forward(request, response);
					
			}else{

			request.getSession().setAttribute("user",pers);
			
			logger.log(Level.INFO,"log in " + pers.getDni() + " " + pers.getNombre() + " " + pers.getApellido());
			
			reservaslogic.actualizarEstadoReservas();
			
			request.getRequestDispatcher("WEB-INF/principal.jsp").forward(request, response);
			}
			}
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		
		
		
	}

}
