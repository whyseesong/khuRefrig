package classes;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JList;

import Databases.IngredientDao;

public class FoodRecipe extends JFrame{
	
	private String reName;
	private int reTime;
	private String description;
	
	private IngredientDao ingredientDao = new IngredientDao();
	private ArrayList<Ingredient> ingredientList = new ArrayList<>();
	private JList jingredientList;
	
//==================constructor & destructor=============
	public FoodRecipe(){
		super("Food Recipe");
		
	}
	public FoodRecipe(String reName, int reTime, String description){
		super();
		this.reName = reName;
		this.reTime = reTime;
		this.description = description;
	}
//=====================================================
	
//===============getset functions (simple)==================
	public String getName(){
		return reName;
	}
	public int getTime(){
		return reTime;
	}
	public String getDescription(){
		return description;
	}
	public void setName(String name){
		this.reName = name;
	}
	public void setTime(int time){
		this.reTime = time;
	}
	public void setDescription(String description){
		this.description = description;
	}
//========================================================
	
//======================ingredient list getset=================
	public ArrayList<Ingredient> getIngredientList(){
		return ingredientList;
	}
	
//============================================================

//===================load & save ingredient list==============
	public ArrayList<Ingredient> loadNsetIngredientList(FoodRecipe frp){
		ArrayList<Ingredient> everyil = ingredientDao.getIngredientList();	
		ArrayList<Ingredient> matchil = new ArrayList<Ingredient>();
		
		for(int i=0; i < everyil.size(); i++){
			if(everyil.get(i).getRecipe_name().equals(frp.getName())){
				matchil.add(everyil.get(i));
			}
		}
		frp.ingredientList = matchil;
		return frp.ingredientList;
	}
	
	public void saveIngredientList(){
		//useless
	}
//============================================================
	public void asdf(){
		//loadNsetIngredientList();
		System.out.println(this.reName);
		System.out.print(this.reTime);System.out.println("min");
		System.out.println(this.description);
		System.out.print(ingredientList.size());
		for(int i=0; i<ingredientList.size(); i++){
			System.out.println(ingredientList.get(i).getName());
		}
	}
}
