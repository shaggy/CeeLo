import java.util.*;

public class Player
{  
   private String name;
   private ArrayList<Integer> dieValues;
   
   Player()
   {
      this.dieValues = new ArrayList<>();
   
   }

  
   
   private final int numDie = 3;
   private final int numSides = 6;

   void playerRolls()
   {
      ArrayList<Die> dieArray = new ArrayList<>(numDie);
             
      
      for (int i = 0; i < numDie; i++)
      {
         dieArray.add(new Die(numSides));
         
      }
      
      for (int i = 0; i < numDie; i++)
      {
         
         dieValues.add(i, dieArray.get(i).getValue());
      }
      
         Collections.sort(dieValues); 
         dieArray.clear();
    } 
    
    
    ArrayList<Integer> getDieValues()
    {
      return dieValues;
    }
    
    void setName(String name)
    {
      this.name = name;
    }
    
    String getName()
    {
      return name;
    }
}



