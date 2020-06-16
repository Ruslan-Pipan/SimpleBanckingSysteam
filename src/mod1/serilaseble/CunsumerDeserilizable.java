package mod1.serilaseble;

import com.sun.istack.internal.NotNull;
import mod1.com.mybanck.domain.Cunsumers;

import java.io.*;

public class CunsumerDeserilizable {
    private CunsumerDeserilizable(){}

    public static Cunsumers deserilizableCunstomer(@NotNull String path){
        File file = new File(path);
        try (ObjectInputStream outputStream = new ObjectInputStream(new FileInputStream(path))){
            return(Cunsumers) outputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Cunsumers deserilizableCunstomer(@NotNull File path){
        try (ObjectInputStream outputStream = new ObjectInputStream(new FileInputStream(path))){
            return(Cunsumers) outputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
