<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   	
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Lista de productos</title>
</head>
<body>
	<link rel="stylesheet" href="css/productoCrud.css">

	<table>
		<tr>
			<td class="cabecera">C&oacute;digo Art&iacute;culo</td>	
			<td class="cabecera">Secci&oacute;n</td>	
			<td class="cabecera">Nombre Art&iacute;culo</td>	
			<td class="cabecera">Precio</td>	
			<td class="cabecera">Fecha</td>	
			<td class="cabecera">Importado</td>	
			<td class="cabecera">Pa&iacute;s de origen</td>	
			<td class="cabecera">Acciones</td>	
		</tr>
		<c:forEach var="e" items="${LISTAPRODUCTOS}">
	
		<c:url var="linkTemp" value="ProductoController">
			<c:param name="instruccion" value="updateRegister"></c:param>
			<c:param name="codigoArticulo" value="${e.codigoArticulo}"></c:param>
		</c:url>		
		
		<tr>
			<td class="filas">${e.codigoArticulo}</td>
			<td class="filas">${e.seccion}</td>
			<td class="filas">${e.nombreArticulo}</td>
			<td class="filas">${e.precio}</td>
			<td class="filas">${e.fecha}</td>
			<td class="filas">${e.importado}</td>
			<td class="filas">${e.paisDeOrigen}</td>
			<td class="filas"><a href="${linkTemp}">Actualizar</a></td>
		</tr>
		</c:forEach>
	</table>
	
	<div id="boton">
		<input type="button" value="Insertar nuevo registro" onclick="window.location.href='FormularioProducto.jsp'"/>
	</div>
	
</body>
</html>