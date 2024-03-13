import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Servelet")
public class Servelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/*http://localhost:8080/Web/web01*/
    public Servelet() {
        super();
    }

    /*ENVIA DADOS PARA O FRONT*/ 	/*PASSA OS ATRIBUTOS PELA URL*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		/*response.getWriter().append("Served at: ").append(request.getContextPath());*/
		
		response.setContentType("text/plain");		
		final PrintWriter out = response.getWriter();
		
		out.println("Dados Recebidos!");
		out.append("LabelTex: ").println(request.getParameter("varLabelTex")); 	        
		out.append("LabelNum: ").println(request.getParameter("varLabelNum")); 		
		out.append("Radio: ").println(request.getParameter("VARRADIO"));		
		out.println("Check:" + request.getParameterValues("VARCHECK"));//Array Strings
	}

	
	/*GUARDA OS DADOS NO BACK*/ 	/*PASSA OS ATRIBUTOS PELO BODY*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*response.getWriter().append("Served at: ").append(request.getContextPath());*/
		
	}

}
