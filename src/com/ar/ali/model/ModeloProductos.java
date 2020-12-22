package com.ar.ali.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
			Date fecha = miResulSet.getDate("fecha");
			String importado = miResulSet.getString("importado");
			String paisOrigen = miResulSet.getString("pais_de_origen");
			
			productosTemp = new Productos(codigoArt, seccion, nombreArt, precio, fecha, importado, paisOrigen);
			
			productos.add(productosTemp);
			
		}
	
		return productos;
	}

	public void addProducto(Productos nuevoProducto) {
		
		// Obtener la conexión
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			connection = origenDatos.getConnection();
		// Creamos instrucción SQL para ingresar el producto (Statement)
		String insert = "INSERT INTO producto "
				+ "(id_articulo,seccion,nombre_articulo,precio,fecha,importado,pais_de_origen)"
				+ "VALUES (?,?,?,?,?,?,?)";
		
		
		// Establecemos los parametros para el nuevo producto
		statement = connection.prepareStatement(insert);
		statement.setString(1, nuevoProducto.getCodigoArticulo());
		statement.setString(2, nuevoProducto.getSeccion());
		statement.setString(3, nuevoProducto.getNombreArticulo());
		statement.setDouble(4, nuevoProducto.getPrecio());
		
		java.util.Date utilDate = nuevoProducto.getFecha();
		java.sql.Date convertDate = new java.sql.Date(utilDate.getTime());
		statement.setDate(5, convertDate);
		
		statement.setString(6, nuevoProducto.getImportado());
		statement.setString(7, nuevoProducto.getPaisDeOrigen());		
		
		// Ejecutamos la instrucción SQL
		statement.execute();
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
