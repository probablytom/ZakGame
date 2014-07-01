package Auditor;

import java.lang.StringBuffer;
import java.io.*;
import java.util.Date;

public class Auditor {

	private static StringBuffer trail = new StringBuffer();

	public static boolean logging = true;

	public Auditor () {

		// Do nothing (???)

	}


	// Add a line to the audit trail.
	public static boolean addLine (String logMessage) {

		// Add the message if logging is enabled. 
		if (logging) {
			trail.append(logMessage + "\n");
		}

		// Return whether the logging actually occurred.
		return logging;

	}

	// Add a message to the audit trail without a newline. 
	public static boolean add (String logMessage) {

		// Add the message if logging is enabled. 
		if (logging) {
			trail.append(logMessage);
		}

		// Return whether the logging actually occurred.
		return logging;

	}

	// TODO: Have the auditor print. 
	// Something like this, but maybe not static? 
	public String toString() {
		return trail.toString();
	}

	// Something to throw the log into some file?
	public static void dumpLog() {

		// Initial log in case something goes wrong with the `try` block below. 
		File log = new File("log.txt");
		
		// Create a new log file without deleting existing logs.
		try {
			log = new File( "log: " + Double.toString( new Date().getTime()) + ".txt" );
		} catch (Exception e) {
			// Pass?
		}
		
		// Try to open the log for writing.
		try {
			FileWriter logOut = new FileWriter(log);
			
			// Write to the log.
			logOut.write( trail.toString() );
			
			// Close the log writer.
			logOut.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Close the log file itself.
		// Apparently we can't? TODO: Look into this. 
		
	}

}
