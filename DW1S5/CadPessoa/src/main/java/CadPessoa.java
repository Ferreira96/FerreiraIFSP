import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cadpessoa")
public class CadPessoa extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//http://localhost:8080/CadPessoa/cadpessoa
	String workSpace = "C:/Users/Ferreira/Documents/EclipseWorkSpace";
		
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = workSpace + "/CadPessoa/src/main/webapp/formulario.html";

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            StringBuilder htmlContent = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                htmlContent.append(line);
            }

            response.setContentType("text/html");
            response.getWriter().write(htmlContent.toString());
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo HTML: " + e.getMessage());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao processar a requisição");
        }
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {					
		String path = workSpace + "/CadPessoa/src/main/java/TABPESSOA.txt";
       
		String info1 = "N";
		String info2 = "N";
		
	    String[] infoParams = request.getParameterValues("info");
	    
	    if(infoParams != null && infoParams.length >= 2) {
	        if(infoParams[0] != null) {
	            info1 = "S";
	        }
	        if(infoParams[1] != null) {
	            info2 = "S";
	        }
	    }
		
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {
            writer.write(request.getParameter("nome")          + ";" + 
            			 request.getParameter("idade")         + ";" + 
            			 request.getParameter("sexo") 		   + ";" + 
            			 info1 + ";" + 
            			 info2 + ";\n");
            
            System.out.println("Dados gravados com sucesso");
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo: " + e.getMessage());
        }
		
        doGet(request, response);
	}

}
