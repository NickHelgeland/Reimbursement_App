package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.database.EmployeeDAO;
import com.revature.database.EventDAO;
import com.revature.database.GradingFormatDAO;
import com.revature.database.RequestDAO;
import com.revature.model.Employee;
import com.revature.model.Event;
import com.revature.model.GradingFormat;
import com.revature.model.PartialRequest;
import com.revature.model.Request;

/**
 * Servlet implementation class CreateRequestServlet
 */
public class CreateRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateRequestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Request newRequest = new Request();
		Event event = new Event();
		RequestDAO requestDAO = new RequestDAO();
		EventDAO eventDAO = new EventDAO();
		EmployeeDAO employeeDAO = new EmployeeDAO();
		GradingFormatDAO gradingDAO = new GradingFormatDAO();
		ObjectMapper mapper = new ObjectMapper();
		PartialRequest partialRequest = null;
		HttpSession session = request.getSession();
		
		partialRequest = mapper.readValue(request.getInputStream(), PartialRequest.class);
		
		event.setEventDate(partialRequest.getStart_date());
		event.setEventTime(partialRequest.getEnd_date());
		event.setEventDescription(partialRequest.getDescription());
		event.setEventLocation(partialRequest.getEvent_location());
		event.setEventType(partialRequest.getEvent_type());
		event.setEventStatus("ongoing");
		
		newRequest.setAmount(partialRequest.getAmount());
		newRequest.setJustification(partialRequest.getJustification());
		newRequest.setStatus("pending supervisor approval");
		
		try 
		{
			GradingFormat format = gradingDAO.selectByScale(partialRequest.getGrading_scale());
			event.setGradingFormat(format);
			event.setEventId(eventDAO.getNewID());
			Employee employee = employeeDAO.selectOne((int)session.getAttribute("employeeID"));
			newRequest.setEmployee(employee);
			newRequest.setEvent(event);
			eventDAO.createNew(event);
			requestDAO.createNew(newRequest);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		String message = "Request created!";
		
		PrintWriter out = response.getWriter();
		String messageJSON = mapper.writeValueAsString(message);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(messageJSON);
		out.flush();
	}

}
