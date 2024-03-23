<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet" href="Login.css">
</head>
<body>

    <form action="login" method="post" class="container">
    
        <h1 class="blue">Login</h1>
        
        <%String usuario = (String) request.getAttribute("usuario");%>
        
        <label for="usuario" class="blue" >Usuario: </label>
        <input type="text" name="usuario" id="usuario" minlength="3" value="<%= usuario %>"><br><br>
        
        <label for="senha" class="blue">Senha: </label>
        <input type="password" name="senha" id="senha" minlength="8"><br><br>
        
        <%String resposta = (String) request.getAttribute("resposta");%>
        <%if (resposta != null && !resposta.isEmpty()) {%>
            <label for="resposta" class="red-label"><%= resposta %></label><br><br>
        <%}%>       
                
        <% List<String> listaErrosRegex = (List<String>) request.getAttribute("listaErrosRegex"); %>
        <% for(String erro : listaErrosRegex) { %>
            <label for="resposta" class="red-label" style="font-size: 12px;"><%= erro %></label>
        <% } %>            
        <% listaErrosRegex.clear(); %>
                
        <button type="submit" value="submit">Enviar</button>    
    </form>
</body>
</html>
