package com.exercise37crudpstmnt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exercise37crudpstmnt.model.Employee;

/**
 * Servlet that allows me to create employee mysql database
 * @autor Uriel
 * @version 1.0
 */
@WebServlet("/CreateServlet")
public class CreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**Post method that allows employee
     * @see HttpServlet#HttpServlet()
     */
    public CreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html charset='utf-8'");
		PrintWriter output =response.getWriter();
		
		Employee myEmployee= new Employee();
		
		//myEmployee.setIdEmployee(Integer.parseInt(request.getParameter("txtIdEmployee"))); //con esto me traigo el objeto
		myEmployee.setNameEmployee(request.getParameter("txtNameEmployee"));
		myEmployee.setAgeEmployee(Integer.parseInt(request.getParameter("txtAgeEmployee")));
		myEmployee.setAddressEmployee(request.getParameter("txtAddressEmployee"));
		myEmployee.setSalaryEmployee(Double.parseDouble(request.getParameter("txtSalaryEmployee")));
		myEmployee.setDepartmentEmployee(request.getParameter("txtDepartmentEmployee"));
		
		//declaramos variables
		String urlServer = "jdbc:mysql://localhost:3306/tiendita?useSSL=false&serverTimezone=UTC";
		String userName="root";
		String password ="admin";
		int rowsAffected =0;
		
		String sentenciaSQLPreparedStatement="INSERT INTO empleados (nombreEmpleado,edadEmpleado,domicilioEmpleado,"
				+ "salarioEmpleado,departamentoEmpleado) VALUES(?,?,?,?,?)";
		
		//declaramos OBJETOS
		Connection conn=null;
		PreparedStatement pstmnt= null;
		
		try {
			//INSTANCIAMOS EL DRIVER
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			
			//abrimos la conexion
			conn = DriverManager.getConnection(urlServer, userName, password);
			
			//5 configuramos el prepared statement
			pstmnt = conn.prepareStatement(sentenciaSQLPreparedStatement);
			pstmnt.setString(1, myEmployee.getNameEmployee());
			pstmnt.setInt(2, myEmployee.getAgeEmployee());
			pstmnt.setString(3, myEmployee.getAddressEmployee());
			pstmnt.setDouble(4, myEmployee.getSalaryEmployee());
			pstmnt.setString(5, myEmployee.getDepartmentEmployee());
			
			//6 ejecutamos la query
			rowsAffected = pstmnt.executeUpdate();  //solo si vez un select es un query, lo demas es update
			if(rowsAffected>0) {
				output.append("Registro añadido con exito!");
			}
			else {
				output.append("Registro no fue añadido");
			}
		} catch (Exception e) {
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
		
		output.close();
		
		
	}

}
