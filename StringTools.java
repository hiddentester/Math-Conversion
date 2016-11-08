//StringTools

package hiddentester;

public class StringTools
{
   public static String reverse(String s)
   {
      String flipped = "";
      
      for (int index = s.length() - 1; index >= 0; index --)
      {
         flipped += String.valueOf(s.charAt(index));
      } //for loop
      
      return flipped;
   } //reverse method
   
   public static int nIndexOf(String data, String search, int num)
   {
      int output = 0;
      
      while (!data.isEmpty() && num > 0)
      {
         if (data.indexOf(search) != -1)
         {
            output += data.indexOf(search) + 1;
            data = data.substring(data.indexOf(search) + 1);
            num --;
         } else
         {
            data = "";
            output = 0;
         } //if structure
      } //for loop
      
      return output -1;
   } //nIndexOf method
   
   public static int nIndexOfIgnoreCase(String data, String search, int num)
   {
      return nIndexOf(data.toLowerCase(), search.toLowerCase(), num);
   } //nIndexOfIgnoreCase method
   
   public static int numberOf(String data, String search)
   {
      int count = 0;
      
      for (int index = 0; index + search.length() < data.length(); index ++)
      {
         if (data.substring(index, index + search.length()).equals(search))
         {
            count ++;
         } //if structure
      } //for loop
      
      return count;
   } //numberOf method
   
   public static int numberOfIgnoreCase(String data, String search)
   {
      return (numberOf(data.toLowerCase(), search.toLowerCase()));
   } //numberOfIgnoreCase method
} //StringTools class