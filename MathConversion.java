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
   
   //Converts Roman numerals to integers
   public static int fromRoman (String input) {
      final String SYMBOLS = "ivxlcdmVXLCDM";
	   int output = 0, index;
	   
      for (int stringIndex = 0; stringIndex < input.length(); stringIndex ++) {
      index = SYMBOLS.indexOf(input.charAt(stringIndex));
         if (index == -1) {
            return 0;
         } //if structure
            
         if (index % 2 == 1) {
            output += Math.pow(10, Math.floor((index + 1) / 2)) / 2;
         } else {
            output += Math.pow(10, index / 2);
            if (stringIndex != input.length() - 1 && index < SYMBOLS.indexOf(input.charAt(stringIndex + 1))) {
               output -= Math.pow(10, index / 2) * 2;
            } //if structure
         } //if structure
      } //for loop
      
      return output;
   } //fromRoman method
   
   public static String toWord (String number) {
      //Word list
      final String HUNDRED = "hundred";
      final String[] MULOF10 = {
         "twenty",
         "thirty",
         "fourty",
         "fifty",
         "sixty",
         "seventy",
         "eighty",
         "ninety"
      };
      final String[] TEENS = {
         "ten",
         "eleven",
         "twelve",
         "thirteen",
         "fourteen",
         "fifteen",
         "sixteen",
         "seventeen",
         "eighteen",
         "nineteen"
      };
      final String[] ONES = {
         "",
         "one",
         "two",
         "three",
         "four",
         "five",
         "six",
         "seven",
         "eight",
         "nine"
      };
      
      //Handle numbers with more than three digits
      if (number.length() > 3) {
         //Find the largest power of 1000 smaller than the number
         int power = (number.length() - 1) / 3;
         //Find the name of the first 1 - 3 digits of the number
         String mulChunk = toWord(number.substring(0, number.length() - (3 * power))).trim();
         //Find the name of the rest of the number
         String remChunk = toWord(number.substring(number.length() - (3 * power))).trim();
         
         if (mulChunk.isEmpty()) {
            return remChunk;
         } else {
            return mulChunk + " " + powOf1000(power - 1) + " " + remChunk;
         } //if structure
      //Handle three-digit numbers
      } else if (number.length() > 2) {
         //Find the name of the first digit of the number
         String mulChunk = toWord(number.substring(0, 1));
         //Find the name of the rest of the number
         String remChunk = toWord(number.substring(1));
         
         if (mulChunk.isEmpty()) {
            return remChunk;
         } else {
            return mulChunk + " " + HUNDRED + " " + remChunk;
         } //if structure
      //Handle two-digit numbers
      } else if (number.length() > 1) {
         //Handle numbers from 99 to 20
         if (number.compareTo("19") > 0) {
            return MULOF10[number.charAt(0) - '2'] + " " + toWord(number.substring(1));
         //Handle numbers from 19 to 10
         } else if (number.charAt(0) != '0') {
            return TEENS[number.charAt(1) - '0'];
         //Handle numbers from 09 to 00
         } else {
            return ONES[number.charAt(1) - '0'];
         } //if structure
      //Handle one-digit numbers
      } else if (number.length() > 0) {
         return ONES[number.charAt(0) - '0'];
      } //if structure
      
      return "";
   } //toWord method
   
   public static String powOf1000(int power) {
      //Word list
      final String[] BELOWTEN = {
         "thousand",    //1 + 0
         "million",     //1 + 1
         "billion",     //1 + 2
         "trillion",    //1 + 3
         "quadrillion", //1 + 4
         "quintillion", //1 + 5
         "sextillion",  //1 + 6
         "septillion",  //1 + 7
         "octillion",   //1 + 8
         "nonillion",   //1 + 9
      };
      final String[] TENS = {
         "decillion",         //1 + 10
         "vigintillion",      //1 + 20
         "trigintillion",     //1 + 30
         "quadragintillion",  //1 + 40
         "quinquagintillion", //1 + 50
         "sexagintillion",    //1 + 60
         "septuagintillion",  //1 + 70
         "octogintillion",    //1 + 80
         "nonagintillion",    //1 + 90
         "centillion"         //1 + 100
      };
      final String[] ONES = {
         "",         //0
         "un",       //1
         "duo",      //2
         "tre",      //3
         "quattuor", //4
         "quin",     //5
         "sex",      //6
         "septen",   //7
         "octo",     //8
         "novem"     //9
      };
      
      //Handle powers of 1000 smaller than 10
      if (power < 10) {
         return BELOWTEN[power];
      //Handle powers of 1000 larger than 9
      } else {
         return ONES[power % 10] + TENS[(power / 10) - 1];
      } //if structure
   } //powOf1000 method
} //MathConversion class
