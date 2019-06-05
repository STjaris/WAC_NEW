package nl.hu.v1wac.firstapp.persistence;

import java.sql.Connection;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class PostgresBaseDao {

	protected final Connection getConnection() {
	Connection conn = null;
	
	try {
		InitialContext ic = new InitialContext();
		DataSource ds = (DataSource) ic.lookup("java:comp/env/jdbc/PostgresDS");
		
		conn = ds.getConnection();
	}catch (Exception e){
		throw new RuntimeException(e);
	}
	
	return conn;
}
}
