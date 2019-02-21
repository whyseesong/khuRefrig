package Databases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import classes.Ingredient;

public class IngredientDao extends Dao{

	public IngredientDao(DatabaseConnector databaseConnector){
		super(databaseConnector);
	}
	public IngredientDao(){
		super(DatabaseConnector.getInstance());
	}
	
	public void close() throws SQLException{
		databaseConnector.close();
	}
	
	public ArrayList<Ingredient> getIngredientList(){
		ArrayList<Ingredient> il = new ArrayList<Ingredient>();
		
		String sql = null;
		ResultSet rs = null;
		
		sql = "SELECT * FROM recipe_ingredient;";
		
		try{
			rs = databaseConnector.query(sql);
			if(!rs.isBeforeFirst()){
				System.out.println("IngredientDao getIngredientList() : No ingredient item");
			}
			while(rs.next()){
				Ingredient ingredient = new Ingredient(rs.getString("name"), rs.getInt("count"), rs.getString("recipe_name"), rs.getInt("id"));
				il.add(ingredient);
			}
		}catch (SQLException e){
			e.printStackTrace();
		}
		
		return il;
	}
}
