package mod1.com.mybanck.domain.serilaseble;

import com.sun.istack.internal.NotNull;
import loger.BadLog;
import loger.GoodLog;
import mod1.com.mybanck.domain.Consumers;

import java.io.*;

public class CunsumerDeserilizable {
    private CunsumerDeserilizable(){}

    public static Consumers deserializationCunstomer(@NotNull String path){
        File file = new File(path);
        try (ObjectInputStream outputStream = new ObjectInputStream(new FileInputStream(path))){
            GoodLog.getInstance().log("Deserialization god.");
            return(Consumers) outputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        BadLog.getInstance().log("Deserialization bad.");
        return null;
    }

    public static Consumers deserializationCunstomer(@NotNull File path){
        try (ObjectInputStream outputStream = new ObjectInputStream(new FileInputStream(path))){
            GoodLog.getInstance().log("Deserialization god.");
            return(Consumers) outputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        BadLog.getInstance().log("Deserialization bad.");
        return null;
    }
}
