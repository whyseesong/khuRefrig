package classes;

import java.awt.BorderLayout;
import java.awt.Color;
//import java.awt.Color;
//import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
//import javax.swing.JTextField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import Databases.FoodDao;
import Databases.SettingDao;

public class UserInterface extends JFrame implements ActionListener{
	Recommendation rcmd = new Recommendation();
	String recipeNameToSee;
	int isBListExist=0;
	int isFListExist=0;
	
	static public FoodDao foodDao = new FoodDao();
	private ArrayList<Food> foodList;   //실제 food 리스트
	private static SettingDao settingdao = new SettingDao();
	
//=============main panel=================================	
	public JPanel panel = new JPanel(new BorderLayout());
	public JPanel panel1 = new JPanel();
	public JPanel panel2 = new JPanel();
	
	public JButton button1 = new JButton("Foods");
	public JButton button2 = new JButton("Recipe");
	public JButton button3 = new JButton("Setting");
	public JLabel label = new JLabel("Welcome to KHUjango");
//========================================================
	
//================recommendation panel====================
	//main recommendation
	JPanel recipe_panel = new JPanel();
	JPanel recipe_panel1 = new JPanel();
	JPanel recipe_panel2 = new JPanel();
	
	JButton recipe_button1 = new JButton("Basic Recommendation");
	JButton recipe_button2 = new JButton("Freshness Based Recommendation");
	JButton recipe_button3 = new JButton("Back");
	JLabel recipe_label = new JLabel("Recipe Recommendation");
	
	//basic recommendation
	JPanel recipe_basic_panel1 = new JPanel();
	JPanel recipe_basic_panel2 = new JPanel();
	JPanel recipe_basic_panel3 = new JPanel();
	JPanel recipe_basic_panel4 = new JPanel();
	JTextArea recipe_basic_area = new JTextArea(); 
	JScrollPane spane = new JScrollPane(recipe_basic_area);
	
	//JButton recipe_basic_button1 = new JButton("basic Detail");
	JButton recipe_basic_button2 = new JButton("Back");
	JLabel recipe_basic_label = new JLabel("Basic Recommendation");
	
	//freshness recommendation
	JPanel recipe_fresh_panel1 = new JPanel();
	JPanel recipe_fresh_panel2 = new JPanel();
	JPanel recipe_fresh_panel3 = new JPanel();
	JPanel recipe_fresh_panel4 = new JPanel();
	JTextArea recipe_fresh_area = new JTextArea(); 
	
	//JButton recipe_fresh_button1 = new JButton("fresh Detail");
	JButton recipe_fresh_button2 = new JButton("Back");
	JLabel recipe_fresh_label = new JLabel("Freshness Based Recommendation");
	
//==============================================================
	
	
	public UserInterface() {
		// TODO Auto-generated constructor stub
		super("냉장 KHU");
		
		foodList = new ArrayList<>();
	    foodList = foodDao.getFoodList();
		
		panel1.add(label);
		panel2.add(button1);
		panel2.add(button2);
		panel2.add(button3);
		
		//this.setLayout(new FlowLayout());
		panel.add(panel1, BorderLayout.NORTH);
		panel.add(panel2, BorderLayout.CENTER);
		this.add(panel);
		this.setSize(800, 600);
		this.setVisible(true);
		
		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);

		//swing에만 있는 X버튼 클릭시 종료
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
	//==================recommendation panel==================		
		//main recommendation
		recipe_panel1.add(recipe_label);
		recipe_panel2.add(recipe_button1);
		recipe_panel2.add(recipe_button2);
		recipe_panel2.add(recipe_button3);
		recipe_panel.add(recipe_panel1, BorderLayout.NORTH);
		recipe_panel.add(recipe_panel2, BorderLayout.CENTER);
		
		recipe_button1.addActionListener(this);
		recipe_button2.addActionListener(this);
		recipe_button3.addActionListener(this);
		
		//basic recommendation
		recipe_basic_panel1.add(recipe_basic_label);
//		recipe_basic_panel3.add(recipe_basic_button1);
		recipe_basic_panel3.add(recipe_basic_button2);
		
//		recipe_basic_button1.addActionListener(this);
		recipe_basic_button2.addActionListener(this);
		
		//fresh recommendation
		recipe_fresh_panel1.add(recipe_fresh_label);
//		recipe_fresh_panel2.add(recipe_fresh_button1);
		recipe_fresh_panel3.add(recipe_fresh_button2);
		
//		recipe_fresh_button1.addActionListener(this);
		recipe_fresh_button2.addActionListener(this);
	//=========================================================
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	//==============main panel===============================
		JButton btn = (JButton)e.getSource();
		
		//save recipe name selection
		recipeNameToSee = btn.getText();
		recipe_basic_panel4.removeAll();
		this.panel.revalidate();
		
		if(btn.getText().equals("Foods")) {
			new Management();		
		}
		else if(btn.getText().equals("Recipe")) {
			this.panel.removeAll();
			this.panel.add(recipe_panel1, BorderLayout.NORTH);
			this.panel.add(recipe_panel2, BorderLayout.CENTER);
			//rcmd.ppprint();
			this.panel.revalidate();
			this.panel.repaint();
		}
		else if(btn.getText().equals("Setting")) {
			JFrame tmp = new JFrame();
	         tmp.setSize(800, 600);
	         tmp.setTitle("Setting");
	         JPanel settingpanel = new JPanel(null);
	         tmp.add(settingpanel);
	         
	         settingpanel.setBackground(Color.white);

	         Setting setting = settingdao.getSettingData();
	         
	         JLabel label1 = new JLabel("         temperature");
	         JLabel label2 = new JLabel("          brightness");
	         JLabel label3 = new JLabel("   water temperature");
	         JLabel label4 = new JLabel("freezer");
	         JLabel label5 = new JLabel("freezer");
	         JLabel label6 = new JLabel("hot water");
	         JLabel label7 = new JLabel("refrigerator");
	         JLabel label8 = new JLabel("refrigerator");
	         JLabel label9 = new JLabel("cold water");
	         
	         label1.setBorder(new LineBorder(Color.black));
	         label2.setBorder(new LineBorder(Color.black));
	         label3.setBorder(new LineBorder(Color.black));
	         
	         JTextField tf1 = new JTextField(setting.getFreezer_temp()+"");
	         JTextField tf2 = new JTextField(setting.getFreezer_brightness()+"");
	         JTextField tf3 = new JTextField(setting.getHotwater_temp()+"");
	         JTextField tf4 = new JTextField(setting.getRefrige_temp()+"");
	         JTextField tf5 = new JTextField(setting.getRefrige_brightness()+"");
	         JTextField tf6 = new JTextField(setting.getColdwater_temp()+"");
	         
	         label1.setBounds(100, 125, 125, 50);
	         label2.setBounds(100, 225, 125, 50);
	         label3.setBounds(100, 325, 125, 50);
	         label4.setBounds(250, 100, 100, 100);
	         label5.setBounds(250, 200, 100, 100);
	         label6.setBounds(250, 300, 100, 100);
	         label7.setBounds(450, 100, 100, 100);
	         label8.setBounds(450, 200, 100, 100);
	         label9.setBounds(450, 300, 100, 100);
	                  
	         tf1.setBounds(330, 125, 100, 50);
	         tf2.setBounds(330, 225, 100, 50);
	         tf3.setBounds(330, 325, 100, 50);
	         tf4.setBounds(530, 125, 100, 50);
	         tf5.setBounds(530, 225, 100, 50);
	         tf6.setBounds(530, 325, 100, 50);
	         
	         settingpanel.add(label1);
	         settingpanel.add(label2);
	         settingpanel.add(label3);
	         settingpanel.add(label4);
	         settingpanel.add(label5);
	         settingpanel.add(label6);
	         settingpanel.add(label7);
	         settingpanel.add(label8);
	         settingpanel.add(label9);
	         
	         settingpanel.add(tf1);
	         settingpanel.add(tf2);
	         settingpanel.add(tf3);
	         settingpanel.add(tf4);
	         settingpanel.add(tf5);
	         settingpanel.add(tf6);
	         
	         tmp.setVisible(true);
	         
	         tmp.addWindowListener(new WindowAdapter() {
	            public void windowClosing(WindowEvent windowEvent) {
	               setting.setFreezer_temp(Integer.parseInt(tf1.getText()));
	               setting.setFreezer_brightness(Integer.parseInt(tf2.getText()));
	               setting.setHotwater_temp(Integer.parseInt(tf3.getText()));
	               setting.setRefrige_temp(Integer.parseInt(tf4.getText()));
	               setting.setRefrige_brightness(Integer.parseInt(tf5.getText()));
	               setting.setColdwater_temp(Integer.parseInt(tf6.getText()));
	               
	               settingdao.updateSettingData(setting);
	            }
	         });
	         
	      }	
		

	//======================================================
		
	//============recommendation panel===================
		//main recommendation to basic
		else if(btn.getText().equals("Basic Recommendation")){
//			for(int i=0; i < foodList.size(); i++){
//				System.out.println(foodList.get(i));
//			}
			ArrayList<FoodRecipe> rbasic = new ArrayList<>();
			rbasic = rcmd.BasicRecipeMode();
			
			this.panel.removeAll();
			
			//print buttons
			if(isBListExist==0){
				for(int i=0; i < rbasic.size(); i++){
					JButton rselectbutton = new JButton(rbasic.get(i).getName());
					this.recipe_basic_panel2.add(rselectbutton);
					rselectbutton.addActionListener(this);
				}			
				isBListExist = 1;
			}
			
			this.panel.add(recipe_basic_panel1, BorderLayout.NORTH);
			this.panel.add(recipe_basic_panel2, BorderLayout.CENTER);
			this.panel.add(recipe_basic_panel3, BorderLayout.SOUTH);
			this.panel.revalidate();
			this.panel.repaint();	
		}
		//main recommendation to freshness
		else if(btn.getText().equals("Freshness Based Recommendation")){
			ArrayList<FoodRecipe> rfresh = new ArrayList<>();
			rfresh = rcmd.FreshBasedRecipeMode();
			
			this.panel.removeAll();
			
			//print buttons
			if(isFListExist==0){
				for(int i=0; i < rfresh.size(); i++){
					JButton rselectbutton = new JButton(rfresh.get(i).getName());
					this.recipe_fresh_panel2.add(rselectbutton);
					rselectbutton.addActionListener(this);
				}			
				isFListExist = 1;
			}
			
			this.panel.add(recipe_fresh_panel1, BorderLayout.NORTH);
			this.panel.add(recipe_fresh_panel2, BorderLayout.CENTER);
			this.panel.add(recipe_fresh_panel3, BorderLayout.SOUTH);
			this.panel.revalidate();
			this.panel.repaint();
		}		
		//go to home
		else if(btn.getText().equals("Back")){
			this.panel.removeAll();
			this.panel.add(panel1, BorderLayout.NORTH);
			this.panel.add(panel2, BorderLayout.CENTER);
			recipe_basic_area.setText("");;
			this.panel.revalidate();
			this.panel.repaint();		
		}
	//========================================================
		
	//===========select recipe=================================
		else if(btn.getText().equals(recipeNameToSee)){
			
			this.panel.removeAll();
			//this.panel.add(panel1, BorderLayout.NORTH);
			//this.panel.add(panel2, BorderLayout.CENTER);
			FoodRecipe frcp = new FoodRecipe();
			for(int i=0; i < rcmd.getFoodRecipeList().size(); i++){
				if(rcmd.getFoodRecipeList().get(i).getName().equals(recipeNameToSee)){
					frcp = rcmd.getFoodRecipeList().get(i);
				}
			}
			JLabel rselectedname = new JLabel(frcp.getName());
			//JLabel rselectedtime = new JLabel(frcp.getTime());
			//JTextArea rselecteddescription = new JTextArea(frcp.getDescription());
			recipe_basic_panel4.add(rselectedname);
			recipe_basic_area.append("Ingredients : ");
			for(int i=0; i<frcp.getIngredientList().size(); i++){
				recipe_basic_area.append(frcp.getIngredientList().get(i).getName());
				recipe_basic_area.append(", ");
			}
			recipe_basic_area.append("\n");
			recipe_basic_area.append("How to cook  : ");
			recipe_basic_area.append(frcp.getDescription());
			
			this.panel.add(recipe_basic_panel4, BorderLayout.NORTH);
			this.panel.add(spane, BorderLayout.CENTER);
			this.panel.add(recipe_basic_panel3, BorderLayout.SOUTH);
		
			this.panel.revalidate();
			this.panel.repaint();
		}
		
	//==========================================================
	}	
}