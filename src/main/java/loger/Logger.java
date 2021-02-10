package loger;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * Simple Logger.
 * @author Ruslan Pipan
 * @version 1.0
 * */
class Logger {
    private final String color;
    private final static File file = new File("D:\\stady\\SimpleStsteamBanck\\src\\main\\java\\logs\\test.html");

    /**
     * Create logger and set color log.
     * */
    protected Logger(String color){
        this.color = color;
    }

    /**
     * Write log in the file.
     * @param message which message will be written in the file.
     * */
    public void log(String message){
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(file,true))){
            printWriter.write("<p style=\"color:"+ color + "; font-size:20; \"> " + time.format(formatter) + "\t " +  message + "</p>");
            printWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
