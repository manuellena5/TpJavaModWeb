package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Categoria;
import entidades.Elemento;
import entidades.Persona;
import entidades.Tipo_Elemento;
import negocio.CategoriasLogic;
import negocio.ElementosLogic;
import negocio.PersonaLogic;
import negocio.Tipo_ElementosLogic;
import util.AppDataException;

/**
 * Servlet implementation class ValidarPersona
 */
@WebServlet({ "/ValidarPersona", "/validarpersona.servlet" })
public class ValidarPersona extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidarPersona() {
        super();
        // TODO Auto-generated constructor stub
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

		CategoriasLogic categoriaslogic = new CategoriasLogic();
		
		Categoria categoria = new Categoria();
		
		Persona persona = new Persona();
		
		PersonaLogic personalogic = new PersonaLogic();
				
		try {
			String nombre = request.getParameter("txtnombre");

			String apellido = request.getParameter("txtapellido");

			String dni = request.getParameter("txtdni");

			String password = request.getParameter("txtpass");
			
			String usuario = request.getParameter("txtusuario");

			int idcategoria = Integer.parseInt(request.getParameter("txtidcategoria"));

			categoria = categoriaslogic.GetById(idcategoria);
			
			boolean estado = Boolean.valueOf(request.getParameter("estado"));
			
			
			
			persona.setNombre(nombre);
			persona.setApellido(apellido);
			persona.setDni(dni);
			persona.setPassword(password);
			persona.setUsuario(usuario);
			persona.setHabilitado(estado);
			persona.setCategoria(categoria);
			
			personalogic.add(persona);
			
			
			request.setAttribute("persona",persona);
		}catch (SQLException e) {
			request.setAttribute("Error", "Ha ocurrido un error inesperado, vuelva a intentarlo mas tarde");
			System.out.println(e.getMessage());
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
		} catch (AppDataException ade) {
			request.setAttribute("Error", ade.getMessage());
			System.out.println(ade.getMessage());
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
		}
		catch (Exception e) {
			response.setStatus(502);
		}
		request.getRequestDispatcher("/WEB-INF/altapersonaexitosa.jsp").forward(request, response);
		
		
		
	}

}
