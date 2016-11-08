//InputTools

package hiddentester;

public class InputTools
{
   public static int inputInt(String num){
      boolean valid = true;
      int index = 0;
      
      num = num.replaceAll(" ", "");
      
      if (num.charAt(0) == '-'){
         index = 1;
      } //if structure
      
      for (; index < num.length() && valid; index ++){
         if (!CharTools.isNumber(num.charAt(index))){
            valid = false;
         } //if structure
      } //for loop
      
      if (!valid){
         System.out.println ("Error: invalid input");
         return -1;
      } else {
         return Integer.parseInt(num);
      } //if structure
   } //inputInt method
} //InputTools class