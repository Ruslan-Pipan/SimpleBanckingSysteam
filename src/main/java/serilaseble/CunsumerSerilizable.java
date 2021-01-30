package serilaseble;

import loger.BadLog;
import loger.GoodLog;
import entety.Consumer;

import java.io.*;

public class CunsumerSerilizable {

    private CunsumerSerilizable(){}

    public static boolean serializationConsumer( String path, Consumer cunstomer){
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(path))){
            outputStream.writeObject(cunstomer);
            outputStream.flush();
            GoodLog.getInstance().log("Serialization god.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        BadLog.getInstance().log("Serialization bad.");
        return false;
    }

    public static boolean serializationConsumer(File path, Consumer cunstomer){
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(path))){
            outputStream.writeObject(cunstomer);
            outputStream.flush();
            GoodLog.getInstance().log("Serialization god.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        BadLog.getInstance().log("Serialization bad.");
        return false;
    }
}
