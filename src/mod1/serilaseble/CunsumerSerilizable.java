package mod1.serilaseble;

import com.sun.istack.internal.NotNull;
import mod1.com.mybanck.domain.Cunsumers;

import java.io.*;

public class CunsumerSerilizable {

    private CunsumerSerilizable(){}

    public static boolean serialesbleCunstomer(@NotNull String path, Cunsumers cunstomer){
        File file = new File(path);
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(path))){
            outputStream.writeObject(cunstomer);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean serialesbleCunstomer(@NotNull File path, Cunsumers cunstomer){
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(path))){
            outputStream.writeObject(cunstomer);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
