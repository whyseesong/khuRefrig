package Databases;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RefriDao extends Dao{

	RefriDao(DatabaseConnector databaseConnector) {
		super(databaseConnector);
	}	
	
	public RefriDao() {
		super(DatabaseConnector.getInstance());
	}

	public void close() throws SQLException  {
		databaseConnector.close();
	}
	
	public int getID() {
		int id = -1;
		String sql = null;
		ResultSet rs = null;
		
		sql = "SELECT id FROM refrigerator;";
		try {
			rs = databaseConnector.query(sql);
			rs.next();
			
			id = rs.getInt("id");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return id;
	}
}
