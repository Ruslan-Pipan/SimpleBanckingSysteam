package mod1.com.mybanck.domain.serilaseble;

import com.sun.istack.internal.NotNull;
import loger.BadLog;
import loger.GoodLog;
import mod1.com.mybanck.domain.Consumers;

import java.io.*;

public class CunsumerSerilizable {

    private CunsumerSerilizable(){}

    public static boolean serializationConsumer(@NotNull String path, Consumers cunstomer){
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

    public static boolean serializationConsumer(@NotNull File path, Consumers cunstomer){
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
