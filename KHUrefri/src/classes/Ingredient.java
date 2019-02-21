package classes;

import java.sql.Date;

public class Ingredient {
//===============ingredient attribute===============
	private String inName;
	private int inCount;
	private String inRecipe_name;
	private int inId;
//==================================================
	
//===============ingredient »ý¼ºÀÚ===================
	public Ingredient(String name, int count, String recipe_name, int id){
		super();
		this.inName = name;
		this.inCount = count;
		this.inRecipe_name = recipe_name;
	}
	public Ingredient(){
		
	}
//=================================================
	
//================get, set functions===============
	public String getName(){
		return inName;
	}
	public int getCount(){
		return inCount;
	}
	public String getRecipe_name(){
		return inRecipe_name;
	}
	public int getId(){
		return inId;
	}
	public void setName(String name){
		this.inName = name;
	}
	public void setCount(int count){
		this.inCount = count;
	}
	public void setRecipe_name(String recipe_name){
		this.inRecipe_name = recipe_name;
	}
	public void setId(int id){
		this.inId = id;
	}
//===================================================
}
