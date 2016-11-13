/*******************************************************************************
 * File Name:     ObjectMethodGen.java
 * Name:          hiddentester
 * Date:          2016/11/12
 * Description:   This program generates accessors, mutators, and constructors.
 *******************************************************************************/

import java.util.*;
import java.io.*;

public class ObjectMethodGen {
	public static void main (String [] args) {
		//Syntax file variables
		final String SYNTAX_DIRECTORY = "syntax/";
		final String[] SYNTAX_FILES = {"constructor", "set", "get"};
		final String FILE_NAME = "FileName";
		final String ARGS = "args";
		final String ASSIGNMENT = "assignment";
		final String IS_STATIC = "isStatic ";
		final String TYPE = "type";
		final String VAR_NAME_1 = "varName";
		final String VAR_NAME_2 = "VarName";
		
		String[][] syntax = new String[SYNTAX_FILES.length][];
		
		//Input file variables
		final String PUBLIC_CLASS = "public class ";
		final String PRIVATE = "private ";
		final String STATIC = "static ";
		final String FINAL = "final ";
		
		String fileName = "FileName";
		ArrayList type = new ArrayList(), varName = new ArrayList(), isStatic = new ArrayList();
		
		BufferedReader in;
		Scanner sc = new Scanner(System.in);
		
		//Load syntax
		for (int file = 0; file < SYNTAX_FILES.length; file++) {
			try {
				String input;
		 		in = new BufferedReader(new FileReader(SYNTAX_DIRECTORY + SYNTAX_FILES[file] + ".txt"));
				
				syntax[file] = new String[Integer.parseInt(in.readLine())];
				
		 		for (int line = 0; line < syntax[file].length && (input = in.readLine()) != null; line++) {
					syntax[file][line] = input;
		 		} //while loop
				
		 		in.close();
			} catch (IOException e) {
		 		System.err.println("There was a problem with the file.");
		 		System.err.println("Exception: " + e.getMessage());
			} //try-catch structure
		} //for loop
		
		System.out.print("Source code file name:\t");
		
		//Load data file
		try {
			String input;
		 	in = new BufferedReader(new FileReader(sc.nextLine() + ".java"));
			
		 	while ((input = in.readLine()) != null) {
				input = input.trim();
				
				//Get class name
				if (input.startsWith(PUBLIC_CLASS)) {
					input = input.replaceFirst(PUBLIC_CLASS, "");
					fileName = input.substring(0, input.indexOf("{")).trim();
				//Get variables
				} else if (input.startsWith(PRIVATE) && input.indexOf(FINAL) == -1 && input.indexOf("{") == -1) {
					String lineIsStatic = "";
					String lineType;
					String[] lineVarName;
					input = input.replaceFirst(PRIVATE, "").trim();
					
					//Determine if variables are static
					if (input.startsWith(STATIC)) {
						lineIsStatic = STATIC;
						input = input.replaceFirst(STATIC, "").trim();
					} //if structure
					
					//Get variable type
					lineType = input.substring(0, input.indexOf(" "));
					input = input.replaceFirst(lineType, "").trim();
					
					//Get variable names
					input = input.replaceAll(",", "");
					input = input.replaceAll(";", "");
					lineVarName = input.split(" ");
					
					//Store variables
					for (String s : lineVarName) {
						type.add(lineType);
						varName.add(s);
						isStatic.add(lineIsStatic);
					} //for loop
				} //if structure
			} //while loop
			
			in.close();
		} catch (Exception e) {
			System.err.println("There was a problem with the file.");
		 	System.err.println("Exception: " + e.getMessage());
		} //try-catch structure
		
		//Output
		System.out.println();
		
		try {
			//Compile arguments
			String arguments = "";
			
			for (int i = 0; i < varName.size(); i++) {
				if (((String)isStatic.get(i)).isEmpty()) {
					arguments += (String)type.get(i) + " " + (String)varName.get(i) + ", ";
				} //if structure
			} //for loop
			
			if (!arguments.isEmpty()) {
				arguments = arguments.substring(0, arguments.length() - 2);
			} //if structure
			
			//Compile assignments
			String assignments = "";
			
			for (int i = 0; i < varName.size(); i++) {
				if (((String)isStatic.get(i)).isEmpty()) {
					assignments += "this." + (String)varName.get(i) + " = " + (String)varName.get(i) + ";\n\t";
				} //if structure
			} //for loop
			
			//Output constructor
			for (int line = 0; line < syntax[0].length; line++) {
				System.out.println(syntax[0][line].
					replaceAll(FILE_NAME, fileName).
					replaceAll(ARGS, arguments).
					replaceAll(ASSIGNMENT, assignments));
			} //for loop
			System.out.println();
			
			//Output methods
			for (int var = 0; var < varName.size(); var++) {
				for (int file = 1; file < syntax.length; file++) {
					for (int line = 0; line < syntax[file].length; line++) {
						System.out.println(syntax[file][line].
							replaceAll(IS_STATIC, (String)isStatic.get(var)).
							replaceAll(TYPE, (String)type.get(var)).
							replaceAll(VAR_NAME_1, (String)varName.get(var)).
							replaceAll(VAR_NAME_2, ((String)varName.get(var)).toUpperCase().charAt(0) +
								((String)varName.get(var)).substring(1)));
					} //for loop
					
					System.out.println();
				} //for loop
			} //for loop
		} catch (NumberFormatException e) {
			System.err.println("Exception: " + e.getMessage());
		} //try-catch structure
	} //main method
} //ObjectMethodGen class