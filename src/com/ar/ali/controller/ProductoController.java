package com.ar.ali.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import java.util.Date;
import java.util.List;

import com.ar.ali.model.ModeloProductos;
import com.ar.ali.productos.Productos;

@WebServlet("/ProductoController")
public class ProductoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ModeloProductos modelo;
	
	// Establecemos el DataSource
	@Resource(name = "jdbc/Productos")
	private DataSource miPool;
	
	// Inicializamos nuestro Servlet con el método init
	@Override
	public void init() throws ServletException {
		super.init();
		
		// Instanciamos nuestro modelo pasando como parametro DataSource
		try {
			modelo = new ModeloProductos(miPool);
		}catch(Exception e) {
			
			throw new ServletException(e);
		}
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Leemos el parámetro del formulario > hidden
		String parameter = request.getParameter("instruccion");
		
		// Si no se envía el parámetro, listamos los productos solamente
		if(parameter==null)
			parameter="listar";
		
		// Redirigimos el flujo de ejecución al método indicado
		switch(parameter) {
			case "listar":
				obtenerProducto(request, response);
				break;
			case "insertForm":
				agregarProducto(request, response);
				break;
			case "updateRegister":
				try {
					actualizarProducto(request, response);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case "ActualizarForm":
				try {
					actualizaElProducto(request, response);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			default:
				obtenerProducto(request, response);
		}
		
	}

	private void actualizaElProducto(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// Leemos los datos que vienen del formulario actualizar
		String codArt = request.getParameter("codigoArticulo");
		String seccion = request.getParameter("seccion");
		String nameArt = request.getParameter("nombreArticulo");
		double precio = Double.parseDouble(request.getParameter("precio"));
		
		// Convertimos la fecha String en formato Date
		SimpleDateFormat formatFecha = new SimpleDateFormat("yyyy-MM-dd");
		Date fecha = null;
		
		try {
			fecha = formatFecha.parse(request.getParameter("fecha"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String importado = request.getParameter("importado");
		String paisDeOrigen = request.getParameter("paisDeOrigen");
		
		// Creamos un objeto del tipo Producto con los datos del formulario
		Productos productoActualizado = new Productos(codArt, seccion, nameArt, precio, fecha, importado, paisDeOrigen);
		
		// Actualizamos la BBDD con los datos del objeto Producto
		modelo.actualizarProducto(productoActualizado);
		
		// Retornamos la lista con la información actualizada
		obtenerProducto(request, response);
		
	}

	private void actualizarProducto(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// Leemos el codígo artículo que viene del listado
		String codigoArt = request.getParameter("codigoArticulo");
		
		// Enviamos el código artículo al modelo
		Productos producto = modelo.getProducto(codigoArt);
		
		// Colocamos el atributo correspondiente para el codígo artículo
		request.setAttribute("actualizarProducto", producto);
		
		// Enviamos el producto al formulario de actualizar
		RequestDispatcher dispatcher = request.getRequestDispatcher("/actualizarProducto.jsp");
		dispatcher.forward(request, response);
		
		
	}

	private void agregarProducto(HttpServletRequest request, HttpServletResponse response) {
		
		// Leemos la información del producto ingresado
		String codArt = request.getParameter("codigoArticulo");
		String seccion = request.getParameter("seccion");
		String nameArt = request.getParameter("nombreArticulo");
		double precio = Double.parseDouble(request.getParameter("precio"));
		
		// Convertimos la fecha String en formato Date
		SimpleDateFormat formatFecha = new SimpleDateFormat("yyyy-MM-dd");
		Date fecha = null;
		
		try {
			fecha = formatFecha.parse(request.getParameter("fecha"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String importado = request.getParameter("importado");
		String paisDeOrigen = request.getParameter("paisDeOrigen");
		
		// Creamos un objeto de tipo Producto
		Productos nuevoProducto = new Productos(codArt, seccion, nameArt, precio, fecha, importado, paisDeOrigen);
		
		// Enviar el objeto al modelo y insertamos el objeto en la base de datos
		modelo.addProducto(nuevoProducto);
		
		// Volvemos a listar los productos
		obtenerProducto(request, response);
		
	}

	private void obtenerProducto(HttpServletRequest request, HttpServletResponse response) {
		
		// Obtener la lista de productos desde el modelo
		List<Productos> productos;
		
		try {
			productos = modelo.getProductos();
		
		// Agregar lista de productos al request
		request.setAttribute("LISTAPRODUCTOS", productos);
		
		// Enviar ese request a la página JSP
		RequestDispatcher miDispatcher = request.getRequestDispatcher("/listaProductos.jsp");
		miDispatcher.forward(request, response);
		
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		
	}

}
