package Databases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import classes.Food;

public class FoodDao extends Dao{

   public FoodDao(DatabaseConnector databaseConnector) {
      super(databaseConnector);
      // TODO Auto-generated constructor stub
   }
   
   public FoodDao() {
      super(DatabaseConnector.getInstance());
   }
   
   public void close() throws SQLException {
      databaseConnector.close();
   }

   public ArrayList<Food> getFoodList() {
      ArrayList<Food> fl = new ArrayList<Food>();
      
      String sql = null;
      ResultSet rs = null;
      
      sql = "SELECT * FROM food;";
      
      try {
         rs = databaseConnector.query(sql);
         if(!rs.isBeforeFirst()) {
            System.out.println("FoodDao getFoodList() : No food item");
         }
         while(rs.next()) {
            Food food = new Food(rs.getInt("id"), rs.getString("name"), rs.getString("due_date"), rs.getString("buy_date"), rs.getInt("type"), rs.getFloat("count"), rs.getString("fresh_rate"));
            fl.add(food);
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }   
      
      return fl;      
   }
   
   public int checkFood(String foodName) {
      String sql = null;
      ResultSet rs = null;
      
      sql = "SELECT * FROM food WHERE name LIKE '" + foodName + "';";
      try {
         rs = databaseConnector.query(sql);
         if(!rs.isBeforeFirst()) {
            return 0;
         }
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      return 1;      
   }
   
   public int addFood(Food food) {
      String sql = null;
      int flg = 0;
      
      if(checkFood(food.getName()) == 1) {
         System.out.println("이미 아이템 존재함");
         return -1;
      }
      food.setFreshRate(String.valueOf(food.calFreshRate(food.getDueDate(), food.getBuyDate())));
      sql = "INSERT INTO food(name, type, fresh_rate, due_date, buy_date, count) VALUES('" 
            + food.getName() + "', "
            + food.getType() + ", '"
            + food.getFreshRate() + "', '"
            + food.getDueDate() + "', '"
            + food.getBuyDate() + "', "
            + food.getCount() + ");";
      
      try {
         flg = databaseConnector.update(sql);
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      
      return flg;
   }
   
   public int updateFoodCount(int id, float count) {
      String sql = null;
      int flg = 0;
      
      sql = "UPDATE food SET count = " + count + " WHERE id = " + id + ";";
      
      try {
         flg = databaseConnector.update(sql);
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      
      return flg;
   }
   
   public Food getFood(int id) {
      Food tmp = null;
      
      String sql = null;
      ResultSet rs = null;
      
      sql = "SELECT * FROM food where id = " + id + ";";
      
      try {
         rs = databaseConnector.query(sql);
         if(!rs.isBeforeFirst()) {
            System.out.println("FoodDao getFoodList() : No food item");
         }
         while(rs.next()) {
            tmp = new Food(rs.getInt("id"), rs.getString("name"), rs.getString("due_date"), rs.getString("buy_date"), rs.getInt("type"), rs.getFloat("count"), rs.getString("fresh_rate"));
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }   
      
      return tmp;
   }

   public int deleteFood(int id) {
      String sql = null;
      int flg = 0;
      
      sql = "DELETE from food WHERE id = " + id + ";";
      
      try {
         flg = databaseConnector.update(sql);
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      
      return flg;      
   }
}