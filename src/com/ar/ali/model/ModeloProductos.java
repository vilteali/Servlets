package com.ar.ali.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import com.ar.ali.productos.Productos;

public class ModeloProductos {

	private DataSource origenDatos;
	
	// Por parametro pasamos el Pool de conexiones
	public ModeloProductos(DataSource origenDatos) {
		
		this.origenDatos = origenDatos;
	}
	
	public List<Productos> getProductos() throws Exception {
		
		// Lista para guardar los productos
		List<Productos> productos = new ArrayList<>();
		
		// Creamos conexión con base de datos
		Connection connection = null;
		Statement miStatement = null;
		ResultSet miResulSet = null;
		Productos productosTemp = null;
		
		// Establecemos la conexión
		connection = origenDatos.getConnection();
		
		// Crear la sentencia SQL y Statement
		String querySql = "SELECT * FROM producto";
		miStatement = connection.createStatement();
		
		// Ejecturar sentencia SQL
		miResulSet = miStatement.executeQuery(querySql);
		
		// Recorrer el ResultSet obtenido
		while(miResulSet.next()) {
			
			// OBTENEMOS LOS VALORES DE LAS COLUMNAS
			String codigoArt = miResulSet.getString("id_articulo");
			String seccion = miResulSet.getString("seccion");
			String nombreArt = miResulSet.getString("nombre_articulo");
			Double precio = miResulSet.getDouble("precio");
			Date fecha2 = miResulSet.getDate("fecha");
			String importado = miResulSet.getString("importado");
			String paisOrigen = miResulSet.getString("pais_de_origen");
			
			productosTemp = new Productos(codigoArt, seccion, nombreArt, precio, fecha2, importado, paisOrigen);
			
			productos.add(productosTemp);
			
		}
	
		return productos;
	}
	
}
