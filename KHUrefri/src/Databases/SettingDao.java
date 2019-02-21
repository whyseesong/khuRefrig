package Databases;

import java.sql.ResultSet;
import java.sql.SQLException;

import classes.Food;
import classes.Setting;

public class SettingDao extends Dao{

	SettingDao(DatabaseConnector databaseConnector) {
		super(databaseConnector);
		// TODO Auto-generated constructor stub
	}

	public SettingDao() {
		super(DatabaseConnector.getInstance());
	}
	
	public void close() throws SQLException {
		databaseConnector.close();
	}
	
	public Setting getSettingData() {
		Setting set = new Setting();
		
		String sql = null;
		ResultSet rs = null;
		
		sql = "SELECT * FROM refrigerator;";
		
		try {
			rs = databaseConnector.query(sql);
			if(!rs.isBeforeFirst()) {
				System.out.println("SettingDao getSettingData() : No setting data");
			}
			while(rs.next()) {
				set = new Setting(rs.getInt("id"), rs.getInt("freezer_temp"), rs.getInt("refridge_temp"), rs.getInt("freezer_bright"), rs.getInt("refridge_bright"), rs.getInt("hotwater_temp"), rs.getInt("coldwater_temp"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
		return set;
	}

	public int updateSettingData(Setting setting) {
		// TODO Auto-generated method stub
		String sql = null;
		int flg=0;
		
		sql = "UPDATE refrigerator SET freezer_temp = " + setting.getFreezer_temp()
				+ ", refridge_temp = " + setting.getRefrige_temp()
				+ ", hotwater_temp = " + setting.getHotwater_temp()
				+ ", coldwater_temp = " + setting.getColdwater_temp()
				+ ", freezer_bright = " + setting.getFreezer_brightness()
				+ ", refridge_bright = " + setting.getRefrige_brightness() + ";";
		
		try {
			flg = databaseConnector.update(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flg;
	}
}
