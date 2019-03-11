package com.revature.database;

import java.sql.SQLException;

public interface Update<T> 
{
	void sendUpdate(T object) throws SQLException;
}
