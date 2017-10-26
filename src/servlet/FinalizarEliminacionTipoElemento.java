package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Tipo_Elemento;
import negocio.Tipo_ElementosLogic;
import util.AppDataException;

/**
 * Servlet implementation class FinalizarEliminacionTipoElemento
 */
@WebServlet({ "/FinalizarEliminacionTipoElemento", "/FinalizarEliminacionTipoElemento.servlet" })
public class FinalizarEliminacionTipoElemento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FinalizarEliminacionTipoElemento() {
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

		int idtipoelemento = Integer.parseInt(request.getParameter("btneleccion"));
		
		Tipo_ElementosLogic tipoelementoLogic = new Tipo_ElementosLogic();
		
		Tipo_Elemento tipoel = new Tipo_Elemento();
		
		
		try {
		tipoel = tipoelementoLogic.GetById(idtipoelemento);
		
		tipoelementoLogic.delete(tipoel);
			
		request.setAttribute("tipoelemento", tipoel);
		} catch (AppDataException ade) {
			request.setAttribute("Error", ade.getMessage());
		}
		catch (Exception e) {
			response.setStatus(502);
		}
		
		request.getRequestDispatcher("WEB-INF/modificacionexitosatipoelemento.jsp").forward(request, response);
	}

}
