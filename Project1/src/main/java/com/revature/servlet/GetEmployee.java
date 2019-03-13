package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.database.EmployeeDAO;
import com.revature.database.RequestDAO;
import com.revature.model.Employee;
import com.revature.model.ID;

/**
 * Servlet implementation class GetEmployee
 */
public class GetEmployee extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetEmployee() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * /Project1/api/get-employee
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		EmployeeDAO dao = new EmployeeDAO();
		ObjectMapper mapper = new ObjectMapper();
		HttpSession session = request.getSession();

		Employee fullEmployee = null;
		
		try 
		{
			fullEmployee = dao.selectOne((int)session.getAttribute("employeeID"));
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		PrintWriter out = response.getWriter();
		String employeeJSON = mapper.writeValueAsString(fullEmployee);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(employeeJSON);
		out.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		EmployeeDAO employeeDAO = new EmployeeDAO();
		RequestDAO requestDAO = new RequestDAO();
		ObjectMapper mapper = new ObjectMapper();
		Employee employee = null;
		
		ID id = mapper.readValue(request.getInputStream(), ID.class);
		
		try 
		{
			employee = employeeDAO.selectOne(requestDAO.selectOne(id.getId()).getEmployee().getEmployeeId());
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		PrintWriter out = response.getWriter();
		String requestJSON = mapper.writeValueAsString(employee);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(requestJSON);
		out.flush();
	}

}
