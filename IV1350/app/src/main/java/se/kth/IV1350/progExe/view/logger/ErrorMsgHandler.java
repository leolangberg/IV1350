package se.kth.IV1350.progExe.view.logger;

import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.io.FileWriter;
import java.io.IOException;

public class ErrorMsgHandler {
    private static final String LOG_FILE_NAME = "Exception-log.txt";

    private PrintWriter logFile;

    public ErrorMsgHandler() {
        try {
        logFile = new PrintWriter(new FileWriter(LOG_FILE_NAME), true);
        } 
        catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    public void log(Exception exception) {
        StringBuilder logMsgBuilder = new StringBuilder();
        logMsgBuilder.append(createTime());
        logMsgBuilder.append(", Exception was thrown: ");
        logMsgBuilder.append(exception.getMessage());
        logFile.println(logMsgBuilder);
        //exception.printStackTrace();
        System.out.println(exception.getMessage() + "\n");
    }

    private String createTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
        return now.format(formatter);
    }
}
