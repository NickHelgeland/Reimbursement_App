package com.revature.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface Select<T>
{
	T selectOne(T object) throws SQLException;
	
	ArrayList<T> selectAll() throws SQLException;
}
