package classes;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import Databases.RecipeDao;
import Databases.FoodDao;

public class Recommendation extends JFrame implements ActionListener{
	
//===================database, list related attributes=========
	private RecipeDao recipeDao = new RecipeDao();
	private ArrayList<FoodRecipe> foodrecipeList = new ArrayList<>();
	static public FoodDao foodDao = new FoodDao();
	private ArrayList<Food> foodList;   //실제 food 리스트
	
	private JList jrecipeList;
//=============================================================
	
//=====================interface related attributes============
	//main recommendation
	public JPanel recipe_panel = new JPanel();
	public JPanel recipe_panel1 = new JPanel();
	public JPanel recipe_panel2 = new JPanel();
	
	public JButton recipe_button1 = new JButton("Basic Recommendation");
	public JButton recipe_button2 = new JButton("Freshness Based Recommendation");
	public JButton recipe_button3 = new JButton("Back");
	public JLabel recipe_label = new JLabel("Recipe Recommendation");
	
	//basic recommendation
	public JPanel recipe_basic_panel1 = new JPanel();
	public JPanel recipe_basic_panel2 = new JPanel();
	
	private JButton recipe_basic_button1 = new JButton("basic Detail");
	private JButton recipe_basic_button2 = new JButton("Back");
	private JLabel recipe_basic_label = new JLabel("Basic Recommendation");
	
	//freshness recommendation
	private JPanel recipe_fresh_panel1 = new JPanel();
	private JPanel recipe_fresh_panel2 = new JPanel();
	
	private JButton recipe_fresh_button1 = new JButton("fresh Detail");
	private JButton recipe_fresh_button2 = new JButton("Back");
	private JLabel recipe_fresh_label = new JLabel("Freshness Based Recommendation");
//===========================================================

//==============constructor & destructor========================
	public Recommendation(){
		super();
		setFoodRecipeList();
		foodList = new ArrayList<>();
	    foodList = foodDao.getFoodList();
	    
		
	//=================panel settings========================
		recipe_panel.setLayout(null);
		
		//main recommendation & preset
		recipe_panel1.add(recipe_label);
		recipe_panel2.add(recipe_button1);
		recipe_panel2.add(recipe_button2);
		recipe_panel2.add(recipe_button3);
		
		recipe_panel.add(recipe_panel1, BorderLayout.NORTH);
		recipe_panel.add(recipe_panel2, BorderLayout.CENTER);
		this.add(recipe_panel);
		this.setSize(800, 600);
		this.setVisible(true);
		
		recipe_button1.addActionListener(this);
		recipe_button2.addActionListener(this);
		recipe_button3.addActionListener(this);
		
		//basic recommendation
		recipe_basic_panel1.add(recipe_basic_label);
		recipe_basic_panel2.add(recipe_basic_button1);
		recipe_basic_panel2.add(recipe_basic_button2);
		
		recipe_basic_button1.addActionListener(this);
		recipe_basic_button2.addActionListener(this);
		
		//fresh recommendation
		recipe_fresh_panel1.add(recipe_fresh_label);
		recipe_fresh_panel2.add(recipe_fresh_button1);
		recipe_fresh_panel2.add(recipe_fresh_button2);
		
		recipe_fresh_button1.addActionListener(this);
		recipe_fresh_button2.addActionListener(this);
	//======================================================
	}
	public Recommendation(ArrayList<FoodRecipe> foodrecipeList){
		super();
		this.foodrecipeList = foodrecipeList;
	}
//==============================================================
	
//======================action handler=======================

//==========================================================
	
//================recipelist handling=========================
	public ArrayList<FoodRecipe> loadRecipeData(){
		ArrayList<FoodRecipe> rl = recipeDao.getRecipeList();
		for (int i=0; i<rl.size(); i++){
			rl.get(i).loadNsetIngredientList(rl.get(i));
		}
		return rl;
	}
	public void saveRecipeData(){
		//useless
	}
	public ArrayList<FoodRecipe> getFoodRecipeList(){
		return foodrecipeList;
	}
	public void setFoodRecipeList(){
		this.foodrecipeList = loadRecipeData();
	}
//==============================================================
	
//===================view mode=================================
	public void viewRecipeMode(){
		//return this.recipe_panel;
		//return parent;
	}
	public ArrayList<FoodRecipe> BasicRecipeMode(){
		ArrayList<FoodRecipe> basiclist = new ArrayList<>();
		
		int foodnum = foodList.size();
		int recipenum = foodrecipeList.size();  //recipe size
		
		for(int i=0; i<recipenum; i++){
			int ingredientnum = foodrecipeList.get(i).getIngredientList().size();  //ingredient size
			int isexistinref=0;
			for(int j=0; j < ingredientnum; j++){
				for(int k=0; k<foodnum; k++){
					String fn = foodList.get(k).getName();
					String in = foodrecipeList.get(i).getIngredientList().get(j).getName();
					if(fn.equals(in)){
						isexistinref++;
					}
				}
			}
			if(isexistinref == ingredientnum){
				basiclist.add(foodrecipeList.get(i));
			}
		}
		return basiclist;    
	}
	public ArrayList<FoodRecipe> FreshBasedRecipeMode(){
		
		ArrayList<FoodRecipe> freshlist = new ArrayList<>();
		
		int foodnum = foodList.size();
		int recipenum = foodrecipeList.size();
		
		for(int i=0; i<recipenum; i++){
			int ingredientnum = foodrecipeList.get(i).getIngredientList().size();  //ingredient size
			int isexistinref=0;
			int isfresh=0;
			for(int j=0; j < ingredientnum; j++){
				for(int k=0; k<foodnum; k++){
					String fn = foodList.get(k).getName();
					String in = foodrecipeList.get(i).getIngredientList().get(j).getName();
					if(fn.equals(in)){
						isexistinref++;
						if(foodList.get(k).getFreshRatetoInt() <= 70){
							isfresh++;
						}
					}
				}
			}
			if((isexistinref == ingredientnum) && (isfresh>0)){
				freshlist.add(foodrecipeList.get(i));
			}
		}
		return freshlist;
	}
//=============================================================

//==============recipe list view==============================
	public void printRecipeList(){
		
	}
	public void printRecipeInfo(){
		
	}
//============================================================
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void ppprint(){
		this.setFoodRecipeList();
		
		for(int i=0; i<this.foodrecipeList.size(); i++){
//			System.out.println(this.foodrecipeList.get(i).getName());
//			System.out.print(this.foodrecipeList.get(i).getTime());System.out.println("min");
//			System.out.println(this.foodrecipeList.get(i).getDescription());
//			System.out.println(" ");
			for(int j=0; j<this.foodrecipeList.get(i).getIngredientList().size(); j++){
				System.out.println(this.foodrecipeList.get(i).getIngredientList().get(j).getName());
			}
		}
	}

//	compareFoodNIngredient
//	selectRecipeToCook
//	recipePrioritySetting

}
