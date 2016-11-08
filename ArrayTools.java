//ArrayTools

package hiddentester;

public class ArrayTools {
   public static void swap(int[] array, int indexA, int indexB) {
      int temp = array[indexA];
      array[indexA] = array[indexB];
      array[indexB] = temp;
   } //swap method (integers)
   
   public static void swap(double[] array, int indexA, int indexB) {
      double temp = array[indexA];
      array[indexA] = array[indexB];
      array[indexB] = temp;
   } //swap method (doubles)
   
   public static void swap(char[] array, int indexA, int indexB) {
      char temp = array[indexA];
      array[indexA] = array[indexB];
      array[indexB] = temp;
   } //swap method (chars)
   
   public static void swap(boolean[] array, int indexA, int indexB) {
      boolean temp = array[indexA];
      array[indexA] = array[indexB];
      array[indexB] = temp;
   } //swap method (booleans)
   
   public static void swap(String[] array, int indexA, int indexB) {
      String temp = array[indexA];
      array[indexA] = array[indexB];
      array[indexB] = temp;
   } //swap method (Strings)
   
   public static void swap(Object[] array, int indexA, int indexB) {
      Object temp = array[indexA];
      array[indexA] = array[indexB];
      array[indexB] = temp;
   } //swap method (Objects)
   
   public static void cycle(int[] array, int shift) {
      shift = shift % array.length;
      
      if (shift != 0) {
         if (shift < 0) {
            shift = array.length + shift;
         }
         
         int[] temp = new int[shift];
         
         for (int i = 0; i < shift; i ++) {
            temp[i] = array[i];
         } //for loop
         
         for (int i = 0; i < array.length - shift; i ++) {
            array[i] = array[i + shift];
         } //for loop
         
         for (int i = 0; i < shift; i ++) {
            array[array.length - shift + i] = temp[i];
         } //for loop
      }
   } //cycle method (integers)
   
   public static void cycle2(int[] array, int shift) {
      shift = shift % array.length;

      if (shift != 0) {
         if (shift < 0) {
            shift = array.length + shift;
         }
         
         int temp, length = array.length, rem = length % shift;

         for (int i = 0; i < array.length && shift != 0; i ++) {
            if (!(i + shift < array.length)) {
               length = array.length - i;
               shift = length - rem;
               if (shift != 0) {
                  rem = length % shift;
               } //if structure
            } //if structure
            
            if (i + shift < array.length) {
               swap(array, i, i + shift);
            } //if structure
         } //for loop
      } //if structure
   } //cycle2 method (integers)
   
   public static void insSort (double[] list) {
      for (int i = 0; i < list.length; i++) {
         int j;
			double temp = list[i];
         
         for (j = i; j > 0 && temp < list[j - 1]; j--) {
            list[j] = list[j - 1];
         } //for loop
         
         list[j] = temp;
      } //for loop
   } //insSort method (integer)
   
   public static void insertSort (String[] array) {
      String temp;
      int j;
      
      for (int i = 1; i < array.length; i++) {
         temp = array[i];
         for (j = i; j > 0 && temp.compareTo(array[j - 1]) < 0; j--) {
            array[j] = array[j - 1];
         } //for loop
         array[j] = temp;
      } //for loop
   } //insertSort method (Strings)
   
   public static void selSort (int[] list) {
      for (int i = 0; i < list.length; i++) {
         int min = i, temp;
      
         for (int j = i; j < list.length; j++) {
            if (list[j] < list[min]) {
               min = j;
            } //for loop
         } //for loop
         
         temp = list[i];
         list[i] = list[min];
         list[min] = temp;
      } //for loop
   } //selSort method
   
   public static void bubSort (int[] list) {
      int temp;
      boolean swapped = true;
      
      for (int i = 0; i < list.length && swapped; i++) {
         swapped = false;
         
         for (int j = list.length - 1; j > i; j--) {
            if (list[j] < list[j - 1]) {
               temp = list[j];
               list[j] = list[j - 1];
               list[j - 1] = temp;
               swapped = true;
            } //if structure
         } //for loop
      } //for loop
   } //bubSort method
   
   public static int binSearch (int[] list, int searchTerm) {
      int bottom = 0, top = list.length, middle;
      
      while (bottom <= top) {
         middle = (bottom + top) / 2;
         
         if (list[middle] == searchTerm) {
            return middle;
         } else if (list[middle] > searchTerm) {
            top = middle - 1;
         } else {
            bottom = middle + 1;
         } //if structure
      } //while loop
      
      return -1;
   } //binSearch method
   
   public static double[] freqAnalysis (double[] array, int intervals, boolean print) {
      double[] frequencies = new double[intervals];
      double min = array[0], max = min, intervalSize;
      
      for (int i = 1; i < array.length; i++) {
         if (array[i] < min) {
            min = array[i];
         } else if (array[i] > max) {
            max = array[i];
         } //if structure
      } //for loop
      
      intervalSize = Math.ceil(((max - min + 1) / intervals));
      
      for (double i : array) {
         frequencies[(int)((i - min) / intervalSize)]++;
      } //for loop
      
      if (print) {
         for (int i = 0; i < intervals; i++) {
            System.out.println((i * intervalSize + min) + " - " + ((i + 1) * intervalSize + min) + ": " + frequencies[i]);
         } //for loop
      } //if structure
      
      return frequencies;
   } //freqAnalysis method
   
   public static double[] freqAnalysis (double[] array, int intervals) {
      return freqAnalysis(array, intervals, false);
   } //freqAnalysis method
   
	public static double median (double[] array) {
      ArrayTools.insSort(array);
		
      return (array[(array.length - 1) / 2] + array[(int)Math.ceil(array.length / 2.0)]) / 2.0;
   } //median method
   
   public static double mean (double[] array) {
      double sum = 0;
      
      for (double i : array) {
         sum += i;
      } //for loop
      
      return sum / array.length;
   } //mean method
   
   public static void mode (double[] array) {
      int[] frequency = new int[array.length];
		double[] num = new double[array.length];
      int emptyIndex = 0;
      
      for (double i : array) {
         int pos = -1;
         
         for (int j = 0; j < emptyIndex; j++) {
            if (num[j] == i) {
               pos = j;
               break;
            } //if structure
         } //for loop
         
         if (pos == -1) {
            frequency[emptyIndex]++;
            num[emptyIndex] = i;
            emptyIndex++;
         } else {
            frequency[pos]++;
            num[pos] = i;
         } //if structure
      } //for loop
      
      double max = 0;
      
      for (int i = 0; i < emptyIndex; i++) {
         max = Math.max(max, frequency[i]);
      } //for loop
      
      for (int i = 0; i < emptyIndex; i++) {
         if (frequency[i] == max) {
            System.out.println(num[i]);
         } //if structure
      } //for loop
   } //mode method
	
	public static double meanDeviation (double[] array) {
		double mean = mean(array);
		double deviation = 0;
		
		for (double d : array) {
			deviation += Math.abs(mean - d);
		} //for loop
		
		return deviation / array.length;
	} //meanDeviation method
	
	public static double interquartileRange (double[] array) {
		ArrayTools.insSort(array);
		double q1, q3;
		
		q1 = (array[(array.length - 1) / 4] + array[(int)Math.ceil(array.length / 4.0)]) / 2.0;
		q3 = (array[3 * (array.length - 1) / 4] + array[(int)Math.ceil(3 * array.length / 4.0)]) / 2.0;
		
		return Math.abs(q3 - q1);
	} //interquartileRange method
	
   public static void output (double[] list) {
      for (int i = 0; i < list.length; i++) {
         System.out.print(list[i] + " ");
      } //for loop
      System.out.println();
   } //output method
} //ArrayTools class