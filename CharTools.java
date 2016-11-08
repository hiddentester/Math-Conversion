//CharTools

package hiddentester;

public class CharTools
{
   public static boolean isVowel (char charIn)
   {
      final String vowels = "aeiou";
      
      if (vowels.indexOf((String.valueOf(charIn)).toLowerCase()) == -1)
      {
         return false;
      } //if structure
      return true;
   } //isVowel method
   
   public static boolean isLetter (char charIn)
   {
      if ((charIn >= 'A' && charIn <= 'Z') || (charIn >= 'a' && charIn <= 'z'))
      {
         return true;
      } //if structure
      return false;
   } //isLetter method
   
   public static boolean isNumber (char charIn)
   {
      if (charIn >= '0' && charIn <= '9')
      {
         return true;
      } //if structure
      return false;
   } //isNumber method
} //CharTools class