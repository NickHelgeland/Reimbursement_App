package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.database.GradingFormatDAO;
import com.revature.model.GradingFormat;

/**
 * Servlet implementation class GetAllFormatsServlet
 */
public class GetAllFormatsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllFormatsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		GradingFormatDAO gDAO = new GradingFormatDAO();
		ObjectMapper mapper = new ObjectMapper();
		ArrayList<GradingFormat> formatList = null;
		
		try 
		{
			formatList = gDAO.selectAll();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			formatList = new ArrayList<GradingFormat>();
		}
		
		PrintWriter out = response.getWriter();
		String formatJSON = mapper.writeValueAsString(formatList);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(formatJSON);
		out.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
