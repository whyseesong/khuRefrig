package classes;

import java.util.StringTokenizer;

public class Food {
   private int id;
   private String name;
   private String dueDate;
   private String buyDate;
   private int type;
   private float count;
   private String freshRate;
   public Food(String name, int type, String freshRate, String dueDate, String buyDate,
         float count) {
      super();
      this.name = name;
      this.dueDate = dueDate;
      this.buyDate = buyDate;
      this.type = type;
      this.count = count;
      this.freshRate = freshRate;
   }
   public Food(String name, int type, String dueDate, String buyDate,
         float count) {
      super();
      this.name = name;
      this.dueDate = dueDate;
      this.buyDate = buyDate;
      this.type = type;
      this.count = count;
   }
   public Food(int id, String name, String dueDate, String buyDate, int type,
         float count, String freshRate) {
      super();
      this.id = id;
      this.name = name;
      this.dueDate = dueDate;
      this.buyDate = buyDate;
      this.type = type;
      this.count = count;
      this.freshRate = freshRate;
   }
   public int getId() {
      return id;
   }
   public void setId(int id) {
      this.id = id;
   }
   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }
   public String getDueDate() {
      return dueDate;
   }
   public void setDueDate(String dueDate) {
      this.dueDate = dueDate;
   }
   public String getBuyDate() {
      return buyDate;
   }
   public void setBuyDate(String buyDate) {
      this.buyDate = buyDate;
   }
   public int getType() {
      return type;
   }
   public void setType(int type) {
      this.type = type;
   }
   public float getCount() {
      return count;
   }
   public void setCount(float count) {
      this.count = count;
   }
   public String getFreshRate() {
      return freshRate;
   }
   public int getFreshRatetoInt() {
      return Integer.parseInt(freshRate);
   }
   public void setFreshRate(String freshRate) {
      this.freshRate = freshRate;
   }
   public void stringtoken(String fr) {
      String[] date = new String[3];
      StringTokenizer st = new StringTokenizer(fr, "-");
      
      for(int i=0; st.hasMoreTokens(); ++i) {
         date[i] = st.nextToken();
      }
   }
   public int calFreshRate(String calfr1, String calfr2) {
      String[] date1 = new String[3];
      StringTokenizer st = new StringTokenizer(calfr1, "-");
      
      for(int i=0; st.hasMoreTokens(); ++i) {
         date1[i] = st.nextToken();
      }
      
      String[] date2 = new String[3];
      st = new StringTokenizer(calfr2, "-");
      
      for(int i=0; st.hasMoreTokens(); ++i) {
         date2[i] = st.nextToken();
      }
      
      int diffYear = Integer.parseInt(date1[0]) - Integer.parseInt(date2[0]);
      int diffMonth = Integer.parseInt(date1[1]) - Integer.parseInt(date2[1]);
      int diffDay = Integer.parseInt(date1[2]) - Integer.parseInt(date2[2]);
      
      return diffYear*365 + diffMonth*30 + diffDay;
      
   }
}