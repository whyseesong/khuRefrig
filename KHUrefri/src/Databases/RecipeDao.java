package Databases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import classes.FoodRecipe;

public class RecipeDao extends Dao{

	public RecipeDao(DatabaseConnector databaseConnector){
		super(databaseConnector);
	}
	
	public RecipeDao(){
		super(DatabaseConnector.getInstance());
	}
	
	public void close() throws SQLException{
		databaseConnector.close();
	}
	
	public ArrayList<FoodRecipe> getRecipeList(){
		ArrayList<FoodRecipe> rl = new ArrayList<FoodRecipe>();
		String sql = null;
		ResultSet rs = null;
		
		sql = "SELECT * FROM recipe;";
		
		try{
			rs = databaseConnector.query(sql);
			if(!rs.isBeforeFirst()){
				System.out.println("RecipeDao getRecipeList() : no recipe item"); 
			}
			while(rs.next()){
				FoodRecipe foodrecipe = new FoodRecipe(rs.getString("name"), rs.getInt("time"), rs.getString("description"));
				rl.add(foodrecipe);
			}
		} catch(SQLException e){
			
		}
		return rl;
	}
}
