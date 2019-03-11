package com.revature.database;

import java.sql.SQLException;

public interface Delete<T> 
{
	void performDelete(T object) throws SQLException;
}
