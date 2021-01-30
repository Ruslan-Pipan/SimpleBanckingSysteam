package serilaseble;


import loger.BadLog;
import loger.GoodLog;
import entety.Consumer;

import java.io.*;

public class CunsumerDeserilizable {
    private CunsumerDeserilizable(){}

    public static Consumer deserializationCunstomer(String path){
        File file = new File(path);
        try (ObjectInputStream outputStream = new ObjectInputStream(new FileInputStream(path))){
            GoodLog.getInstance().log("Deserialization god.");
            return(Consumer) outputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        BadLog.getInstance().log("Deserialization bad.");
        return null;
    }

    public static Consumer deserializationCunstomer( File path){
        try (ObjectInputStream outputStream = new ObjectInputStream(new FileInputStream(path))){
            GoodLog.getInstance().log("Deserialization god.");
            return(Consumer) outputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        BadLog.getInstance().log("Deserialization bad.");
        return null;
    }
}
