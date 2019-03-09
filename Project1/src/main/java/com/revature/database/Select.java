package com.revature.database;

import java.sql.SQLException;
import java.util.ArrayList;

public interface Select<T>
{
	T selectOne(int id) throws SQLException;
	
	ArrayList<T> selectAll() throws SQLException;
}
