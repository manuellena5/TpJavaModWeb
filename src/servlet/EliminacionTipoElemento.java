package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Tipo_Elemento;
import negocio.Tipo_ElementosLogic;

/**
 * Servlet implementation class EliminacionTipoElemento
 */
@WebServlet({ "/EliminacionTipoElemento", "/EliminacionTipoElemento.servlet" })
public class EliminacionTipoElemento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminacionTipoElemento() {
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

		int idtipoelemento = Integer.parseInt(request.getParameter("id"));
		
		Tipo_ElementosLogic tipoelementologic = new Tipo_ElementosLogic();
		Tipo_Elemento tipoelemento = new Tipo_Elemento();
		try {
			tipoelemento = tipoelementologic.GetOne(idtipoelemento);

			request.setAttribute("tipoelemento", tipoelemento);
		} catch (Exception e) {
			response.setStatus(502);
		}
		request.getRequestDispatcher("WEB-INF/eliminartipoelemento.jsp").forward(request, response);
	}

}