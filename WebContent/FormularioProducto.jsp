<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<link rel="stylesheet" href="css/formularioForm.css">
	
	<h4>Insertar Registros</h4>
	<br>
	<form name="miFormulario"  method="get" action="ProductoController">
	<input type="hidden" name="instruccion" value="insertForm">
		<table>
			<tr>
				<td>Codigo articulo</td>
				<td><label for=codigoArticulo></label> 
				<input type="text" name=codigoArticulo id=codigoArticulo></td>
			</tr>
			<tr>
				<td>Secci&oacute;n</td>
				<td><label for=seccion></label> 
				<input type="text" name=seccion id=seccion></td>
			</tr>
			<tr>
				<td>Nombre Art&iacute;ulo</td>
				<td><label for=nombreArticulo></label> 
				<input type="text" name=nombreArticulo id=nombreArticulo></td>
			</tr>
			<tr>
				<td>Precio</td>
				<td><label for=precio></label> 
				<input type="text" name=precio id=precio></td>
			</tr>
			<tr>
				<td>Fecha</td>
				<td><label for=fecha></label> 
				<input type="text" name=fecha id=fecha></td>
			</tr>
			<tr>
				<td>Importado</td>
				<td><label for=importado></label> 
				<input type="text" name=importado id=importado></td>
			</tr>
			<tr>
				<td>Pa&iacute;s de origen</td>
				<td><label for=paisDeOrigen></label> 
				<input type="text" name=paisDeOrigen id=paisDeOrigen></td>
			</tr>							
			<tr>
				<td><input type="submit" name="envio" id="envio" value="Enviar"></td>
				<td><input type="reset" name="borrar" id="borrar" value="Restablecer"></td>
			</tr>
		</table>
	</form>
</body>
</html>