package com.revature.database;

import java.sql.SQLException;

public interface Insert<T>
{
	void createNew(T object) throws SQLException;
}
