package assignment4;
/* CRITTERS Main.java
 * EE422C Project 4 submission by
 * Replace <...> with your actual data.
 * <Student1 Name>
 * <Student1 EID>
 * <Student1 5-digit Unique No.>
 * <Student2 Name>
 * <Student2 EID>
 * <Student2 5-digit Unique No.>
 * Slip days used: <0>
 * Fall 2016
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
        
        boolean errorFlag = false;
        boolean quitFlag = true;
        
        //remove this
//        fakeMake();

        while(quitFlag) {
        	System.out.print("critters>");
        	String input = kb.nextLine();
        	String inputF = input.replaceAll(" ","");
        	inputF = inputF.replaceAll("	","");

        	if(input.indexOf("q") != -1 && inputF.indexOf("q") == 0) {
        		String newInput = input.replaceAll(" ","");
        		newInput = newInput.replaceAll("	","");

        		if(processQuit(input)) {
        			quitFlag = false;
        		}else if(newInput.indexOf("quit") == 0 && input.contains("quit")){
        			System.out.println("error processing: " + input);
				}else{
        			System.out.println("invalid command: " + input);
        		}
        	}else if(input.indexOf("s") != -1 && inputF.indexOf("s") == 0) {
        		String newInput = input.replaceAll(" ","");
            	newInput = newInput.replaceAll("	", "");
        		if(newInput.indexOf("show") == 0 && input.indexOf("show") != -1) {
        			if(processShow(input)) {
						Critter.displayWorld();
					}
					else{
						System.out.println("error processing: " + input);
					}
        		
        		}else if(newInput.indexOf("step") == 0 && input.indexOf("step") != -1) {
        			boolean isValidSwitch = processStep(input.replace("step", ""));

        			if(newInput.equals("step") && isValidSwitch) {
        				Critter.worldTimeStep();
        			}else if(processStep(input.replace("step", ""))) {
        				newInput = input.replace("step", "");
        				newInput = newInput.replaceAll(" ", "");
        				newInput = newInput.replaceAll("	", "");
        				int loop = Integer.parseInt(newInput,10);
        				for(int i = 0; i < loop; i++) {
        					Critter.worldTimeStep();
        				}
        			}
        			else{
						System.out.println("error processing: " + input);
					}
        		
        		}else if(newInput.indexOf("seed") == 0 && input.indexOf("seed") != -1) {
        			if(processSeed(input.replace("seed", ""))) {
						newInput = input.replace("seed", "");
						newInput = newInput.replaceAll(" ", "");
						newInput = newInput.replaceAll("	", "");
						int seed = Integer.parseInt(newInput, 10);
						Critter.setSeed(seed);
					}else{
						System.out.println("error processing: " + input);
					}
        		
        		}else if(newInput.indexOf("stats") == 0 && input.indexOf("stats") != -1) {
        			if(!processStats(input.replace("stats", ""))){
						System.out.println("error processing: " + input);
					}
        		}else{
        			System.out.println("invalid command: " + input);
        		}
        		
        	}else if(input.indexOf("m") != -1 && inputF.indexOf("m") == 0) {
        		String newInput = input.replaceAll(" ","");
            	newInput = newInput.replaceAll("	", "");
        		if(newInput.indexOf("make") == 0 && input.indexOf("make") != -1) {
        			newInput = input.replace("make","");
        			if(!processMake(newInput)) {
        				System.out.println("error processing: " + input);
        			}
        		}
        		else {
        			System.out.println("invalid command: " + input);
        		}
        	}else {
        		System.out.println("invalid command: " + input);
        	}
        	
        }
        /* Write your code above */
        System.out.flush();

    }
    
    public static boolean processQuit(String input) {
    	String newInput = input.replaceAll(" ","");
    	newInput = newInput.replaceAll("	", "");
    	if(newInput.equals("quit") && input.indexOf("quit") != -1) {
    		return true;
    	}
    	return false;
    }
    
    public static boolean processShow(String input) {
    	if(input.equals("show")) {
    		return true;
    	}
    	return false;
    }
    
    public static boolean processStep(String input) {
    	String ni = input.replaceAll(" ", "");
    	ni = ni.replaceAll("	", "");
    	if(ni.equals("")) {
    		return true;
    	}
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
    	if(length == 1) {
    		try{
    			int loop = Integer.parseInt(goodElements.get(0),10);
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
    
    public static boolean processSeed(String input) {
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
    	if(length == 1) {
    		try{
    			int loop = Integer.parseInt(goodElements.get(0),10);
    			if(loop < 0) {
    				return false;
    			}
    			return true;
    		}catch(Exception e) {
				System.out.println("error Processing: " + input);
    		}
    	}
    	return false;
    }
    
    public static boolean processStats(String input) {
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
    	if(length == 1) {
    		try{

    			List<Critter> critterList = Critter.getInstances(goodElements.get(0));
    			Class critterType = Class.forName(myPackage + "." + goodElements.get(0));
    			Method m = critterType.getMethod("runStats", java.util.List.class);
				m.invoke(critterList.getClass(), critterList);
				return true;
    		}catch(Exception e) {
    			return false;
    		}
    	}
    	return false;
    }
    
    public static boolean processMake(String input) {
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
    	if(length == 1) {
    		try {
    			Critter.makeCritter(goodElements.get(0));
    			return true;
    		}catch(Exception e) {
    			return false;
    		}
    	}else if(length == 2) {
    		try{
    			int loop = Integer.parseInt(goodElements.get(1),10);
    			if(loop <= 0) {
    				return false;
    			}
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
    
    public static void fakeMake() {
    	try {
    		for(int i = 0; i < 100; i++) {
    			Critter.makeCritter("Algae");
    		}
    		for(int i = 0; i < 25; i++) {
    			Critter.makeCritter("Craig");
    		}
    	}catch(Exception e){
    		System.out.println("fakeMe() didn't work");
    	}
    }
    
}
