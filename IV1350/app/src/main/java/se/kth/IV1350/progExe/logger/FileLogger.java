package se.kth.IV1350.progExe.logger;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Prints log messagse to a file. The log file will be in the 
 * current directory an will be called log.txt
 */
public class FileLogger implements Logger {
    
    private PrintWriter logStream;

    /**
     * Creats a new instance and also creates a new log file.
     * An existing log file will be deleted. 
     */
    public FileLogger() {
        try {
            logStream = new PrintWriter(new FileWriter("log.txt", true));
        }
        catch (IOException ioe) {
            System.out.println("CAN NOT LOG.");
            ioe.printStackTrace();
        }
    }

    /**
     * Prints the specified string to the log file.
     * 
     * @param message The string that will be printed ot the log file.
     */
    @Override
    public void log(String message) {
        logStream.println(message);
    }
}
