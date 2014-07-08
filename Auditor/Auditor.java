package Auditor;

import java.lang.StringBuffer;
import java.io.*;
import java.util.Date;

public class Auditor {

	private static StringBuffer trail = new StringBuffer();

	// TODO: Make this class be what we print through, and have the system manage what gets logged based on what gets printed. No more `System.out.println()`!

	public static boolean logging = true; // Keep track of general logging. 
	public static File log;

	
	// Consutrctors. TODO: CHANGE THIS SO WE CAN'T CHANGE THE LOG FILE BY CREATING A NEW AUDITOR INSTANCE. '-.-
	public Auditor () {

		// Create a new log file without deleting existing logs. Use the current time. 
		try {
			log = new File( "log: " + Double.toString( new Date().getTime()) + ".txt" );
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public Auditor (String filename) {

		// Create a new log file using the filename provided. 
		try {
			log = new File( filename );
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	// Add a line to the audit trail.
	public static boolean presentLine (String logMessage) {

		// Add the message if logging is enabled. 
		try {
			if (logging) {
				trail.append(logMessage + "\n");
				System.out.println(logMessage);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		// Return whether the logging actually occurred.
		return logging;

	}
	
	// Add and print a blank line.
	public static boolean presentLine() {
		return presentLine("\n");
	}

	// Add a message to the audit trail without a newline. 
	public static boolean present (String logMessage) {

		// Add the message if logging is enabled. 
		if (logging) {
			trail.append(logMessage);
		}

		// Return whether the logging actually occurred.
		return logging;

	}

	// Print a message without logging. 
	public static void quietPrint (String message) {

		System.out.printf(message);

	}

	// Print a line without logging. 
	public static void quietPrintLine(String message) {

		// No conditions. We're just printing. 
		System.out.println(message +'\n');
	}

	// Log a message without printing.
	public static boolean add(String logMessage) {

		// Take care just in case nasty stuff happens adding to the file. 
		try {
			trail.append(logMessage);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;

	}

	// Log a line without printing.
	public static boolean addLine(String logMessage) {

		// Take care just in case nasty stuff happens adding to the file.
		try {
			trail.append(logMessage + '\n');
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;

	}

	// TODO: Have the auditor print. 
	// Something like this, but maybe not static? 
	public static String getTrail() {
		return trail.toString();
	}
	
	// Because having a toString method too can be useful if I'm forgetful (and does't complicate things anyway).
	public String toString() {
		return trail.toString();
	}

	// Something to throw the log into some file?
	public static void dumpLog() {
		
		if (log == null) {
			// We have no log! We must be accessing this entirely statically. Create a new log. 
			new Auditor(); // This will create the log for us. 
		}

		// Try to open the log for writing.
		try {
			FileWriter logOut = new FileWriter(log);

			// Write to the log.
			logOut.write( trail.toString() );

			// Close the log writer.
			logOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Close the log file itself.
		// Apparently we can't? TODO: Look into this. 

	}

	// Get a boolean choice from the user, and feed it back to the calling code in a bool.
	public static boolean askYesNo(String message) {

		// Present the choice (and log it where chosen.)
		presentLine(message + "  [Y/N]  :: ");

		String choice = "";

		// Get a result from the user.
		try {
			boolean chosen = false;
			while ( !(chosen)) {

				choice = System.console().readLine();

				choice = choice.toUpperCase().trim();

				if ((choice.compareTo("Y") == 0) || (choice.compareTo("N") == 0) ){
					chosen = true;
				}

			}

		} catch	(Exception e) {
			e.printStackTrace();
		}

		// Log the user's choice. 
		addLine("(The user chose: \"" + choice + "\".)");

		if (choice == "Y") {
			return true;
		} else {
			return false;
		}

	}

}
