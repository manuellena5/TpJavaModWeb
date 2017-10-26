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
 * Servlet implementation class ValidarTipoElemento
 */
@WebServlet({ "/ValidarTipoElemento", "/validartipoelemento.servlet" })
public class ValidarTipoElemento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidarTipoElemento() {
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
		
		String nombre =  request.getParameter("txtnombre");
		int cantmaxreservaspend = Integer.parseInt(request.getParameter("txtcantmaxreservaspend"));
		
		tipoelemento.setNombre(nombre);
		tipoelemento.setCantMaxReservasPend(cantmaxreservaspend);
		
		
		try {
			tipoelementoslogic.add(tipoelemento);
			
			request.setAttribute("tipoelemento", tipoelemento);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/WEB-INF/altatipoelementoexitosa.jsp").forward(request, response);
		
		
		
		
	}

}
