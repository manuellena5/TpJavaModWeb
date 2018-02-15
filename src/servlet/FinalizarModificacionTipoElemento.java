package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;

import entidades.Tipo_Elemento;
import negocio.Tipo_ElementosLogic;
import util.AppDataException;

/**
 * Servlet implementation class FinalizarModificacionTipoElemento
 */
@WebServlet({ "/FinalizarModificacionTipoElemento", "/FinalizarModificacionTipoElemento.servlet" })
public class FinalizarModificacionTipoElemento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FinalizarModificacionTipoElemento() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		Tipo_ElementosLogic tipoelementoLogic = new Tipo_ElementosLogic();
		
		Tipo_Elemento tipoel = new Tipo_Elemento();
		
		
		try {
			
			int idtipoelemento = Integer.parseInt(request.getParameter("txtid"));
			String acceso = request.getParameter("acceso");
			int cantMaxReservasPend = Integer.parseInt(request.getParameter("txtcantmax"));
		
		tipoel = tipoelementoLogic.GetById(idtipoelemento);
		if (tipoel == null) {
			request.setAttribute("Error", "Ha ocurrido un error inesperado, vuelva a intentarlo mas tarde");
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
			
		}else{
		tipoel.setCantMaxReservasPend(cantMaxReservasPend);
		tipoel.setAcceso(acceso);
		
		tipoelementoLogic.update(tipoel);
			
		request.setAttribute("tipoelemento", tipoel);
		}
		}catch (SQLException e) {
			request.setAttribute("Error", "Ha ocurrido un error inesperado, vuelva a intentarlo mas tarde");
			new AppDataException(e, e.getMessage(),Level.ERROR);
			System.out.println(e.getMessage());
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
		} catch (AppDataException ade) {
			request.setAttribute("Error", "Ha ocurrido un error inesperado, vuelva a intentarlo mas tarde");
			new AppDataException(ade, ade.getMessage(),Level.ERROR);
			System.out.println(ade.getMessage());
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
		}
		catch (Exception e) {
			request.setAttribute("Error", "Ha ocurrido un error inesperado, vuelva a intentarlo mas tarde");
			new AppDataException(e, e.getMessage(),Level.ERROR);
			System.out.println(e.getMessage());
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
		}
		
		request.getRequestDispatcher("WEB-INF/modificacionexitosa.jsp").forward(request, response);
	}

}
