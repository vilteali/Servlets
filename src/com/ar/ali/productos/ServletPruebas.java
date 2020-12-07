package com.ar.ali.productos;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;


import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ServletPruebas")
public class ServletPruebas extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	// Establecemos el DataSource
	@Resource(name = "jdbc/Productos")
	private DataSource miPool;
	
    public ServletPruebas() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// Creamos el objeto printWritter
		PrintWriter salida = response.getWriter();
		response.setContentType("text/plain");
		
		// Crear conexión con BBDD
		Connection miConexion = null;
		Statement miStatement = null;
		ResultSet miResulset = null;
		
		try {
			miConexion = miPool.getConnection();
			
			String mySql = "SELECT * FROM producto";
			miStatement = miConexion.createStatement();
			miResulset = miStatement.executeQuery(mySql);
			
			while(miResulset.next()) {
				
				String nombreArticulo = miResulset.getString(3); //.getString es el N lugar de la columna en la tabla
				salida.println(nombreArticulo);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
