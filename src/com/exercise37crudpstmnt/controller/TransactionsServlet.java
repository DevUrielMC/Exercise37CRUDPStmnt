package com.exercise37crudpstmnt.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TransactionsServlet
 */
@WebServlet("/TransactionsServlet")
public class TransactionsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransactionsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html charset='utf-8'");
		
		Properties props= new Properties();
		InputStream in= this.getClass().getClassLoader().getResourceAsStream("resources.properties");
		
		props.load(in);
		//1 declarar variables
		String urlServer = props.getProperty("urlServer");
		String user = props.getProperty("username");
		String pass = props.getProperty("password");
		
		String sql1="INSERT INTO empleados (nombreEmpleado, edadEmpleado, domicilioEmpleado, "
				+ "salarioEmpleado, departamentoEmpleado) VALUES ('pa',10,'pa',10.0,'10')";
		
		String sql2="INSERT INTO empleados (nombreEmpleado, edadEmpleado, domicilioEmpleado, "
				+ "salarioEmpleado, departamentoEmpleado) VALUES ('pe',10,'pe',10.0,'10')";
		String sql3="INSERT INTO empleados (nombreEmpleado, edadEmpleado, domicilioEmpleado, "
				+ "salarioEmpleado, departamentoEmpleado) VALUES ('pi',10,'pi',10.0,'10')";
		String sql4="INSERT INTO empleados (nombreEmpleado, edadEmpleado, domicilioEmpleado, "
				+ "salarioEmpleado, departamentoEmpleado) VALUES ('po',10,'po',10.0,'10')";
		String sql5="INSERT INTO empleados (nombreEmpleado, edadEmpleado, domicilioEmpleado, "
				+ "salarioEmpleado, departamentoEmpleado) VALUES ('pu',10,'pu',10.0,'10')";
		
		System.out.println("url:"+urlServer);
		System.out.println("user:"+user);
		System.out.println("password:"+pass);
		
		
		//2 declarar objetos
		Connection conn=null;
		PreparedStatement pstmnt= null;
		int rowsAffected=0;
		
		//3 conectar bd
		try {
		Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
		
		//abrimos la conexion
		conn = DriverManager.getConnection(urlServer, user, pass);
		conn.setAutoCommit(false);
		
		pstmnt= conn.prepareStatement(sql1);
		pstmnt.executeUpdate();
				
		pstmnt= conn.prepareStatement(sql2);
		pstmnt.executeUpdate();
		
		pstmnt= conn.prepareStatement(sql3);
		pstmnt.executeUpdate();
		
		pstmnt= conn.prepareStatement(sql4);
		pstmnt.executeUpdate();
				
		pstmnt= conn.prepareStatement(sql5);
		pstmnt.executeUpdate();
				
			
		conn.commit();
		
		response.getWriter().append("Transations executed!");
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			
			try {
				pstmnt.close();
				conn.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//4 configurar el prepared statement
		//5 ejecutar instruccion
		//6 procesar datos
		//7 cerrar conexion
	}

}
