package loger;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Loger {
    private  String color;
    private static File file = new File("D:\\Навчання\\SimpleStsteamBanck\\src\\logs\\test.html");
    protected Loger(String color){
        this.color = color;
    }

    public void log(String mesege){
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(file,true))){
            printWriter.write("<p style=\"color:"+ color + "; font-size:20; \"> " + time.format(formatter) + "\t " +  mesege + "</p>");
            printWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
