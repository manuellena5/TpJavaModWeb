package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Elemento;
import entidades.Tipo_Elemento;
import negocio.ElementosLogic;
import negocio.Tipo_ElementosLogic;
import util.AppDataException;

/**
 * Servlet implementation class ValidarElemento
 */
@WebServlet({ "/ValidarElemento", "/validarelemento.servlet" })
public class ValidarElemento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidarElemento() {
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
		
		Tipo_ElementosLogic tipoelementoslogic = new Tipo_ElementosLogic();
		Tipo_Elemento tipoelemento = new Tipo_Elemento();
		Elemento elemento = new Elemento();
		ElementosLogic elementoslogic = new ElementosLogic();
		
		
		
		try {
			String nombre = request.getParameter("txtnombre");

			String descripcion = request.getParameter("txtdescripcion");
			
			

			String autor = request.getParameter("txtautor");

			String genero = request.getParameter("txtgenero");
			
			if (descripcion.isEmpty()) {
				descripcion="Sin descripcion";
			}
			if (autor.isEmpty()) {
				autor="Sin autor";
			}
			if (genero.isEmpty()) {
				genero="Sin genero";
			}
			

			/*int stock = Integer.parseInt(request.getParameter("txtstock"));*/
			
			int stock = 1;

			int idtipoelemento = Integer.parseInt(request.getParameter("txtidtipoelemento"));

			tipoelemento = tipoelementoslogic.GetById(idtipoelemento);
			
			elemento.setTipo_Elemento(tipoelemento);
			elemento.setStock(stock);
			elemento.setGenero(genero);
			elemento.setAutor(autor);
			elemento.setDescripcion(descripcion);
			elemento.setNombre(nombre);
			
			elementoslogic.add(elemento);
			
			request.setAttribute("elemento", elemento);
		
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
		request.getRequestDispatcher("/WEB-INF/altaexitosa.jsp").forward(request, response);
		
		
		
	}

}
