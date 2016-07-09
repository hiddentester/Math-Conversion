//MathConversion

package hiddentester;

public class MathConversion {
   //Converts integers 1 to 999999 to Roman numerals
   public static String toRoman (int input) {
	   final String SYMBOLS = "ivxlcdmVXLCDM";
	   int unit = (int) Math.pow(10, (String.valueOf(input).length() - 1));
	   int index = (String.valueOf(input).length() - 1) * 2;
	   char unitChar = SYMBOLS.charAt(index), fiveChar = SYMBOLS.charAt(index + 1), tenChar = SYMBOLS.charAt(index + 2);
	   
      if (input < 1 || input > 999999) {
		   return "";
	   } else if (input < unit * 5) {
		   if (input >= unit * 4) {
			   return "" + unitChar + fiveChar + toRoman(input % unit);
		   } //if structure
         String output = "";
		   for (int i = 1; i * unit <= input; i++) {
			   output += unitChar;
		   } //for loop
		   return output + toRoman(input % unit);
	   } else if (input >= unit * 9) {
		   return "" + unitChar + tenChar + toRoman(input % unit);
	   } //if structure
	   return "" + fiveChar + toRoman(input % (5 * unit));
   } //toRoman method
} //MathConversion class