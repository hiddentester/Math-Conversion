//Math Tools

package hiddentester;

public class MathTools
{
   public static int randInt (int min, int max) {
      return (int)(Math.floor(Math.random() * (1 + max - min) + min));
   } //randInt method

   public static String decToBin (int num)
   {
      String binaryValue = "";
      
      do
      {
         if (num % 2 != 0)
         {
            num = (num - 1) / 2;
            binaryValue = "1" + binaryValue;
         } else
         {
            num /= 2;
            binaryValue = "0" + binaryValue;
         } //if structure
      } while (num > 0);
      
      return binaryValue;
   } //decToBin method
   
   public static int binToDec (int num)
   {
      String input = String.valueOf(num);
      int decimalValue = 0;
      boolean valid = true;
      
      for (int index = 0; index < input.length() && valid; index ++)
      {
         if (input.charAt(index) != '1' && input.charAt(index) != '0')
         {
            valid = false;
         } //if structure
      } //for loop
      
      if (valid)
      {
         for (int index = 0; index < input.length(); index ++)
         {
            decimalValue *= 2;
            if (input.charAt(index) == '1')
            {
               decimalValue ++;
            } //if structure
         } //for loop
         return decimalValue;
      } else
      {
         System.out.println ("Error: invalid input");
         return -1;
      } //if structure
   } //binToDec method
   
   //Prime factors numbers
   public static long[] primes = new long[10000];
   public static int max = 1;
   
   public static boolean isPrime(long num) {
      for (int i = 0; i < max; i++) {
         if (num % primes[i] == 0) {
            return false;
         } //if structure
      } //for loop
      return true;
   } //isPrime method
   
	public static void primeFactor (long num) {
      long testFactor = 2;
      int i;
      primes[0] = 2;
      
      do {
         i = 0;
         while (i < max) {
            if (num % primes[i] == 0) {
               testFactor = primes[i];
               System.out.println(testFactor);
               num /= testFactor;
            } else {
               i++;
            } //if structure
         } //while loop
         
         if (Math.pow(testFactor, 2) < num) {
            while (Math.pow(testFactor, 2) < num) {
               if (testFactor > 2) {
                  testFactor += 2;
               } else {
                  testFactor++;
               } //if structure
               
               if (isPrime(testFactor)) {
                  primes[max] = testFactor;
                  max++;
               } //if structure
            } //while loop
         } else if (num != 1) {
            System.out.println(num);
            num = 1;
         } //if structure
      } while (num != 1); //do-while loop
   } //primeFactor method
   
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
   
   public static long gcd (long num1, long num2) {
      long temp;
      
      while (num2 != 0) {
         temp = num2;
         num2 = num1 % num2;
         num1 = temp;
      } //while loop
      
      return num1;
   } //gcd method
   
   public static long lcm (long num1, long num2) {
      return num1 / gcd(num1, num2) * num2;
   } //lcm method
   
   public static long factorial (long num, long limit) {
      long product = 1;
   
      for (; num > limit; num--) {
         product *= num;
      } //for loop
      
      return product;
   } //factorial method
   
   public static long factorial (long num) {
      return factorial(num, 0);
   } //factorial method
   
   public static long[] simplify (long num1, long num2) {
      long[] simpleNums = new long[2];
      long factor = gcd(num1, num2);
      
      simpleNums[0] = num1 / factor;
      simpleNums[1] = num2 / factor;
      
      return simpleNums;
   } //simplify method
   
   public static long permute (int num1, int num2) {
      return factorial(num1, num1 - num2);
   } //permute method
   
   public static long choose (int num1, int num2) {
      return permute(num1, num2) / factorial(num2);
   } //choose method
} //MathTools class