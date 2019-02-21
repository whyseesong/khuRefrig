package classes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;

import Databases.FoodDao;

public class Management extends JFrame implements ActionListener{
   static final String ORDER_URI = "http://www.coupang.com/np/categories/149958";
   
   static final String EDIT = "Edit";
   static final String ORDER = "Order";
   static final String REMOVE = "Remove";
   static final String ADD = "ADD";
   
   static final String FISH = "Fish";
   static final String ETC = "Etc";
   static final String FRUIT = "Fruit";
   static final String MEAT = "Meat";
   
   static public FoodDao foodDao = new FoodDao();
   private ArrayList<Food> foodList;      // 실제 food 리스트
   
   /// FoodListPanel 
   private JPanel FoodListPanel;
   private JScrollPane jScrollPane;
   private JList jfoodList;      //화면에 리스트 뷰   
   
   /// BottomPanel
   private JPanel BottomPanel;   
   private JButton btnEdit;
   private JButton btnOrder;
   private JButton btnRemove;
   private JButton btnAdd;
   
   /// RefriPanel
   private JPanel RefriPanel;
   private JButton btnFish;
   private JButton btnEtc;
   private JButton btnFruit;
   private JButton btnMeat;
   
   //EditDialog
   private EditDialog editDialog;
   
   /// AddDialog
   private AddDialog addDialog;
   
   public Management() {
      super("Food List");
      this.setLayout(new GridBagLayout());
      this.setSize(800, 600);
      this.setVisible(true);
      
      configFoodPanel();
      
      configBottomPanel();
      
      configRefriPanel();
      
      GridBagConstraints c = new GridBagConstraints();
      c.anchor = GridBagConstraints.WEST;      
      this.add(FoodListPanel, c);
      
      c.weightx = 0.1;
      this.add(BottomPanel, c);
      
      c.weightx = 0.0;
      this.add(RefriPanel, c);
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      // TODO Auto-generated method stub
      Object o = e.getSource();
      if(o instanceof JButton) {
         JButton btn = (JButton)o;
         
         if(btn.getText().equals(EDIT)) {
            System.out.println("Management actionPerformed() : " + EDIT);
            if(jfoodList.getSelectedIndex() < 0) {
               System.out.println("Management actionPerformed() : " + EDIT + "Selected item is none.");
               return ;
            }
            editDialog = new EditDialog(foodList.get(jfoodList.getSelectedIndex()));
         }
         else if(btn.getText().equals(ORDER)) {            
            System.out.println("Management actionPerformed() : " + ORDER);
             Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
             if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
                 try {
                    URI uri = URI.create(ORDER_URI);
                     desktop.browse(uri);
                 } catch (Exception ee) {
                     ee.printStackTrace();
                 }
             }
         }
         else if(btn.getText().equals(REMOVE)) {
            System.out.println("Management actionPerformed() : " + REMOVE);
            if(jfoodList.getSelectedIndex() < 0) {
               System.out.println("Management actionPerformed() : " + EDIT + "Selected item is none.");
               return ;
            }
            
            int selcIdx = jfoodList.getSelectedIndex();
            Food delFood  = foodList.get(selcIdx);
            int result = JOptionPane.showConfirmDialog(this, delFood.getName() + "을(를) 삭제하시겠습니까?", "WARNING", JOptionPane.YES_NO_OPTION);
            if(result == JOptionPane.YES_OPTION) {
               if(foodDao.deleteFood(delFood.getId()) > 0 ) {
                  System.out.println("Management Revome Success : " + delFood.getName());
                  foodList.remove(selcIdx);
                  jfoodList.invalidate();
                  jScrollPane.repaint();
               }
               else {
                  System.out.println("Management Revome Fail : " + delFood.getName());                  
               }
               
            }
         }
         else if(btn.getText().equals(ADD)) {
            System.out.println("Management actionPerformed() : " + ADD);
            addDialog = new AddDialog();
         }
         else if(btn.getText().equals(FISH)) {
            System.out.println("Management actionPerformed() : " + FISH);
            /// 해물 타입은 2
            jfoodList.clearSelection();
            jfoodList.setSelectedIndices(getIndices(2));
         }
         else if(btn.getText().equals(ETC)) {
            System.out.println("Management actionPerformed() : " + ETC);
            /// 기타 타입은 4
            jfoodList.clearSelection();
            jfoodList.setSelectedIndices(getIndices(4));
         }
         else if(btn.getText().equals(FRUIT)) {
            System.out.println("Management actionPerformed() : " + FRUIT);
            /// 채소 타입은 3
            jfoodList.clearSelection();
            jfoodList.setSelectedIndices(getIndices(3));
         }
         else if(btn.getText().equals(MEAT)) {
            System.out.println("Management actionPerformed() : " + MEAT);
            /// 육류 타입은 1
            jfoodList.clearSelection();
            jfoodList.setSelectedIndices(getIndices(1));
         }
      }
      
   }
      
   private int[] getIndices(int type) {
      ArrayList<Integer> idx = new ArrayList();
      for(int i = 0; i < foodList.size(); ++i) {
         if(foodList.get(i).getType() == type) {
            idx.add(i);
         }
      }
       int[] ret = new int[idx.size()];
       for (int i=0; i < ret.length; i++)
       {
           ret[i] = idx.get(i).intValue();
       }
       return ret;      
   }
   
   private void configFoodPanel() {
      
      //DB연결 후 ArrayList인 foodList에 food를 저장한다.
      foodList = new ArrayList<>();
      foodList = foodDao.getFoodList();
      
      //ArrayList를 배열로 바꾼다.
      Food foodArr[] = new Food[foodList.size()];         
      foodArr = foodList.toArray(foodArr);      
         
      ///   JList setting
      jfoodList = new JList(foodArr);
      jfoodList.setCellRenderer(new FoodCellRenderer());
      jfoodList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
      jfoodList.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
            // TODO Auto-generated method stub
             int index = jfoodList.locationToIndex(e.getPoint());
             btnMeat.setBackground(Color.white);
             btnMeat.setForeground(Color.black);
             btnFish.setBackground(Color.white);
             btnFish.setForeground(Color.black);
             btnFruit.setBackground(Color.white);
             btnFruit.setForeground(Color.black);
             btnEtc.setBackground(Color.white);
             btnEtc.setForeground(Color.black);
             switch(foodList.get(index).getType()) {
                case 1:
                   btnMeat.setBackground(Color.black);
                   btnMeat.setForeground(Color.WHITE);
                   break;
                case 2:
                   btnFish.setBackground(Color.black);
                   btnFish.setForeground(Color.WHITE);
                   break;
                case 3:
                   btnFruit.setBackground(Color.black);
                   btnFruit.setForeground(Color.WHITE);
                   break;
                case 4:
                   btnEtc.setBackground(Color.black);
                   btnEtc.setForeground(Color.WHITE);
                   break;
             }
         }
      });
      jfoodList.setVisible(true);      
      
      //스크롤 패널에 리스트를 표현한다.
      jScrollPane = new JScrollPane(jfoodList);
      
      /// 푸드 리스트를 패널에 넣는다.
      FoodListPanel = new JPanel();
      FoodListPanel.setLayout(new BorderLayout());
      FoodListPanel.add(jScrollPane);
      FoodListPanel.setSize(600, 400);
      FoodListPanel.setVisible(true);   
      
   }
   
   private void configBottomPanel() {
      BottomPanel = new JPanel();
      
      btnEdit = new JButton();
      btnOrder = new JButton();
      btnRemove = new JButton();
      btnAdd = new JButton();
      
      btnEdit.setText(EDIT);
      btnOrder.setText(ORDER);
      btnRemove.setText(REMOVE);
      btnAdd.setText(ADD);
      
      btnEdit.setBackground(Color.white);
      btnOrder.setBackground(Color.white);
      btnRemove.setBackground(Color.white);
      btnAdd.setBackground(Color.white);
      
      btnEdit.addActionListener(this);
      btnOrder.addActionListener(this);
      btnRemove.addActionListener(this);
      btnAdd.addActionListener(this);
      
      
      btnEdit.setVisible(true);;
      btnOrder.setVisible(true);
      btnRemove.setVisible(true);
      btnRemove.setVisible(true);
      
      BottomPanel.add(btnEdit);
      BottomPanel.add(btnOrder);
      BottomPanel.add(btnRemove);
      BottomPanel.add(btnAdd);
      BottomPanel.setBackground(Color.white);      
      BottomPanel.setVisible(true);
      
   }
   
   private void configRefriPanel() {
      RefriPanel = new JPanel(new FlowLayout());
      
      btnFish = new JButton();
      btnEtc = new JButton();
      btnFruit = new JButton();
      btnMeat = new JButton();
      
      btnFish.setText(FISH);
      btnEtc.setText(ETC);
      btnFruit.setText(FRUIT);
      btnMeat.setText(MEAT);
      
      btnFish.setBackground(Color.white);
      btnEtc.setBackground(Color.white);
      btnFruit.setBackground(Color.white);
      btnMeat.setBackground(Color.white);
      
      btnFish.setVisible(true);
      btnEtc.setVisible(true);
      btnFruit.setVisible(true);
      btnMeat.setVisible(true);
      
      btnFish.addActionListener(this);
      btnEtc.addActionListener(this);
      btnFruit.addActionListener(this);
      btnMeat.addActionListener(this);
      
      RefriPanel.add(btnFish);
      RefriPanel.add(btnEtc);
      RefriPanel.add(btnFruit);
      RefriPanel.add(btnMeat);
      RefriPanel.setVisible(true);
      
   }
   
   class FoodCellRenderer extends JPanel implements ListCellRenderer {      
      private ArrayList<JLabel> jLabelList = new ArrayList<JLabel>();
      
      public FoodCellRenderer() {
         //setOpaque(true);
         for(int i = 0; i < 3; ++i) {
            // name, duedate, type
            jLabelList.add(new JLabel());            
         }
      }

      @Override
      public Component getListCellRendererComponent(JList list, Object value,
            int index, boolean isSelected, boolean cellHasFocus) {
         // TODO Auto-generated method stub
         
         Food food = (Food)value;
         jLabelList.get(0).setText(food.getName());
         jLabelList.get(1).setText(food.getDueDate());
         //jLabelList.get(2).setText(food.getType() + "");
         
         for(JLabel tmp : jLabelList) {
            tmp.setVisible(true);

            this.add(tmp);
         }
         this.setBackground(Color.white);
          
         /// fresh_rate 40 이하 색깔 따로 지정
            if(Integer.parseInt(food.getFreshRate()) <= 70) {
               this.setBackground(Color.RED);   
            }
            else {
               this.setBackground(Color.white);
            }
            
         this.setVisible(true);

         for(JLabel tmp : jLabelList) {
            if(isSelected) {
               tmp.setForeground(Color.green);
            }
            else {
               tmp.setBackground(Color.white);
               tmp.setForeground(Color.black);
            }
         }
         return this;
      }
      
   }

   class EditDialog extends JDialog implements ActionListener{
      private static final String EDIT = "Edit";
      private static final String CANCEL = "Cancel";
      
      private Food selcFood;
      private JComboBox comboBox;
      
      private String selcAttr;
      private JLabel curAttrLabel;
      private JLabel newAttrLabel;      
      private JTextField newAttrTextField;
      
      private JButton btnEdit;
      private JButton btnCancel;
   
      public EditDialog(Food food) {
         /// Selected Food
         selcFood = food;
                  
         /// Edit Dialog Layout Setting
         this.setLayout(new GridBagLayout());

         /// GridBagLayout Constraints(Option)
           GridBagConstraints gbc = new GridBagConstraints();
         
           /// Title Label Setting
           JLabel title = new JLabel("Edit " + food.getName());
         addGrid(gbc, title, 0, 0, 1, 1, 0, 0);
         
         this.getContentPane().add(title, gbc);
         
         /// ComboBox Setting
         JLabel editlabel = new JLabel("Count");
         addGrid(gbc, editlabel, 0, 1, 1, 1, 0, 0);
         
         this.getContentPane().add(editlabel, gbc);
         
         /// Current Attribute Setting
         selcAttr = new String("Count");
         curAttrLabel = new JLabel(String.format("%-10s %-10s :   %.2f", "currnet", selcAttr, selcFood.getCount()) );
         addGrid(gbc, curAttrLabel,  0,  2,  1,  1,  0,  0);
         
         this.getContentPane().add(curAttrLabel,  gbc);
         
         /// New Attribute Setting
         newAttrLabel = new JLabel(String.format("%-10s %-10s :   ", "new", selcAttr));
         addGrid(gbc, newAttrLabel,  0,  3,  1,  1,  0,  0);
         this.getContentPane().add(newAttrLabel,  gbc);
         
         newAttrTextField = new JTextField(5);
         addGrid(gbc, newAttrTextField,  1,  3,  1,  1,  0,  0);
         this.getContentPane().add(newAttrTextField,  gbc);
         
         //Edit, Cancel Button Setting
         btnEdit = new JButton(EDIT);
         btnEdit.addActionListener(this);
         addGrid(gbc, btnEdit,  0,  4,  1,  1,  0,  0);
         this.getContentPane().add(btnEdit,  gbc);
         btnCancel = new JButton(CANCEL);
         btnCancel.addActionListener(this);
         addGrid(gbc, btnCancel,  1,  4,  1,  1,  0,  0);
         this.getContentPane().add(btnCancel,  gbc);
         
         this.setSize(400, 300);
         this.setModal(true);
         this.setVisible(true);
      }

      @Override
      public void actionPerformed(ActionEvent e) {
         // TODO Auto-generated method stub
         if(e.getSource() instanceof JButton) {
            JButton tmp = (JButton)e.getSource();
            if(tmp.getText().equals(EDIT)) {
               String inputStr = new String(newAttrTextField.getText());
               System.out.println("EditDialog actionPerformed() : " + EDIT + " clicked");
               if(inputStr == "") {
                  System.out.println("EditDialog actionPerformed() : new count is none.");
               }
               else {
                  System.out.println("EditDialog actionPerformed() : new count : " + inputStr);
                  try {
                     if(Management.foodDao.updateFoodCount(selcFood.getId(), Float.valueOf(inputStr)) > 0) {
                        System.out.println("EditDialog Edit : sucess!!");            
                        int idx = foodList.indexOf(selcFood);
                        foodList.set(idx, foodDao.getFood(selcFood.getId()));
                     }
                     else {
                        System.out.println("EditDialog Edit : fail!!");
                     }
                  } catch(NumberFormatException  nfe) {
                     System.out.println("EditDialog actionPerformed() : new count is not numeric.");
                  }
               }                               
            }
            else if(tmp.getText().equals(CANCEL)) {
               System.out.println("EditDialog actionPerformed() : " + CANCEL + " clicked");
            }
            
            this.dispose();
         }
      }
   }
   
   class AddDialog extends JDialog implements ActionListener{
      private static final String ADD = "ADD";
      private static final String CANCEL = "Cancel";
      
      private JTextField nameField;
      private JTextField typeField;
      //private JTextField freshRateField;
      private JTextField dueDateField;
      private JTextField buyDateField;
      private JTextField countField;
      
      private JButton btnAdd;
      private JButton btnCancel;
      
      public AddDialog() {
         // TODO Auto-generated constructor stub

         this.setLayout(new GridBagLayout());
         
         /// GridBagLayout Constraints(Option)
           GridBagConstraints gbc = new GridBagConstraints();
                             
           JLabel nameLabel = new JLabel("Name");
           addGrid(gbc, nameLabel, 0, 0, 1, 1, 0, 0);
           this.getContentPane().add(nameLabel, gbc);
           
           nameField = new JTextField(5);
           addGrid(gbc, nameLabel, 1, 0, 1, 1, 0, 0);
           this.getContentPane().add(nameField, gbc);

           JLabel typeLabel = new JLabel("Type");
           addGrid(gbc, typeLabel, 0, 1, 1, 1, 0, 0);
           this.getContentPane().add(typeLabel, gbc);
           
           typeField = new JTextField(5);
           addGrid(gbc, typeField, 1, 1, 1, 1, 0, 0);
           this.getContentPane().add(typeField, gbc);

//           JLabel freshRateLabel = new JLabel("Fresh Rate");
//           addGrid(gbc, freshRateLabel, 0, 2, 1, 1, 0, 0);
//           this.getContentPane().add(freshRateLabel, gbc);
//           
//           freshRateField = new JTextField(5);
//           addGrid(gbc, freshRateField, 1, 2, 1, 1, 0, 0);
//           this.getContentPane().add(freshRateField, gbc);

           JLabel dueDateLabel = new JLabel("Due Date");
           addGrid(gbc, dueDateLabel, 0, 3, 1, 1, 0, 0);
           this.getContentPane().add(dueDateLabel, gbc);
           
           dueDateField = new JTextField(5);
           addGrid(gbc, dueDateField, 1, 3, 1, 1, 0, 0);
           this.getContentPane().add(dueDateField, gbc);

           JLabel buyDateLabel = new JLabel("Buy Date");
           addGrid(gbc, buyDateLabel, 0, 4, 1, 1, 0, 0);
           this.getContentPane().add(buyDateLabel, gbc);
           
           buyDateField = new JTextField(5);;
           addGrid(gbc, buyDateField, 1, 4, 1, 1, 0, 0);
           this.getContentPane().add(buyDateField, gbc);

           JLabel countLabel = new JLabel("Count");
           addGrid(gbc, countLabel, 0, 5, 1, 1, 0, 0);
           this.getContentPane().add(countLabel, gbc);
           
           countField = new JTextField(5);
           addGrid(gbc, countField, 1, 5, 1, 1, 0, 0);
           this.getContentPane().add(countField, gbc);
         
         //Edit, Cancel Button Setting
         btnEdit = new JButton(ADD);
         btnEdit.addActionListener(this);
         addGrid(gbc, btnEdit,  0,  6,  1,  1,  0,  0);
         this.getContentPane().add(btnEdit,  gbc);
         btnCancel = new JButton(CANCEL);
         btnCancel.addActionListener(this);
         addGrid(gbc, btnCancel,  1,  6,  1,  1,  0,  0);
         this.getContentPane().add(btnCancel,  gbc);
         
         JLabel noticetype = new JLabel("육류 : 1, 해물 : 2, 채소 : 3, etc : 4, 실외 : 0");
           addGrid(gbc, noticetype, 0, 7, 1, 1, 0, 0);
           this.getContentPane().add(noticetype, gbc);
           
         this.setSize(400, 300);
         this.setModal(true);
         this.setVisible(true);

      }

      @Override
      public void actionPerformed(ActionEvent e) {
         // TODO Auto-generated method stub
         if(e.getSource() instanceof JButton) {
            JButton tmp = (JButton)e.getSource();
            if(tmp.getText().equals(ADD)) {               
               System.out.println("EditDialog actionPerformed() : " + ADD + " clicked");
               Food food = null;
               try {
                  food = new Food(nameField.getText(),
                        Integer.valueOf(typeField.getText()),
                        dueDateField.getText(), 
                        buyDateField.getText(),
                        Float.valueOf(countField.getText()));   
                  if(food.getName() == "") {
                     System.out.println("AddDialog actionPerformed() : name is none.");
                     return ;
                  }
               } catch(NumberFormatException nfs) {
                  System.out.println("AddDialog actionPerformed() : value is not numeric.");
                  return ;
               }
               if(foodDao.addFood(food) < 0) {
                  System.out.println("AddDialog actionPerformed() : Add Fail");
               }
               else {
                  System.out.println("AddDialog actionPerformed() : Add Success");
               }
            }
            else if(tmp.getText().equals(CANCEL)) {
               System.out.println("EditDialog actionPerformed() : " + CANCEL + " clicked");
            }
            
            this.dispose();
         }
      }
   }
   
    public void addGrid(GridBagConstraints gbc, Component c,  
            int gridx, int gridy, int gridwidth, int gridheight, int weightx, int weighty) {
      gbc.gridx = gridx;
      gbc.gridy = gridy;
      gbc.gridwidth = gridwidth;
      gbc.gridheight = gridheight;
      gbc.weightx = weightx;
      gbc.weighty = weighty;
    }         
}
