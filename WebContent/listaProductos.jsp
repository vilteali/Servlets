<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="java.util.*, com.ar.ali.model.*, com.ar.ali.controller.*, com.ar.ali.productos.*" %>    

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Lista de productos</title>
</head>

<%
 	// SCRIPLET 
 	// Obtenemos los productos del controller(servlet)
	List<Productos> losProductos = (List<Productos>) request.getAttribute("LISTAPRODUCTOS");
%>


<body>

	<%= losProductos %>

</body>
</html>