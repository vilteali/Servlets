package com.ar.ali.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.PartialResultException;
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

	public Productos getProducto(String codigoArt) {
		
		Productos producto = null;
		Connection connection = null;
		PreparedStatement miStatement = null;
		ResultSet miResultSet = null;
		String cArt = codigoArt;
		
		try {
		// Establecemos conexión con la BBDD
		connection = origenDatos.getConnection();
		
		// Creamos la query SQL para buscar el producto del parametro enviado
		String sqlQuery = "SELECT * FROM producto WHERE id_articulo = ?";
		
		// Creamos la consulta preparada
		miStatement = connection.prepareStatement(sqlQuery);
		
		// Establecemos los parámetros 
		miStatement.setString(1, cArt);
		
		// Ejecutamos la consulta
		miResultSet = miStatement.executeQuery();
		
		// Obtenemos los datos 
		if(miResultSet.next()) {
			
			// OBTENEMOS LOS VALORES DE LAS COLUMNAS
			String codigoArticulo = miResultSet.getString("id_articulo");
			String seccion = miResultSet.getString("seccion");
			String nombreArt = miResultSet.getString("nombre_articulo");
			Double precio = miResultSet.getDouble("precio");
			Date fecha = miResultSet.getDate("fecha");
			String importado = miResultSet.getString("importado");
			String paisOrigen = miResultSet.getString("pais_de_origen");
			
			// Llamamos al constructor sin codigoArticulo / id_articulo
			producto = new Productos(codigoArticulo, seccion, nombreArt, precio, fecha, importado, paisOrigen);
			
			} else {
				throw new Exception("No se encontro el producto con código: "+cArt);
			}

		}catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
	}

	public void actualizarProducto(Productos productoActualizado) throws Exception {
		
		Connection miConexion = null;
		PreparedStatement miStatement = null;
		final String sql = "UPDATE producto SET seccion=?, nombre_articulo=?, precio=?, fecha=?, "
				+ "importado=?, pais_de_origen=? WHERE id_articulo=?";
		
		// Establecemos conexión
		miConexion = origenDatos.getConnection();
		
		// Creamos la consulta preparada 
		miStatement = miConexion.prepareStatement(sql);
		
		// Establecemos los parámetros
		miStatement.setString(1, productoActualizado.getSeccion());
		miStatement.setString(2, productoActualizado.getNombreArticulo());
		miStatement.setDouble(3, productoActualizado.getPrecio());
		
		// Casteamos el objeto sql.Date > util.Date
		java.util.Date utilDate = productoActualizado.getFecha();
		java.sql.Date convertDate = new java.sql.Date(utilDate.getTime());
		miStatement.setDate(4, convertDate);
		miStatement.setString(5, productoActualizado.getImportado());
		miStatement.setString(6, productoActualizado.getPaisDeOrigen());
		miStatement.setString(7, productoActualizado.getCodigoArticulo());
		
		// Ejecutamos la sentencia SQL
		miStatement.executeUpdate();
		
	}

	public void eliminarProducto(String codArt) throws Exception {
		
		Connection miConexion = null;
		PreparedStatement miStatement = null;
		final String sql = "DELETE from producto WHERE id_articulo=?";
		
		miConexion = origenDatos.getConnection();
		miStatement = miConexion.prepareStatement(sql);
		
		miStatement.setString(1, codArt);
		miStatement.execute();
		
	}
	
}
