package assignment4;
/* CRITTERS Main.java
 * EE422C Project 4 submission by
 * Replace <...> with your actual data.
 * <ThienSon Ho>
 * <tsh848>
 * <15505>
 * <Arjun Singh>
 * <AS78363>
 * <15505>
 * Slip days used: <0>
 * Spring 2018
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.io.*;
import java.lang.reflect.Method;


/*
 * Usage: java <pkgname>.Main <input file> test
 * input file is optional.  If input file is specified, the word 'test' is optional.
 * May not use 'test' argument without specifying input file.
 */
public class Main {

    static Scanner kb;	// scanner connected to keyboard input, or input file
    private static String inputFile;	// input file, used instead of keyboard input if specified
    static ByteArrayOutputStream testOutputString;	// if test specified, holds all console output
    private static String myPackage;	// package of Critter file.  Critter cannot be in default pkg.
    private static boolean DEBUG = false; // Use it or not, as you wish!
    static PrintStream old = System.out;	// if you want to restore output to console


    // Gets the package name.  The usage assumes that Critter and its subclasses are all in the same package.
    static {
        myPackage = Critter.class.getPackage().toString().split(" ")[1];
    }

    /**
     * Main method.
     * @param args args can be empty.  If not empty, provide two parameters -- the first is a file name, 
     * and the second is test (for test output, where all output to be directed to a String), or nothing.
     */
    public static void main(String[] args) { 
        if (args.length != 0) {
            try {
                inputFile = args[0];
                kb = new Scanner(new File(inputFile));			
            } catch (FileNotFoundException e) {
                System.out.println("USAGE: java Main OR java Main <input file> <test output>");
                e.printStackTrace();
            } catch (NullPointerException e) {
                System.out.println("USAGE: java Main OR java Main <input file>  <test output>");
            }
            if (args.length >= 2) {
                if (args[1].equals("test")) { // if the word "test" is the second argument to java
                    // Create a stream to hold the output
                    testOutputString = new ByteArrayOutputStream();
                    PrintStream ps = new PrintStream(testOutputString);
                    // Save the old System.out.
                    old = System.out;
                    // Tell Java to use the special stream; all console output will be redirected here from now
                    System.setOut(ps);
                }
            }
        } else { // if no arguments to main
            kb = new Scanner(System.in); // use keyboard and console
        }

        /* Do not alter the code above for your submission. */
        /* Write your code below. */
        
        
        //a flag that quits program when false
        boolean quitFlag = true;
        
        //remove this
//        fakeMake();

        while(quitFlag) {
        	//Display prompt
        	System.out.print("critters>");
        	//read in next line
        	String input = kb.nextLine();
        	//remove tabs and spaces
        	String inputF = input.replaceAll(" ","");
        	inputF = inputF.replaceAll("	","");
        	
        	//if the first index is q, analyze input for quit
        	if(input.indexOf("q") != -1 && inputF.indexOf("q") == 0) {
        		String newInput = inputF;
        		
        		//If it is a quit, flip the flag
        		if(processQuit(input)) {
        			quitFlag = false;
        			
        		//Check if quit is contained to throw error message
        		}else if(newInput.indexOf("quit") == 0 && input.contains("quit")){
        			System.out.println("error processing: " + input);
				}else{
					//must be invalid command
        			System.out.println("invalid command: " + input);
        		}
        		
        	//If first index is S, check for show, step, seed, and stats
        	}else if(input.indexOf("s") != -1 && inputF.indexOf("s") == 0) {
        		String newInput = inputF;
        		
        		//check if input is a attempt at show
        		if(newInput.indexOf("show") == 0 && input.indexOf("show") != -1) {
        			//display world if valid
        			if(processShow(input)) {
						Critter.displayWorld();
					
        			//must be error otherwise
        			}else{
						System.out.println("error processing: " + input);
					}
        		
        			
        		//check if input is a attempt at step
        		}else if(newInput.indexOf("step") == 0 && input.indexOf("step") != -1) {
        			boolean isValidSwitch = processStep(input.replace("step", ""));
        			
        			//if valid and equals step, execute a single step
        			if(newInput.equals("step") && isValidSwitch) {
        				Critter.worldTimeStep();
        				
        			//if valid, call multiple time steps
        			}else if(processStep(input.replace("step", ""))) {
        				//remove extra words
        				newInput = input.replace("step", "");
        				newInput = newInput.replaceAll(" ", "");
        				newInput = newInput.replaceAll("	", "");
        				
        				//parse the String to an int in base 10
        				int loop = Integer.parseInt(newInput,10);
        				
        				//Call time step (parameter) times
        				for(int i = 0; i < loop; i++) {
        					Critter.worldTimeStep();
        				}
        			}
        			else{
        				//must be an error
						System.out.println("error processing: " + input);
					}
        		
        			
        		//check if input is a attempt at seed
        		}else if(newInput.indexOf("seed") == 0 && input.indexOf("seed") != -1) {
        			//if valid, set seed
        			if(processSeed(input.replace("seed", ""))) {
        				//remove all extra words
						newInput = input.replace("seed", "");
						newInput = newInput.replaceAll(" ", "");
						newInput = newInput.replaceAll("	", "");
						
						//cast and set seed in base 10
						int seed = Integer.parseInt(newInput, 10);
						Critter.setSeed(seed);
					}else{
						//invalid value passed
						System.out.println("error processing: " + input);
					}
        		
        			
        		//check if input is a attempt at stats
        		}else if(newInput.indexOf("stats") == 0 && input.indexOf("stats") != -1) {
        			//call to process invokes stats
        			if(!processStats(input.replace("stats", ""))){
        				//error handling
						System.out.println("error processing: " + input);
					}
        			
        		//must be an invalid command
        		}else{
        			System.out.println("invalid command: " + input);
        		}
        		
        		
        	//If first index is m, analyze input for make
        	}else if(input.indexOf("m") != -1 && inputF.indexOf("m") == 0) {
        		String newInput = inputF;
        		
        		//Check to see if its an attempt at make
        		if(newInput.indexOf("make") == 0 && input.indexOf("make") != -1) {
        			newInput = input.replace("make","");
        			
        			//Make called in process, handle error if process fails
        			if(!processMake(newInput)) {
        				System.out.println("error processing: " + input);
        			}
        		}
        		else {
        			//must be invalid
        			System.out.println("invalid command: " + input);
        		}
        		
        	//must be an invalid command	
        	}else {
        		System.out.println("invalid command: " + input);
        	}
        	
        }
        /* Write your code above */
        System.out.flush();

    }
    
    
    /**
     * Determines if input is a valid quit command
     * @param input the string input
     * @return true if valid, false otherwise
     */
    public static boolean processQuit(String input) {
    	//removes tabs and spaces
    	String newInput = input.replaceAll(" ","");
    	newInput = newInput.replaceAll("	", "");
    	
    	// if the element is exactly quit then input is valid
    	if(newInput.equals("quit") && input.indexOf("quit") != -1) {
    		return true;
    	}
    	return false;
    }
    
    
    /**
     * Determines if input is a valid show command
     * @param input the string input
     * @return true if valid, false otherwise
     */
    public static boolean processShow(String input) {
    	//if the input is exactly show, the input is correct
    	if(input.equals("show")) {
    		return true;
    	}
    	return false;
    }
    
    
    /**
     * Determines if input is a valid step command
     * @param input the string input
     * @return true if valid, false otherwise
     */
    public static boolean processStep(String input) {
    	//Remove tabs and spaces
    	String ni = input.replaceAll(" ", "");
    	ni = ni.replaceAll("	", "");
    	
    	//if the new String is empty then it must be a ningle Step
    	if(ni.equals("")) {
    		return true;
    	}
    	
    	//splits elements by spaces
    	String[] twoInputs = input.split(" ");
    	int length = 0;
    	ArrayList<String> goodElements = new ArrayList<String>();
    	for(String x : twoInputs) {
    		x = x.replaceAll("	", "");
    		if(!(x.equals(""))) {
    			length++;
    			goodElements.add(x);
    		}
    	}
    	
    	//if there is exactly one good element attempt a cast
    	if(length == 1) {
    		try{
    			//convert element to int
    			int loop = Integer.parseInt(goodElements.get(0),10);
    			//only positive steps allowed
    			if(loop <= 0) {
    				return false;
    			}
    			return true;
    		}catch(Exception e) {
    			return false;
    		}
    	}
    	return false;
    }
    
    
    /**
     * Determines if input is a valid seed command
     * @param input the string input
     * @return true if valid, false otherwise
     */
    public static boolean processSeed(String input) {
    	//splits elements by spaces
    	String[] twoInputs = input.split(" ");
    	int length = 0;
    	
    	//Counts a valid element if String isn't empty or only contains a space
    	ArrayList<String> goodElements = new ArrayList<String>();
    	for(String x : twoInputs) {
    		x = x.replaceAll("	", "");
    		if(!(x.equals(""))) {
    			length++;
    			goodElements.add(x);
    		}
    	}
    	
    	//If there is one good element, attempt a single seed command
    	if(length == 1) {
    		try{
    			//convert element to int
    			int seed = Integer.parseInt(goodElements.get(0),10);
    			if(seed < 0) {
    				return false;
    			}
    			return true;
    		}catch(Exception e) {
				System.out.println("error Processing: " + input);
    		}
    	}
    	return false;
    }
    
    
    /**
     * Determines if input is a valid stats command
     * @param input the string input
     * @return true if valid, false otherwise
     */
    public static boolean processStats(String input) {
    	//splits elements by spaces
    	String[] twoInputs = input.split(" ");
    	int length = 0;
    	ArrayList<String> goodElements = new ArrayList<String>();
    	
    	//Counts a valid element if String isn't empty or only contains a space
    	for(String x : twoInputs) {
    		x = x.replaceAll("	", "");
    		if(!(x.equals(""))) {
    			length++;
    			goodElements.add(x);
    		}
    	}
    	
    	//If there is one good element, attempt a single stats command
    	if(length == 1) {
    		try{
    			//make package name from element
    			List<Critter> critterList = Critter.getInstances(goodElements.get(0));
    			Class critterType = Class.forName(myPackage + "." + goodElements.get(0));
    			Method m = critterType.getMethod("runStats", java.util.List.class);
    			
    			//invoke method
				m.invoke(critterList.getClass(), critterList);
				return true;
    		}catch(Exception e) {
    			return false;
    		}
    	}
    	return false;
    }
    
    
    /**
     * Determines if input is a valid make command
     * @param input the string input
     * @return true if valid, false otherwise
     */
    public static boolean processMake(String input) {
    	//splits elements by spaces
    	String[] twoInputs = input.split(" ");
    	int length = 0;
    	ArrayList<String> goodElements = new ArrayList<String>();
    	
    	//Counts a valid element if String isn't empty or only contains a space
    	for(String x : twoInputs) {
    		x = x.replaceAll("	", "");
    		if(!(x.equals(""))) {
    			length++;
    			goodElements.add(x);
    		}
    	}
    	
    	// If there is one good element, attempt a single make statement
    	if(length == 1) {
    		//attempt to pass the element as the class name
    		try {
    			Critter.makeCritter(goodElements.get(0));
    			return true;
    		}catch(Exception e) {
    			return false;
    		}
    		
    	//if there are two good elements, attempt a looped make statement
    	}else if(length == 2) {
    		try{
    			//convert second parameter to an int in base 10
    			int loop = Integer.parseInt(goodElements.get(1),10);
    			if(loop <= 0) {
    				return false;
    			}
    			//loop make statement 
    			for(int i = 0; i < loop; i++) {
    				Critter.makeCritter(goodElements.get(0));
    			}
    			return true;
    		}catch(Exception e) {
    			return false;
    		}
    	}
    	return false;
    }
    
    
    /**
     * Creates 100 Algae and 25 Craigs
     */
    public static void fakeMake() {
    	try {
    		//loop for 100 Algae
    		for(int i = 0; i < 100; i++) {
    			Critter.makeCritter("Algae");
    		}
    		//loop for 25 Algae
    		for(int i = 0; i < 25; i++) {
    			Critter.makeCritter("Craig");
    		}
    	}catch(Exception e){
    		//Catch exceptions in making Critters
    		System.out.println("fakeMe() didn't work");
    	}
    }
    
}
