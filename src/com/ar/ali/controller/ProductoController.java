package com.ar.ali.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
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
		// TODO Auto-generated method stub
		super.init();
		
		// Instanciamos nuestro modelo pasando como parametro DataSource
		try {
			modelo = new ModeloProductos(miPool);
		}catch(Exception e) {
			
			throw new ServletException(e);
		}
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
