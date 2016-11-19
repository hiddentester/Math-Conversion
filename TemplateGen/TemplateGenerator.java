/*******************************************************************************
 * File Name:     TemplateGenerator.java
 * Name:          Jonathan Chung
 * Class:         ICS4U-01
 * Date:          2016/11/18
 * Description:   This program generates templates.
 *******************************************************************************/

import java.util.Scanner;
import java.util.Calendar;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TemplateGenerator {
	public static void main (String [] args) {
		//Template file variables
		final String TEMPLATE_DIRECTORY = "TemplateFiles";
		final String[] TEMPLATE_FILES = {"header", "class", "main", "fileInputOutput", "imports"};
		final String FILE_NAME = "FILE_NAME";
		final String AUTHOR_NAME = "AUTHOR_NAME";
		final String CLASS = "CLASS_NAME";
		final String YEAR = "YYYY";
		final String MONTH = "MM";
		final String DAY = "DD";
		final String DESCRIPTION = "DESC";
		final String BODY = "BODY";
		
		//Data file variables
		final String DATA_DIRECTORY = "Defaults";
		final String[] DATA_FILES = {"authorName", "className", "description"};
		String year = Calendar.getInstance().get(Calendar.YEAR) + "";
		String month = (Calendar.getInstance().get(Calendar.MONTH) + 1) + "";
		month = ("0" + month).substring(month.length() - 1);
		String day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + "";
		day = ("0" + day).substring(day.length() - 1);
		String fileName, authorName = "", className = "", description = "";
		String[] fileInputOutput = new String[0], main = new String[0], wholeClass = new String[0];
		
		//Output variables
		final String OUTPUT_DIRECTORY = "Output";
		boolean useMain, useFiles = false;
		
		String input;
		BufferedReader in;
		BufferedWriter out;
		Scanner sc = new Scanner (System.in);
		
		//Load data from file
		try {
			//Author Name
	 		in = new BufferedReader(new FileReader(DATA_DIRECTORY + "/" + DATA_FILES[0] + ".txt"));
			authorName = in.readLine();
	 		in.close();
			
			//Class Name
			in = new BufferedReader(new FileReader(DATA_DIRECTORY + "/" + DATA_FILES[1] + ".txt"));
			className = in.readLine();
	 		in.close();
			
			//Description
			in = new BufferedReader(new FileReader(DATA_DIRECTORY + "/" + DATA_FILES[2] + ".txt"));
			description = in.readLine();
	 		in.close();
		} catch (IOException e) {
	 		System.err.println("There was a problem with " + e.getMessage());
		} //try-catch structure
		
		//Input
		System.out.print("File Name: ");
		fileName = sc.nextLine();
		System.out.print("Main method? ");
		useMain = sc.nextLine().toUpperCase().charAt(0) == 'Y';
		
		if (useMain) {
			System.out.print("File input/output? ");
		 	useFiles = sc.nextLine().toUpperCase().charAt(0) == 'Y';
		} //if structure
		
		//Load template from file
		try {
			in = new BufferedReader(new FileReader(TEMPLATE_DIRECTORY + "/" + TEMPLATE_FILES[0] + ".txt"));
			out = new BufferedWriter(new FileWriter(OUTPUT_DIRECTORY + "/" + fileName + ".java"));
			
			//Write to file
			while ((input = in.readLine()) != null) {
				out.write(input.replaceAll(FILE_NAME, fileName).
					replaceAll(AUTHOR_NAME, authorName).
					replaceAll(CLASS, className).
					replaceAll(YEAR, year).
					replaceAll(MONTH, month).
					replaceAll(DAY, day).
					replaceAll(DESCRIPTION, description));
				out.newLine();
			} //while loop
			
			out.newLine();
			out.close();
	 		in.close();
		} catch (Exception e) {
			System.err.println("There was a problem with " + e.getMessage());
		} //try-catch structure
		
		//Generate file input/output
		if (useFiles) {
			//Write imports
			try {
				in = new BufferedReader(new FileReader(TEMPLATE_DIRECTORY + "/" + TEMPLATE_FILES[4] + ".txt"));
				out = new BufferedWriter(new FileWriter(OUTPUT_DIRECTORY + "/" + fileName + ".java", true));
			
				String output = "";
			
				//Write to file
				while ((input = in.readLine()) != null) {
					out.write(input);
					out.newLine();
				} //while loop
				
				out.newLine();
				out.close();
		 		in.close();
			} catch (IOException e) {
				System.err.println("There was a problem with " + e.getMessage());
			} //try-catch structure
			
			//Read file input/output
			try {
				in = new BufferedReader(new FileReader(TEMPLATE_DIRECTORY + "/" + TEMPLATE_FILES[3] + ".txt"));
				out = new BufferedWriter(new FileWriter(OUTPUT_DIRECTORY + "/" + fileName + ".java", true));
			
				fileInputOutput = new String[Integer.parseInt(in.readLine())];
				
				for (int i = 0; i < fileInputOutput.length; i++) {
					fileInputOutput[i] = in.readLine();
				} //for loop

				out.close();
		 		in.close();
			} catch (IOException e) {
				System.err.println("There was a problem with " + e.getMessage());
			} //try-catch structure
		} //if structure
		
		//Generate main method
		if (useMain) {
			try {
				in = new BufferedReader(new FileReader(TEMPLATE_DIRECTORY + "/" + TEMPLATE_FILES[2] + ".txt"));
				out = new BufferedWriter(new FileWriter(OUTPUT_DIRECTORY + "/" + fileName + ".java", true));
			
				main = new String[Integer.parseInt(in.readLine()) + fileInputOutput.length - 1];
				int j = 0;
				
				for (int i = 0; i < main.length - fileInputOutput.length + 1; i++) {
					input = in.readLine();
					
					if (input.trim().equals(BODY)) {
						for (j = 0; j < fileInputOutput.length; j++) {
							main[i + j] = "\t" + fileInputOutput[j];
						} //for loop
						
						j--;
					} else {
						main[i + j] = input;
					} //if structure
				} //for loop

				out.close();
		 		in.close();
			} catch (IOException e) {
				System.err.println("There was a problem with " + e.getMessage());
			} //try-catch structure
		} //if structure
		
		//Generate class
		try {
			in = new BufferedReader(new FileReader(TEMPLATE_DIRECTORY + "/" + TEMPLATE_FILES[1] + ".txt"));
			out = new BufferedWriter(new FileWriter(OUTPUT_DIRECTORY + "/" + fileName + ".java", true));
		
			wholeClass = new String[Integer.parseInt(in.readLine()) + main.length - 1];
			int j = 0;
			
			for (int i = 0; i < wholeClass.length - main.length + 1; i++) {
				input = in.readLine();
			
				if (input.trim().equals(BODY)) {
					for (j = 0; j < main.length; j++) {
						wholeClass[i + j] = "\t" + main[j];
					} //for loop
					
					j--;
				} else {
					wholeClass[i + j] = input.replaceAll(FILE_NAME, fileName);
				} //if structure
			} //for loop
			
			//Write class
			for (String s : wholeClass) {
				if (s != null) {
					out.write(s);
					out.newLine();
				} //if structure
			} //for loop
			
			out.newLine();
			out.close();
		 	in.close();
		} catch (IOException e) {
			System.err.println("There was a problem with " + e.getMessage());
		} //try-catch structure
	} //main method
} //TemplateGenerator class