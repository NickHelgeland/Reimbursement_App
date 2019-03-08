package com.revature.main;

import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.database.EmployeeDAO;
import com.revature.database.Login;
import com.revature.model.Employee;
import com.revature.model.LoginInfo;

public class Main 
{
	public static void main(String[] args)
	{
		LoginInfo info = new LoginInfo("TheDude","password");
		
		Login login = new Login();
		
		if(login.verify(info))
		{
			System.out.println("Success!");
		}
		else
		{
			System.out.println("Failure");
		}
	}
}
