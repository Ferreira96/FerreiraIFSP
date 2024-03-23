package aluno.ifsp.edu.br;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    String resposta = "";
    String usuario  = "";
    List<String> listaErrosRegex = new ArrayList<>();
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("usuario", usuario);
		request.setAttribute("listaErrosRegex", listaErrosRegex);
		RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");	
		rd.forward(request, response);					
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {			
		String usuario = request.getParameter("usuario");
		String senha   = request.getParameter("senha");
		
		if(validar(usuario, senha)) {
			//response.getWriter().append("Login realizado com sucesso. ");
			 response.getWriter().append("<script>alert('" + "Login realizado com sucesso. " + "');</script>");

		}else {
			response.getWriter().append(resposta);
			request.setAttribute("resposta", resposta);
			request.setAttribute("listaErrosRegex", listaErrosRegex);
			this.usuario = usuario;

			doGet(request, response);
		}
						
		
	}
	
	public boolean validar(String usuario, String senha) {
		boolean retorno = validaRegex(senha);
		
		try {						
			//Validação usuario			
			if(usuario == null || usuario.equals("")) {
				resposta = "O campo usuario não pode ser vazio.";	
				return false;
			}
			
			if(usuario.length()<3){
				resposta = "O campo usuario deve conter mais de 3 digitos.";
				return false;
			}
				
			//Validação senha			
			if(senha == null || senha.equals("")) {				
				resposta = "O campo senha não deve ser vazio.";	
				return false;
			}
			
			if(senha.length()<8){
				resposta = "O campo senha deve conter mais de 8 digitos.";
				return false;
			}
										
		} catch (Exception e) {
			resposta = "Dado invalido.";
			return false;
		}
		
		return retorno;
		
	}
	
	public boolean validaRegex(String senha) { 	
		boolean retorno = true;
		
		// dígito  
        if(!Pattern.compile(".*\\d.*").matcher(senha).matches()) {
        	listaErrosRegex.add("*A senha deve conter um digito");
        	retorno = false;
        }
        
        // letra minúscula
        if(!Pattern.compile(".*[a-z].*").matcher(senha).matches()) {
        	listaErrosRegex.add("*A senha deve conter uma letra minuscula");
        	retorno = false;
        }
        
        // letra maiúscula
        if(!Pattern.compile(".*[A-Z].*").matcher(senha).matches()) {
        	listaErrosRegex.add("*A senha deve conter uma letra maiuscula");
        	retorno = false;
        }
        
        // caractere especial
        if(!Pattern.compile(".*[^a-zA-Z0-9].*").matcher(senha).matches()) {
        	listaErrosRegex.add("*A senha deve conter um caractere especial");
        	retorno = false;
        }
        
        return retorno;      
	}

}
